package org.qiunet.handler.iodata;

import org.qiunet.handler.iodata.adapter.DataInputStreamAdapter;
import org.qiunet.handler.iodata.adapter.DataOutputStreamAdapter;
import org.qiunet.handler.iodata.adapter.InputByteStreamAdapter;
import org.qiunet.handler.iodata.adapter.OutputByteStreamAdapter;
import org.qiunet.handler.iodata.base.InputByteStream;
import org.qiunet.handler.iodata.base.OutputByteStream;

import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * 创建一个工厂类. 可以默认使用DataInputStream DataOutputStream
 * 也可以自己指定一个Adapter
 * @author qiunet
 *         Created on 17/3/1 20:46.
 */
public final class IoByteStreamFactory<DATAIN, DATAOUT> {
	private InputByteStreamAdapter<DATAIN> inputByteStreamAdapter;
	private OutputByteStreamAdapter<DATAOUT> outputByteStreamAdapter;

	private IoByteStreamFactory(InputByteStreamAdapter<DATAIN> inputByteStreamAdapter, OutputByteStreamAdapter<DATAOUT> outputByteStreamAdapter) {
		this.inputByteStreamAdapter = inputByteStreamAdapter;
		this.outputByteStreamAdapter = outputByteStreamAdapter;
	}
	/**
	 * 返回一个可读取的源	 * InputByteStream
	 * @param t
	 * @return
	 */
	public InputByteStream getInputByteStream(DATAIN t){
		return inputByteStreamAdapter.getInputByteStream(t);
	}
	/**
	 * 返回一个可写入的源	 * OutputByteStream
	 * @param t
	 * @return
	 */
	public OutputByteStream getOutputByteStream(DATAOUT t){
		return outputByteStreamAdapter.getOutputByteStream(t);
	}
	/**
	 * 使用DataINputStream DataOutputStream创建默认factory
	 * @return
	 */
	public static IoByteStreamFactory<DataInputStream, DataOutputStream> createDefault(){
		return create(new DataInputStreamAdapter() , new DataOutputStreamAdapter());
	}

	/**
	 * 指定adapter 创建factory
	 * @param inputByteStreamAdapter
	 * @param outputByteStreamAdapter
	 * @return
	 */
	public static <IN, OUT> IoByteStreamFactory<IN, OUT> create(InputByteStreamAdapter<IN> inputByteStreamAdapter, OutputByteStreamAdapter<OUT> outputByteStreamAdapter){
		return new IoByteStreamFactory(inputByteStreamAdapter, outputByteStreamAdapter);
	}
}
