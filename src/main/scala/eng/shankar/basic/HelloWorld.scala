// Copyright (c) 2018 Travelex Ltd

package eng.shankar.basic

import java.util.{Date, Locale}
import java.text.DateFormat._
import collection.mutable

object HelloWorld {
  def main(args: Array[String]){
    hello
    jdate
    sum(List.range(1,10))
    sum(mutable.MutableList(1,3,5))
    pyramid
  }

  private def hello = {
    println("Hello World!!")
  }

  private def jdate = {
    val now = new Date
    val df = getDateInstance(LONG, Locale.US)
    println(df format now)
  }

  private def pyramid = {
    var arr = Array("1", "2", "3", "4", "5", "6", "7", "8", "9")
    var cnt = 0
    for (elem <- arr) {
      arr.foreach(print)
      arr(cnt) = " "
      arr(8 - cnt) = " "
      cnt += 1
      println()
    }
  }

  private def sum(list: List[Int]) = {
    println(list.reduce(_ + _))
  }

  private def sum(list: mutable.MutableList[Int]) = {
    println(list.reduce(_ + _))
  }
}
