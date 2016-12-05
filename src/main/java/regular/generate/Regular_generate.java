package regular.generate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Regular_generate {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		File file = new File("/Users/jiangyc/Downloads/ftp_files_list");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String tempString = null;

		String file_name = "";
		long total_size = 0L;

		while ((tempString = reader.readLine()) != null)
			if (!"".equals(tempString.trim())) {
				String[] line = tempString.split("\\|");
				file_name = line[0];
				long file_size = Long.parseLong(line[1]);
				total_size += file_size;
			}
		long average_size = total_size / 8L;
		System.out.println(average_size);
		long current_file_size = 0L;
		long file_size = 0L;
		BufferedReader reader1 = new BufferedReader(new FileReader(file));
		while ((tempString = reader1.readLine()) != null)
			if (!"".equals(tempString.trim())) {
				String[] line = tempString.split("\\|");
				file_name = line[0];
				current_file_size = Long.parseLong(line[1]);
				if (file_size < average_size) {
					file_size += current_file_size;
				} else {
					System.out.println(file_name);
					file_size = 0L;
				}
			}
		System.out.println(file_name);
		reader.close();
	}
}