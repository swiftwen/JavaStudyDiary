package com.wp.study.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

/**
 * @desc 
 * @Author wenpeng
 * @2018年6月26日 上午11:49:34
 */
public class ServerHandler implements Runnable{

	
	private Selector selector;
	private ServerSocketChannel channel;
	private volatile boolean started;  
	
	@Override
	public void run() {
		while(started) {
			try {
				selector.select(1000); 
				Set<SelectionKey> keys = selector.selectedKeys();
				for(SelectionKey key : keys) {
					
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void stop(){  
        started = false;  
    }  
	public ServerHandler(int port) {
		try {
			selector = Selector.open();
			channel = ServerSocketChannel.open();
			channel.configureBlocking(false);
			channel.socket().bind(new InetSocketAddress(port),1024);
			channel.register(selector, SelectionKey.OP_ACCEPT);
			started = true;  
            System.out.println("服务器已启动，端口号：" + port);  
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
