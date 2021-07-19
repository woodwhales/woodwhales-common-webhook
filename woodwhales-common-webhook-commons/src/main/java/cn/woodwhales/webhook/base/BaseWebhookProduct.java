package cn.woodwhales.webhook.base;

/**
 * @author woodwhales on 2021-07-16 21:17
 * @description
 */
public interface BaseWebhookProduct {

    /**
     * webhook 类型
     * @return
     */
    WebhookProductEnum webhookType();

    /**
     * 简单文本通知
     * @param content
     * @return
     */
    String simpleText(String content);
}
