package com.xwei.xchat

import android.os.Build
import com.xwei.commonutils.util.LogUtil
import com.xwei.xchat.section.base.BaseActivity
import com.xwei.xchat.common.constant.KeyCenter
import im.zego.zegoexpress.ZegoExpressEngine
import im.zego.zegoexpress.callback.IZegoEventHandler
import im.zego.zegoexpress.constants.*
import im.zego.zegoexpress.entity.*
import kotlinx.android.synthetic.main.activity_zego_main.*
import org.json.JSONObject
import java.util.*


class ZegoMainActivity : BaseActivity() {

    private var engine: ZegoExpressEngine? = null
    private var userID = ""
    private val roomID = "2875"
    private val streamID = ((Math.random() * 9 + 1) * 100000).toString()
    private var testUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"

    override fun getLayoutResId(): Int {
        return R.layout.activity_zego_main
    }

    override fun initSetup() {
        initZegoEngine()
        loginRoom()
        setEventHandler()
    }

    /**
     *  初始化
     */
    private fun initZegoEngine() {
        val profile = ZegoEngineProfile()
        profile.appID = KeyCenter.appID
        profile.appSign = KeyCenter.appSign
        profile.scenario = ZegoScenario.GENERAL
        profile.application = application
        engine = ZegoExpressEngine.createEngine(profile, null)
    }

    /**
     *  登录房间
     */
    private fun loginRoom() {
        userID = ("Android_" + Build.MODEL).replace(" ".toRegex(), "_")
        val user = ZegoUser(userID)
        val config = ZegoRoomConfig()
        // 使能用户登录/登出房间通知
        // Enable notification when user login or logout
        config.isUserStatusNotify = true
        engine?.loginRoom(roomID, user, config)
        // Android_Redmi_Note_4X, Android_MIX_2S
        LogUtil.e(TAG, "userID = $userID")
        engine?.startPreview(ZegoCanvas(playView))
        engine?.startPublishingStream(streamID)
    }

    /**
     * 在onRoomStreamUpdate回调中，我们可以获取流的增加/移除信息，流增加时进行拉流播放 startPlayingStream，
     * 流移除时停止拉流 stopPlayingStream
     */
    private fun setEventHandler() {
        engine?.setEventHandler(object : IZegoEventHandler() {

            override fun onRoomStateUpdate(
                roomID: String?,
                state: ZegoRoomState?,
                errorCode: Int,
                extendedData: JSONObject?
            ) {
                /** 房间状态更新回调，登陆房间后，当房间连接状态发生变更（如出现房间断开，登陆认证失败等情况），SDK会通过该回调通知 */
                LogUtil.e(
                    "onRoomStateUpdate", "roomID = $roomID, state = $state, " +
                            "errorCode = $errorCode, extendedData = $extendedData"
                )

            }

            override fun onRoomUserUpdate(
                roomID: String?,
                updateType: ZegoUpdateType?,
                userList: ArrayList<ZegoUser>?
            ) {
                /** 用户状态更新，登陆房间后，当房间内有用户新增或删除时，SDK会通过该回调通知 */
                userList?.let {
                    for (user in userList) {
                        LogUtil.e("onRoomUserUpdate", "user = ${user.userID}, ${user.userName}")
                    }
                }
            }

            override fun onRoomStreamUpdate(
                roomID: String?, updateType: ZegoUpdateType?, streamList: ArrayList<ZegoStream>?,
                extendedData: JSONObject?
            ) {
                /** 流状态更新，登陆房间后，当房间内有用户新推送或删除音视频流时，SDK会通过该回调通知 */
                when (updateType) {
                    ZegoUpdateType.ADD -> {
                        streamList?.let { // 暂时只做一对一视频聊天
                            for (zegoStream in streamList) {
                                LogUtil.e(
                                    "onRoomStreamUpdate",
                                    "zegoStream = ${zegoStream.streamID}"
                                )
                            }
                            engine?.startPlayingStream(streamList[0].streamID, ZegoCanvas(preview))
                        }
                    }

                    ZegoUpdateType.DELETE -> {
                        streamList?.let { // 暂时只做一对一视频聊天
                            for (zegoStream in streamList) {
                                LogUtil.e(
                                    "onRoomStreamUpdate",
                                    "zegoStream = ${zegoStream.streamID}"
                                )
                            }
                            engine?.stopPlayingStream(streamList[0].streamID)
                        }
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        engine?.stopPublishingStream()
        engine?.stopPreview()
        engine?.logoutRoom(roomID)
        ZegoExpressEngine.destroyEngine(null)
        super.onDestroy()
    }

}