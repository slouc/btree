package com.slouc.btree

import java.util.NoSuchElementException

/**
 * Class represents a classic binary search tree.
 * <br><br>
 * It offers four methods to manipulate the tree:
 * <ul>
 *  <li>insert a new element</li>
 *  <li>delete an existing element</li>
 *  <li>see if tree contains some element</li>
 *  <li>merge the tree with another tree</li>
 * </ul>
 *
 * @author slouc
 *
 */
abstract class BTree {

  /**
   * Inserts given element into the tree.
   *
   *
   * @param x element to be inserted
   * @return new <code>BTree<code> which includes element x
   */
  def insert(x: Int): BTree

  /**
   * Deletes given element from the tree.
   *
   * @param x element to be deleted (if no such element exists in the tree,
   * NoSuchElementException is thrown)
   * @return new <code>BTree<code> which includes element x
   */
  def del(x: Int): BTree

  /**
   * Checks whether the tree contains some element.
   *
   * @param x element to be found
   * @return true if the tree contains x, false otherwise
   */
  def contains(x: Int): Boolean

  /**
   * Merges this tree with the given tree.
   *
   * @param other tree to be merged with this one
   * @return new <code>BTree<code>, result of merging this tree with other tree
   */
  def union(other: BTree): BTree

}

/**
 * An empty tree, defined as object to preserve memory.
 *
 * @author slouc
 *
 */
object Empty extends BTree {

  def insert(x: Int): BTree = new NonEmpty(x, Empty, Empty)

  def del(x: Int) = throw new NoSuchElementException

  def contains(x: Int): Boolean = false

  def union(other: BTree): BTree = other

  override def toString = ""
}

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

  def del(x: Int) = {
    if (x == elem) {
      right match {
        case ne: NonEmpty =>
          new NonEmpty(ne getMin, left, right del (ne getMin))
        case Empty => left
      }
    } else if (x < elem) new NonEmpty(elem, left del (x), right)
    else new NonEmpty(elem, left, right del (x))
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