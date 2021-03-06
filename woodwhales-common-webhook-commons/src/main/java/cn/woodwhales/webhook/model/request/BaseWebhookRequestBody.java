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
public abstract class BaseWebhookRequestBody {

    @JsonIgnore
    protected WebhookProductEnum webhookProductEnum;

    @JsonIgnore
    protected GlobalInfo globalInfo;

    @JsonIgnore
    protected List<String> userIdList;

    @JsonIgnore
    protected List<String> userMobileList;

    @JsonIgnore
    protected Map<String, Object> map = new LinkedHashMap<>();

    public String toJsonSting() {
        List<Pair<String, String>> allInfoPair = this.globalInfo.getAllInfoPair();
        allInfoPair.stream().forEach(pair -> map.put(pair.getLeft(), pair.getRight()));
        preToJsonSting();
        return JsonUtils.toJson(this);
    }

    /**
     * 添加用户id集合
     * @param userIdList 用户id集合
     * @return BaseWebhookRequestBody 对象
     */
    protected BaseWebhookRequestBody addUserIdList(List<String> userIdList) {
        if(userIdList != null && userIdList.size() > 0) {
            this.userIdList = userIdList;
        }
        return this;
    }

    /**
     * 添加用户手机号集合
     * @param userMobileList 用户手机号集合
     * @return BaseWebhookRequestBody 对象
     */
    protected BaseWebhookRequestBody addUserMobileList(List<String> userMobileList) {
        if(userMobileList != null && userMobileList.size() > 0) {
            this.userMobileList = userMobileList;
        }
        return this;
    }

    /**
     * 对象转json字符串之前的操作
     */
    public abstract void preToJsonSting();

    public Map<String, Object> getMap() {
        return map;
    }

    public BaseWebhookRequestBody addContent(String tag, String text) {
        this.map.put(tag, text);
        return this;
    }

    public BaseWebhookRequestBody addContent(String tag, Object obj) {
        this.map.put(tag, obj);
        return this;
    }

    public void setWebhookProductEnum(WebhookProductEnum webhookProductEnum) {
        this.webhookProductEnum = webhookProductEnum;
    }

    public WebhookProductEnum getWebhookProductEnum() {
        return webhookProductEnum;
    }

    public BaseWebhookRequestBody addGlobalInfo(GlobalInfo globalInfo) {
        this.globalInfo = globalInfo;
        return this;
    }

}
