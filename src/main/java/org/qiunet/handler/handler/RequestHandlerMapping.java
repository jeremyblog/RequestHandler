package org.qiunet.handler.handler;

import org.qiunet.utils.exceptions.SingletonException;

import java.util.HashMap;
import java.util.Map;
/**
 * 全局的RequestHandler 一个对应Mapping 类
 * 单例模式
 * @author qiunet
 *         Created on 17/3/3 16:46.
 */
public class RequestHandlerMapping {
	private volatile static RequestHandlerMapping instance;
	/**所有的 handler*/
	private Map<Integer, IHandler> handlers = new HashMap<>();

	private RequestHandlerMapping() {
		synchronized (RequestHandlerMapping.class) {
			if (instance != null ){
				throw new SingletonException("RequestHandlerMapping instance was duplicate");
			}
			instance = this;
		}
	}
	
	public static RequestHandlerMapping getInstance() {
		if (instance == null) {
			synchronized (RequestHandlerMapping.class) {
				if (instance == null)
				{
					new RequestHandlerMapping();
				}
			}
		}
		return instance;
	}

	/**
	 * 存一个handler对应mapping
	 * @param requestId
	 * @param handler
	 */
	public void addHandler(int requestId, IHandler handler) {
		handler.setRequestID(requestId);
		this.handlers.put(requestId, handler);
	}

	/**
	 * 得到一个handler
	 * @param requestId
	 * @return
	 */
	public IHandler getHandler(int requestId) {
		return handlers.get(requestId);
	}
}
