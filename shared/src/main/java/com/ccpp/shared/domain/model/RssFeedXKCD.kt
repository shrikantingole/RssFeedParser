package com.ccpp.shared.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class RssFeedXKCD : Parcelable {
    var channel: RssChannel? = null
    fun getChannel() {
        val chas = channel
    }
}