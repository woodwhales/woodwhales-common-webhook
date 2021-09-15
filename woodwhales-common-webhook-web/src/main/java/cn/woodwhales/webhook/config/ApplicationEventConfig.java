package cn.woodwhales.webhook.config;

import cn.woodwhales.webhook.enums.WebhookProductEnum;
import cn.woodwhales.webhook.event.WebhookEvent;
import cn.woodwhales.webhook.executor.WebhookExecutorFactory;
import cn.woodwhales.webhook.model.request.BaseWebhookRequestBody;
import cn.woodwhales.webhook.plugin.WebhookExtraInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.concurrent.TimeUnit;

/**
 * @author woodwhales on 2021-09-15 12:13
 * @description
 */
@Log4j2
@Configuration
public class ApplicationEventConfig {

    @Value("${notice.url}")
    private String noticeUrl;

    private String basePackageName = "cn.woodwhales.webhook";

    @Bean
    public WebhookExtraInfo webhookExtraInfo() {
        return new WebhookExtraInfo(5, TimeUnit.MINUTES);
    }

    @EventListener
    public void handleCustomEvent(WebhookEvent webhookEvent) {
        if(webhookEvent.needFillField()) {
            WebhookProductEnum webhookProductEnum = WebhookProductEnum.getWebhookProductEnumByNoticeUrl(noticeUrl);
            webhookEvent.fillField(webhookProductEnum);
        }

        log.info("监听到异常报警事件，消息为：{}, 发布时间：{}",
                 webhookEvent.getOccurTime(),
                 webhookEvent.getTitle());
        webhookEvent.setBasePackName(basePackageName);

        // 增加额外扩展信息
        WebhookExtraInfo webhookExtraInfo = webhookExtraInfo();
        webhookEvent.setGitProperties(webhookExtraInfo.getGitProperties());
        webhookEvent.setMachineInfoMap(webhookExtraInfo.getMachineInfoMap());

        BaseWebhookRequestBody webhookRequest = webhookEvent.getBaseWebhookRequestBody();
        WebhookExecutorFactory.execute(noticeUrl, webhookRequest);
    }

}
