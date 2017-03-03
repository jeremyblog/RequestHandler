package netdata;

import org.junit.Assert;
import org.junit.Test;
import org.qiunet.handler.iodata.base.InputByteStream;
import org.qiunet.handler.iodata.IoByteStreamFactory;
import org.qiunet.handler.iodata.base.OutputByteStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * @author qiunet
 *         Created on 17/3/1 21:01.
 */
public class TestIoByteSteam {
	@Test
	public void testIoByteStream(){
		IoByteStreamFactory<DataInputStream, DataOutputStream> factory = IoByteStreamFactory.createDefault();
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
		DataOutputStream dos = new DataOutputStream(bos);
		OutputByteStream obs = factory.getOutputByteStream(dos);
		boolean exception = false;
		try {
			obs.writeByte("byte", Byte.MAX_VALUE);
			obs.writeShort("short", Short.MAX_VALUE);
			obs.writeInt("int", Integer.MAX_VALUE);
			obs.writeLong("long", Long.MAX_VALUE);
			obs.writeFloat("float", 1f);
			obs.writeDouble("double", 1d);
			obs.writeBoolean("bool", true);
			obs.writeString("String", "qiunet");
		} catch (Exception e) {
			exception = true;
		}finally {
			try {
				obs.close();
				bos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Assert.assertFalse(exception);
		
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		DataInputStream dis = new DataInputStream(bis);
		InputByteStream ibs = factory.getInputByteStream(dis);
		
		try {
			byte val = ibs.readByte("byte");
			Assert.assertTrue(Byte.MAX_VALUE == val);

			short s = ibs.readShort("short");
			Assert.assertTrue(Short.MAX_VALUE == s);

			int i = ibs.readInt("int");
			Assert.assertTrue(Integer.MAX_VALUE == i);

			long l = ibs.readLong("long");
			Assert.assertTrue(Long.MAX_VALUE == l);

			float f = ibs.readFloat("float");
			Assert.assertTrue(1f == f);

			double d = ibs.readDouble("double");
			Assert.assertTrue(1d == d);

			boolean bool = ibs.readBoolean("bool");
			Assert.assertTrue(bool);
			
			String string = ibs.readString("string");
			Assert.assertEquals("qiunet", string);
		} catch (Exception e) {
			exception = true;
		}
		Assert.assertFalse(exception);
		
		try {
			ibs.readBoolean("a");
		} catch (Exception e) {
			// 如果抛出异常. 是能捕获到在哪出的. 
			exception = true;
		}
		Assert.assertTrue(exception);
	}
}
