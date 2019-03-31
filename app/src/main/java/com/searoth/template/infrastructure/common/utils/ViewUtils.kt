package com.searoth.template.infrastructure.common.utils

import android.widget.EditText
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import com.searoth.template.infrastructure.TemplateApplication

fun AndroidViewModel.app(): TemplateApplication {
    return getApplication()
}

fun AndroidViewModel.getString(@StringRes stringRes: Int): String {
    return app().getString(stringRes)
}

fun AndroidViewModel.getString(@StringRes stringRes: Int, vararg args: Any?): String {
    return app().getString(stringRes, *args)
}

fun EditText.on(actionId: Int, func: () -> Unit) {
    setOnEditorActionListener { _, receivedActionId, _ ->

        if (actionId == receivedActionId) {
            func()
        }

        true
    }
}
