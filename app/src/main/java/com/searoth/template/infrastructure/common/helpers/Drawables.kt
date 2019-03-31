package com.searoth.template.infrastructure.common.helpers

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.annotation.AnyRes
import androidx.annotation.NonNull
import com.searoth.template.BuildConfig
import com.searoth.template.R


fun Int.getDrawablePath(): String {
    return Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + this).toString()
}

fun getDrawableIdFromFileName(context: Context, nameOfDrawable: String) : Int {
    return context.resources.getIdentifier(nameOfDrawable, "drawable", context.packageName)
}

fun getURLForResource(resourceId: Int): String {
    return Uri.parse("android.resource://" + R::class.java.getPackage()!!.name + "/" + resourceId).toString()
}

fun getUriToDrawable(@NonNull context:Context,
                     @AnyRes drawableId:Int): Uri {
    return Uri.parse(
        ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + context.resources.getResourcePackageName(drawableId)
                + '/'.toString() + context.resources.getResourceTypeName(drawableId)
                + '/'.toString() + context.resources.getResourceEntryName(drawableId)
    )
}