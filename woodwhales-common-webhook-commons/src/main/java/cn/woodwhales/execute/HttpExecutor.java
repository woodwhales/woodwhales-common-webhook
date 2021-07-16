package cn.woodwhales.execute;

import cn.hutool.http.HttpUtil;
import cn.woodwhales.model.ExecuteParam;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.Objects.nonNull;

/**
 * @author woodwhales on 2021-07-16 21:24
 * @description 请求执行器
 */
@Slf4j
public class HttpExecutor {

    private Function<String, Boolean> parseResponseContentFunction;

    private Consumer<ExecuteParam> beforeHandler;

    private Function<String, Object> parseResponseHandler;

    private Function<Object, Boolean> checkResponseObjectHandler;

    private BiConsumer<ExecuteParam, Object> failHandler;

    /**
     * 执行发送消息
     * @param executeParam
     */
    public boolean execute(ExecuteParam executeParam) {
        if(nonNull(this.beforeHandler)) {
            this.beforeHandler.accept(executeParam);
        }

        String responseContent = HttpUtil.post(executeParam.url, executeParam.content);

        if(nonNull(this.parseResponseHandler)) {
            Object responseObject = this.parseResponseHandler.apply(responseContent);
            if(nonNull(this.checkResponseObjectHandler)) {
                Boolean noticeSuccess = this.checkResponseObjectHandler.apply(responseObject);
                if(nonNull(noticeSuccess) && !noticeSuccess) {
                    if(nonNull(this.failHandler)) {
                        this.failHandler.accept(executeParam, responseObject);
                    }
                    log.error("发送消息失败, responseContent = {}", responseContent);
                    return false;
                }
            }
        } else {
            log.error("responseContent = {}", responseContent);
        }

        return true;
    }

    public HttpExecutor(Function<String, Boolean> parseResponseContentFunction,
                        Consumer<ExecuteParam> beforeHandler,
                        Function<String, Object> parseResponseHandler,
                        Function<Object, Boolean> checkResponseObjectHandler,
                        BiConsumer<ExecuteParam, Object> failHandler) {
        this.parseResponseContentFunction = parseResponseContentFunction;
        this.beforeHandler = beforeHandler;
        this.parseResponseHandler = parseResponseHandler;
        this.checkResponseObjectHandler = checkResponseObjectHandler;
        this.failHandler = failHandler;
    }
}
