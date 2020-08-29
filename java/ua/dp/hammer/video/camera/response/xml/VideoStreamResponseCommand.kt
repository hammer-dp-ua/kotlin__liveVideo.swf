package ua.dp.hammer.video.camera.response.xml

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "VideoStreamResponseCommand")
open class VideoStreamResponseCommand {
    var consumerId: Int? = null
        @Attribute(name = "ConsumerId") get
        @Attribute(name = "ConsumerId") set

    var streamAddr: StreamAddr? = null
        @Element(name = "StreamAddr") get
        @Element(name = "StreamAddr") set

    var sVStreamParamList: AVStreamParamList? = null
        @Element(name = "AVStreamParamList") get
        @Element(name = "AVStreamParamList") set
}