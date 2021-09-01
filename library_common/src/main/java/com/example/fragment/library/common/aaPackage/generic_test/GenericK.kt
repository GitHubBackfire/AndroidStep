package com.example.fragment.library.common.aaPackage.generic_test

import android.media.Image

fun main() {
    //上界约束
    val fruit = Plate(Fruit())
    val apple = Plate(Apple())
    val banana = PlateSoft(Banana());
}

class Plate<T : Fruit>(val t: T)

class PlateSoft<T>(val t: T) where T : Fruit, T : Soft {

}


open class Fruit() {}

class Apple : Fruit() {}

class Banana : Fruit(), Soft {
    override fun toJuice() {
        super.toJuice()
    }
}


interface Soft {
    fun toJuice() {}

}

/**
 * 对应java中的类型通配符?
 */
fun printList(list: List<*>) {
    for (any in list) {
        println(any)
    }
}

/**
 * todo  协变与不变
 * 协变：如果 A 是 B 的子类，那么 A[] 就是 B[] 的子类型。相对的，Object[] 就是所有数组对象的父类型
 */


/**
 * 泛型协变
 * out 本身带有出去的意思，本身带有倾向于取值操作的意思，用于泛型协变
 */
fun <T> copyAll(to: MutableList<T>, from: MutableList<out T>) {
    to.addAll(from)
}


/**
 * 泛型逆变
 *  * in 本身带有进来的意思，本身带有倾向于传值操作的意思，用于泛型逆变

 */
fun <T> copyAll2(to: MutableList<in T>, from: MutableList<T>) {
    to.addAll(from)
}

abstract class BaseItem<T : BaseItem<T>>{}


class CommonItem<T : BaseItem<T>> : BaseItem<T>(){}



