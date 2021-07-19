package cn.woodwhales.model.request;

import cn.woodwhales.util.JsonUtils;

/**
 * @author woodwhales on 2021-07-19 14:52
 * @description
 */
public class BaseWebhookRequestBody {

    public String toJsonSting() {
        return JsonUtils.toJson(this);
    }

}
