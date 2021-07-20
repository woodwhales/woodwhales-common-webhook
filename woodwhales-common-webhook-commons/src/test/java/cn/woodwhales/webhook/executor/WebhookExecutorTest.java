package cn.woodwhales.webhook.executor;

import cn.woodwhales.webhook.enums.WebhookProductEnum;
import cn.woodwhales.webhook.model.GlobalInfo;
import cn.woodwhales.webhook.model.request.BaseWebhookRequestBody;
import cn.woodwhales.webhook.model.request.WebhookRequestBodyFactory;
import org.junit.Test;

public class WebhookExecutorTest {

    @Test
    public void DingTalkExecutor() {
        String url = "https://oapi.dingtalk.com/robot/send?access_token=zzz";

        BaseWebhookRequestBody requestBody = WebhookRequestBodyFactory.newInstance(WebhookProductEnum.DING_TALK, "test title");
        requestBody.addContent("key1：", "value1");
        requestBody.addContent("key2：", "value2");
        requestBody.addContent("key3：", "value3");

        GlobalInfo globalInfo = new GlobalInfo(new NullPointerException("报错啦"), "cn.woodwhales.webhook");
        requestBody.addGlobalInfo(globalInfo);

        WebhookExecutorFactory.execute(url, requestBody);
    }

    @Test
    public void FeiShuExecutor() {
        String url = "https://open.feishu.cn/open-apis/bot/v2/hook/xxx";

        BaseWebhookRequestBody requestBody = WebhookRequestBodyFactory.newInstance(WebhookProductEnum.FEI_SHU, "test title");
        requestBody.addContent("key1：", "value1");
        requestBody.addContent("key2：", "value2");
        requestBody.addContent("key3：", "value3");

        GlobalInfo globalInfo = new GlobalInfo(new NullPointerException("报错啦"), "cn.woodwhales.webhook");
        requestBody.addGlobalInfo(globalInfo);

        WebhookExecutorFactory.execute(url, requestBody);
    }

    @Test
    public void WeComExecutor() {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=yyy";

        WebhookExecutorFactory.execute(WebhookProductEnum.WE_COM, url, "test title", req -> {
            req.addContent("key1：", "value1");
            req.addContent("key2：", "value2");
            req.addContent("key3：", "value3");
            GlobalInfo globalInfo = new GlobalInfo(new NullPointerException("报错啦"), "cn.woodwhales.webhook");
            req.addGlobalInfo(globalInfo);
        });
    }

}