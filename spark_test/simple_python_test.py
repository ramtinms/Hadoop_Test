from pyspark import SparkContext
sc = SparkContext("local","Simple App")

logFile="/cs/natlang-projects/users/ramtin/spark-0.9.0-incubating-bin-hadoop2/README.md"

logData = sc.textFile(logFile).cache()

numAs = logData.filter(lambda s: 'a' in s).count()
numBs = logData.filter(lambda s: 'b' in s).count()

print "Lines with a: %i, lines with b: %i" % (numAs,numBs)
