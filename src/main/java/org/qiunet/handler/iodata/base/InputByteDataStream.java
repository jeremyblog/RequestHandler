package org.qiunet.handler.iodata.base;

import org.apache.log4j.Logger;

/**
 * 一个外观类. 装饰IoByteStream使用
 * 顺便记录logger日志
 * @author qiunet
 *         Created on 17/3/1 19:39.
 */
public class InputByteDataStream implements InputByteStream {
	private static final Logger logger = Logger.getLogger(InputByteDataStream.class);
	private InputByteStream ioByteStream;
	
	public InputByteDataStream(InputByteStream ioByteStream) {
		this.ioByteStream = ioByteStream;
	}
	
	@Override
	public byte readByte(String desc) throws Exception {
		byte ret = ioByteStream.readByte(desc);
		if (logger.isInfoEnabled()) {
			logger.info("readByte ["+desc+"]: "+ret);
		}
		return ret;
	}

	@Override
	public short readShort(String desc) throws Exception {
		short ret = ioByteStream.readShort(desc);
		if (logger.isInfoEnabled()) {
			logger.info("readShort ["+desc+"]: "+ret);
		}
		return ret;
	}

	@Override
	public int readInt(String desc) throws Exception {
		int ret = ioByteStream.readInt(desc);
		if (logger.isInfoEnabled()) {
			logger.info("readInt ["+desc+"]: "+ret);
		}
		return ret;
	}

	@Override
	public long readLong(String desc) throws Exception {
		long ret = ioByteStream.readLong(desc);
		if (logger.isInfoEnabled()) {
			logger.info("readLong ["+desc+"]: "+ret);
		}
		return ret;
	}

	@Override
	public float readFloat(String desc) throws Exception {
		float ret = ioByteStream.readFloat(desc);
		if (logger.isInfoEnabled()) {
			logger.info("readFloat ["+desc+"]: "+ret);
		}
		return ret;
	}

	@Override
	public double readDouble(String desc) throws Exception {
		double ret = ioByteStream.readDouble(desc);
		if (logger.isInfoEnabled()) {
			logger.info("readDouble ["+desc+"]: "+ret);
		}
		return ret;
	}

	@Override
	public boolean readBoolean(String desc)  throws Exception{
		boolean ret = ioByteStream.readBoolean(desc);
		if (logger.isInfoEnabled()) {
			logger.info("readBoolean ["+desc+"]: "+ret);
		}
		return ret;
	}

	@Override
	public String readString(String desc)  throws Exception {
		String ret = ioByteStream.readString(desc);
		if (logger.isInfoEnabled()) {
			logger.info("readString ["+desc+"]: "+ret);
		}
		return ret;
	}
	@Override
	public void close() throws Exception {
		if (logger.isDebugEnabled()) logger.debug("calling close");
		ioByteStream.close();
	}
}
