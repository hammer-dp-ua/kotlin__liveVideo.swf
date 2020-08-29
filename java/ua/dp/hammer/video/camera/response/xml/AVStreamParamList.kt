package ua.dp.hammer.video.camera.response.xml

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "AVStreamParamList")
class AVStreamParamList {
    var aVStreamParam: AVStreamParam? = null
        @Element(name = "AVStreamParam") get
        @Element(name = "AVStreamParam") set
}