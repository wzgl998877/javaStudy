package com.zt.javastudy.config;


import io.netty.handler.codec.http.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.yeauty.annotation.*;
import org.yeauty.pojo.Session;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * com.jlpay.cloud.config.websocket
 *
 * @Author liujiaxing
 * @Description TODO
 * @date 2021年02月22日  11:32:22
 */
@ServerEndpoint(port = "${ws.port}", path = "/imserver/{clientOrderId}")
@Component
@Slf4j
public class WebSocketServer {

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static LongAdder onlineCount = new LongAdder();
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。-总公司的缓存对象
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static ConcurrentHashMap<String, WebSocketServer> branchWebSocketMap = new ConcurrentHashMap<>();


    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收clientOrderId
     */
    private String clientOrderId = "";


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, HttpHeaders headers, @RequestParam String req, @RequestParam MultiValueMap reqMap, @PathVariable String clientOrderId, @PathVariable Map pathMap) throws IOException {
        log.info("建立连接信息：clientOrderId[{}]", clientOrderId);
        this.session = session;
        this.clientOrderId = clientOrderId;
        if (webSocketMap.containsKey(clientOrderId)) {
            webSocketMap.remove(clientOrderId);
            webSocketMap.put(clientOrderId, this);
            //加入set中
        } else {
            //加入set中
            webSocketMap.put(clientOrderId, this);
            //在线数加1
            addOnlineCount();
        }
        log.info("用户连接:" + clientOrderId + ",当前在线人数为:" + getOnlineCount());

    }

    private boolean checkToken(String clientOrderId, String token, String companyCode) throws IOException {
        //白名单 校验通过的 clientOrderId + token 可以登录所有分公司
        return false;
    }


    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) throws IOException {
        if (webSocketMap.containsKey(clientOrderId)) {
            webSocketMap.remove(clientOrderId);
            //从set中删除
        }
        subOnlineCount();
        log.info("用户退出:" + clientOrderId + ",当前在线人数为:" + getOnlineCount());
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + this.clientOrderId + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(Session session, String message) {
        log.info("用户消息:" + clientOrderId + ",报文:" + message);
        //可以群发消息
        //消息保存到数据库、redis

    }

    public static int getOnlineCount() {
        return onlineCount.intValue();
    }

    public static void addOnlineCount() {
        WebSocketServer.onlineCount.increment();
    }

    public static void subOnlineCount() {
        WebSocketServer.onlineCount.decrement();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.sendText(message);
    }

    public void sendMessage(byte[] message) throws IOException {
        this.session.sendBinary(message);
    }

    /**
     * 发送自定义消息给所有连接者
     */
    public static void sendInfo(String message) throws IOException {
        Iterator it = webSocketMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, WebSocketServer> entry = (Map.Entry<String, WebSocketServer>) it.next();
            log.info("发送消息到:" + entry.getKey() + "，报文:" + message);
            entry.getValue().sendMessage(message);
        }
    }


    /**
     * 发送自定义消息给所有连接者
     */
    public static void sendInfo(byte[] message) throws IOException {
        Iterator it = webSocketMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, WebSocketServer> entry = (Map.Entry<String, WebSocketServer>) it.next();
            log.info("发送消息到:" + entry.getKey() + "，报文:" + message);
            entry.getValue().sendMessage(message);
        }
    }


}
