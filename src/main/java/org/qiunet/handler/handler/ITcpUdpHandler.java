package org.qiunet.handler.handler;

import org.qiunet.handler.iodata.net.AbstractRequestData;
import org.qiunet.handler.iodata.net.AbstractResponseData;

/**
 * @author qiunet
 *         Created on 17/3/3 12:01.
 */
public interface ITcpUdpHandler<RequestData extends AbstractRequestData> extends IHandler<RequestData> {
	/**
	 * tcp udp 处理. 下行一般在逻辑里面处理了
	 * @param requestData
	 * @return
	 */
	public void handler(RequestData requestData);
}
