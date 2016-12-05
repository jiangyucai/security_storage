package mr.dataProcess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TestReg {
	@SuppressWarnings({ "resource", "unused" })
	public static void main(String[] args) throws Exception {
		File file = new File("/Users/jiangyc/Downloads/ftp_files_list");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String tempString = null;

		String file_name = "";
		long total_size = 0L;

		while ((tempString = reader.readLine()) != null)
			if (!"".equals(tempString.trim())) {
				String[] line = tempString.split("\\|");

				System.out.println(line.length);
			}
	}
}
