package com.xwei.player

/**
 *  播放器
 */
interface IPlayer {

    fun start()

    fun pause()

    fun stop()

    fun reset()

    fun release()

    fun getDuration(): Long

    fun getCurrentPosition(): Long

    fun seekTo(pos: Long)

}