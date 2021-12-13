package cn.woodwhales.webhook.config;

import cn.woodwhales.common.webhook.event.WebhookEvent;
import cn.woodwhales.common.webhook.event.WebhookEventHandler;
import cn.woodwhales.common.webhook.plugin.WebhookExtraInfo;
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

    @Value("${notice.secret}")
    private String noticeSecret;

    private String basePackageName = "cn.woodwhales.webhook";

    @Bean
    public WebhookExtraInfo webhookExtraInfo() {
        return new WebhookExtraInfo(5, TimeUnit.MINUTES);
    }

    @EventListener
    public void handleCustomEvent(WebhookEvent webhookEvent) {
        WebhookEventHandler.handleCustomEvent(webhookEvent, noticeUrl, noticeSecret, basePackageName, webhookExtraInfo());
    }

}
