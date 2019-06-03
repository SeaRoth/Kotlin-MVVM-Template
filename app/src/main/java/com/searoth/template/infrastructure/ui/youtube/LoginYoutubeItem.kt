package com.searoth.template.infrastructure.ui.youtube

import androidx.databinding.ViewDataBinding
import com.searoth.template.R
import com.searoth.template.databinding.YoutubeLoginBinding
import com.xwray.groupie.databinding.BindableItem

class LoginYoutubeItem(val youtubeActivityViewModel: YoutubeActivityViewModel) : BindableItem<ViewDataBinding>() {

    override fun getLayout(): Int {
        return R.layout.youtube_login
    }

    override fun bind(viewBinding: ViewDataBinding, position: Int) {
        (viewBinding as YoutubeLoginBinding).activityViewModel = youtubeActivityViewModel
    }
}