package com.xwei.xchat.section.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentTransaction
import com.hyphenate.easeui.ui.base.EaseBaseFragment
import com.xwei.commonutils.util.LogUtil
import com.xwei.xchat.R
import com.xwei.xchat.section.base.BaseActivity
import com.xwei.xchat.section.contact.ContactListFragment
import com.xwei.xchat.section.conversation.ConversationListFragment
import com.xwei.xchat.section.me.AboutMeFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @desc
 * @author wei
 * @date  2022/1/5
 **/
class MainActivity : BaseActivity() {

    private var mConversationListFragment: EaseBaseFragment? = null
    private var mCurrentFragment: EaseBaseFragment? = null
    private var mFriendsFragment: EaseBaseFragment? = null
    private var mAboutMeFragment: EaseBaseFragment? = null

    companion object {
        fun startAction(context: Context) {
            val starter = Intent(context, MainActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.conversation_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_friend -> { // 添加好友

            }
        }
        return true
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        setFitSystem(false)
        // 可以动态显示隐藏相应tab
        //navView.getMenu().findItem(R.id.em_main_nav_me).setVisible(false);
        switchToHome()
        checkIfShowSavedFragment(savedInstanceState)
    }

    override fun initSetup() {
        super.initSetup()
        var title = resources.getString(R.string.em_main_title_home)
        nav_view.setOnNavigationItemReselectedListener { menuItem ->
            LogUtil.e(TAG, "menuItem: ${menuItem.toString()}")
            when (menuItem.itemId) {
                R.id.em_main_nav_home -> {  // 会话
                    switchToHome()
                    title = resources.getString(R.string.em_main_title_home)
                }

                R.id.em_main_nav_friends -> { // 通讯录
                    switchToFriends()
                    title = resources.getString(R.string.em_main_title_friends)
                }

                R.id.em_main_nav_me -> {   // 我
                    switchToAboutMe()
                    title = resources.getString(R.string.em_main_title_me)
                }
            }
            title_bar_main.setTitle(title)
            invalidateOptionsMenu()
        }
    }

    private fun switchToHome() {
        if (mConversationListFragment == null) {
            mConversationListFragment = ConversationListFragment()
        }
        replace(mConversationListFragment!!, "conversation")
    }

    private fun switchToFriends() {
        if (mFriendsFragment == null) {
            mFriendsFragment = ContactListFragment()
        }
        replace(mFriendsFragment!!, "contact")
    }

    private fun switchToAboutMe() {
        if (mAboutMeFragment == null) {
            mAboutMeFragment = AboutMeFragment()
        }
        //获取自己用户信息
        fetchSelfInfo()
        replace(mAboutMeFragment!!, "me")
    }

    private fun replace(fragment: EaseBaseFragment, tag: String) {
        if (mCurrentFragment != fragment) {
            val fragmentTransaction : FragmentTransaction = supportFragmentManager.beginTransaction()
            mCurrentFragment?.let { fragmentTransaction.hide(it) }
            mCurrentFragment = fragment
            if (!fragment.isAdded) {
                fragmentTransaction.add(R.id.fl_main_fragment, fragment, tag).show(fragment).commitNow()
            } else {
                fragmentTransaction.show(fragment).commitNow()
            }
        }
    }

    private fun fetchSelfInfo() {
        
    }

    private fun checkIfShowSavedFragment(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            val tag = it.getString("tag")
            if (tag?.isNotEmpty() == true) {
                val fragment = supportFragmentManager.findFragmentByTag(tag)
                if (fragment is EaseBaseFragment) {
                    replace(fragment, tag)
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mCurrentFragment?.let { outState.putString("tag", it.tag) }
    }

}