package recfun

import common._

import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }

    println("------------------------")
    println("Balance Parentheses")
    println(balance("".toList))
    println(balance("((()))".toList))
    println(balance(")()()(".toList))
    println(balance("(1+2+3))(1+2))".toList))

    println("------------------------")
    println("Count Change")
    println(countChange(-10, List(1, 2, 5))) // >>> 0: Отрицательную сумму собрать невозможно
    println(countChange(1, List.empty)) // >>> 0: Нет монет => нет комбинаций
    println(countChange(0, List(1, 2, 5))) // >>> 1: Сумму равную нулю можно собрать из пустой комбинации
    println(countChange(10, List(1, 2, 5)))
  }


  /**
   * Exercise 1
   */
  //assuming that indices will be non-negative
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || r == 0) 1
    else pascal(c - 1, r) + pascal(c, r - 1)
  }

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
    @tailrec
    def balanceParenthesis(chars: List[Char], parCounter: Int = 0): Boolean = {
      if (parCounter < 0) false
      else if (chars.isEmpty) parCounter == 0
      else
        chars.head match {
          case '(' => balanceParenthesis(chars.tail, parCounter + 1)
          case ')' => balanceParenthesis(chars.tail, parCounter - 1)
          case _ => balanceParenthesis(chars.tail, parCounter)
        }
    }

    balanceParenthesis(chars)
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */
  //assume that all coin denominations will be positive
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) 1
    else if (money > 0 && coins.nonEmpty)
      countChange(money, coins.tail) + //do not take this denomination
        countChange(money - coins.head, coins)
    else 0
  }
}
