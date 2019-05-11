package cn.huihongcloud.listener;

import cn.huihongcloud.server.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by 钟晖宏 on 2018/5/31
 */

@Component
public class StartListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private Server server;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                server.run();
            }
        }).start();
    }
}
