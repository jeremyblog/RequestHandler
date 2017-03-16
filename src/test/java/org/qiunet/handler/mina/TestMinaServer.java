package org.qiunet.handler.mina;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.junit.Test;
import org.qiunet.handler.iodata.entitys.LoginRequestData;
import org.qiunet.handler.mina.handler.MinaHandler;
import org.qiunet.handler.mina.protocols.MessageCodecFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.CountDownLatch;

/**
 * @author qiunet
 *         Created on 17/3/16 15:04.
 */
public class TestMinaServer {
	static final short port = 10020;
	public static void main(String [] args){
		MinaServer minaServer = new MinaServer();
		CountDownLatch latch = new CountDownLatch(1);
		minaServer.setPort(port);
		minaServer.start();
		
		
//		IoSession ioSession = socketConnect("127.0.0.1", (short) 10020);
//		if (ioSession != null ) {
//			LoginRequestData loginRequestData = new LoginRequestData();
//			loginRequestData.getCommon().setUid(1000);
//			loginRequestData.getLeader().setCmdId((short) 100);
//
//			loginRequestData.setOpenid("qiunet");
//			loginRequestData.setSecret("qiuyang");
//			loginRequestData.setToken("xiangyang");
//
//			ioSession.write(loginRequestData);
//		}
//		
//		try {
//			latch.await();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		minaServer.stop();
	}

	public static IoSession socketConnect(String ip, short port){
		NioSocketConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(10000L);
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MessageCodecFactory()));
		connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 60);
		connector.setHandler(MinaHandler.getInstance());
		connector.setDefaultRemoteAddress(new InetSocketAddress(ip, port));
		ConnectFuture cf = connector.connect();
		cf.awaitUninterruptibly();
		if (cf.isDone()) {
			if (cf.isConnected()) {
				return cf.getSession();
			}
			connector.dispose(true);
		}
		return null;
	}	
}