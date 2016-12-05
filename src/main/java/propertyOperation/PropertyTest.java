package propertyOperation;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyTest {
	public static void main(String[] args) {
		Properties prop = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					"/Users/jiangyc/Documents/workspace/storageSecurity/src/main/java/storageSecurity/keysFile.properties"));
			prop.load(in);
			String[] key = prop.getProperty("aeskey").split("\\,");
			String keyFirst = key[0].replaceAll(" ", "");
			String keyLast = key[(key.length - 1)].replaceAll(" ", "");
			byte[] aeskey = new byte[key.length];
			aeskey[0] = Byte.parseByte(keyFirst.substring(1, keyFirst.length()));
			aeskey[(key.length - 1)] = Byte.parseByte(keyLast.substring(0, keyLast.length() - 1));
			for (int i = 1; i < key.length - 1; i++) {
				aeskey[i] = Byte.parseByte(key[i].replaceAll(" ", ""));
			}
			for (int i = 0; i < key.length; i++)
				System.out.print(aeskey[i] + ",");
			System.out.println();

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}