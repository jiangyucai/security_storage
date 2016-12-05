package encryption.algorithm;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;

public class BoncAes {
	private static byte[] keyBytes;

	public static String encryptDataDes(String data, String password) {
		keyBytes = password.getBytes();

		Key key = new SecretKeySpec(keyBytes, "AES");

		byte[] result = new byte[0];
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(1, key);
			result = cipher.doFinal(data.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Hex.encodeHexString(result);
	}

	public static String decryptdataDes(String cryptData, String password) {
		keyBytes = password.getBytes();
		Key key = new SecretKeySpec(keyBytes, "AES");

		byte[] result = new byte[0];
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

			cipher.init(2, key);
			result = cipher.doFinal(Hex.decodeHex(cryptData.toCharArray()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(result);
	}
}
