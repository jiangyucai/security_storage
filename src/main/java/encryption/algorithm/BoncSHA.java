package encryption.algorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;

public class BoncSHA {
	public static String SHA_Encrypt(String src) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA");
			md.update(src.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return Hex.encodeHexString(md.digest());
	}
}
