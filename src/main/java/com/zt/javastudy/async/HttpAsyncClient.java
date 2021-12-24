package com.zt.javastudy.async;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Map;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HttpAsyncClient implements IAsyncClient {
    private String url;
    private String logId;
    @Builder.Default
    private ContentType contentType = ContentType.APPLICATION_JSON;
    private Map<String, String> headers;
    @Autowired
    private AsyncHttptemplate asyncHttptemplate;
    @Override
    public String getUri() {
        return url;
    }

    @Override
    public void send(String context,IClientCallback clientCallback) {
        AsyncHttptemplate asyncHttptemplate = SpringContextUtil.getBean(AsyncHttptemplate.class);
        asyncHttptemplate.execute(this, context, clientCallback, headers);
    }

}
