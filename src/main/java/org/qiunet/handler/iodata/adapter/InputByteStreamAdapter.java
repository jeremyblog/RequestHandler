package org.qiunet.handler.iodata.adapter;

import org.qiunet.handler.iodata.base.InputByteStream;

/**
 * @author qiunet
 *         Created on 17/3/1 19:58.
 */
public interface InputByteStreamAdapter<T> {
	/**
	 * 返回字节流处理类.
	 * @param dataInput
	 * @return
	 */
	public abstract InputByteStream getInputByteStream(T dataInput); 
}
