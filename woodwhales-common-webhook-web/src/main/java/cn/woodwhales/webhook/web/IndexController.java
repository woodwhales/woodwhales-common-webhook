package cn.woodwhales.webhook.web;

import cn.woodwhales.common.webhook.event.WebhookEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author woodwhales on 2021-07-16 20:46
 * @description
 */
@RestController
@RequestMapping("test")
public class IndexController {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    private RuntimeException exception = new RuntimeException("报错啦");

    /**
     * 发送 webhook 通知
     *
     * @param content 要发送的内容
     * @return 响应对象
     */
    @GetMapping("/send")
    public String send(@RequestParam("content") String content) {

        // 方式1 不推荐，显示创建指定webhook事件对象
        example1(content);

        // 方式1 推荐，不用显示创建指定webhook事件对象，根据通知发送链接自动识别创建对应的webhook事件对象
        example2(content);

        example3(content);

        return "ok";
    }

    private void example1(String content) {
        applicationEventPublisher.publishEvent(WebhookEvent.Builder.build(this, "测试标题")
                .throwable(exception)
                .consumer(request -> {
                    request.addContent("content：", content);
                    request.addContent("key：", content);
                })
                .build());
    }

    private void example2(String content) {
        applicationEventPublisher.publishEvent(WebhookEvent.Builder.build(this, "测试标题")
                .throwable(exception)
                .consumer(request -> {
                    request.addContent("content：", content);
                    request.addContent("key：", content);
                })
                .noticeUrl("https://oapi.dingtalk.com/robot/send?access_token=xxx", "yyy")
                .userIdList(Arrays.asList("xxx")).build());
    }

    private void example3(String content) {
        applicationEventPublisher.publishEvent(WebhookEvent.Builder.build(this, "测试标题")
                .throwable(exception)
                .consumer(request -> {
                    request.addContent("content：", content);
                    request.addContent("key：", content);
                })
                // 发送到指定webhook，不使用默认配置的webhook
                .noticeUrl("https://open.feishu.cn/open-apis/bot/v2/hook/xxx", "yyy").build()
        );
    }
}
