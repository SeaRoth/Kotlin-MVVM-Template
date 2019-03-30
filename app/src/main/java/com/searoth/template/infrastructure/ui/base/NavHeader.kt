package com.searoth.planner.infrastructure.ui.base

import androidx.databinding.ViewDataBinding
import com.searoth.planner.R
import com.searoth.planner.databinding.HeaderNavBinding
import com.searoth.planner.infrastructure.ui.base.NavHeaderViewModel
import com.xwray.groupie.databinding.BindableItem

class NavHeader(private val navHeaderViewModel: NavHeaderViewModel) : BindableItem<ViewDataBinding>() {

    override fun getLayout(): Int {
        return R.layout.header_nav
    }

    override fun bind(viewBinding: ViewDataBinding, position: Int) {
        (viewBinding as HeaderNavBinding).activityViewModel = navHeaderViewModel
    }
}