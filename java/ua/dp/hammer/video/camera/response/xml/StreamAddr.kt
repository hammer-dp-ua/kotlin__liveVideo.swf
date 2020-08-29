package ua.dp.hammer.video.camera.response.xml

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "StreamAddr")
class StreamAddr {
    var ip: String? = null
        @Attribute(name = "IP") get
        @Attribute(name = "IP") set

    var port: Int? = null
        @Attribute(name = "Port") get
        @Attribute(name = "Port") set


    var iPProtoVar: Int? = null
        @Attribute(name = "IPProtoVar") get
        @Attribute(name = "IPProtoVar") set
}