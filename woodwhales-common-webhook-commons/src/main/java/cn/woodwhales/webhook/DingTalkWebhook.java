package cn.woodwhales.webhook;

import cn.woodwhales.webhook.base.BaseWebhook;
import cn.woodwhales.webhook.base.WebhookTypeEnum;
import lombok.Data;

/**
 * @author woodwhales on 2021-07-16 21:16
 * @description
 */
@Data
public class DingTalkWebhook implements BaseWebhook {

    @Override
    public WebhookTypeEnum webhookType() {
        return WebhookTypeEnum.DINGTALK;
    }

    @Override
    public String simpleText(String content) {
        return null;
    }

}
