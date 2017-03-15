package org.qiunet.handler.context;

import org.qiunet.handler.handler.IHandler;
import org.qiunet.handler.handler.RequestHandlerMapping;
import org.qiunet.handler.handler.acceptor.Acceptor;
import org.qiunet.handler.iodata.net.AbstractRequestData;

/**
 * @author qiunet
 *         Created on 17/3/13 19:50.
 */
abstract class BaseContext implements IContext {
	protected AbstractRequestData requestData;
	private Acceptor acceptor;
	protected IHandler handler;
	protected BaseContext(AbstractRequestData requestData) {
		this.requestData = requestData;
		this.handler = RequestHandlerMapping.getInstance().getHandler(requestData.getLeader().getCmdId());
		if (handler == null) {
			throw new NullPointerException("Request id not found!");
		}
	}

	@Override
	public IHandler getHandler() {
		return handler;
	}

	@Override
	public AbstractRequestData getRequestData() {
		return requestData;
	}

	@Override
	public void setAcceptor(Acceptor acceptor) {
		this.acceptor = acceptor;
	}

	@Override
	public boolean handler() {
		acceptor.handler(this);
		return true;
	}

	@Override
	public String toStr() {
		return "RequestID ["+handler.getRequestID()+"]:"+requestData.toString();
	}
}
