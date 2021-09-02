package com.example.fragment.library.common.aaPackage.generic_test

import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis


private fun log(msg: Any?) = println("[${Thread.currentThread().name}] $msg")

/**
 * GlobalScope。即全局协程作用域，在这个范围内启动的协程可以一直运行直到应用停止运行。GlobalScope 本身不会阻塞当前线程，且启动的协程相当于守护线程，不会阻止 JVM 结束运行
 * runBlocking。一个顶层函数，和 GlobalScope 不一样，它会阻塞当前线程直到其内部所有相同作用域的协程执行结束
 * 自定义 CoroutineScope。可用于实现主动控制协程的生命周期范围，对于 Android 开发来说最大意义之一就是可以避免内存泄露

 */

fun main() {
    GlobalScope.launch(context = Dispatchers.IO) {
        delay(1000)
        log("launch")
    }

    //主动休眠两秒，防止JVM过快退出
    Thread.sleep(2000)
    log("end")


}

class ClassA() {

    //挂起与恢复
    suspend fun fetchDocs() {                             // Dispatchers.Main
        val result = get("https://developer.android.com") // Dispatchers.IO for `get`
        //show(result)                                      // Dispatchers.Main

    }

    suspend fun get(url: String) = withContext(Dispatchers.IO) { /* ... */ }


    fun globalScopeTest() {
        log("start")
        GlobalScope.launch {
            launch {
                delay(400)
                log("launch A")
            }
            launch {
                delay(300)
                log("launch B")
            }
            log("GlobalScope")
        }
        log("end")
        Thread.sleep(500)
    }

    //只有当内部相同作用域的所有协程都运行结束后，声明在 runBlocking 之后的代码才能执行，即 runBlocking 会阻塞其所在线程
    //runBlocking 本身带有阻塞线程的意味，但其内部运行的协程又是非阻塞的
    fun runBlockingTest() {
        GlobalScope.launch(Dispatchers.IO) {
            delay(600)
            log("GlobalScope")
        }
        runBlocking {
            delay(500)
            // runBlocking 会早于 GlobalScope 输出日志
            log("runBlocking")
        }
        //主动休眠两百毫秒，使得和 runBlocking 加起来的延迟时间少于六百毫秒
        Thread.sleep(200)
        log("after sleep")

    }

    //coroutineScope 不会阻塞当前线程
    fun coroutineScopeTest() {

    }

    //CoroutineScope 的实例可以通过 CoroutineScope() 或 MainScope() 的工厂函数来构建。前者创建通用作用域，后者创建 UI 应用程序的作用域并使用 Dispatchers.Main 作为默认的调度器
    class Activity : AppCompatActivity() {
        private val mainScope = MainScope()

        override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
            super.onCreate(savedInstanceState, persistentState)

            mainScope.launch {
                repeat(5) {
                    delay(1000L * it)
                }

            }
        }

        override fun onDestroy() {
            super.onDestroy()
            mainScope.cancel()
        }
    }

    class Activity2 : AppCompatActivity(), CoroutineScope by CoroutineScope(Dispatchers.Default) {
        override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
            super.onCreate(savedInstanceState, persistentState)
            val job: Job = launch {
                repeat(5) {
                    delay(1000L * it)
                }

            }
//            job.isActive;
//            job.isCancelled;
//            job.start()
//            job.cancel()
//            job.join()
//            //当 Job 结束运行时（不管由于什么原因）回调此方法，可用于接收可能存在的运行异常
//            job.invokeOnCompletion {}
        }

        override fun onDestroy() {
            super.onDestroy()
            cancel()
        }
    }


    /**
     * CoroutineBuilder
     */

    /**
     * supervisorScope  异常不会连锁取消同级协程
     */

    fun main() = runBlocking {
        launch {
            delay(100)
            log("Task from runBlocking")
        }
        supervisorScope {
            launch {
                delay(500)
                log("Task throw Exception")
                throw Exception("failed")
            }
            launch {
                delay(600)
                log("Task from nested launch")
            }
        }
        log("Coroutine scope is over")
    }

    /**
     * CoroutineBuilder
     */
    //launch
    fun lunchTest() = runBlocking {
        val launchA = launch {
            repeat(3) {
                delay(100)
                log("launchA - $it")
            }
        }
        val launchB = launch {
            repeat(3) {
                delay(100)
                log("launchB - $it")
            }
        }
    }

    //async   通过await()方法可以拿到 async 协程的执行结果
    fun asyncTest() {
        val time = measureTimeMillis {
            runBlocking {
                val asyncA = async(start = CoroutineStart.LAZY) {
                    delay(3000)
                    1
                }

                val asyncB = async(start = CoroutineStart.LAZY) {
                    delay(3000)
                    2
                }
                //start = CoroutineStart.DEFAULT时
                log(asyncA.await() + asyncB.await())

                //start = CoroutineStart.LAZY时，需要先调用 start
                asyncA.start()
                asyncB.start()
                log(asyncA.await() + asyncB.await())
            }

        }
        log(time)
    }

    //async 并行分解
    class AsyncParallelClass {
        suspend fun fetchTwoDocs() {
            coroutineScope {
                val deferredOne = async {
                    fetchDocs(1)
                }
                val deferredTwo = async {
                    fetchDocs(2)
                }
                deferredOne.await()
                deferredTwo.await()
                // 同上
                val deferreds = listOf<Deferred<Unit>>(
                    async {
                        fetchDocs(1)
                    },
                    async {
                        fetchDocs(2)
                    }
                )
                deferreds.awaitAll()
            }
        }

        //挂起与恢复
        suspend fun fetchDocs(id: Int) {                             // Dispatchers.Main
            val result = get("https://developer.android.com$id") // Dispatchers.IO for `get`
            //show(result)                                      // Dispatchers.Main

        }

        suspend fun get(url: String) = withContext(Dispatchers.IO) { /* ... */ }

        //CoroutineDispatcher
        fun main() = runBlocking<Unit> {
            //默认从外部的协程作用域继承上下文和调度器
            val jobOne = launch(CoroutineName("mainCoroutine") + Dispatchers.Main) {
                log("main runBlocking")

            }
            val jobTwo = launch(Dispatchers.Default) {

                log("Default")
            }
            val jobThree = launch(Dispatchers.IO) {
                log("IO")
            }
            launch(newSingleThreadContext("MyOwnThread")) {
                log("newSingleThreadContext")
            }
            jobOne.cancel()
            jobOne.join()
            //同上
            //jobOne.cancelAndJoin()

        }

        //父协程和子协程
        fun fatherAndChildCoroutine() = runBlocking {
            val parentJob = launch {
                repeat(3) { i ->
                    launch {
                        delay((i + 1) * 200L)
                        log("Coroutine $i is done")
                    }
                }
                log("request: I'm done and I don't explicitly join my children that are still active")
            }
        }


    }


}
