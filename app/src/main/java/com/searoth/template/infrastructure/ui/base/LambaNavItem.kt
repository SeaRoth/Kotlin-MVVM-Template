package com.searoth.planner.infrastructure.ui.base

import com.searoth.planner.R
import com.searoth.planner.databinding.ItemNavBinding
import com.xwray.groupie.databinding.BindableItem

class LambaNavItem(val label: Int, val lambda: () -> Unit) : BindableItem<ItemNavBinding>() {
    override fun getLayout(): Int {
        return R.layout.item_nav
    }

    override fun bind(viewBinding: ItemNavBinding, position: Int) {
        viewBinding.text.setText(label)
    }
}