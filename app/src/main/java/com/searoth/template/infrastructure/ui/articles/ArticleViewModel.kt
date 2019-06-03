package com.searoth.template.infrastructure.ui.articles

import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding
import com.searoth.template.domain.models.news.Article
import com.searoth.template.infrastructure.ui.base.BaseBindableViewModel
import com.xwray.groupie.databinding.BindableItem

class ArticleViewModel(val article: Article, val articlesActivityViewModel: ArticlesActivityViewModel) : BaseBindableViewModel() {
    override fun getItemFactory(): (BaseBindableViewModel) -> BindableItem<ViewDataBinding> {
        return { it -> ArticleItem((it as ArticleViewModel))}
    }

    val title   = ObservableField<String>(article.title)
    val imgUrl  = ObservableField<String?>(article.urlToImage)
    val desc    = ObservableField<String>(article.description)
    val author  = ObservableField<String>(article.author)
    val date   = ObservableField<String>(article.publishedAt)
    val source   = ObservableField<String>(article.source.name)
}