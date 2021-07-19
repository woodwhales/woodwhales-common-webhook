package cn.woodwhales.executor;

import cn.woodwhales.model.ExecuteResponse;
import cn.woodwhales.model.request.WeComRequestBody;
import cn.woodwhales.model.response.WeComResponse;
import cn.woodwhales.webhook.base.WebhookProductEnum;

import java.util.Objects;

/**
 * @author woodwhales on 2021-07-19 11:32
 * @description
 */
public class WeComExecutor extends BaseWebhookExecutor<WeComRequestBody, WeComResponse> {

    private static final int ERR_CODE_SUCCESS = 0;

    public static WeComExecutor newInstance() {
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
