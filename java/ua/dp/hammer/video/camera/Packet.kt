package ua.dp.hammer.video.camera

import ua.dp.hammer.video.camera.CommonConst.Companion.CONST_LENGTH_PACKET_HEAD
import ua.dp.hammer.video.camera.CommonConst.Companion.CONST_LENGTH_PACKET_RESERVE
import java.lang.IllegalStateException
import java.nio.ByteBuffer

class Packet {
    private val packetHead = PacketHead()
    var payload: ByteBuffer? = null

    fun setSessionId(sessionId: Short) {
        packetHead.setSessionId(sessionId)
    }

    fun getByteBuffer(): ByteBuffer {
        val payloadCapacity = payload?.capacity() ?: throw IllegalStateException("Payload isn't set")
        val byteBuffer = ByteBuffer.allocate(CONST_LENGTH_PACKET_HEAD + payloadCapacity)

        packetHead.setPayloadLength(payloadCapacity)
        byteBuffer.put(packetHead.getByteBuffer().array())
        byteBuffer.put(payload?.array() ?: throw IllegalStateException("Payload isn't set"))
        return byteBuffer
    }

    fun parsePacketHead(response: ByteArray): Int {
        packetHead.parse(response)
        return getPacketHeadSize()
    }

    companion object {
        fun getPacketHeadSize(): Int {
            return CONST_LENGTH_PACKET_HEAD
        }
    }

    private class PacketHead {
        val byMagic = byteArrayOf(0xFF.toByte())
        val byVersion = byteArrayOf(0x01)
        val iSeq = ByteArray(4)
        val iAck = ByteArray(4)
        val byFlag = byteArrayOf(0x00)
        val iOffset = ByteArray(4)
        val sessionId = ByteArray(2)
        val sReserve = ByteArray(CONST_LENGTH_PACKET_RESERVE)
        var payloadLength = ByteArray(4)

        init {
            fillBytesFromInt(1, iSeq)
            fillBytesFromInt(1, iAck)
        }

        fun setSessionId(sessionIdParam: Short) {
            fillBytesFromShort(sessionIdParam, sessionId)
        }

        fun setPayloadLength(length: Int) {
            fillBytesFromInt(length, payloadLength)
        }

        fun getByteBuffer(): ByteBuffer {
            val byteBuffer = ByteBuffer.allocate(CONST_LENGTH_PACKET_HEAD)

            byteBuffer.put(byMagic)
            byteBuffer.put(byVersion)
            byteBuffer.put(iSeq)
            byteBuffer.put(iAck)
            byteBuffer.put(byFlag)
            byteBuffer.put(iOffset)
            byteBuffer.put(sessionId)
            byteBuffer.put(sReserve)
            byteBuffer.put(payloadLength)
            return byteBuffer
        }

        fun parse(response: ByteArray) {
            fillArrays(response, 0, byMagic, byVersion, iSeq, iAck, byFlag, iOffset, sessionId, sReserve, payloadLength)
        }
    }
}