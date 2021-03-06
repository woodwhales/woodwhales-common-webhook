package cn.woodwhales.webhook.event;

import cn.woodwhales.webhook.enums.WebhookProductEnum;
import cn.woodwhales.webhook.model.GlobalInfo;
import cn.woodwhales.webhook.model.request.BaseWebhookRequestBody;
import cn.woodwhales.webhook.model.request.WebhookRequestBodyFactory;
import org.springframework.context.ApplicationEvent;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author woodwhales on 2021-09-15 12:45
 * @description
 */
public class WebhookEvent extends ApplicationEvent {

    private GlobalInfo globalInfo;

    private String title;

    private BaseWebhookRequestBody baseWebhookRequestBody;

    private WebhookProductEnum webhookProductEnum;

    private Consumer<BaseWebhookRequestBody> consumer;

    private Throwable throwable;

    /**
     * 用户id集合
     */
    private List<String> userIdList;

    /**
     * 用户手机号集合
     */
    private List<String> userMobileList;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public WebhookEvent(Object source,
                        Throwable throwable,
                        WebhookProductEnum webhookProductEnum,
                        String title,
                        Consumer<BaseWebhookRequestBody> consumer) {
        super(source);
        new WebhookEvent(source, throwable, webhookProductEnum, title, consumer, null, null);
    }

    public WebhookEvent(Object source,
                                     Throwable throwable,
                                     WebhookProductEnum webhookProductEnum,
                                     String title,
                                     List<String> userIdList,
                                     Consumer<BaseWebhookRequestBody> consumer) {
        super(source);
        this.title = title;
        this.consumer = consumer;
        this.throwable = throwable;
        if(userIdList != null && userIdList.size() > 0) {
            this.userIdList = userIdList;
        }
        if (Objects.nonNull(webhookProductEnum)) {
            fillField(webhookProductEnum);
        }
    }

    public WebhookEvent(Object source,
                                     Throwable throwable,
                                     WebhookProductEnum webhookProductEnum,
                                     String title,
                                     Consumer<BaseWebhookRequestBody> consumer,
                                     List<String> userMobileList) {
        super(source);
        this.title = title;
        this.consumer = consumer;
        this.throwable = throwable;
        if(userMobileList != null && userMobileList.size() > 0) {
            this.userMobileList = userMobileList;
        }
        if (Objects.nonNull(webhookProductEnum)) {
            fillField(webhookProductEnum);
        }
    }

    public WebhookEvent(Object source,
                        Throwable throwable,
                        WebhookProductEnum webhookProductEnum,
                        String title,
                        Consumer<BaseWebhookRequestBody> consumer,
                        List<String> userIdList,
                        List<String> userMobileList) {
        super(source);
        this.title = title;
        this.consumer = consumer;
        this.throwable = throwable;
        if(userIdList != null && userIdList.size() > 0) {
            this.userIdList = userIdList;
        }
        if(userMobileList != null && userMobileList.size() > 0) {
            this.userMobileList = userMobileList;
        }
        if (Objects.nonNull(webhookProductEnum)) {
            fillField(webhookProductEnum);
        }
    }

    public WebhookEvent fillField(WebhookProductEnum webhookProductEnum) {
        this.webhookProductEnum = webhookProductEnum;
        this.baseWebhookRequestBody =
            WebhookRequestBodyFactory.newInstance(webhookProductEnum, title, this.userIdList, this.userMobileList);
        this.consumer.accept(this.baseWebhookRequestBody);
        this.globalInfo = new GlobalInfo(webhookProductEnum, this.throwable, null);
        this.baseWebhookRequestBody.addGlobalInfo(this.globalInfo);
        return this;
    }

    public boolean needFillField() {
        return Objects.isNull(this.webhookProductEnum);
    }

    public WebhookProductEnum getWebhookProductEnum() {
        return webhookProductEnum;
    }

    public void setMachineInfoMap(LinkedHashMap<String, String> machineInfoMap) {
        this.globalInfo.setMachineInfoMap(machineInfoMap);
    }

    public void setGitProperties(Properties gitProperties) {
        this.globalInfo.setGitProperties(gitProperties);
    }

    public String getOccurTime() {
        return this.globalInfo.getOccurTime();
    }

    public BaseWebhookRequestBody getBaseWebhookRequestBody() {
        return baseWebhookRequestBody;
    }

    public String getTitle() {
        return title;
    }

    public void setBasePackName(String basePackName) {
        this.globalInfo.setBasePackName(basePackName);
    }

}
