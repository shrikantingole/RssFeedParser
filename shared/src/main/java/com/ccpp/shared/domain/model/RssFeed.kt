package com.ccpp.shared.domain.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root


@Root(name = "rss")
class RssFeed {
    @get:Element
    var channel: RssChannel? = null

}