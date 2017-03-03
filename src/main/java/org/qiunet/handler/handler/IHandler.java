package org.qiunet.handler.handler;

import org.qiunet.handler.iodata.net.AbstractRequestData;

/**
 * 游戏的逻辑处理handler
 * handler是单例实现, 不要在类里面做全局数据赋值等
 * @author qiunet
 *         Created on 17/3/3 11:56.
 */
public interface IHandler<RequestData extends AbstractRequestData> {
	/**
	 * 得到 RequestID
	 * @return
	 */
	public int getRequestID();
	/**
	 * 设置id
	 * @param requestid
	 * @return
	 */
	public int setRequestID(int requestid);
	/**
	 * 是否是正式action. 如果false的话. 不允许生产环境调用action
	 * @return
	 */
	public boolean offical();
	/**
	 * 忽略服务器维护标记.
	 * @return
	 */
	public boolean ignoreMaintain();
	/**
	 * 必须要sid 才能进入action
	 * @return
	 */
	public boolean needSid();
	/**
	 * 得到requestData的class 先行解析
	 * @return
	 */
	public Class<RequestData> getRequestDataClass();
}
