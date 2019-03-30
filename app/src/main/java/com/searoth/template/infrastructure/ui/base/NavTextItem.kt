package com.searoth.planner.infrastructure.ui.base

import android.widget.FrameLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.searoth.planner.R
import com.searoth.planner.databinding.NavTextItemBinding
import com.searoth.planner.infrastructure.common.utils.dpToPx
import com.xwray.groupie.databinding.BindableItem


class NavTextItem(val label: String) : BindableItem<NavTextItemBinding>() {
    override fun getLayout(): Int {
        return R.layout.nav_text_item
    }

    override fun bind(viewBinding: NavTextItemBinding, position: Int) {
        viewBinding.text.text = label
        adjustPadding(viewBinding.text)
    }

    private fun adjustPadding(textView: TextView) {
        val params =
            ConstraintLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        if (textView.text.contains("VERSION")) {
            params.setMargins(dpToPx(16), dpToPx(0), dpToPx(16), dpToPx(0))
            textView.layoutParams = params
        }

        if (textView.text.contains("COPYRIGHT")) {
            params.setMargins(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(16))
            textView.layoutParams = params
        }

    }

}