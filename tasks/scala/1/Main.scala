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
      if (c == 0 || c == r) 1
      else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
      chars.filter(char => char.equals('(') || char.equals(')')).foldLeft(0)((sum, par) => par match {
          case '(' => sum + 1
          case ')' => if (sum > 0) sum - 1 else return false
          case _ => sum
      }) == 0
      // is using foldLeft()() considered as using a recursion
      // for this task's purpose?
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */
  def countChange(money: Int, coins: List[Int]): Int = {
      coins match {
          case Nil => 0
          case _ => {
              if (money < 0) 0
              else if (money == 0) 1
              else countChange(money - coins.head, coins) + countChange(money, coins.tail)
          }
      }
  }
}
