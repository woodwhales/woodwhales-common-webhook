package cn.woodwhales.webhook.model.request;

import cn.woodwhales.webhook.enums.WebhookProductEnum;
import cn.woodwhales.webhook.model.GlobalInfo;
import cn.woodwhales.webhook.util.JsonUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.tuple.Pair;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author woodwhales on 2021-07-19 14:52
 * @description
 */
public class BaseWebhookRequestBody {

    @JsonIgnore
    protected WebhookProductEnum webhookProductEnum;

    @JsonIgnore
    protected Map<String, String> map = new LinkedHashMap<>();

    public String toJsonSting() {
        return JsonUtils.toJson(this);
    }

    public Map<String, String> getMap() {
        return map;
    }

    public BaseWebhookRequestBody addContent(String tag, String text) {
        map.put(tag, text);
        return this;
    }

    public void setWebhookProductEnum(WebhookProductEnum webhookProductEnum) {
        this.webhookProductEnum = webhookProductEnum;
    }

    public WebhookProductEnum getWebhookProductEnum() {
        return webhookProductEnum;
    }

    public BaseWebhookRequestBody addGlobalInfo(GlobalInfo globalInfo) {
        List<Pair<String, String>> allInfoPair = globalInfo.getAllInfoPair();
        allInfoPair.stream().forEach(pair -> map.put(pair.getLeft(), pair.getRight()));
        return this;
    }

}
