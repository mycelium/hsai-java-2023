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
      case _ => r match {
        case x if (x == c) => 1
        case _ => pascal(c, r-1) + pascal(c-1, r-1)
      }
  }

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
	def amog_us(chars: List[Char], bracket: Int): Boolean = {
	  chars match {
		case Nil => bracket match {
		  case 0 => true
		  case _ => false
		}
		case x :: xs => x match {
		  case ')' => bracket match {
			case 0 => false
			case _ => amog_us(xs, bracket - 1)
		  }
		  case '(' => amog_us(xs, bracket + 1)
		}
	  }
	}

	amog_us(chars, 0)
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */
  def countChange(money: Int, coins: List[Int]): Int = {
	if (money < 0) return 0
	else if (money == 0) return 1
	
	coins match {
        case Nil => 0
        case y :: ys => countChange(money - y, coins) + countChange(money, ys)
    }
  }
}
