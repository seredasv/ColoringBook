package com.seredasv.coloringbook.kotlin_test

import java.util.concurrent.locks.Lock

const val SUBSYSTEM_DEPRECATED: String = ""

@Deprecated(SUBSYSTEM_DEPRECATED) fun foo() {

}

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

    var size: Int = 0
    val isEmpty: Boolean
        get() = this.size == 0

    fun setDataFromString(value: String): Unit {
    }

    var stringRepresentation: String
        get() = this.toString()
        set(value) = setDataFromString(value)

    var setterVisibility: String = "abc"
        private set

    //    var setterWithAnnotation: Any? = null
    //        @Inject set

    var c: String? = ""
    val l: Int = if (c != null) (c as String).length else -1
    val l2: Int = c?.length ?: -1
    val l3 = c!!.length

    val aInt: Int? = c as? Int


    var name: String = ""
    var age: Int = 0
    fun copy(name: String = this.name, age: Int = this.age) = User(name, age)
    val jack = User(name = "Jack", age = 1)
    val olderJack = jack.copy(age = 2)

    val name2 = jack.component1()
    val age2 = jack.component2()

    //    var result = lock(lock, ::toBeSynchronized)

    fun compare(a: String, b: String): Boolean = a.length < b.length

    fun <T> max(collection: Collection<T>, less: (T, T) -> Boolean): T? {
        var max: T? = null

        for (it in collection) {
            if (max == null || less(max, it)) {
                max = it
            }
        }

        return max
    }

    val sum = { x: Int, y: Int -> x + y }
    val sum2: (Int, Int) -> Int = { x, y -> x + y }

    //    var lis = mutableListOf(1, 2, 3)
    //    lis.swap(0,2)

    val <T> List<T>.lastIndex: Int
        get() = size - 1

    //    MyClass.foo()

    val box: Box<Int> = Box<Int>(1)
    val box2 = Box(1)
}

data class User(val name: String, val age: Int)

fun <T> lock(lock: Lock, body: () -> T): T {
    lock.lock()

    try {
        return body()
    } finally {
        lock.unlock()
    }
}

fun toBeSynchronized() = {}

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

public class MyClass {
    companion object {

    }
}

fun MyClass.Companion.foo() {

}

class Box<T>(t: T) {
    var value = t
}

