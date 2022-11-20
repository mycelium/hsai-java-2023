package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }

    val list = List(')','(','(', '(', ')', ')', ')',')')
    println(balance(list))

    val coins = List(1,2,5)
    println(countChange(5, coins))
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (r < 0 || c < 0) 0
    else if (c == 0) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {

    def recFunc(chars: List[Char], sum: Int): Int = {
      if (chars.nonEmpty && sum >= 0) {
        if (chars.head == ')') {
          recFunc(chars.tail, sum - 1)
        } else recFunc(chars.tail, sum + 1)
      } else sum
    }

    recFunc(chars, 0).==(0)
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
    else if (coins.isEmpty) return 0

    countChange(money,coins.take(coins.length - 1)) + countChange(money - coins.last,coins)
  }
}
