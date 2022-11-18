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
  def pascal(c: Int, r: Int): Int = c match {
    case 0 => 1
    case x if x == r => 1
    case _ => pascal(c, r - 1) + pascal(c - 1, r - 1)
  }

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
    val closeIndex: Int = chars.indexOf(')')
    val openIndex:Boolean = closeIndex > 0
    if (chars.isEmpty)
      true
    else if (closeIndex != -1 & openIndex)
      balance(chars.slice(0, closeIndex - 1) ++ chars.slice(closeIndex + 1, chars.length))
    else
      false
  }


  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    val counts: List[Int] = coins.map(cur => money - cur match {
      case 0 => 1
      case x if x > 0 => countChange(x, coins.slice(coins.indexOf(cur),coins.length))
      case x if x < 0 => 0
    })

    counts.reduce(_ + _)
  }
}
