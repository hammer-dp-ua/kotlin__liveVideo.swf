package ua.dp.hammer.video.camera

import ua.dp.hammer.video.camera.CommandConst.Companion.CONST_COMMANDID_VIDEO_STREAM_REQUEST
import java.nio.ByteBuffer

class VideoStreamRequestCommand(
    iCameraId: Int,
    iStreamId: Int,
    iMode: Int,
    iDataChannelId: Int) {
    private val byteBuffer: ByteBuffer

    init {
        val commandHead = CommandHead()
        val requestBody = String.format("<VideoStreamRequestCommand CameraId=\"%d\" StreamId=\"%d\" StreamFormat=\"%d\" DataChannelId=\"%d\" />",
            iCameraId, iStreamId, iMode, iDataChannelId)
        val payloadLength = requestBody.length + 1

        commandHead.setCommandId(CONST_COMMANDID_VIDEO_STREAM_REQUEST)
        commandHead.setPayloadLength(payloadLength)
        byteBuffer = ByteBuffer
            .allocate(CommandHead.getSize() + payloadLength)
            .put(commandHead.getByteBuffer().array())
            .put(requestBody.toByteArray())
            .put(0)
    }

    fun getByteBuffer(): ByteBuffer {
        return byteBuffer
    }
}