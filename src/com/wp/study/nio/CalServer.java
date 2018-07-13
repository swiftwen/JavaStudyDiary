package com.wp.study.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;
/**
 * @desc 
 * @Author wenpeng
 * @2018年7月13日 下午3:43:47
 */
public class CalServer {

	public void start(int port) throws Exception {
		ServerSocket ss = new ServerSocket(port);
		while (true) {
			System.out.println("start");
			Socket s = null;
			try {
				s = ss.accept();
				System.out.println("request is coming");
				byte[] b = new byte[1024];
				s.getInputStream().read(b);
				System.out.println(new String(b));
				s.getOutputStream().write("ok".getBytes());
			} catch (Exception e) {
				if (null != s) {
					s.close();
				}
			}
		}

	}

	public void start2(int port) throws Exception {
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);
		ssc.bind(new InetSocketAddress(port));
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		ByteBuffer sizeBuffer = ByteBuffer.allocate(4);
		StringBuilder sb = new StringBuilder();
		Selector selector = Selector.open();
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		byte b[] = new byte[1024];
		while (true) {
			try {
				while(selector.select()>0) {
					System.out.println("in");
					Set<SelectionKey> keys = selector.selectedKeys();
					System.out.println(keys.size());
					for(SelectionKey key : keys) {
						if(key.isAcceptable()) {
							SocketChannel socketChannel = ssc.accept();
							if(null != socketChannel) {
								socketChannel.configureBlocking(false);
			                    socketChannel.register(selector, SelectionKey.OP_READ);
							}
		                    
						}else if(key.isReadable()) {
							System.out.println("------------");
							SocketChannel socketChannel = (SocketChannel)key.channel();
							ByteBuffer bb = ByteBuffer.allocate(1024);
							int len;
							len = socketChannel.read(bb);
							bb.flip();
							bb.get(b,0,len);
							System.out.println(new String(b,0,len));
						}
					}
					System.out.println("end");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) throws Exception {
		new CalServer().start2(999);
	}

}
