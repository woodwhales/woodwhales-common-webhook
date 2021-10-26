package cn.woodwhales.webhook.executor;

import cn.woodwhales.webhook.enums.WebhookProductEnum;
import cn.woodwhales.webhook.model.param.ExecuteParam;
import cn.woodwhales.webhook.model.request.BaseWebhookRequestBody;
import cn.woodwhales.webhook.model.response.ExecuteResponse;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.lang.reflect.ParameterizedType;

import static java.util.Objects.nonNull;

/**
 * @author woodwhales on 2021-07-16 21:24
 * @description 请求执行器
 */
@Slf4j
public abstract class BaseWebhookExecutor<RequestBody extends BaseWebhookRequestBody, Response> {

    /**
     * 请求之前的处理方法
     * @param executeParam
     */
    protected void beforeHandler(ExecuteParam executeParam) {
        return;
    }

    /**
     * 请求之后的处理方法
     * @param executeParam
     * @param executeResponse
     */
    protected void afterHandler(ExecuteParam executeParam, ExecuteResponse<Response> executeResponse) {
        return;
    }

    /**
     * 校验解析后的响应结果
     * @param executeResponse
     * @return
     */
    protected abstract boolean checkResponseObjectHandler(ExecuteResponse<Response> executeResponse);

    /**
     * 解析响应结果
     * @param executeResponse
     * @return
     */
    protected Response parseResponseHandler(ExecuteResponse<Response> executeResponse) {
        ParameterizedType genericSuperclass = (ParameterizedType)this.getClass()
                                                                          .getGenericSuperclass();
        Class<Response> clazz = (Class<Response>)genericSuperclass.getActualTypeArguments()[1];
        return new Gson().fromJson(executeResponse.originResponseContent, clazz);
    }

    /**
     * 校验响应结果失败之后的处理
     * @param executeParam
     * @param executeResponse
     */
    protected void checkFailHandler(ExecuteParam executeParam, ExecuteResponse<Response> executeResponse) {
        log.error("{}发送消息失败, requestContent = {}, originResponseContent = {}",
                  webhookProductEnum().chineseName,
                  executeParam.content,
                  executeResponse.originResponseContent);
    }

    /**
     * 校验响应结果成功之后的处理
     * @param executeParam
     * @param executeResponse
     */
    protected void checkSuccessHandler(ExecuteParam executeParam, ExecuteResponse<Response> executeResponse) {
        log.info("{}发送消息成功, requestContent = {}, originResponseContent = {}",
                 webhookProductEnum().chineseName,
                 executeParam.content,
                 executeResponse.originResponseContent);
    }

    /**
     * webhook 产品信息
     * @return
     */
    protected abstract WebhookProductEnum webhookProductEnum();

    /**
     * 执行发送消息
     * @param url
     * @param content
     */
    protected void execute(String url, String content) {
        this.execute(ExecuteParam.newInstance(url, content));
    }

    /**
     * 执行发送消息
     * @param executeParam
     */
    protected void execute(ExecuteParam executeParam) {
        // 请求之前处理
        this.beforeHandler(executeParam);

        // 执行请求
        ExecuteResponse<Response> executeResponse = executeRequest(executeParam);

        // 解析请求
        executeResponse.parsedResponseObject = this.parseResponseHandler(executeResponse);

        // 校验请求
        executeResponse.checkResult = this.checkResponseObjectHandler(executeResponse);
        if(!executeResponse.checkResult) {
            this.checkFailHandler(executeParam, executeResponse);
        } else {
            this.checkSuccessHandler(executeParam, executeResponse);
        }

        // 请求之后处理
        this.afterHandler(executeParam, executeResponse);
    }

    private ExecuteResponse<Response> executeRequest(ExecuteParam executeParam) {
        ExecuteResponse<Response> executeResponse = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpPost post = new HttpPost(executeParam.url);
            post.setHeader("Accept","aplication/json");
            post.setHeader("Content-Type", "application/json;charset=UTF-8");
            StringEntity se = new StringEntity(executeParam.content, "UTF-8");
            se.setContentEncoding("UTF-8");
            se.setContentType("application/json");
            post.setEntity(se);
            CloseableHttpResponse response = httpClient.execute(post);

            int statusCode = response.getStatusLine().getStatusCode();
            String originResponseContent = EntityUtils.toString(response.getEntity());
            executeResponse  = new ExecuteResponse<>(statusCode, originResponseContent);
        } catch (Exception e) {
            log.error("{}发送消息失败, 异常原因：", e.getMessage(), e);
        }
        return executeResponse;
    }

    public void execute(String url, RequestBody requestBody) {
        if(nonNull(requestBody)) {
            this.execute(url, requestBody.toJsonSting());
        } else {
            log.warn("dingTalkRequestBody is NULL");
        }
    }

    protected Response getParsedResponse(ExecuteResponse<Response> executeResponse) {
        return executeResponse.parsedResponseObject;
    }

}
