package com.xwei.xchat.common.widget

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.hyphenate.easeui.widget.EaseImageView
import com.xwei.xchat.R

/**
 * @desc
 * @author wei
 * @date  2022/1/5
 **/
class ArrowItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var avatar: EaseImageView? = null
    private var tvTitle: TextView? = null
    private var tvContent: TextView? = null
    private var ivArrow: ImageView? = null
    private var viewDivider: View? = null
    private var title: String? = null
    private var content: String? = null
    private var titleColor = 0
    private var contentColor = 0
    private var titleSize = 0f
    private var contentSize = 0f

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val root: View = LayoutInflater.from(context).inflate(R.layout.layout_item_arrow, this)
        avatar = findViewById(R.id.avatar)
        tvTitle = findViewById(R.id.tv_title)
        tvContent = findViewById(R.id.tv_content)
        ivArrow = findViewById(R.id.iv_arrow)
        viewDivider = findViewById(R.id.view_divider)

        val a = context.obtainStyledAttributes(attrs, R.styleable.ArrowItemView)
        val titleResourceId = a.getResourceId(R.styleable.ArrowItemView_arrowItemTitle, -1)
        title = a.getString(R.styleable.ArrowItemView_arrowItemTitle)
        if (titleResourceId != -1) {
            title = getContext().getString(titleResourceId)
        }
        tvTitle?.text = title

        val titleColorId = a.getResourceId(R.styleable.ArrowItemView_arrowItemTitleColor, -1)
        titleColor = a.getColor(
            R.styleable.ArrowItemView_arrowItemTitleColor,
            ContextCompat.getColor(getContext(), R.color.em_color_common_text_black)
        )
        if (titleColorId != -1) {
            titleColor = ContextCompat.getColor(getContext(), titleColorId)
        }
        tvTitle?.setTextColor(titleColor)

        val titleSizeId = a.getResourceId(R.styleable.ArrowItemView_arrowItemTitleSize, -1)
        titleSize =
            a.getDimension(R.styleable.ArrowItemView_arrowItemTitleSize, sp2px(getContext(), 14f))
        if (titleSizeId != -1) {
            titleSize = resources.getDimension(titleSizeId)
        }
        tvTitle?.paint?.textSize = titleSize

        val contentResourceId = a.getResourceId(R.styleable.ArrowItemView_arrowItemContent, -1)
        content = a.getString(R.styleable.ArrowItemView_arrowItemContent)
        if (contentResourceId != -1) {
            content = getContext().getString(contentResourceId)
        }
        tvContent?.text = content

        val contentColorId = a.getResourceId(R.styleable.ArrowItemView_arrowItemContentColor, -1)
        contentColor = a.getColor(
            R.styleable.ArrowItemView_arrowItemContentColor,
            ContextCompat.getColor(getContext(), R.color.em_color_common_text_gray)
        )
        if (contentColorId != -1) {
            contentColor = ContextCompat.getColor(getContext(), contentColorId)
        }
        tvContent?.setTextColor(contentColor)

        val contentSizeId = a.getResourceId(R.styleable.ArrowItemView_arrowItemContentSize, -1)
        contentSize = a.getDimension(R.styleable.ArrowItemView_arrowItemContentSize, 14f)
        if (contentSizeId != -1) {
            contentSize = resources.getDimension(contentSizeId)
        }
        tvContent?.textSize = contentSize

        val showDivider = a.getBoolean(R.styleable.ArrowItemView_arrowItemShowDivider, true)
        viewDivider?.visibility = if (showDivider) VISIBLE else GONE

        val showArrow = a.getBoolean(R.styleable.ArrowItemView_arrowItemShowArrow, true)
        ivArrow?.visibility = if (showArrow) VISIBLE else GONE

        val showAvatar = a.getBoolean(R.styleable.ArrowItemView_arrowItemShowAvatar, false)
        avatar?.visibility = if (showAvatar) VISIBLE else GONE

        val avatarSrcResourceId = a.getResourceId(R.styleable.ArrowItemView_arrowItemAvatarSrc, -1)
        if (avatarSrcResourceId != -1) {
            avatar?.setImageResource(avatarSrcResourceId)
        }

        val avatarHeightId = a.getResourceId(R.styleable.ArrowItemView_arrowItemAvatarHeight, -1)
        var height = a.getDimension(R.styleable.ArrowItemView_arrowItemAvatarHeight, 0f)
        if (avatarHeightId != -1) {
            height = resources.getDimension(avatarHeightId)
        }

        val avatarWidthId = a.getResourceId(R.styleable.ArrowItemView_arrowItemAvatarWidth, -1)
        var width = a.getDimension(R.styleable.ArrowItemView_arrowItemAvatarWidth, 0f)
        if (avatarWidthId != -1) {
            width = resources.getDimension(avatarWidthId)
        }

        a.recycle()

        val params = avatar?.layoutParams
        params?.height = if (height == 0f) ViewGroup.LayoutParams.WRAP_CONTENT else height.toInt()
        params?.width = if (width == 0f) ViewGroup.LayoutParams.WRAP_CONTENT else width.toInt()
    }

    fun getTvContent(): TextView? {
        return tvContent
    }

    fun getTvTitle(): TextView? {
        return tvTitle
    }

    fun getAvatar(): EaseImageView? {
        return avatar
    }

    /**
     * sp to px
     * @param context
     * @param value
     * @return
     */
    fun sp2px(context: Context, value: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            value,
            context.resources.displayMetrics
        )
    }

}