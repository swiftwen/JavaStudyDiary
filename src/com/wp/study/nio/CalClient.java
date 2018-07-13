package com.wp.study.nio;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class CalClient {

	public void send() throws Exception {
		Socket s = new Socket("localhost", 999);
		s.getOutputStream().write("test2".getBytes());
		byte[] b = new byte[1024];
		s.getInputStream().read(b);
		System.out.println(new String(b));
		s.close();
	}

	public void send2() throws Exception {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.connect(new InetSocketAddress(999));
		String msg = "test3";
		byte[] bytes = msg.getBytes("UTF-8");
		int size = bytes.length;
		ByteBuffer buffer = ByteBuffer.allocate(size);
		ByteBuffer sizeBuffer = ByteBuffer.allocate(4);

		sizeBuffer.putInt(size);
		buffer.put(bytes);

		buffer.flip();
			socketChannel.write(buffer);
	}

	public static void main(String[] args) throws Exception {
		new CalClient().send2();
	}
}
