package cn.woodwhales.webhook.plugin;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.nonNull;

/**
 * @author woodwhales on 2021-09-15 15:35
 * @description
 */
@Slf4j
@Getter
public class WebhookExtraInfo {

    private LinkedHashMap<String, String> machineInfoMap;

    private Properties gitProperties;

    private Cache<String, String> cache;

    public WebhookExtraInfo(long duration, TimeUnit unit) {
        init(duration, unit);
    }

    private void init(long duration, TimeUnit unit) {
        fillGitProperties();
        fillCache(duration, unit);
        fillMachineInfo();
    }

    private void fillMachineInfo() {
        this.machineInfoMap = new LinkedHashMap<>(6);

        try {
            InetAddress localHost = InetAddress.getLocalHost();
            this.machineInfoMap.put("服务器名称：", localHost.getHostName());
            this.machineInfoMap.put("服务器地址：", localHost.getHostAddress());
        } catch (UnknownHostException e) {
            log.error("获取本机ip地址失败");
        }
    }

    private void fillCache(long duration, TimeUnit unit) {
        this.cache = CacheBuilder.newBuilder()
                            .expireAfterAccess(duration, unit)
                            .build();
    }

    public void fillGitProperties() {
        try (InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("git.properties")) {
            if(Objects.isNull(resourceAsStream)) {
                return;
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream,
                                                                                     Charset.defaultCharset().name()));
            if(nonNull(bufferedReader)) {
                this.gitProperties = new Properties();
                this.gitProperties.load(bufferedReader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
