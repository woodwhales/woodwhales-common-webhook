package cn.woodwhales.webhook.event;

import cn.woodwhales.webhook.enums.WebhookProductEnum;
import cn.woodwhales.webhook.executor.WebhookExecutorFactory;
import cn.woodwhales.webhook.model.request.BaseWebhookRequestBody;
import cn.woodwhales.webhook.plugin.WebhookExtraInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author woodwhales on 2021-09-15 16:40
 * @description
 */
@Slf4j
public class WebhookEventHandler {

    public static void handleCustomEvent(WebhookEvent webhookEvent,
                                         String noticeUrl,
                                         String basePackageName,
                                         WebhookExtraInfo webhookExtraInfo) {
        if(webhookEvent.needFillField()) {
            WebhookProductEnum webhookProductEnum = WebhookProductEnum.getWebhookProductEnumByNoticeUrl(noticeUrl);
            webhookEvent.fillField(webhookProductEnum);
        }

        log.info("监听到异常报警事件，消息为：{}, 发布时间：{}",
                 webhookEvent.getOccurTime(),
                 webhookEvent.getTitle());
        webhookEvent.setBasePackName(basePackageName);

        // 增加额外扩展信息
        if(Objects.nonNull(webhookExtraInfo)) {
            webhookEvent.setGitProperties(webhookExtraInfo.getGitProperties());
            webhookEvent.setMachineInfoMap(webhookExtraInfo.getMachineInfoMap());
        }

        BaseWebhookRequestBody webhookRequest = webhookEvent.getBaseWebhookRequestBody();
        WebhookExecutorFactory.execute(noticeUrl, webhookRequest);
    }

}
