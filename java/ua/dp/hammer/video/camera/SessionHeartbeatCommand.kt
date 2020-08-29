package ua.dp.hammer.video.camera

import ua.dp.hammer.video.camera.CommandConst.Companion.CONST_COMMANDID_SESSION_HEARTBEAT
import java.nio.ByteBuffer

class SessionHeartbeatCommand(sessionId: Short) {
    private val byteBuffer: ByteBuffer

    init {
        val sessionIdArray = ByteArray(2)
        val commandHead = CommandHead()

        fillBytesFromShort(sessionId, sessionIdArray)
        commandHead.setPayloadLength(sessionIdArray.size)
        commandHead.setCommandId(CONST_COMMANDID_SESSION_HEARTBEAT)

        byteBuffer = ByteBuffer
                .allocate(sessionIdArray.size + CommandHead.getSize())
                .put(commandHead.getByteBuffer().array())
                .put(sessionIdArray)
    }

    fun getByteBuffer(): ByteBuffer {
        return byteBuffer
    }
}