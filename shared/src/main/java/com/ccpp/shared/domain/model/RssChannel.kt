package com.ccpp.shared.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class RssChannel : Parcelable {
    var title: String? = null

    var item: List<RssItem>? = null


}