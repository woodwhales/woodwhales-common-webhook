package cn.woodwhales.webhook.executor;

import cn.woodwhales.model.response.WeComResponse;
import cn.woodwhales.webhook.enums.WebhookProductEnum;
import cn.woodwhales.webhook.model.request.BaseWebhookRequestBody;
import cn.woodwhales.webhook.model.request.WeComRequestBody;
import cn.woodwhales.webhook.model.response.ExecuteResponse;

import java.util.Objects;

/**
 * @author woodwhales on 2021-07-19 11:32
 * @description
 */
public class WeComExecutor<RequestBody extends BaseWebhookRequestBody> extends BaseWebhookExecutor<WeComRequestBody, WeComResponse> {

    private static final int ERR_CODE_SUCCESS = 0;

    public static <RequestBody extends BaseWebhookRequestBody> WeComExecutor<RequestBody> newInstance() {
        return new WeComExecutor();
    }

    @Override
    protected boolean checkResponseObjectHandler(ExecuteResponse<WeComResponse> executeResponse) {
        WeComResponse weComResponse = executeResponse.parsedResponseObject;
        return Objects.equals(ERR_CODE_SUCCESS, weComResponse.getErrcode());
    }

    @Override
    protected WebhookProductEnum webhookProductEnum() {
        return WebhookProductEnum.WE_COM;
    }
}
