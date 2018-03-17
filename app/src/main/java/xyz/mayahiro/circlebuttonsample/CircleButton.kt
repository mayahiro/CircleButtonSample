package xyz.mayahiro.circlebuttonsample

import android.content.Context
import android.content.res.ColorStateList
import android.databinding.DataBindingUtil
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v4.widget.ImageViewCompat
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import xyz.mayahiro.circlebuttonsample.databinding.CircleButtonBinding

class CircleButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding = DataBindingUtil.inflate<CircleButtonBinding>(LayoutInflater.from(context), R.layout.circle_button, this, true)

    private var iconSrc: Int
    private var iconSize: Int
    private var iconTint: Int
    private var text: String? = null
    private var textGravity: Int
    private var textColor: Int
    private var textSize: Int

    init {
        // Load attributes
        val a = context.obtainStyledAttributes(attrs, R.styleable.CircleButton, defStyleAttr, 0)

        iconSrc = a.getResourceId(R.styleable.CircleButton_iconSrcCompat, R.drawable.ic_android)
        iconSize = a.getDimensionPixelSize(R.styleable.CircleButton_iconSize, 0)
        iconTint = a.getColor(R.styleable.CircleButton_iconTint, ContextCompat.getColor(context, android.R.color.black))

        if (a.hasValue(R.styleable.CircleButton_text)) {
            text = a.getString(R.styleable.CircleButton_text)
        }
        textGravity = a.getInt(R.styleable.CircleButton_textGravity, 0)
        textColor = a.getColor(R.styleable.CircleButton_textColor, ContextCompat.getColor(context, android.R.color.black))
        textSize = a.getDimensionPixelSize(R.styleable.CircleButton_textSize, 0)

        a.recycle()

        binding.imageView.setImageDrawable(ContextCompat.getDrawable(context, iconSrc))
        val layoutParams = binding.imageView.layoutParams
        layoutParams.height = iconSize
        layoutParams.width = iconSize
        binding.imageView.layoutParams = layoutParams
        ImageViewCompat.setImageTintList(binding.imageView, ColorStateList.valueOf(iconTint))

        if (TextUtils.isEmpty(text)) {
            binding.textView.visibility = View.GONE
        } else {
            binding.textView.visibility = View.VISIBLE
            binding.textView.text = text
            binding.textView.gravity = when (textGravity) {
                1 -> Gravity.START
                2 -> Gravity.END
                else -> Gravity.CENTER
            }
            binding.textView.setTextColor(textColor)
            binding.textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
        }
    }
}
