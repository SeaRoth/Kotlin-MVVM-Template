package com.searoth.template.infrastructure.ui.base

import android.content.Context
import android.content.Intent
import android.view.View
import com.searoth.template.R
import com.searoth.template.databinding.ItemNavBinding

import com.xwray.groupie.databinding.BindableItem

class NavItem(
    val label: Int,
    val newIntent: (Context) -> Intent,
    val iconResource: Int? = null,
    val divider: Boolean = false
) : BindableItem<ItemNavBinding>() {
    override fun getLayout(): Int {
        return R.layout.item_nav
    }

    override fun bind(viewBinding: ItemNavBinding, position: Int) {
        viewBinding.text.setText(label)
        iconResource?.let {
            viewBinding.icon.setImageResource(iconResource)
            viewBinding.icon.visibility = View.VISIBLE
        }
        if (divider) viewBinding.divider.visibility = View.VISIBLE
    }

}