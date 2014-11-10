package com.slouc.btree

import org.scalatest.FunSuite

class TestSuite extends FunSuite {

  trait TestSets {
    val root = new NonEmpty(23, Empty, Empty)
    val tree1 = root insert 18 insert 19
    val tree2 = tree1 union root insert 86
    val tree3 = tree2 del 18
  }

  test("Insert test") {
    new TestSets {
      assert(tree1.toString === "<<18<19>>23>")
    }
  }

  test("Del test") {
    new TestSets {
      assert(tree3 contains 19)
      assert(tree3 contains 86)
      assert(!(tree3 contains 18))
    }
  }

  test("Contains test") {
    new TestSets {
      assert(tree1 contains 18)
      assert(tree1 contains 19)
    }
  }

  test("Union test") {
    new TestSets {
      assert(tree2 contains 18)
      assert(tree2 contains 19)
      assert(tree2 contains 86)
    }
  }

  test("Edge cases") {
    new TestSets {
      assert((Empty insert 3).toString === (new NonEmpty(3, Empty, Empty)).toString)
      intercept[NoSuchElementException] {
        Empty del 19
      }
      assert(!(Empty contains 18))
      assert((Empty union tree1) === tree1)
    }
  }
}
