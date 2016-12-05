package mr.dataProcess;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import encryption.algorithm.BoncAes;

@SuppressWarnings("deprecation")
public class MRdataProcessDecryptdataDesApplication {
	public static String keys = "";

	public static void main(String[] args) {
		try {
			Configuration conf = new Configuration();
			conf.set("mapreduce.input.fileinputformat.input.dir.recursive", "true");
			String key_file = args[1];
			String regs = args[2];
			conf.set("mapred.cache.files", key_file + "," + regs);
			conf.set("mapreduce.input.fileinputformat.input.dir.recursive", "true");
			conf.set("mapreduce.map.output.compress", "false");
			conf.set("mapreduce.output.fileoutputformat.compress", "false");
			Job job = new Job(conf, "MRdataProcessDecryptdataDesApplication");
			job.setJarByClass(MRdataProcessDecryptdataDesApplication.class);
			job.setMapperClass(MyMap.class);
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(NullWritable.class);
			job.setOutputKeyClass(Text.class);
			job.setNumReduceTasks(0);
			job.setOutputValueClass(NullWritable.class);
			String toPath = args[3];
			FileInputFormat.addInputPath(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(toPath));
			System.exit(job.waitForCompletion(true) ? 0 : 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static class MyMap extends Mapper<Object, Text, Text, NullWritable> {
		String Mobile_regx = "5,4,*";
		String line;
		String uniqueness;
		String uniqueness_decrypt;
		int Mobile_regx_start = Integer.parseInt(this.Mobile_regx.split("\\,")[0]);
		int Mobile_regx_end = Integer.parseInt(this.Mobile_regx.split("\\,")[1]);
		String Mobile_regx_symbol = this.Mobile_regx.split("\\,")[2];

		protected void setup(Mapper<Object, Text, Text, NullWritable>.Context context)
				throws IOException, InterruptedException {
			Path[] localCacheFiles = DistributedCache.getLocalCacheFiles(context.getConfiguration());
			Properties props_keys = new Properties();
			Path KeysPath = localCacheFiles[0];
			String keys_file = KeysPath.toString();
			FileInputStream fileInputStream_key = new FileInputStream(new File(keys_file));
			props_keys.load(fileInputStream_key);
			MRdataProcessDecryptdataDesApplication.keys = (String) props_keys.get("Aes_key");
			Properties props1 = new Properties();
			Path proPath1 = localCacheFiles[1];
			String proFile1 = proPath1.toString();
			FileInputStream fileInputStream1 = new FileInputStream(new File(proFile1));
			props1.load(fileInputStream1);

			this.Mobile_regx = ((String) props1.get("Mobile_regx"));
		}

		protected void map(Object key, Text value, Mapper<Object, Text, Text, NullWritable>.Context context) {
			try {
				this.line = value.toString();
				String[] split = this.line.split("\001");

				this.uniqueness = split[0];
				this.uniqueness_decrypt = this.uniqueness;

				this.uniqueness = BoncAes.decryptdataDes(this.uniqueness, MRdataProcessDecryptdataDesApplication.keys);

				if ((!"".equals(this.uniqueness)) && (this.uniqueness != null)) {
					this.uniqueness = SensitiveInfoUtils.mobilePhone(this.uniqueness, this.Mobile_regx_start,
							this.Mobile_regx_end, this.Mobile_regx_symbol);
					this.line = this.line.replace(this.uniqueness_decrypt, this.uniqueness);
				}
				context.write(new Text(this.line), NullWritable.get());
			} catch (Exception localException) {
			}
		}
	}
}
