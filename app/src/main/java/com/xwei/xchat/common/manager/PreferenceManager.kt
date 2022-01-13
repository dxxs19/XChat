package com.xwei.xchat.common.manager

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

/**
 * @desc
 * @author wei
 * @date  2022/1/5
 **/
class PreferenceManager {
    /**
     * name of preference
     */
    val PREFERENCE_NAME = "saveInfo"
    private var mSharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    private val SHARED_KEY_SETTING_NOTIFICATION = "shared_key_setting_notification"
    private val SHARED_KEY_SETTING_SOUND = "shared_key_setting_sound"
    private val SHARED_KEY_SETTING_VIBRATE = "shared_key_setting_vibrate"
    private val SHARED_KEY_SETTING_SPEAKER = "shared_key_setting_speaker"

    private val SHARED_KEY_SETTING_CHATROOM_OWNER_LEAVE = "shared_key_setting_chatroom_owner_leave"
    private val SHARED_KEY_SETTING_DELETE_MESSAGES_WHEN_EXIT_GROUP =
        "shared_key_setting_delete_messages_when_exit_group"
    private val SHARED_KEY_SETTING_DELETE_MESSAGES_WHEN_EXIT_CHAT_ROOM =
        "shared_key_setting_delete_messages_when_exit_chat_room"
    private val SHARED_KEY_SETTING_TRANSFER_FILE_BY_USER =
        "shared_key_setting_transfer_file_by_user"
    private val SHARED_KEY_SETTING_AUTODOWNLOAD_THUMBNAIL =
        "shared_key_setting_autodownload_thumbnail"
    private val SHARED_KEY_SETTING_AUTO_ACCEPT_GROUP_INVITATION =
        "shared_key_setting_auto_accept_group_invitation"
    private val SHARED_KEY_SETTING_ADAPTIVE_VIDEO_ENCODE =
        "shared_key_setting_adaptive_video_encode"
    private val SHARED_KEY_SETTING_OFFLINE_PUSH_CALL = "shared_key_setting_offline_push_call"
    private val SHARED_KEY_SETTING_RECORD_ON_SERVER = "shared_key_setting_record_on_server"
    private val SHARED_KEY_SETTING_MERGE_STREAM = "shared_key_setting_merge_stream"
    private val SHARED_KEY_SETTING_OFFLINE_LARGE_CONFERENCE_MODE =
        "shared_key_setting_offline_large_conference_mode"

    private val SHARED_KEY_SETTING_GROUPS_SYNCED = "SHARED_KEY_SETTING_GROUPS_SYNCED"
    private val SHARED_KEY_SETTING_CONTACT_SYNCED = "SHARED_KEY_SETTING_CONTACT_SYNCED"
    private val SHARED_KEY_SETTING_BALCKLIST_SYNCED = "SHARED_KEY_SETTING_BALCKLIST_SYNCED"

    private val SHARED_KEY_CURRENTUSER_USERNAME = "SHARED_KEY_CURRENTUSER_USERNAME"
    private val SHARED_KEY_CURRENTUSER_USER_PASSWORD = "SHARED_KEY_CURRENTUSER_USER_PASSWORD"
    private val SHARED_KEY_CURRENTUSER_NICK = "SHARED_KEY_CURRENTUSER_NICK"
    private val SHARED_KEY_CURRENTUSER_AVATAR = "SHARED_KEY_CURRENTUSER_AVATAR"

    private val SHARED_KEY_REST_SERVER = "SHARED_KEY_REST_SERVER"
    private val SHARED_KEY_IM_SERVER = "SHARED_KEY_IM_SERVER"
    private val SHARED_KEY_IM_SERVER_PORT = "SHARED_KEY_IM_SERVER_PORT"
    private val SHARED_KEY_ENABLE_CUSTOM_SERVER = "SHARED_KEY_ENABLE_CUSTOM_SERVER"
    private val SHARED_KEY_ENABLE_CUSTOM_SET = "SHARED_KEY_ENABLE_CUSTOM_SET"
    private val SHARED_KEY_ENABLE_CUSTOM_APPKEY = "SHARED_KEY_ENABLE_CUSTOM_APPKEY"
    private val SHARED_KEY_CUSTOM_APPKEY = "SHARED_KEY_CUSTOM_APPKEY"
    private val SHARED_KEY_MSG_ROAMING = "SHARED_KEY_MSG_ROAMING"
    private val SHARED_KEY_SHOW_MSG_TYPING = "SHARED_KEY_SHOW_MSG_TYPING"

    private val SHARED_KEY_CALL_MIN_VIDEO_KBPS = "SHARED_KEY_CALL_MIN_VIDEO_KBPS"
    private val SHARED_KEY_CALL_MAX_VIDEO_KBPS = "SHARED_KEY_CALL_Max_VIDEO_KBPS"
    private val SHARED_KEY_CALL_MAX_FRAME_RATE = "SHARED_KEY_CALL_MAX_FRAME_RATE"
    private val SHARED_KEY_CALL_AUDIO_SAMPLE_RATE = "SHARED_KEY_CALL_AUDIO_SAMPLE_RATE"
    private val SHARED_KEY_CALL_BACK_CAMERA_RESOLUTION = "SHARED_KEY_CALL_BACK_CAMERA_RESOLUTION"
    private val SHARED_KEY_CALL_FRONT_CAMERA_RESOLUTION = "SHARED_KEY_FRONT_CAMERA_RESOLUTIOIN"
    private val SHARED_KEY_CALL_FIX_SAMPLE_RATE = "SHARED_KEY_CALL_FIX_SAMPLE_RATE"

    private val SHARED_KEY_EXTERNAL_INPUT_AUDIO_RESOLUTION =
        "SHARED_KEY_EXTERNAL_INPUT_AUDIO_RESOLUTION"
    private val SHARED_KEY_WATER_MARK_RESOLUTION = "SHARED_KEY_WATER_MARK_RESOLUTION"

    private val SHARED_KEY_PUSH_USE_FCM = "shared_key_push_use_fcm"
    private val SHARED_KEY_AUTO_LOGIN = "shared_key_auto_login"
    private val SHARED_KEY_HTTPS_ONLY = "shared_key_https_only"
    private val SHARED_KEY_SORT_MESSAGE_BY_SERVER_TIME = "sort_message_by_server_time"

    private val SHARED_KEY_ENABLE_TOKEN_LOGIN = "enable_token_login"

    @SuppressLint("CommitPrefEdits")
    private constructor(cxt: Context?) {
        mSharedPreferences = cxt?.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        editor = mSharedPreferences?.edit()
    }

    companion object {
        private var mPreferencemManager: PreferenceManager? = null
        /**
         * get instance of PreferenceManager
         *
         * @param
         * @return
         */
        @Synchronized
        fun getInstance(): PreferenceManager {
            if (mPreferencemManager == null) {
                throw RuntimeException("please init first!")
            }
            return mPreferencemManager as PreferenceManager
        }

        @Synchronized
        fun init(cxt: Context?) {
            if (mPreferencemManager == null) {
                mPreferencemManager = PreferenceManager(cxt)
            }
        }

    }

    fun setSettingMsgNotification(paramBoolean: Boolean) {
        editor?.putBoolean(SHARED_KEY_SETTING_NOTIFICATION, paramBoolean)
        editor?.apply()
    }

    fun getSettingMsgNotification(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_SETTING_NOTIFICATION, true) == true
    }

    fun setSettingMsgSound(paramBoolean: Boolean) {
        editor?.putBoolean(SHARED_KEY_SETTING_SOUND, paramBoolean)
        editor?.apply()
    }

    fun getSettingMsgSound(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_SETTING_SOUND, true) == true
    }

    fun setSettingMsgVibrate(paramBoolean: Boolean) {
        editor?.putBoolean(SHARED_KEY_SETTING_VIBRATE, paramBoolean)
        editor?.apply()
    }

    fun getSettingMsgVibrate(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_SETTING_VIBRATE, true) == true
    }

    fun setSettingMsgSpeaker(paramBoolean: Boolean) {
        editor?.putBoolean(SHARED_KEY_SETTING_SPEAKER, paramBoolean)
        editor?.apply()
    }

    fun getSettingMsgSpeaker(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_SETTING_SPEAKER, true) == true
    }

    fun setSettingAllowChatroomOwnerLeave(value: Boolean) {
        editor?.putBoolean(SHARED_KEY_SETTING_CHATROOM_OWNER_LEAVE, value)
        editor?.apply()
    }

    fun getSettingAllowChatroomOwnerLeave(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_SETTING_CHATROOM_OWNER_LEAVE, true) == true
    }

    fun setDeleteMessagesAsExitGroup(value: Boolean) {
        editor?.putBoolean(SHARED_KEY_SETTING_DELETE_MESSAGES_WHEN_EXIT_GROUP, value)
        editor?.apply()
    }

    fun isDeleteMessagesAsExitGroup(): Boolean {
        return mSharedPreferences?.getBoolean(
            SHARED_KEY_SETTING_DELETE_MESSAGES_WHEN_EXIT_GROUP,
            true
        ) == true
    }

    fun setDeleteMessagesAsExitChatRoom(value: Boolean) {
        editor?.putBoolean(SHARED_KEY_SETTING_DELETE_MESSAGES_WHEN_EXIT_CHAT_ROOM, value)
        editor?.apply()
    }

    fun isDeleteMessagesAsExitChatRoom(): Boolean {
        return mSharedPreferences?.getBoolean(
            SHARED_KEY_SETTING_DELETE_MESSAGES_WHEN_EXIT_CHAT_ROOM,
            true
        ) == true
    }

    fun setTransferFileByUser(value: Boolean) {
        editor?.putBoolean(SHARED_KEY_SETTING_TRANSFER_FILE_BY_USER, value)
        editor?.apply()
    }

    fun isSetTransferFileByUser(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_SETTING_TRANSFER_FILE_BY_USER, true) == true
    }

    fun setAudodownloadThumbnail(autodownload: Boolean) {
        editor?.putBoolean(SHARED_KEY_SETTING_AUTODOWNLOAD_THUMBNAIL, autodownload)
        editor?.apply()
    }

    fun isSetAutodownloadThumbnail(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_SETTING_AUTODOWNLOAD_THUMBNAIL, true) == true
    }

    fun setAutoAcceptGroupInvitation(value: Boolean) {
        editor?.putBoolean(SHARED_KEY_SETTING_AUTO_ACCEPT_GROUP_INVITATION, value)
        editor?.commit()
    }

    fun isAutoAcceptGroupInvitation(): Boolean {
        return mSharedPreferences?.getBoolean(
            SHARED_KEY_SETTING_AUTO_ACCEPT_GROUP_INVITATION,
            true
        ) == true
    }

    fun setAdaptiveVideoEncode(value: Boolean) {
        editor?.putBoolean(SHARED_KEY_SETTING_ADAPTIVE_VIDEO_ENCODE, value)
        editor?.apply()
    }

    fun isAdaptiveVideoEncode(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_SETTING_ADAPTIVE_VIDEO_ENCODE, false) == true
    }

    fun setPushCall(value: Boolean) {
        editor?.putBoolean(SHARED_KEY_SETTING_OFFLINE_PUSH_CALL, value)
        editor?.apply()
    }

    fun isPushCall(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_SETTING_OFFLINE_PUSH_CALL, true) == true
    }

    fun setRecordOnServer(value: Boolean) {
        editor?.putBoolean(SHARED_KEY_SETTING_RECORD_ON_SERVER, value)
        editor?.apply()
    }

    fun isRecordOnServer(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_SETTING_RECORD_ON_SERVER, false) == true
    }

    fun setMergeStream(value: Boolean) {
        editor?.putBoolean(SHARED_KEY_SETTING_MERGE_STREAM, value)
        editor?.apply()
    }

    fun isMergeStream(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_SETTING_MERGE_STREAM, false) == true
    }

    fun setGroupsSynced(synced: Boolean) {
        editor?.putBoolean(SHARED_KEY_SETTING_GROUPS_SYNCED, synced)
        editor?.apply()
    }

    fun isGroupsSynced(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_SETTING_GROUPS_SYNCED, false) == true
    }

    fun setContactSynced(synced: Boolean) {
        editor?.putBoolean(SHARED_KEY_SETTING_CONTACT_SYNCED, synced)
        editor?.apply()
    }

    fun isContactSynced(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_SETTING_CONTACT_SYNCED, false) == true
    }

    fun setBlacklistSynced(synced: Boolean) {
        editor?.putBoolean(SHARED_KEY_SETTING_BALCKLIST_SYNCED, synced)
        editor?.apply()
    }

    fun isBacklistSynced(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_SETTING_BALCKLIST_SYNCED, false) == true
    }

    fun setCurrentUserNick(nick: String?) {
        editor?.putString(SHARED_KEY_CURRENTUSER_NICK, nick)
        editor?.apply()
    }

    fun setCurrentUserAvatar(avatar: String?) {
        editor?.putString(SHARED_KEY_CURRENTUSER_AVATAR, avatar)
        editor?.apply()
    }

    fun getCurrentUserNick(): String? {
        return mSharedPreferences?.getString(SHARED_KEY_CURRENTUSER_NICK, null)
    }

    fun getCurrentUserAvatar(): String? {
        return mSharedPreferences?.getString(SHARED_KEY_CURRENTUSER_AVATAR, null)
    }

    fun setCurrentUserName(username: String?) {
        editor?.putString(SHARED_KEY_CURRENTUSER_USERNAME, username)
        editor?.apply()
    }

    fun getCurrentUsername(): String? {
        return mSharedPreferences?.getString(SHARED_KEY_CURRENTUSER_USERNAME, null)
    }

    fun setCurrentUserPwd(pwd: String?) {
        editor?.putString(SHARED_KEY_CURRENTUSER_USER_PASSWORD, pwd)
        editor?.apply()
    }

    fun getCurrentUserPwd(): String? {
        return mSharedPreferences?.getString(SHARED_KEY_CURRENTUSER_USER_PASSWORD, null)
    }

    fun setRestServer(restServer: String?) {
        editor?.putString(SHARED_KEY_REST_SERVER, restServer)?.commit()
        editor?.commit()
    }

    fun getRestServer(): String? {
        return mSharedPreferences?.getString(SHARED_KEY_REST_SERVER, null)
    }

    fun setIMServer(imServer: String?) {
        editor?.putString(SHARED_KEY_IM_SERVER, imServer)
        editor?.commit()
    }

    fun getIMServer(): String? {
        return mSharedPreferences?.getString(SHARED_KEY_IM_SERVER, null)
    }

    fun enableCustomServer(enable: Boolean) {
        editor?.putBoolean(SHARED_KEY_ENABLE_CUSTOM_SERVER, enable)
        editor?.commit()
    }

    fun isCustomServerEnable(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_ENABLE_CUSTOM_SERVER, false) == true
    }

    fun isCustomSetEnable(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_ENABLE_CUSTOM_SET, false) == true
    }

    fun enableCustomSet(enable: Boolean) {
        editor?.putBoolean(SHARED_KEY_ENABLE_CUSTOM_SET, enable)
        editor?.commit()
    }

    fun enableCustomAppkey(enable: Boolean) {
        editor?.putBoolean(SHARED_KEY_ENABLE_CUSTOM_APPKEY, enable)
        editor?.commit()
    }

    fun isCustomAppkeyEnabled(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_ENABLE_CUSTOM_APPKEY, true) == true
    }

    fun getCustomAppkey(): String? {
        return mSharedPreferences?.getString(SHARED_KEY_CUSTOM_APPKEY, "")
    }

    fun setCustomAppkey(appkey: String?) {
        editor?.putString(SHARED_KEY_CUSTOM_APPKEY, appkey)
        editor?.commit()
    }

    fun removeCurrentUserInfo() {
        editor?.remove(SHARED_KEY_CURRENTUSER_NICK)
        editor?.remove(SHARED_KEY_CURRENTUSER_AVATAR)
        editor?.apply()
    }

    fun isMsgRoaming(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_MSG_ROAMING, false) == true
    }

    fun setMsgRoaming(isRoaming: Boolean) {
        editor?.putBoolean(SHARED_KEY_MSG_ROAMING, isRoaming)
        editor?.apply()
    }

    fun isShowMsgTyping(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_SHOW_MSG_TYPING, false) == true
    }

    fun showMsgTyping(show: Boolean) {
        editor?.putBoolean(SHARED_KEY_SHOW_MSG_TYPING, show)
        editor?.apply()
    }

    /**
     * 设置是否自动登录,只有登录成功后，此值才能设置为true
     * @param autoLogin
     */
    fun setAutoLogin(autoLogin: Boolean) {
        editor?.putBoolean(SHARED_KEY_AUTO_LOGIN, autoLogin)
        editor?.commit()
    }

    /**
     * 获取是否是自动登录
     * @return
     */
    fun getAutoLogin(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_AUTO_LOGIN, false) == true
    }

    /**
     * using Https only
     * @param usingHttpsOnly
     */
    fun setUsingHttpsOnly(usingHttpsOnly: Boolean) {
        editor?.putBoolean(SHARED_KEY_HTTPS_ONLY, usingHttpsOnly)
        editor?.commit()
    }

    /**
     * get if using Https only
     * @return
     */
    fun getUsingHttpsOnly(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_HTTPS_ONLY, false) == true
    }

    /**
     * ----------------------------------------- Call Option -----------------------------------------
     */

    /**
     * ----------------------------------------- Call Option -----------------------------------------
     */
    /**
     * Min Video kbps
     * if no value was set, return -1
     * @return
     */
    fun getCallMinVideoKbps(): Int {
        return mSharedPreferences?.getInt(SHARED_KEY_CALL_MIN_VIDEO_KBPS, -1)!!
    }

    fun setCallMinVideoKbps(minBitRate: Int) {
        editor?.putInt(SHARED_KEY_CALL_MIN_VIDEO_KBPS, minBitRate)
        editor?.apply()
    }

    /**
     * Max Video kbps
     * if no value was set, return -1
     * @return
     */
    fun getCallMaxVideoKbps(): Int {
        return mSharedPreferences?.getInt(SHARED_KEY_CALL_MAX_VIDEO_KBPS, -1)!!
    }

    fun setCallMaxVideoKbps(maxBitRate: Int) {
        editor?.putInt(SHARED_KEY_CALL_MAX_VIDEO_KBPS, maxBitRate)
        editor?.apply()
    }

    /**
     * Max frame rate
     * if no value was set, return -1
     * @return
     */
    fun getCallMaxFrameRate(): Int {
        return mSharedPreferences?.getInt(SHARED_KEY_CALL_MAX_FRAME_RATE, -1)!!
    }

    fun setCallMaxFrameRate(maxFrameRate: Int) {
        editor?.putInt(SHARED_KEY_CALL_MAX_FRAME_RATE, maxFrameRate)
        editor?.apply()
    }

    /**
     * audio sample rate
     * if no value was set, return -1
     * @return
     */
    fun getCallAudioSampleRate(): Int {
        return mSharedPreferences?.getInt(SHARED_KEY_CALL_AUDIO_SAMPLE_RATE, -1)!!
    }

    fun setCallAudioSampleRate(audioSampleRate: Int) {
        editor?.putInt(SHARED_KEY_CALL_AUDIO_SAMPLE_RATE, audioSampleRate)
        editor?.apply()
    }

    /**
     * back camera resolution
     * format: 320x240
     * if no value was set, return ""
     */
    fun getCallBackCameraResolution(): String? {
        return mSharedPreferences?.getString(SHARED_KEY_CALL_BACK_CAMERA_RESOLUTION, "")
    }

    fun setCallBackCameraResolution(resolution: String?) {
        editor?.putString(SHARED_KEY_CALL_BACK_CAMERA_RESOLUTION, resolution)
        editor?.apply()
    }

    /**
     * front camera resolution
     * format: 320x240
     * if no value was set, return ""
     */
    fun getCallFrontCameraResolution(): String? {
        return mSharedPreferences?.getString(SHARED_KEY_CALL_FRONT_CAMERA_RESOLUTION, "")
    }

    fun setCallFrontCameraResolution(resolution: String?) {
        editor?.putString(SHARED_KEY_CALL_FRONT_CAMERA_RESOLUTION, resolution)
        editor?.apply()
    }

    /**
     * fixed video sample rate
     * if no value was set, return false
     * @return
     */
    fun isCallFixedVideoResolution(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_CALL_FIX_SAMPLE_RATE, false) == true
    }

    fun setCallFixedVideoResolution(enable: Boolean) {
        editor?.putBoolean(SHARED_KEY_CALL_FIX_SAMPLE_RATE, enable)
        editor?.apply()
    }

    fun setExternalAudioInputResolution(enable: Boolean) {
        editor?.putBoolean(SHARED_KEY_EXTERNAL_INPUT_AUDIO_RESOLUTION, enable)
        editor?.apply()
    }

    fun isExternalAudioInputResolution(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_EXTERNAL_INPUT_AUDIO_RESOLUTION, false) == true
    }

    fun setWatermarkResolution(enable: Boolean) {
        editor?.putBoolean(SHARED_KEY_WATER_MARK_RESOLUTION, enable)
        editor?.apply()
    }

    fun isWatermarkResolution(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_WATER_MARK_RESOLUTION, false) == true
    }

    fun setUseFCM(useFCM: Boolean) {
        editor?.putBoolean(SHARED_KEY_PUSH_USE_FCM, useFCM)
        editor?.apply()
    }

    fun isUseFCM(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_PUSH_USE_FCM, false) == true
    }

    /**
     * set the server port
     * @param port
     */
    fun setIMServerPort(port: Int) {
        editor?.putInt(SHARED_KEY_IM_SERVER_PORT, port)
    }

    fun getIMServerPort(): Int {
        return mSharedPreferences?.getInt(SHARED_KEY_IM_SERVER_PORT, 0)!!
    }

    fun setSortMessageByServerTime(sortByServerTime: Boolean) {
        editor?.putBoolean(SHARED_KEY_SORT_MESSAGE_BY_SERVER_TIME, sortByServerTime)
        editor?.apply()
    }

    fun isSortMessageByServerTime(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_SORT_MESSAGE_BY_SERVER_TIME, true) == true
    }

    /**
     * 是否允许token登录
     * @param isChecked
     */
    fun setEnableTokenLogin(isChecked: Boolean) {
        editor?.putBoolean(SHARED_KEY_ENABLE_TOKEN_LOGIN, isChecked)
        editor?.apply()
    }

    fun isEnableTokenLogin(): Boolean {
        return mSharedPreferences?.getBoolean(SHARED_KEY_ENABLE_TOKEN_LOGIN, false) == true
    }

}