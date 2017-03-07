package org.qiunet.handler.iodata.net;

import org.qiunet.handler.iodata.base.InputByteStream;
import org.qiunet.handler.iodata.base.OutputByteStream;
import org.qiunet.utils.data.StringData;

/**
 *  作为responsedata 和 requestData的父类. 设置通用的方法. 但是不对外开放. 仅本目录可见
 * @author qiunet
 *         Created on 17/3/3 14:53.
 */
abstract class BaseIoData<HEADER extends IoData, COMMON extends IoData>  implements IoData {
	private HEADER header;
	private COMMON common;
	BaseIoData(HEADER header, COMMON common){
		header = header;
		common = common;
	}
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

	/***
	 * 得到header
 	 * @return
	 */
	public HEADER getHeader(){
		return header;
	}
	/**
	 * 得到common
	 * @return
	 */
	public COMMON getCommon(){
		return common;
	}
	/**
	 *  toString
	 * @return
	 */
	@Override
	public String toString(){
		return StringData.parseString(this);
	}
}
