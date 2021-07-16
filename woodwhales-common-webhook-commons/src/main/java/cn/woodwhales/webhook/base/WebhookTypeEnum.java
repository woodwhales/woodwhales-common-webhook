package cn.woodwhales.webhook.base;

/**
 * @author woodwhales on 2021-07-16 21:09
 * @description webhook 类型枚举
 */
public enum WebhookTypeEnum {

    /**
     * 企业微信
     */
    WORK_WEIXIN("WORK_WEIXIN", "企业微信"),

    /**
     * 钉钉
     */
    DINGTALK("DINGTALK", "钉钉"),

    /**
     * 飞书
     */
    FEISHU("FEISHU", "飞书"),
    ;

    public final String product;
    public final String chineseName;

    WebhookTypeEnum(String product, String chineseName) {
        this.product = product;
        this.chineseName = chineseName;
    }
}
