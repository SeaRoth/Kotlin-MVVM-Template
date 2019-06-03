package com.searoth.template.infrastructure.ui.articles

import androidx.databinding.ViewDataBinding
import com.searoth.template.R
import com.searoth.template.databinding.ItemArticleBinding
import com.xwray.groupie.databinding.BindableItem

class ArticleItem constructor(var articleViewModel: ArticleViewModel) : BindableItem<ViewDataBinding>() {
    override fun getLayout(): Int {
        return R.layout.item_article
    }

    override fun bind(viewBinding: ViewDataBinding, position: Int) {
        (viewBinding as ItemArticleBinding).apply {
            viewModel = articleViewModel
        }
    }

}