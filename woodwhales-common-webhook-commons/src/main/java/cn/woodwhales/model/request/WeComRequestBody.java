package cn.woodwhales.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author woodwhales on 2021-07-19 18:26
 * @description
 */
@Data
public class WeComRequestBody extends BaseWebhookRequestBody {

    private String msgtype = "markdown";
    private MarkdownContent markdown = new MarkdownContent();

    @JsonIgnore
    private Map<String, String> map = new LinkedHashMap<>();

    public static WeComRequestBody newInstance(String title) {
        WeComRequestBody feiShuNoticeRequestBody = new WeComRequestBody();
        feiShuNoticeRequestBody.getMap().put("# ", title);
        return feiShuNoticeRequestBody;
    }

    @Override
    public String toJsonSting() {
        StringBuilder stringBuilder = new StringBuilder();
        if(Objects.nonNull(map) && !map.isEmpty()) {
            map.entrySet().stream().forEach(entry ->
                                                stringBuilder.append(entry.getKey())
                                                             .append(entry.getValue())
                                                             .append(" \n")
            );
        }
        this.markdown.setContent(stringBuilder.toString());
        return super.toJsonSting();
    }

    public WeComRequestBody addContent(String tag, String text) {
        map.put(tag, text);
        return this;
    }

    @Data
    private static class MarkdownContent {
        private String content;
    }

}
