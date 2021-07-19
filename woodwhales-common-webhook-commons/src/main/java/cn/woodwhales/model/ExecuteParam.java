package cn.woodwhales.model;

/**
 * @author woodwhales on 2021-07-16 21:38
 * @description 请求参数对象
 */
public class ExecuteParam {
    /**
     * 通知地址
     */
    public String url;

    /**
     * 通知内容
     */
    public String content;

    private ExecuteParam(String url, String content) {
        this.url = url;
        this.content = content;
    }

    public static ExecuteParam newInstance(String url, String content) {
        return new ExecuteParam(url, content);
    }
}
