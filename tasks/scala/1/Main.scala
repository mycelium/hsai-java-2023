package recfun
import common._

object Main {
  def main(args: Array[String]) {
    /** 1 **/
    println("Exercise 1: Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
    /** 2 **/
    println("\nExercise 2: Parentheses Balancing")
    val par1: List[Char] = List('x','(','z','+','k',')','(','a','-','b',')')
    val par2: List[Char] = List('(','(','a','-','z',')','*','k',')','(','a',')')
    val par3: List[Char] = List('(','a',')',')','(','(','b',')')
    for (element <- par1) {
      print(element)
    }
    print(" - ")
    println(balance(par1))
    for (element <- par2) {
      print(element)
    }
    print(" - ")
    println(balance(par2))
    for (element <- par3) {
      print(element)
    }
    print(" - ")
    println(balance(par3))
    /** 3 **/
    println("\nExercise 3: Counting Change")
    val coi1: List[Int] = List(5)
    val coi2: List[Int] = List(5, 2)
    val coi3: List[Int] = List(10, 5, 2, 1)
    print("Change for 15 with ")
    for (element <- coi1) {
      print(element)
      print(" ")
    }
    print(": ")
    print(countChange(15, coi1))
    println(" way(s).")
    print("Change for 7 with ")
    for (element <- coi2) {
      print(element)
      print(" ")
    }
    print(": ")
    print(countChange(7, coi2))
    println(" way(s).")
    print("Change for 15 with ")
    for (element <- coi2) {
      print(element)
      print(" ")
    }
    print(": ")
    print(countChange(15, coi2))
    println(" way(s).")
    print("Change for 21 with ")
    for (element <- coi3) {
      print(element)
      print(" ")
    }
    print(": ")
    print(countChange(21, coi3))
    println(" way(s).")
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    c match {
      case 0 => 1
      case _ => r match {
        case x if (x < c) => 0
        case x if (x == c) => 1
        case _ => pascal(c-1, r-1) + pascal(c, r-1)
      }
    }
  }

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
    def balance_count(chars: List[Char], opened: Int): Boolean = {
      chars match {
        case Nil => opened match {
          case 0 => true
          case _ => false
        }
        case x :: xs => x match {
          case ')' => opened match {
            case 0 => false
            case _ => balance_count(xs, opened - 1)
          }
          case '(' => balance_count(xs, opened + 1)
          case _ => balance_count(xs, opened)
        }
      }
    }

    balance_count(chars, 0)
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomination
   * 2 and 3: 2+3.
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    money match {
      case x if (x < 0) => 0
      case 0 => 1
      case _ => coins match {
        case Nil => 0
        case y :: ys => countChange(money, ys) + countChange(money - y, coins)
      }
    }
  }
}
