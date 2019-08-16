package com.tts.logdome.common.nio;

import java.io.IOException;
import java.nio.channels.Channel;
import java.nio.channels.Selector;

/**
 * @创建人 zp
 * @创建时间 2019/8/16
 * @描述 Selector 和 ServerSocketChannel
 */
public class MultiPortEcho {

    public static void main(String[] args) throws IOException {
        //NIO第三个对象Selector
        Selector selector = Selector.open();
        //Selector可以单线程处理多个渠道channel,可以监听IO事件的发生，可将channel注册到Selector上
        //但Channel必须有异步模式（FileChannel没有，所以不行，SelectChannel可以）
//        Channel channel =

    }

}
