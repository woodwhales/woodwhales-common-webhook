package cn.woodwhales.webhook.enums;

import org.junit.Assert;
import org.junit.Test;

public class WebhookProductEnumTest {

    @Test
    public void test() {
        WebhookProductEnum webhookProductEnum =
            WebhookProductEnum.getWebhookProductEnumByNoticeUrl("https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=yyy");
        Assert.assertEquals(WebhookProductEnum.WE_COM, webhookProductEnum);
    }
}