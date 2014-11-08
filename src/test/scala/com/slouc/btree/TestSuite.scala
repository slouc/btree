package com.slouc.btree

import org.scalatest.FunSuite

class TestSuite extends FunSuite {

  trait TestSets {
    val root = new NonEmpty(23, Empty, Empty)
    val tree1819 = root.insert(18).insert(19)
    val tree181986 = tree1819 union root.insert(86)
    val tree1986 = tree181986.del(18)
  }

  test("Insert test") {
    new TestSets {
      assert(tree1819.toString() === "<<18<19>>23>")
    }
  }

  test("Del test") {
    new TestSets {
      assert(tree1986.contains(19))
      assert(tree1986.contains(86))
      assert(!tree1986.contains(18))
    }
  }

  test("Contains test") {
    new TestSets {
      assert(tree1819.contains(18))
      assert(tree1819.contains(19))
    }
  }

  test("Union test") {
    new TestSets {
      assert(tree181986.contains(18))
      assert(tree181986.contains(19))
      assert(tree181986.contains(86))
    }
  }
}
