package cn.woodwhales.executor;

import cn.woodwhales.model.request.DingTalkRequestBody;
import cn.woodwhales.model.request.FeiShuRequestBody;
import cn.woodwhales.model.request.WeComRequestBody;
import org.junit.Test;

public class WebhookExecutorTest {

    @Test
    public void DingTalkExecutor() {
        String url = "https://oapi.dingtalk.com/robot/send?access_token=xx";

        DingTalkRequestBody dingTalkRequestBody = DingTalkRequestBody.newInstance("test title");
        dingTalkRequestBody.addContent("key1：", "value1");
        dingTalkRequestBody.addContent("key2：", "value2");
        dingTalkRequestBody.addContent("key3：", "value3");

        DingTalkExecutor.newInstance().execute(url, dingTalkRequestBody);
    }

    @Test
    public void FeiShuExecutor() {
        String url = "https://open.feishu.cn/open-apis/bot/v2/hook/xx";

        FeiShuRequestBody feiShuRequestBody = FeiShuRequestBody.newInstance("test title");
        feiShuRequestBody.addContent("key1：", "value1");
        feiShuRequestBody.addContent("key2：", "value2");
        feiShuRequestBody.addContent("key3：", "value3");

        FeiShuExecutor.newInstance().execute(url, feiShuRequestBody);
    }

    @Test
    public void WeComExecutor() {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=xx";

        WeComRequestBody weComRequestBody = WeComRequestBody.newInstance("test title");
        weComRequestBody.addContent("key1：", "value1");
        weComRequestBody.addContent("key2：", "value2");
        weComRequestBody.addContent("key3：", "value3");

        WeComExecutor.newInstance().execute(url, weComRequestBody);
    }

}