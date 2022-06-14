package com.zt.javastudy.async;

import brave.Tracing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cloud.sleuth.SpanNamer;
import org.springframework.cloud.sleuth.instrument.async.TraceRunnable;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.AsyncContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 测试异步http请求
 *
 * @author zhengtao on 2021/9/23
 */
@RestController
@Slf4j
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AsyncController {
    @Resource
    @Qualifier("httpWorkThreadPool")
    private ThreadPoolTaskExecutor executor;
    @Autowired
    private Tracing tracing;
    @Autowired
    private SpanNamer defaultSpanNamer;
//    private static LongAdder start = new LongAdder();
    private volatile long start;

    @RequestMapping("/testAsync")
    public void test(HttpServletRequest request, HttpServletResponse response) {
        log.info("请求开始！");
        start = System.currentTimeMillis();
        AsyncContext asyncContext = request.startAsync(request, response);
        asyncContext.addListener(new HttpAsyncListener());
        executor.execute(new TraceRunnable(tracing, defaultSpanNamer, () -> {
            try {
                doInvoke(asyncContext);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }
    @RequestMapping("/testCommon")
    public String testCommon() throws InterruptedException {
        log.info("请求开始！");
        start = System.currentTimeMillis();
        log.info("请求处理时间:{}ms", (System.currentTimeMillis() - start));
        return "hello world!";
    }

    /**
     * 处理业务
     *
     * @param asyncContext
     */
    private void doInvoke(AsyncContext asyncContext) throws InterruptedException {
//        Thread.sleep(1000);
        completeResponse("这是一个异步的http请求", 200, asyncContext);
    }

    /**
     * 将
     * @param context
     * @param status
     * @param asyncContext
     */
    private void completeResponse(String context, int status, AsyncContext asyncContext) {
        HttpServletResponse servletResponse = (HttpServletResponse) asyncContext.getResponse();
        if (!ObjectUtils.isEmpty(context)) {
            servletResponse.setContentType(asyncContext.getRequest().getContentType());
            servletResponse.setStatus(status);
            completeResponse(servletResponse, context);
        }
        // 调用了complete方法后才算请求完成
        asyncContext.complete();
        log.info("请求处理时间:{}ms", (System.currentTimeMillis() - start));
    }

    private void completeResponse(HttpServletResponse servletResponse, String context) {
        ServletOutputStream out = null;
        try {
            byte[] buff = context.getBytes();
            servletResponse.setContentLength(buff.length);
            out = servletResponse.getOutputStream();
            out.write(buff);
            out.flush();
        } catch (IOException e) {
            log.error("complete http reqeust error", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

}
