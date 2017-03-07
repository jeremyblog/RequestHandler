package org.qiunet.handler.handler.intecepter;

import org.qiunet.handler.handler.IHandler;
import org.qiunet.handler.iodata.net.AbstractRequestData;

/**
 *  拦截器.
 *  可以多组一起使用.
 * @author qiunet
 *         Created on 17/3/6 18:04.
 */
public interface Intercepter<LogicData> {
	/**
	 * 前置处理.
	 * @param requestData
	 * @return
	 */
	public LogicData preHandler(AbstractRequestData requestData);

	/**
	 * 处理  
	 * @param handler  处理的handler
	 * @param requestData 请求的数据
	 * @param logicData preHandler 返回的T 给重新传入当参数.
	 */
	public void handler(IHandler handler, AbstractRequestData requestData, LogicData logicData);
	/**
	 * 后置处理
	 * @param logicData
	 */
	public void postHandler(LogicData logicData);
	/**
	 * 异常处理
	 * @param e
	 */
	public void throwCause(Exception e);
}
