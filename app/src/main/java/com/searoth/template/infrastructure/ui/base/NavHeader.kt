package com.searoth.template.infrastructure.ui.base

import androidx.databinding.ViewDataBinding
import com.searoth.template.R
import com.searoth.template.databinding.HeaderNavBinding
import com.xwray.groupie.databinding.BindableItem

class NavHeader(private val navHeaderViewModel: NavHeaderViewModel) : BindableItem<ViewDataBinding>() {

    override fun getLayout(): Int {
        return R.layout.header_nav
    }

    override fun bind(viewBinding: ViewDataBinding, position: Int) {
        (viewBinding as HeaderNavBinding).activityViewModel = navHeaderViewModel
    }
}