package com.xwei.player

import tv.danmaku.ijk.media.player.IMediaPlayer

class VideoPlayerListener : IMediaPlayer.OnPreparedListener, IMediaPlayer.OnInfoListener,
    IMediaPlayer.OnCompletionListener, IMediaPlayer.OnSeekCompleteListener,
    IMediaPlayer.OnErrorListener,
    IMediaPlayer.OnBufferingUpdateListener {

    override fun onPrepared(p0: IMediaPlayer?) {
        TODO("Not yet implemented")
    }

    override fun onInfo(p0: IMediaPlayer?, p1: Int, p2: Int): Boolean {
        return false
    }

    override fun onCompletion(p0: IMediaPlayer?) {
        TODO("Not yet implemented")
    }

    override fun onSeekComplete(p0: IMediaPlayer?) {
        TODO("Not yet implemented")
    }

    override fun onError(p0: IMediaPlayer?, p1: Int, p2: Int): Boolean {
        return false
    }

    override fun onBufferingUpdate(p0: IMediaPlayer?, p1: Int) {
        TODO("Not yet implemented")
    }

}