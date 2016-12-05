package normal.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MRTools {
	static String toPath = new SimpleDateFormat("YYYYMMddHHmmss").format(new Date());
	static String fileName = "/Users/jiangyc/Documents/workspace/storageSecurity/logs/" + toPath;

	public MRTools() throws IOException {
		File file = new File(fileName);
		if (!file.exists())
			file.createNewFile();
	}

	public static String getToPath() {
		return toPath;
	}

	public static void Print(String str) throws IOException {
		FileWriter fw = new FileWriter(fileName, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("-------------------------------------------\n");
		bw.write(str + "\n");
		bw.close();
		fw.close();
	}
}