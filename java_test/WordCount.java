package org.myorg;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;

public class WordCount {
	// Mapper Class
	public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
		// IntWritable for values
		private final static IntWritable one = new IntWritable(1);

		// Text class for keys
		private Text word = new Text();
		// Map function format : (key , value ,output of mapper format, reporter)
		public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
			// Map funtion
			String line = value.toString();
			StringTokenizer tokenizer = new StringTokenizer(line);
			while (tokenizer.hasMoreTokens()) {
				word.set(tokenizer.nextToken());
				output.collect(word, one);
			}
		}
	}	

	// Reducer Class
	public static class Reduce extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
		// Note to Iterator for values
		public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
			int sum = 0;
			while (values.hasNext()) {
				sum += values.next().get();
			}
			output.collect(key, new IntWritable(sum));
		}
	}	

	// Main 
	public static void main(String[] args) throws Exception {

		JobConf conf = new JobConf(WordCount.class);
		//// Configuration of jobs
		// Job Name 
		conf.setJobName("wordcount");

		// Key and Value format defined in Map and Reduce class
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);

		// Set Mapper , Combiner , Reducer
		conf.setMapperClass(Map.class);
		conf.setCombinerClass(Reduce.class);
		conf.setReducerClass(Reduce.class);
		
		// Input and Output format of the task
		conf.setInputFormat(TextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);

		// Reading hdfs path (input, output) from args
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));

		// RUN !!!!!
		JobClient.runJob(conf);
	}
}
