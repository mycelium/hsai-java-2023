package funsets

import common._

/**
 * 2. Purely Functional Sets.
 */
object FunSets {
  /**
   * We represent a set by its characteristic function, i.e.
   * its `contains` predicate.
   */
  type MySet = Int => Boolean

  /**
   * Indicates whether a set contains a given element.
   */
  def contains(s: MySet, elem: Int): Boolean = s(elem)

  /**
   * Returns the set of the one given element.
   */
    def singletonSet(elem: Int): MySet = k => k == elem


  /**
   * Returns the union of the two given sets,
   * the sets of all elements that are in either `s` or `t`.
   */
    def union(s: MySet, t: MySet): MySet = k => t match {
      case Nil => s(k)
      case _ => s(k) | t(k)
    }

  /**
   * Returns the intersection of the two given sets,
   * the set of all elements that are both in `s` and `t`.
   */
    def intersect(s: MySet, t: MySet): MySet = k => t match {
      case Nil => s(k)
      case _ => s(k) & t(k)
    }

  /**
   * Returns the difference of the two given sets,
   * the set of all elements of `s` that are not in `t`.
   */
    def diff(s: MySet, t: MySet): MySet = k => t match {
      case Nil => s(k)
      case _ => s(k) & !t(k)
    }

  /**
   * Returns the subset of `s` for which `p` holds.
   */
    def filter(s: MySet, p: Int => Boolean): MySet = k => s(k) & p(k)


  /**
   * The bounds for `forall` and `exists` are +/- 1000.
   */
  val bound = 1000

  /**
   * Returns whether all bounded integers within `s` satisfy `p`.
   */
    def forall(s: MySet, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (a > bound) true
      else if (!p(a) & contains(s,a)) false
      else iter(a + 1)
    }
    iter(-bound)
  }

  /**
   * Returns whether there exists a bounded integer within `s`
   * that satisfies `p`.
   */
    def exists(s: MySet, p: Int => Boolean): Boolean = ! forall(s, k => !p(k))

  /**
   * Returns a set transformed by applying `f` to each element of `s`.
   */
    def map(s: MySet, f: Int => Int): MySet = {
      def iter(a: Int): MySet = {
        if (a > bound) Nil
        else if (contains(s,a)) union(singletonSet(f(a)),iter(a + 1))
        else iter(a + 1)
      }
      iter(-bound)
    }
  /**
   * Displays the contents of a set
   */
  def toString(s: MySet): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }

  /**
   * Prints the contents of a set on the console.
   */
  def printSet(s: MySet) {
    println(toString(s))
  }
}
