package org.qiunet.handler.iodata.net;

import org.qiunet.handler.iodata.base.InputByteStream;
import org.qiunet.handler.iodata.base.OutputByteStream;
import org.qiunet.utils.data.StringData;

/**
 * @author qiunet
 *         Created on 17/3/3 14:53.
 */
abstract class BaseIoData<HEADER extends IoData, COMMON extends IoData>  implements IoData {
	private HEADER header;
	private COMMON common;
	BaseIoData(){
		try {
			header = getHeaderClass().newInstance();
			common = getCommonClass().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 得到Header 的class
	 * @return
	 */
	protected abstract Class<HEADER> getHeaderClass();
	/**
	 * 得到 Common 类的class
	 * @return
	 */
	protected abstract Class<COMMON> getCommonClass();
	
	@Override
	public void dataReader(InputByteStream in) throws Exception {
		header.dataReader(in);
		common.dataReader(in);
		dataReaders(in);
	}

	@Override
	public void dataWriter(OutputByteStream out) throws Exception {
		header.dataWriter(out);
		common.dataWriter(out);
		dataWriters(out);
	}
	/**
	 * 子类的读取
	 * @param in
	 * @throws Exception
	 */
	protected abstract void dataReaders(InputByteStream in) throws Exception ;

	/**
	 *  子类的写入
	 * @param out
	 * @throws Exception
	 */
	protected abstract void dataWriters(OutputByteStream out) throws Exception ;
	/**
	 *  toString
	 * @return
	 */
	@Override
	public String toString(){
		return StringData.parseString(this);
	}
}
