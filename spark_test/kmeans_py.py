from pyspark import SparkContext
from pyspark.mllib.clustering import KMeans
from numpy import array
from numpy import sqrt

sc = SparkContext("local","Simple App")

# load and parse the data 
data = sc.textFile("kmeans_data.txt")

parsedData = data.map(lambda line: array([float(x) for x in line.split(' ')])).cache()

clusters= KMeans.train(parsedData,2, maxIterations = 10 , runs =1)

def error(point):
    center = clusters.centers[clusters.predict(point)]
    return sqrt(sum([x**2 for x in (point - center)]))

cost = parsedData.map(lambda point: error(point)).reduce(lambda x,y : x+y)

print("sum of squared error = "+str(cost))

for center in clusters.centers:
    print center


