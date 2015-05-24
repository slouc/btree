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