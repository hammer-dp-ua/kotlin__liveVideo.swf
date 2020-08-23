package ua.dp.hammer.video.camera

import ua.dp.hammer.video.camera.CommonConst.Companion.CONST_LENGTH_COMMAND_HEAD
import java.nio.ByteBuffer

class CommandHead {
    private val commandId = ByteArray(4)
    private val version = byteArrayOf(0x1)
    private val bySourceId = ByteArray(32) // empty
    private val byDestId = ByteArray(32) // 0x31 0x30 0x31 0x38 0x41 0x33
    private val byCharSet = ByteArray(0)
    private val byReserve = ByteArray(2) // empty
    private val payloadLength = ByteArray(4) // User length

    init {
        byDestId[31] = 0x31 //'1'
        byDestId[30] = 0x30 //'0'
        byDestId[29] = 0x31 //'1'
        byDestId[28] = 0x38 //'8'
        byDestId[27] = 0x41 //'A'
        byDestId[26] = 0x33 //'3'
    }

    fun setCommandId(commandId: Int) {
        this.commandId[0] = commandId.toByte()
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