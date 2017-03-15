package org.qiunet.handler.mina.handler;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.qiunet.handler.context.MinaContext;
import org.qiunet.handler.handler.acceptor.Acceptor;
import org.qiunet.handler.intercepter.HandlerIntercepter;
import org.qiunet.handler.iodata.net.AbstractRequestData;
import org.qiunet.handler.mina.evnet.ISessionEvent;
import org.qiunet.handler.mina.session.DefaultSessionBuilder;
import org.qiunet.handler.mina.session.ISessionBuilder;
import org.qiunet.handler.mina.session.MinaSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qiunet
 *         Created on 17/3/7 18:28.
 */
public class MinaHandler extends IoHandlerAdapter {
	public final Map<Long, MinaSession> sessionManager = new ConcurrentHashMap<>();
	private ISessionBuilder sessionBuilder = new DefaultSessionBuilder();
	private volatile static MinaHandler instance;
	private ISessionEvent sessionEvent;
	private Acceptor acceptor;
	private MinaHandler() {
		acceptor = Acceptor.create(new HandlerIntercepter());
		instance = this;
	}

	public static MinaHandler getInstance() {
		if (instance == null) {
			synchronized (MinaHandler.class) {
				if (instance == null)
				{
					new MinaHandler();
				}
			}
		}
		return instance;
	}

	public void setSessionEvent(ISessionEvent sessionEvent) {
		this.sessionEvent = sessionEvent;
	}
	public void setSessionBuilder(ISessionBuilder sessionBuilder) {
		if (sessionBuilder == null) throw new NullPointerException("sessionBuilder can not be null!");
		this.sessionBuilder = sessionBuilder;
	}
	
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		sessionManager.put(session.getId(), sessionBuilder.build(session));
		if (needCallSessionEvent(session)) sessionEvent.sessionCreated(sessionManager.get(session.getId()));
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		if (needCallSessionEvent(session)) sessionEvent.sessionClosed(sessionManager.get(session.getId()));
		session.closeNow();
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		super.sessionIdle(session, status);
		if (needCallSessionEvent(session)) sessionEvent.sessionIdle(sessionManager.get(session.getId()));
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		super.sessionOpened(session);
		if (needCallSessionEvent(session)) sessionEvent.sessionOpened(sessionManager.get(session.getId()));
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		super.messageSent(session, message);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		acceptor.process(new MinaContext((AbstractRequestData) message, session));
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		super.exceptionCaught(session, cause);
		if (needCallSessionEvent(session)) sessionEvent.exceptionCaught(sessionManager.get(session.getId()), cause);
		session.closeNow();
	}

	/**
	 * 是否调用sessionEvent
	 * @param session
	 * @return
	 */
	private boolean needCallSessionEvent(IoSession session){
		return  sessionEvent != null && sessionManager.containsKey(session.getId());
	}
}
