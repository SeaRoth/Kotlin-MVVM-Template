package com.searoth.template.infrastructure.ui.base

import com.searoth.template.R
import com.searoth.template.databinding.ItemNavBinding
import com.xwray.groupie.databinding.BindableItem

class LambaNavItem(val label: Int, val lambda: () -> Unit) : BindableItem<ItemNavBinding>() {
    override fun getLayout(): Int {
        return R.layout.item_nav
    }

    override fun bind(viewBinding: ItemNavBinding, position: Int) {
        viewBinding.text.setText(label)
    }
}