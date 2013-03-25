package akka.samples.mapreduce.messages

case class Word(word:String, count:Int)
case class MapData(dataList: Array[Word])
case class ReduceData(reduceDataMap: Map[String, Int])
case class Result()
