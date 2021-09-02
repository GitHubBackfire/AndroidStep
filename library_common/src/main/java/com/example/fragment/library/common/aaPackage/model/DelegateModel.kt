package com.example.fragment.library.common.aaPackage.model

fun main() {
    val realGamePlayer = RealGamePlayer("小明")
    val delegateGamePlayer = DelegateGamePlayer(realGamePlayer);
    delegateGamePlayer.rank()
    delegateGamePlayer.upgrade()
}

//约束类
interface IGamePlayer{
    fun rank()
    fun upgrade()
}

class RealGamePlayer(private val name:String):IGamePlayer{
    override fun rank() {
        TODO("Not yet implemented")
        println("$name 开始")
    }

    override fun upgrade() {
        TODO("Not yet implemented")
        println("$name 升级")

    }

}

class DelegateGamePlayer(private val play:IGamePlayer):IGamePlayer by play{

}