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

    println("Parentheses Balancing")
    if (balance("(a))(2))((")) println("Balanced")
    else println("Unbalanced")

    println("Counting Change")
    println(countChange(18, [1,2,5,10]))
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) 1
    else pascal(c, r - 1) + pascal(c - 1, r - 1)
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
      }
    }
    counts(chars.filter(x => x.equals('(') || x => x.equals(')')), 0)
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (coins.isEmpty) 0
    else {
      if (money < 0) 0
      else if (money == 0) 1
      else countChange(money - coins.head, coins) + countChange(money, coins.tail)
    }
  }
}
