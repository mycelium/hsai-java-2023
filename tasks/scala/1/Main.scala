package recfun

import common._

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
      case 0 => 1
      case x if (c == r) => 1
      case _ => pascal(c - 1, r - 1) + pascal(c, r - 1)
    }
  }

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
    def counts(chars: List[Char], count: Int): Boolean = {
      if (count < 0) false
      else if (chars.isEmpty) true
      else chars.head match {
        case ')' => counts(chars.tail, count - 1)
        case '(' => counts(chars.tail, count + 1)
        case _ => counts(chars.tail, count)
      }
    }

    counts(chars, 0)
  }

  println("Parentheses Balancing")
  println(balance(List('(', ')')))
  println(balance(List('(', ')', ')', '(', '(', ')')))
  println(balance(List('(', '(', ')', '(', ')', ')')))


  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    money match {
      case 0 => 1
      case negative if (money < 0) => 0
      case empty if coins.isEmpty => 0
      case _ => countChange(money, coins.tail) + countChange(money - coins.head, coins)
    }
  }

  println("Counting Change")
  println(countChange(0, List(1, 2)) == 1)
  println(countChange(0, List()) == 1)
  println(countChange(4, List()) == 0)
  println(countChange(5, List(2, 3)) == 1)
  println(countChange(4, List(1, 2)) == 3)
}
