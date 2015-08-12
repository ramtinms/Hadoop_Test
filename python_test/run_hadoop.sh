#!/bin/bash

##### Streaming hadoop library
jar_file=/usr/lib/hadoop-mapreduce/hadoop-streaming.jar

#####  input information
path_hdfs=/user/rmehdiza/python_test
path_local=`pwd`
ngram=1

hadoop fs -mkdir $path_hdfs
countFreq()
{

        mapper=phraseCount-mapper.py
        reducer=phraseCount-reducer.py
        name=phraseCount
        mapper_tasks=18
        reducer_tasks=18

	echo $path_hdfs
        # loading input
	hadoop fs -mkdir /user/rmehdiza/python_test/input
        hadoop fs -copyFromLocal monotext-200.en $path_hdfs

        input=$path_hdfs/monotext-200.en
        output=$path_hdfs/wordFreq$ngram_str.en

        hadoop fs -rm -r $output
        hadoop jar $jar_file -D mapred.job.name=$name -D mapred.map.tasks=$mapper_tasks -D mapred.reduce.tasks=$reducer_tasks -mapper "\"$mapper $ngram\"" -file $mapper -reducer $reducer -file $reducer -input $input -output $output
        if [ ! $? -eq 0 ]; then exit $?; fi

        hadoop fs -getmerge "$output/part*" $path_local/output
}

countFreq
