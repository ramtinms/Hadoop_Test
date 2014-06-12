Hadoop_Test
===========

This repository provides codes required for testing hadoop functionality. 

## connecting to head node


```bash
 ssh <user_id>@oak.fas.sfu.ca
 ssh <user_id>@hadoop.rcg.sfu.ca
```

## Working with HDFS

```bash
hadoop fs -<shell_command> 

hadoop fs -ls /user/<user_id>
hadoop fs -copyFromLocal <local_path> <HDFS_path>
hadoop fs -rm <HDFS_path>

```
## Adding Spake module
```
module load NL/HADOOP/SPARK/1.0.0
```

## running code on Spark (Python)

```
pyspark <python_file>
```

## running code on Spark (Scala)
You can use interactive mode by 
```
spark-shell
```

```
val myTextFile = sc.textFile("<local or hadf file>")
myTextFile.count()
myTextFile.first()
val linesWithSpark = myTextFile.filter(line => line.contains("Spark"))

```
## Creating Socket Proxy 
###ssh and direct connect (SOCKS5) :
The following line will start the ssh client and connect to username@remote_machine.com. Port 8080 on localhost (127.0.0.1) will listen for requests and send them to the remote machine. The remote machine will then send the packets out as if they originated from itself. The ssh options are in the man page of ssh, but to summarize them in order: Compression, SSH2 only, Quite, Force pseudo-tty allocation, Redirect stdin from /dev/null, and Place the ssh client into "master" mode for connection sharing.

```
ssh -C2qTnN -D 8080 username@remote_machine.com
```

> https://calomel.org/firefox_ssh_proxy.html
