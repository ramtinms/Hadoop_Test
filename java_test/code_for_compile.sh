#!/bin/sh


# Author : Ramtin 


javac -cp `hadoop classpath` -d wordcount_classes WordCount.java
jar cvf WordCount.jar -C wordcount_classes/ .

