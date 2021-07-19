package cn.woodwhales.webhook;

import cn.woodwhales.webhook.base.BaseWebhookProduct;
import cn.woodwhales.webhook.base.WebhookProductEnum;
import lombok.Data;

/**
 * @author woodwhales on 2021-07-16 21:16
 * @description
 */
@Data
public class FeiShuWebhookProduct implements BaseWebhookProduct {

    @Override
    public WebhookProductEnum webhookType() {
        return WebhookProductEnum.FEI_SHU;
    }

    @Override
    public String simpleText(String content) {
        return null;
    }
}
