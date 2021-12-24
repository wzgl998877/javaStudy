package com.zt.javastudy.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author zhengtao on 2021/12/24
 */
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class IClientCallbackImpl implements IClientCallback {
    @Override
    public void onSuccess(String result) {
        log.info("服务异步回调成功,result={}", result);
    }

    @Override
    public void onFail(Exception e) {
        log.info("服务异步回调失败", e);
    }

    @Override
    public void onConnect() {

    }
}
