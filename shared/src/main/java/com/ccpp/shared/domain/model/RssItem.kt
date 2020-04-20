package com.ccpp.shared.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class RssItem : Parcelable {

    var title: String? = null

    var link: String? = null

    var pubDate: String? = null

    var description: String? = null

    var guid: String? = null

}
