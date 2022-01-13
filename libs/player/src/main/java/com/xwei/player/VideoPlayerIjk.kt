package com.xwei.player

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.FrameLayout
import tv.danmaku.ijk.media.player.IMediaPlayer
import tv.danmaku.ijk.media.player.IjkMediaPlayer

class VideoPlayerIjk @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), IPlayer {

    /**
     *  由ijkplayer提供，用于播放视频，需要给他传入一个surfaceView
     */
    private var mMediaPlayer: IMediaPlayer? = null

    /**
     *  播放文件地址
     */
    private var mPath = ""
    private var surfaceView: SurfaceView? = null
    private var listener: VideoPlayerListener? = null
    private var mContext: Context? = null

    init {
        mContext = context
        isFocusable = true
    }

    fun setVideoPath(path: String) {
        if (mPath.isEmpty()) {
            mPath = path
            createSurfaceView()
        } else {
            mPath = path
            loading()
        }
    }

    private fun createSurfaceView() {
        surfaceView = SurfaceView(mContext)
        surfaceView?.holder?.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(p0: SurfaceHolder) {
                //surfaceview创建成功后，加载视频
                loading()
            }

            override fun surfaceChanged(p0: SurfaceHolder, format: Int, width: Int, height: Int) {}

            override fun surfaceDestroyed(p0: SurfaceHolder) {}
        })
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT, Gravity.CENTER)
        surfaceView?.layoutParams = layoutParams
        addView(surfaceView)
    }

    fun loading() {
        createPlayer()
        mMediaPlayer?.dataSource = mPath
        mMediaPlayer?.setDisplay(surfaceView?.holder)
        mMediaPlayer?.prepareAsync()
    }

    /**
     *  创建一个新的player
     */
    private fun createPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer?.stop()
            mMediaPlayer?.setDisplay(null)
            mMediaPlayer?.release()
        }

        val ijkMediaPlayer = IjkMediaPlayer()

        // 开启硬解码
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 1)
        mMediaPlayer = ijkMediaPlayer

        if (listener != null) {
            mMediaPlayer?.let {
                it.setOnPreparedListener(listener)
                it.setOnInfoListener(listener)
                it.setOnSeekCompleteListener(listener)
                it.setOnBufferingUpdateListener(listener)
                it.setOnErrorListener(listener)
            }
        }
    }

    /*********************  播放器常规操作封装  *********************************/
    override fun start() {
        if (mMediaPlayer != null) {
            mMediaPlayer?.start()
        }
    }

    override fun pause() {
        if (mMediaPlayer != null) {
            mMediaPlayer?.pause()
        }
    }

    override fun stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer?.stop()
        }
    }

    override fun reset() {
        if (mMediaPlayer != null) {
            mMediaPlayer?.reset()
        }
    }

    override fun release() {
        if (mMediaPlayer != null) {
            mMediaPlayer?.reset()
            mMediaPlayer?.release()
            mMediaPlayer = null
        }
    }

    override fun getDuration(): Long {
        return if (mMediaPlayer != null) {
            mMediaPlayer?.duration ?: 0
        } else {
            0
        }
    }

    override fun getCurrentPosition(): Long {
        return if (mMediaPlayer != null) {
            mMediaPlayer?.currentPosition ?: 0
        } else {
            0
        }
    }

    override fun seekTo(pos: Long) {
        if (mMediaPlayer != null) {
            mMediaPlayer?.seekTo(pos)
        }
    }


}