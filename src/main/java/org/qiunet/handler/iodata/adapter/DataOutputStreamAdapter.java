package org.qiunet.handler.iodata.adapter;

import org.qiunet.handler.iodata.base.OutputByteDataStream;
import org.qiunet.handler.iodata.base.OutputByteStream;

import java.io.DataOutputStream;

/**
 * @author qiunet
 *         Created on 17/3/1 20:37.
 */
public class DataOutputStreamAdapter implements OutputByteStreamAdapter<DataOutputStream> {
	@Override
	public OutputByteStream getOutputByteStream(DataOutputStream dataOut) {
		return new OutputByteDataStream(new ByteDataOutputStream(dataOut));
	}
	
	private class ByteDataOutputStream implements OutputByteStream {
		private DataOutputStream dos;
		ByteDataOutputStream (DataOutputStream dos) {
			this.dos = dos;
		}
		@Override
		public void writeByte(String desc, byte val) throws Exception {
			dos.writeByte(val);
		}

		@Override
		public void writeShort(String desc, short val) throws Exception {
			dos.writeShort(val);
		}

		@Override
		public void writeInt(String desc, int val) throws Exception {
			dos.writeInt(val);
		}

		@Override
		public void writeLong(String desc, long val) throws Exception {
			dos.writeLong(val);
		}

		@Override
		public void writeFloat(String desc, float val) throws Exception {
			dos.writeFloat(val);
		}

		@Override
		public void writeDouble(String desc, double val) throws Exception {
			dos.writeDouble(val);
		}

		@Override
		public void writeBoolean(String desc, boolean bool) throws Exception {
			dos.writeBoolean(bool);
		}

		@Override
		public void writeString(String desc, String string) throws Exception {
			dos.writeUTF(string);
		}

		@Override
		public void writeBytes(byte[] bytes) throws Exception {
			dos.write(bytes);
		}

		@Override
		public void close() throws Exception{
			dos.close();
		}
	}
}
