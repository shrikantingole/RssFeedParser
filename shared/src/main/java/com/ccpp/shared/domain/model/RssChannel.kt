package com.ccpp.shared.domain.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root


@Root(name = "channel")
class RssChannel {
    @get:Element
    val title: String? = null

    @get:ElementList(inline = true, required = false)
    var item: List<RssItem>? = null


}