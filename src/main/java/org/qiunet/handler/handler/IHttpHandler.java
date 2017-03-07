package org.qiunet.handler.handler;

import org.qiunet.handler.iodata.net.AbstractRequestData;
import org.qiunet.handler.iodata.net.AbstractResponseData;

/**
 * http的处理
 * @author qiunet
 *         Created on 17/3/3 12:01.
 */
public interface IHttpHandler<RequestData extends AbstractRequestData> extends IHandler<RequestData> {
	/**
	 * 一般用于http协议. true的情况不允许频繁访问.  
	 * @return
	 */
	public boolean fastRequestControl();
	/**
	 * http返回处理后的ResponseData
	 * @param requestData
	 * @return
	 */
	public AbstractResponseData handler(RequestData requestData);
}
