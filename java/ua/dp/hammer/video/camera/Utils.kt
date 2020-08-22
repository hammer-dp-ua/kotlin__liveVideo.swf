package ua.dp.hammer.video.camera

import java.lang.IllegalArgumentException
import java.nio.ByteBuffer

fun fillBytesFromString(stringVal: String?, charArray: ByteArray) {
    if (stringVal == null) {
        return
    }

    for ((index, charValue: Char) in stringVal.toCharArray().withIndex()) {
        if (index < charArray.size) {
            charArray[index] = charValue.toByte()
        } else {
            break
        }
    }
}

fun fillBytesFromInt(digit: Int?, digitArray: ByteArray) {
    if (digit == null) {
        return
    }

    if (digitArray.size != 4) {
        throw IllegalArgumentException("Check array size")
    }

    digitArray[3] = digit.toByte()
    digitArray[2] = (digit ushr 8).toByte()
    digitArray[1] = (digit ushr 16).toByte()
    digitArray[0] = (digit ushr 24).toByte()
}

fun fillBytesFromShort(digit: Short?, digitArray: ByteArray) {
    if (digit == null) {
        return
    }

    val toIntDigit = digit.toInt()

    digitArray[1] = digit.toByte()
    digitArray[0] = (toIntDigit ushr 8).toByte()
}

fun createByteBuffer(vararg arrays: ByteArray): ByteBuffer {
    var sumSize = 0

    for (array in arrays) {
        sumSize += array.size
    }

    val buffer = ByteBuffer.allocate(sumSize)
    var offset = 0

    for (array in arrays) {
        buffer.put(array, offset, array.size)
        offset += array.size
    }
    return buffer
}