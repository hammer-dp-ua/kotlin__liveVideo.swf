package ua.dp.hammer.video.camera.response.xml

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "AVStreamParam")
class AVStreamParam {
    var cameraId: Int? = null
        @Attribute(name = "CameraId") get
        @Attribute(name = "CameraId") set

    var streamId: Int? = null
        @Attribute(name = "StreamId") get
        @Attribute(name = "StreamId") set

    var streamName: String? = null
        @Attribute(name = "StreamName") get
        @Attribute(name = "StreamName") set

    var videoHeight: Int? = null
        @Attribute(name = "VideoHeight") get
        @Attribute(name = "VideoHeight") set

    var videoWidth: Int? = null
        @Attribute(name = "VideoWidth") get
        @Attribute(name = "VideoWidth") set

    var frameRate: Int? = null
        @Attribute(name = "FrameRate") get
        @Attribute(name = "FrameRate") set

    var bitRateType: Int? = null
        @Attribute(name = "BitRateType") get
        @Attribute(name = "BitRateType") set

    var bitRate: Int? = null
        @Attribute(name = "BitRate") get
        @Attribute(name = "BitRate") set

    var quality: Int? = null
        @Attribute(name = "Quality") get
        @Attribute(name = "Quality") set

    var frameInterval: Int? = null
        @Attribute(name = "IFrameInterval") get
        @Attribute(name = "IFrameInterval") set

    var frameIntervalUnit: Int? = null
        @Attribute(name = "IFrameIntervalUnit") get
        @Attribute(name = "IFrameIntervalUnit") set

    var videoEncoderType: Int? = null
        @Attribute(name = "VideoEncoderType") get
        @Attribute(name = "VideoEncoderType") set

    var audioEncoderType: Int? = null
        @Attribute(name = "AudioEncoderType") get
        @Attribute(name = "AudioEncoderType") set
}