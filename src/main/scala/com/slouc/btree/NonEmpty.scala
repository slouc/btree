package com.slouc.btree

/**
 * A non-empty tree, represented by its element and its
 * left and right children trees.
 *
 * @author slouc
 *
 */
class NonEmpty(elem: Int, left: BTree, right: BTree) extends BTree {

  def insert(x: Int): BTree =
    if (x < elem) new NonEmpty(elem, left insert x, right)
    else if (x > elem) new NonEmpty(elem, left, right insert x)
    else this

  def del(x: Int): BTree = {
    if (x == elem) {
      right match {
        case ne: NonEmpty =>
          new NonEmpty(ne getMin, left, right del (ne getMin))
        case Empty => left
      }
    } else if (x < elem) new NonEmpty(elem, left del x, right)
    else new NonEmpty(elem, left, right del x)
  }

  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true

  def union(other: BTree): BTree =
    ((left union right) union other) insert elem

  /**
   * Helper function for finding the minimum element value in the tree.
   */
  val getMin: Int =
    (left, right) match {
      case (Empty, Empty) => elem
      case (left: NonEmpty, Empty) => Math min (left getMin, elem)
      case (Empty, right: NonEmpty) => Math min (right getMin, elem)
      case (left: NonEmpty, right: NonEmpty) =>
        Math min (elem, Math min (left getMin, right getMin))
    }

  override def toString = "<" + left + elem + right + ">"
}