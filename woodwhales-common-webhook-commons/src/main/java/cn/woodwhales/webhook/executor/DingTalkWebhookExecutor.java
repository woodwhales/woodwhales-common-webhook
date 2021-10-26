package cn.woodwhales.webhook.executor;

import cn.woodwhales.model.response.DingTalkResponse;
import cn.woodwhales.webhook.enums.WebhookProductEnum;
import cn.woodwhales.webhook.model.request.BaseWebhookRequestBody;
import cn.woodwhales.webhook.model.request.DingTalkRequestBody;
import cn.woodwhales.webhook.model.response.ExecuteResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author woodwhales on 2021-07-19 9:34
 * @description 钉钉webhook请求执行器
 *
 * 正常响应报文：
 * {
 *     "errcode": 0,
 *     "errmsg": "ok"
 * }
 *
 */
@Slf4j
public class DingTalkWebhookExecutor<RequestBody extends BaseWebhookRequestBody> extends BaseWebhookExecutor<DingTalkRequestBody, DingTalkResponse> {

    private static final int ERR_CODE_SUCCESS = 0;

    @Override
    protected WebhookProductEnum webhookProductEnum() {
        return WebhookProductEnum.DING_TALK;
    }

    @Override
    protected boolean checkResponseObjectHandler(ExecuteResponse<DingTalkResponse> executeResponse) {
        DingTalkResponse dingTalkResponse = executeResponse.parsedResponseObject;
        return Objects.equals(ERR_CODE_SUCCESS, dingTalkResponse.getErrcode());
    }

    public static <RequestBody extends BaseWebhookRequestBody> DingTalkWebhookExecutor<RequestBody> newInstance() {
        return new DingTalkWebhookExecutor();
    }

}
