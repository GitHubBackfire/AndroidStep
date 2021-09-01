package com.example.fragment.library.common.aaPackage

import androidx.core.util.rangeTo

fun main() {
    val msg: String = "Hello World"
    println(msg)
    println("Hello World $msg")

    var anyInt: Any = 100;
    var anyAny: Any? = null;
    anyInt = 120;
    anyAny = anyAny;

    //可变参数
    compute()
    compute("leavesC")
    compute("leavesC", "leavesc")
    compute("leavesC", "leavesc", "叶")
    //when
    chooseType(5)

    val point = Point(1, 2)
    Point(1)
    Point(1L)

}

/**
 * 可变参数
 */
fun compute(vararg name: String) {
    name.forEach { println(it) }
}

/**
 * 嵌套函数
 */
fun compute(name: String, country: String) {
    fun check(string: String) {
        if (string.isEmpty()) {
            throw IllegalArgumentException("参数错误")
        }
    }
    check(name)
    check(country)
}

/**
 * when表达式子
 */
fun chooseType(value: Int) {
    when (value) {
        in 4..9 -> println("in 4..9") //区间判断
        3 -> println("value is 3")    //相等性判断
        2, 6 -> println("value is 2 or 6")    //多值相等性判断
        is Int -> println("is Int")   //类型判断
        else -> println("else")       //如果以上条件都不满足，则执行 else
    }

}

/**
 * rangeTo
 */
fun rangeType(index: Int) {
    if (index >= 0 && index <= 10) {

    }

    if (index in 0..10) {

    }

    if (index in 0.rangeTo(10)) {

    }

    for (index in 10..0) {
        println(index)
    }

    for (index in 10 downTo 0) {
        println(index)
    }
    //step
    for (index in 1..8 step 2) {
        println(index)
    }
    for (index in 8 downTo 1 step 2) {
        println(index)
    }
    //闭区间
    for (index in 0 until 4) {
        println(index)
    }

}


/**
 * 可空性
 */
fun emptyVerify() {
    var cantNull: String = "cantNull"
    //编译错误
    //name = null
    var canNull: String = "canNull"

    //todo ?. 允许把一次 null 检查和一次方法调用合并为一个操作，如果变量值非空，则方法或属性会被调用，否则直接返回 null
    fun check1(name: String?) {
        if (name != null) {
            println(name.toUpperCase())
        } else {
            println(null)
        }
    }

    fun check2(name: String?) {
        println(name?.toUpperCase())
    }

    //todo ?: 用于替代 ?. 直接返回默认值 null 的情况
    fun check3(name: String?) {
        println(name ?: "default")
    }
    check1(cantNull)
    check1(canNull);

    //todo 安全转换运算符 as? 用于把值转换为指定的类型，如果值不适合该类型则返回 null
    fun checkAny(any: Any) {
        val result = any as? String
        println(result ?: println("is not String"))
    }

    //todo 非空断言：!!
    fun checkNoEmpty(name: String?) {
        println(name!!.length)
    }

    /**
     * lambda
     */
    val lambdaTest = LambdaTest();
    val sum = lambdaTest.sumFun()
    println(sum(10)) //10


}

/**
 * class
 * todo class Point constructor(val x: Int, val y: Int){

 */
class Point public constructor(val x: Int, val y: Int) {

    //次构造函数1
    constructor(base: Int) : this(base + 1, base + 1) {
        println("constructor(base: Int)")
    }

    //次构造函数2
    constructor(base: Long) : this(base.toInt()) {
        println("constructor(base: Long)")
    }

    fun calculatePoint(): Int {
        return x * y;
    }


    //todo 延迟初始化 lateinit 修饰的属性或变量必须为非空类型，并且不能是原生类型


}

/**
 * 数据类
 * 主构造函数需要包含一个参数
 * 主构造函数的所有参数需要标记为 val 或 var
 * 数据类不能是抽象、开放、密封或者内部的
 */
data class DataClass(val x: Int, val y: Int) {

}

/**
 * 集合的可空
 */
class ArrayFunction() {

    fun test() {
        //List<Int?> 是能持有 Int? 类型值的列表
        val intList1: List<Int?> = listOf(10, 20, 30, 40, null)
        //List<Int>? 是可以为 null 的列表
        var intList2: List<Int>? = listOf(10, 20, 30, 40)
        intList2 = null
        //List<Int?>? 是可以为 null 的列表，且能持有 Int? 类型值
        var intList3: List<Int?>? = listOf(10, 20, 30, 40, null)
        intList3 = null
    }


}


/**
 * todo 扩展对象与属性
 */
fun String.lastChar() = get(length - 1);
var String.customLen: Int
    get() = length
    set(value) {
        println("set")
    }

class Namer {
    companion object {
        val defaultName = "mike"
    }

}

fun Namer.Companion.getName(): String {
    return defaultName;
}


/**
 * Lambda表达式
 */

class LambdaTest() {
    fun lambdaTestFun1() {
        //由于存在类型推导，所以以下三种声明方式都是完全相同的
        val plus1: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
        val plus2: (Int, Int) -> Int = { x, y -> x + y }
        val plus3 = { x: Int, y: Int -> x + y }
        println(plus3(1, 2))

    }

    fun sumFun(): (Int) -> Int {
        var base = 0;
        return fun(va: Int): Int {
            base += va;
            return base;
        }

    }

    data class Person(val name: String, val age: Int)

    fun lambdaUsingInListFun() {
        val people = listOf(Person("leavesC", 24), Person("Ye", 22))
        println(people.maxByOrNull { it.age }) //Person(name=leavesC, age=24)
    }

    /**
     * todo 从 Lambda 内部访问外部变量，我们称这些变量被 Lambda 捕捉
     */
    fun getMoreThen20Numbers() {
        var number = 0
        val list = listOf(10, 20, 30, 40)
        list.forEach {
            if (it > 20) {
                number++
            }
        }
        println(number) //2
    }

}
















