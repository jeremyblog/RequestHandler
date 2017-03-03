package org.qiunet.handler.iodata.adapter;

import io.netty.buffer.ByteBuf;
import org.qiunet.handler.iodata.base.InputByteStream;

/**
 * @author qiunet
 *         Created on 17/3/2 15:59.
 */
public class ByteBufInputStreamAdapter implements InputByteStreamAdapter<ByteBuf> {
	@Override
	public InputByteStream getInputByteStream(ByteBuf dataInput) {
		return null;
	}
	private class ByteDataInputStream implements InputByteStream{
		private ByteBuf byteBuf;
		
		@Override
		public byte readByte(String desc) throws Exception {
			return byteBuf.getByte(0);
		}

		@Override
		public short readShort(String desc) throws Exception {
			return 0;
		}

		@Override
		public int readInt(String desc) throws Exception {
			return 0;
		}

		@Override
		public long readLong(String desc) throws Exception {
			return 0;
		}

		@Override
		public float readFloat(String desc) throws Exception {
			return 0;
		}

		@Override
		public double readDouble(String desc) throws Exception {
			return 0;
		}

		@Override
		public boolean readBoolean(String desc) throws Exception {
			return false;
		}

		@Override
		public String readString(String desc) throws Exception {
			return null;
		}

		@Override
		public void close() throws Exception {

		}
	}
}
