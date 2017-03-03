package org.qiunet.handler.iodata.adapter;

import org.qiunet.handler.iodata.base.OutputByteStream;

/**
 * @author qiunet
 *         Created on 17/3/1 19:58.
 */
public interface OutputByteStreamAdapter<T> {
	/**
	 * 返回字节流处理类.
	 * @param dataOut
	 * @return
	 */
	public abstract OutputByteStream getOutputByteStream(T dataOut); 
}
