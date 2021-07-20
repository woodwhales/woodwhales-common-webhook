package cn.woodwhales.webhook.enums;

/**
 * @author woodwhales on 2021-07-16 21:09
 * @description webhook 类型枚举
 */
public enum WebhookProductEnum {

    /**
     * 企业微信
     */
    WE_COM("WE_COM", "企业微信"),

    /**
     * 钉钉
     */
    DING_TALK("DING_TALK", "钉钉"),

    /**
     * 飞书
     */
    FEI_SHU("FEI_SHU", "飞书"),
    ;

    public final String code;
    public final String chineseName;

    WebhookProductEnum(String code, String chineseName) {
        this.code = code;
        this.chineseName = chineseName;
    }
}
