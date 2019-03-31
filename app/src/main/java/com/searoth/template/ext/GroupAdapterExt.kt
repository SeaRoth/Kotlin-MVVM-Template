package com.searoth.template.ext

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder

fun GroupAdapter<ViewHolder>.withNewSection(block: Section.() -> Unit) {
    val newSection = Section()
    newSection.block()
    this.add(newSection)
}

