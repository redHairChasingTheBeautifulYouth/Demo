package nio;
/*
 *一句话描述该类作用:【】
 *@Author:LB
 *
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class ServerHandle implements Runnable {

    private Selector selector;

    private ServerSocketChannel socketChannel;

    private volatile boolean started;

    public ServerHandle(int port){
        try {
            //创建选择器
            selector = Selector.open();
            //打开监听通道
            socketChannel = ServerSocketChannel.open();
            //若为true，将此通道置于阻塞模式
            socketChannel.configureBlocking(false);
            //绑定端口 ，backlog设为1024
            socketChannel.socket().bind(new InetSocketAddress(port) ,1024);
            //监听客户端请求
            socketChannel.register(selector , SelectionKey.OP_ACCEPT);
            //标记服务开启
            started = true;
            System.out.println("服务已启动，端口号：" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public  void stop(){
        started = false;
    }

    @Override
    public void run() {

    }
}
