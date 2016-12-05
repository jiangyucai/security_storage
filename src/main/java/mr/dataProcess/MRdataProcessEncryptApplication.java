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

import encryption.algorithm.BoncSHA;

@SuppressWarnings("deprecation")
public class MRdataProcessEncryptApplication {
	public static void main(String[] args) {
		try {
			Configuration conf = new Configuration();

			conf.set("mapreduce.input.fileinputformat.input.dir.recursive", "true");

			String keys = args[1];
			conf.set("mapred.cache.files", keys);
			conf.set("mapreduce.input.fileinputformat.input.dir.recursive", "true");
			conf.set("mapreduce.map.output.compress", "false");
			conf.set("mapreduce.output.fileoutputformat.compress", "false");
			Job job = new Job(conf, "MRdataProcessEncryptApplication");
			job.setJarByClass(MRdataProcessEncryptApplication.class);
			job.setMapperClass(MyMap.class);

			job.setMapOutputKeyClass(NullWritable.class);
			job.setMapOutputValueClass(Text.class);
			job.setOutputKeyClass(Text.class);
			job.setNumReduceTasks(0);
			job.setOutputValueClass(NullWritable.class);
			String toPath = args[2];
			FileInputFormat.addInputPath(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(toPath));
			System.exit(job.waitForCompletion(true) ? 0 : 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static class MyMap extends Mapper<Object, Text, Text, NullWritable> {
		public String keys;

		protected void setup(Mapper<Object, Text, Text, NullWritable>.Context context)
				throws IOException, InterruptedException {
			Path[] localCacheFiles = DistributedCache.getLocalCacheFiles(context.getConfiguration());
			Properties props_keys = new Properties();
			Path KeysPath = localCacheFiles[0];
			String keys_file = KeysPath.toString();
			FileInputStream fileInputStream_key = new FileInputStream(new File(keys_file));
			props_keys.load(fileInputStream_key);
			this.keys = ((String) props_keys.get("Aes_key"));
		}

		protected void map(Object key, Text value, Mapper<Object, Text, Text, NullWritable>.Context context) {
			try {
				String line = value.toString();

				String firstline_drt = line.split("\001")[0];
				String firstline = BoncSHA.SHA_Encrypt(firstline_drt);
				line = line.replace(firstline_drt, firstline);

				context.write(new Text(line), NullWritable.get());
			} catch (Exception localException) {
			}
		}
	}
}