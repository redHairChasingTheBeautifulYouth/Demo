package nio;
/*
 *一句话描述该类作用:【简易多人聊天服务端】
 *@Author:LB
 *
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CharRoomServer {

    private ServerSocketChannel serverSocketChannel = null;

    private Selector selector = null;

    public static final int PORT_NUM = 1198;

    private boolean active = true;

    private Charset charset = Charset.forName("UTF-8");

    private List<String> users  = new ArrayList<String>();

    private ByteBuffer byteBuffer = ByteBuffer.allocate(2*1024);

    public static final String protocol = "#user#";

    public CharRoomServer(){
        this.init();
    }

    public void init() {
        try {
            // 获得一个ServerSocket通道
            serverSocketChannel = ServerSocketChannel.open();
            // 设置通道为非阻塞
            serverSocketChannel.configureBlocking(false);
            // 将该通道对应的ServerSocket绑定到port端口
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT_NUM));
            // 获得一个通道選擇器
            selector = Selector.open();
            //将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
            //当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        System.out.println("开始监听。。。。");
        while (active) {
            try {
                //阻塞连接
                int s = selector.select();
                System.out.println("服务端接受到连接总数"+selector.keys().size());
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 获得selector中选中的项的迭代器，选中的项为注册的事件
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey k = keys.next();
                // 删除已选的key,以防重复处理
                keys.remove();
                //处理逻辑
                doHandler(selector, k);
            }
        }
    }

    private void doHandler(Selector selector, SelectionKey k){
        //连接事件
        if (k.isConnectable()) {
            System.out.println("Connectable 连接事件");
        }else if (k.isAcceptable()) {
            ServerSocketChannel ser = (ServerSocketChannel) k.channel();
            if (ser == serverSocketChannel) {
                System.out.println("同一个连接");
            }
            try {
                SocketChannel socketChannel = ser.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
                socketChannel.write(charset.encode("please enter login name:"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (k.isReadable()) {
            //获取客户端连接
            SocketChannel socketChannel = (SocketChannel) k.channel();
            StringBuffer content = new StringBuffer();
            int sum = 0;
            try {
                byteBuffer.clear();
                while ((sum = socketChannel.read(byteBuffer)) > 0) {
                    byteBuffer.flip();
                    content.append(charset.decode(byteBuffer));
                }
                System.out.println(sum);
                //判断客户端连接关闭
                if (sum == -1) {
                    socketChannel.close();
                    System.out.println("1--关闭连接");
                }
            }catch (Exception e) {

            }
        }
    }


}
