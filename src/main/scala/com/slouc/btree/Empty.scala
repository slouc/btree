package com.slouc.btree

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