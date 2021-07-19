package cn.woodwhales.model;

/**
 * @author woodwhales on 2021-07-19 9:38
 * @description 通知响应对象
 */
public class ExecuteResponse<R> {

    /**
     * 响应原始报文
     */
    public String originResponseContent;

    /**
     * 原始报文解析之后的数据对象
     */
    public R parsedResponseObject;

    /**
     * 校验响应结果
     */
    public boolean checkResult;

    /**
     * http 响应状态码
     */
    public int statusCode;

    public ExecuteResponse(int statusCode, String originResponseContent) {
        this.statusCode = statusCode;
        this.originResponseContent = originResponseContent;
    }
}
