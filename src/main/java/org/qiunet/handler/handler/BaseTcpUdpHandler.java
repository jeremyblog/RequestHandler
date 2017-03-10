package org.qiunet.handler.handler;

import org.qiunet.handler.enums.HandlerType;
import org.qiunet.handler.iodata.net.AbstractRequestData;

/**
 * @author qiunet
 *         Created on 17/3/7 10:43.
 */
public abstract class BaseTcpUdpHandler<RequestData extends AbstractRequestData> implements ITcpUdpHandler<RequestData> {
	private short requestId;
	@Override
	public short getRequestID() {
		return requestId;
	}

	@Override
	public HandlerType getHandlerType() {
		return HandlerType.TCP_UDP;
	}

	@Override
	public boolean offical() {
		return true;
	}

	@Override
	public boolean ignoreMaintain() {
		return false;
	}

	@Override
	public boolean needSid() {
		return true;
	}
}
