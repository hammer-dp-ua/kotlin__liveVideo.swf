package ua.dp.hammer.video.camera

import ua.dp.hammer.video.camera.CommonConst.Companion.CONST_LENGTH_COMMAND_HEAD
import java.nio.ByteBuffer

class CommandHead {
    private val commandId = ByteArray(4)
    private val version = byteArrayOf(0x1)
    private val bySourceId = ByteArray(32)
    private val byDestId = ByteArray(32)
    private val byCharSet = ByteArray(1)
    private val byReserve = ByteArray(2)
    private val payloadLength = ByteArray(4)

    init {
        byDestId[0] = 0x30 //'0'
        byDestId[1] = 0x30 //'0'
        byDestId[2] = 0x31 //'1'
        byDestId[3] = 0x38 //'8'
        byDestId[4] = 0x41 //'A'
        byDestId[5] = 0x32 //'2'
    }

    fun setCommandId(commandId: Int) {
        this.commandId[3] = commandId.toByte()
    }

    fun setPayloadLength(length: Int) {
        fillBytesFromInt(length, payloadLength)
    }

    fun getByteBuffer(): ByteBuffer {
        return createByteBuffer(commandId, version, bySourceId, byDestId, byCharSet, byReserve, payloadLength)
    }

    fun parse(response: ByteArray, startIndex: Int): Int {
        fillArrays(response, startIndex, commandId, version, bySourceId, byDestId, byCharSet, byReserve, payloadLength)
        return getSize()
    }

    companion object {
        fun getSize(): Int {
            return CONST_LENGTH_COMMAND_HEAD
        }
    }
}