package org.qiunet.handler.handler.intecepter;

import org.qiunet.handler.handler.IHandler;
import org.qiunet.handler.handler.IHttpHandler;
import org.qiunet.handler.handler.ITcpUdpHandler;
import org.qiunet.handler.iodata.net.AbstractRequestData;

/**
 * intercepter 可以继承这个类省掉部分方法. 否则自己实现也行
 * @author qiunet
 *         Created on 17/3/7 11:33.
 */
public abstract class AbstractIntercepter<LogicData> implements Intercepter<LogicData> {

	@Override
	public void postHandler(LogicData logicData) {
		
	}

	@Override
	public void throwCause(Exception e) {
		
	}

	@Override
	public void handler(IHandler handler, AbstractRequestData requestData, LogicData logicData) {
		switch (handler.getHandlerType()){
			case HTTP:
				this.httpHandler((IHttpHandler) handler, requestData, logicData);
				break;
			case TCP_UDP:
				this.tcpUdpHandler((ITcpUdpHandler) handler, requestData, logicData);
				break;
		}
	}

	/**
	 * http handler
	 */
	protected abstract void httpHandler(IHttpHandler handler, AbstractRequestData requestData, LogicData logicData);

	/**
	 * tcp udp handler
	 */
	protected abstract void tcpUdpHandler(ITcpUdpHandler handler, AbstractRequestData requestData, LogicData logicData);
}
