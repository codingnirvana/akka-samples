package akka.samples.mapreduce.actors

import akka.actor.Actor
import akka.samples.mapreduce.messages.{Result, ReduceData}
import collection.mutable

class AggregateActor extends Actor {

  var finalReducedMap = new mutable.HashMap[String, Int]

  def receive: Receive = {
    case data: ReduceData => aggregateInMemoryReduce(data.reduceDataMap)
    case result: Result => System.out.println(finalReducedMap)
  }

  def aggregateInMemoryReduce(reducedList: Map[String, Int]) {
    reducedList.foreach {
        case(k,v)  => finalReducedMap.put(k, finalReducedMap.getOrElse(k, 0)  + reducedList(k))
    }
  }
}
