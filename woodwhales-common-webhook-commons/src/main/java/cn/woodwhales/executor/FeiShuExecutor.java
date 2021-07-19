package cn.woodwhales.executor;

import cn.woodwhales.model.ExecuteResponse;
import cn.woodwhales.model.request.FeiShuRequestBody;
import cn.woodwhales.model.response.FeiShuResponse;
import cn.woodwhales.webhook.base.WebhookProductEnum;

import java.util.Objects;

/**
 * @author woodwhales on 2021-07-19 10:36
 * @description 飞书webhook请求执行器
 *
 * {
 *         "code": 19024,
 *         "msg": "Key Words Not Found"
 * }
 */
public class FeiShuExecutor extends BaseWebhookExecutor<FeiShuRequestBody, FeiShuResponse> {

    private static final int ERR_CODE_SUCCESS = 0;

    public static FeiShuExecutor newInstance() {
        return new FeiShuExecutor();
    }

    @Override
    protected boolean checkResponseObjectHandler(ExecuteResponse<FeiShuResponse> executeResponse) {
        FeiShuResponse feiShuResponse = executeResponse.parsedResponseObject;
        return Objects.equals(ERR_CODE_SUCCESS, feiShuResponse.getStatusCode());
    }

    @Override
    protected WebhookProductEnum webhookProductEnum() {
        return WebhookProductEnum.FEI_SHU;
    }
}
