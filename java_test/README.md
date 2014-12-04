# Running on your local machine 

## Loading Stand Alone Hadoop 
You need to load stand alone hadoop module on your machine first.

```module load NL/HADOOP/StandAloneHadoop/2.5.2 ```

## Running the code
```hadoop jar WordCount.jar org.myorg.WordCount sample_files/ <output_path>```

Note: that <output_path> should not exist. 
