package ua.dp.hammer.video.camera

import org.simpleframework.xml.core.Persister
import ua.dp.hammer.video.camera.response.xml.VideoStreamResponseCommand
import java.nio.charset.Charset

class AVStreamConnector {
    fun open(sessionId: Short) {
        val videoStreamRequestCommand = VideoStreamRequestCommand(1, 1, -3, -1)
        val response = TCPTransfer().sendCommand(videoStreamRequestCommand.getByteBuffer(), sessionId)

        val commandHead = CommandHead()
        val parsedPacket = Packet()
        var readBytes = parsedPacket.parsePacketHead(response)

        readBytes += commandHead.parse(response, readBytes)

        val xmlResponseLength = commandHead.getPayloadLength() - 1
        val xmlResponse = String(response, readBytes, xmlResponseLength, Charset.forName("US-ASCII"))

        val serializer = Persister()
        val responseObject = serializer.read(VideoStreamResponseCommand::class.java, xmlResponse)

        println(xmlResponse)
    }
}