package com.ccpp.shared.domain.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
class RssItem {
    @get:Element
    val title: String? = null
    @get:Element
    val link: String? = null
    @get:Element
    val pubDate: String? = null
    @get:Element
    val description: String? = null
    @get:Element
    val guid: String? = null

    override fun toString(): String {
        return ("RssItem [title=" + title + ", link=" + link + ", pubDate=" + pubDate
                + ", description=" + description + "]")
    }
}
