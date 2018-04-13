package eng.shankar.basic

import java.io.InputStream

import scala.io.Source
import scala.collection.mutable.Map

// Copyright (c) 2018 Travelex Ltd

class Anagrams {

  def detect = {
    //read word as string from file, store in to a list
    val stream: InputStream = getClass.getResourceAsStream("/anagramsSmall")
    val words = Source.fromInputStream(stream).getLines

    //for-each word, re-arrange by asc order in a map
    var wordMap = Map[String, String]()
    for (word <- words) {
      var splicedWord = word.toCharArray.sorted
      if (wordMap contains splicedWord.mkString) {
        wordMap += (splicedWord.mkString -> wordMap(splicedWord.mkString).mkString.concat(",").concat(word))
      }
      else
        wordMap += (splicedWord.mkString -> word)
    }

    //match all values in the map which are equal, print their keys side by side
    println("cnt = " + wordMap.valuesIterator.count(_.contains(",")))
    wordMap.valuesIterator.filter(_.contains(",")).foreach(println)
  }
}

object Anagrams {
  def main(args: Array[String]): Unit = {
    val ana = new Anagrams
    ana.detect
  }
}

object AnagramsAgain {
  def main(args: Array[String]): Unit = {
    val wordList = Source.fromInputStream(getClass.getResourceAsStream("/anagrams")).getLines().toList
    val wordMap = wordList map (word => word.toCharArray.sorted.mkString -> word) groupBy (_._1)
    val wordMapBySize = wordMap mapValues(_.size)
    val anagrams = wordMapBySize filter (_._2 > 1)
    val maxAnagramsFor = anagrams.maxBy(_._2)
    val longest = anagrams.reduceLeft((x, y) => if (x._1.length > y._1.length) x else y)

    println("Total number of anagrams = ")
    println(anagrams.size)
    println()
    println("The anagrams with most repetitions = ")
    println (wordMap(maxAnagramsFor._1) map (t => t._2))
    println()
    println("Longest anagrams = ")
    println(wordMap(longest._1) map (t => t._2))
    println()
    println("Anagrams = ")
    anagrams.keysIterator.foreach(anagram => println(wordMap(anagram) map (t => t._2)))
  }
}

object JustAnagrams {
  def main(args: Array[String]): Unit = {
    val wordMap = Source.fromInputStream(
      getClass.getResourceAsStream("/anagrams"))
      //make a list of words
      //List(brace, caber)
      .getLines().toList
      //map sorted word (by char) to actual word
      //Map(acerb -> brace, acerb -> caber)
      .map(word => word.toCharArray.sorted.mkString -> word)
      //group by sorted word, this will produce a
      //map of sorted word mapped to
      // list of tuples with (sorted word, anagram)
      //Map(acerb -> List((acerb, brace), (acerb, caber)
      .groupBy(_._1)
    //covert the grouped map to sorted word and size of list
    // and filter those which have size>1 (anagrams)
    //Map(acerb -> 2)
    val anagrams = wordMap mapValues (_.size) filter (_._2 > 1)
    println("Total number of anagrams = " + anagrams.size)
    println("Anagrams = ")
    anagrams.keysIterator
      .foreach(anagram => println(wordMap(anagram) map (_._2)))
  }
}
