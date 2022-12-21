package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }

    println("Parentheses Balancing")
    println(balance("()[]{}".toList)) // Output: true
    println(balance("({)}".toList)) // Output: false

    println("Counting Change")
    println(countChange(5, List(2,3)))
    println(countChange(5, List(1,2,3)))
  }

  //  Exercise 1 Pascal's Triangle

  def pascal(c: Int, r: Int): Int = {
    if (c==0 || c==r) 1
    else pascal(c-1, r-1) + pascal(c, r-1)
  }

  // Exercise 2 Parentheses Balancing

  def balance(chars: List[Char]): Boolean = {
    def check(chars: List[Char], stack: List[Char]): Boolean = {
      if (chars.isEmpty) {
        stack.isEmpty
      } else if (chars.head == '(' || chars.head == '[' || chars.head == '{')
      {
        check(chars.tail, chars.head :: stack)
      }
      else if (stack.nonEmpty &&
        (chars.head == ')' && stack.head == '(' ||
          chars.head == ']' && stack.head == '[' ||
          chars.head == '}' && stack.head == '{')) {
        check(chars.tail, stack.tail)
      } else {
        false
      }
    }

    check(chars, List())
  }

   // Exercise 3 Counting Change

   def countChange(money: Int, coins: List[Int]): Int = {
     if (money == 0) {
       // There is only 1 way to make change for 0 - by not using any coins
       return 1
     }
     if (money < 0 || coins.isEmpty) {
       // There are 0 ways to make change for a negative money or if there are no coins left
       return 0
     }
     val withoutFirstDenomination = countChange(money, coins.tail)
     val withFirstDenomination = countChange(money - coins.head, coins)

     withoutFirstDenomination + withFirstDenomination
   }

}
