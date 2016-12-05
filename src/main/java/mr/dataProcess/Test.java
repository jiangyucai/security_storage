package mr.dataProcess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Test {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		File file = new File("/Users/jiangyc/Downloads/dpi_filename.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String tempString = null;

		while ((tempString = reader.readLine()) != null)
			if (tempString.matches("\\S{0,}-00[1-9]\\d\\d+\\S{0,}"))
				System.out.println(tempString);
	}
}