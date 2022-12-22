package recfun
import common._

import scala.collection.immutable.Range

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    c match {
      case negative if (r < 0 || c < 0) => 0
      case 0 => 1
      case _ => pascal(c - 1, r - 1) + pascal(c, r - 1)
    }
  }

  println("Test for parentheses balancing")
  println(pascal(1, 1))
  println(pascal(2, 4))

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
    def count(chars: List[Char], countOpen: Int): Boolean = {
      if (countOpen < 0) false
      else if (chars.isEmpty) {
        countOpen == 0
        true
      }
      else {
        if (chars.head == '(') count(chars.tail, countOpen + 1)
        else if (chars.head == ')') count(chars.tail, countOpen - 1)
        else count(chars.tail, countOpen)
      }
    }
    count(chars, 0)
  }

  println("Test for parentheses balancing")
  println(balance(List('(', ')', ')', '(', '(', ')')))
  println(balance(List('(', '(', ')', ')')))
  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) return 1
    if ((money < 0) || (coins.isEmpty && money > 0)) return 0
    countChange(money, coins.take(coins.length - 1)) + countChange(money - coins.last, coins)
  }

  println("Test for counting change")
  println(countChange(0, List(1, 2)) == 1)
}
