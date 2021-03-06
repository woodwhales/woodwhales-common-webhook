package cn.woodwhales.webhook.model.request;

import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @author woodwhales on 2021-07-19 18:26
 * @description
 */
@Data
public class WeComRequestBody extends BaseWebhookRequestBody {

    private String msgtype = "markdown";

    private MarkdownContent markdown = new MarkdownContent();

    public static WeComRequestBody newInstance(String title) {
        WeComRequestBody feiShuNoticeRequestBody = new WeComRequestBody();
        feiShuNoticeRequestBody.getMap().put("# ", title);
        return feiShuNoticeRequestBody;
    }

    @Override
    public void preToJsonSting() {
        StringBuilder stringBuilder = new StringBuilder();
        if(Objects.nonNull(map) && !map.isEmpty()) {
            map.entrySet().stream().forEach(entry ->
                                                stringBuilder.append(entry.getKey())
                                                             .append(entry.getValue())
                                                             .append(" \n")
            );
        }
        this.markdown.setContent(stringBuilder.toString());
    }

    @Data
    private static class MarkdownContent {
        private String content;
    }

}
