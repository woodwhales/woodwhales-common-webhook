package cn.woodwhales.webhook.model.request;

import lombok.Data;

import java.util.Objects;

/**
 * @author woodwhales on 2021-07-19 14:52
 * @description
 */
@Data
public class DingTalkRequestBody extends BaseWebhookRequestBody {

    private String msgtype = "markdown";
    private MarkdownContent markdown = new MarkdownContent();

    public static DingTalkRequestBody newInstance(String title) {
        DingTalkRequestBody dingTalkRequestBody = new DingTalkRequestBody();
        dingTalkRequestBody.getMarkdown().setTitle(title);
        dingTalkRequestBody.getMap().put("# ", title);
        return dingTalkRequestBody;
    }

    @Override
    public String toJsonSting() {
        StringBuilder stringBuilder = new StringBuilder();
        if(Objects.nonNull(map) && !map.isEmpty()) {
            map.entrySet().stream().forEach(entry ->
                                                stringBuilder.append(entry.getKey())
                                                             .append(entry.getValue())
                                                             .append(" \n\r")
            );
        }
        this.markdown.setText(stringBuilder.toString());
        return super.toJsonSting();
    }

    @Data
    private static class MarkdownContent {
        private String title;
        private String text;
    }

}
