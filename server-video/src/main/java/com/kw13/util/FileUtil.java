/**
 * 
 */
package com.kw13.util;


import java.io.*;

/**
 * @author Richard Huo 2011-11-11
 * 
 */
public class FileUtil {

	public static byte[] loadFile(String path) {
		return loadFile(new File(path));
	}

	public static byte[] loadFile(File file) {
		// ByteArrayInputStream in=new ByteArrayInputStream();
		byte[] abyte1 = null;
		try {
			// File file = new File(path);
			if (!file.exists())
				return null;
			abyte1 = loadFile(new FileInputStream(file));
		} catch (Exception e) {
			return null;
		}
		return abyte1;
	}
	
	public static byte[] loadResource(String path)
	{
		byte[] abyte1 = null;
		try {
			InputStream tmpIn = FileUtil.class.getResourceAsStream(path);
			if(tmpIn != null)
				abyte1 = loadFile(tmpIn);
			
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
		}
		return abyte1;
	}
	public static byte[] loadFile(InputStream in) {
		// ByteArrayInputStream in=new ByteArrayInputStream();
		byte[] abyte1 = null;
		try {
			byte abyte0[] = new byte[2048];
			DataInputStream inputstream = new DataInputStream(in);
			ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
			for (int i1 = 0; (i1 = inputstream.read(abyte0)) != -1;)
				bytearrayoutputstream.write(abyte0, 0, i1);

			inputstream.close();
			inputstream = null;
			abyte0 = null;
			abyte1 = bytearrayoutputstream.toByteArray();
			bytearrayoutputstream.close();
			bytearrayoutputstream = null;
		} catch (Exception e) {
			return null;
		}
		return abyte1;
	}

}
