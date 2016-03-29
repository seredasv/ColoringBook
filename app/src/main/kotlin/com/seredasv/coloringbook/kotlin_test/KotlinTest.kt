package com.seredasv.coloringbook.kotlin_test

class A {
    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    fun sum2(a: Int, b: Int) = a + b

    fun printSum(a: Int, b: Int): Unit {
        print(a + b)
    }

    fun printSum2(a: Int, b: Int) {
        print(a + b)
    }

    val a: Int = 1
    val b = 1

    var x = 5
    //x+=1

    fun main(args: Array<String>) {
        if (args.size == 0) return

        print("First argument: ${args[0]}")
    }

    fun max(a: Int, b: Int): Int {
        if (a > b) return a
        else return b
    }

    fun max2(a: Int, b: Int) = if (a > b) a else b
}
