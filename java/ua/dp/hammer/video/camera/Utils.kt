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
    for (array in arrays) {
        buffer.put(array)
    }
    return buffer
}

fun fillArrays(source: ByteArray, startIndex: Int, vararg arrays: ByteArray) {
    var readBytes = 0

    for (array in arrays) {
        source.copyInto(array, 0, startIndex + readBytes, readBytes + array.size)
        readBytes += array.size
    }
}

@ExperimentalUnsignedTypes
fun parseInt(byteArray: ByteArray, startIndex: Int): Int {
    var result = 0

    result = result.or(byteArray[startIndex].toUByte().toInt() shl 24)
    result = result.or(byteArray[startIndex + 1].toUByte().toInt() shl 16)
    result = result.or(byteArray[startIndex + 2].toUByte().toInt() shl 8)
    result = result.or(byteArray[startIndex + 3].toUByte().toInt())
    return result
}