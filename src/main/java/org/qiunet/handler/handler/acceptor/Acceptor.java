package org.qiunet.handler.handler.acceptor;

import org.qiunet.handler.handler.IHandler;
import org.qiunet.handler.handler.intecepter.Intercepter;
import org.qiunet.handler.iodata.net.AbstractRequestData;
import org.qiunet.utils.exceptions.SingletonException;
import org.qiunet.utils.threadLocal.ThreadContextData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 接受线程请求的地方. 
 * @author qiunet
 *         Created on 17/3/6 18:44.
 */
public class Acceptor {
	private volatile static Acceptor instance;
	private Intercepter intercepter;
	private Acceptor() {
		if (instance != null)  throw new SingletonException("Duplicate Call Constructor!");
		instance = this;
	}

	public static Acceptor getInstance() {
		if (instance == null) {
			synchronized (Acceptor.class) {
				if (instance == null)
				{
					new Acceptor();
				}
			}
		}
		return instance;
	}

	/***
	 * 添加拦截器对象
	 * @param intercepter
	 */
	public void setIntercepter(Intercepter intercepter) {
		this.intercepter = intercepter;
	}

	/**
	 * 处理请求
	 * @param requestData
	 */
	public void accept(IHandler handler, AbstractRequestData requestData){
		if (intercepter == null) {
			throw new NullPointerException("Need  set intercepter");
		}
		try{
			Object logicData = intercepter.preHandler(requestData);
			intercepter.handler(handler, requestData, logicData);
			intercepter.postHandler(logicData);
		}catch (Exception e) {
			// 将异常交给 intercepter 处理
			intercepter.throwCause(e);
		}finally {
			/**清理所有的本地线程数据*/
			ThreadContextData.removeAll();
		}
	}
}
