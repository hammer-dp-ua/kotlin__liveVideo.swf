package ua.dp.hammer.video.camera

import java.nio.ByteBuffer

class User {
    private val sourceId = ByteArray(32)
    private val ip = ByteArray(48)
    private val port = byteArrayOf(0x75, 0x31) //30001
    private val userName = ByteArray(32)
    private val userPassword = ByteArray(32)

    fun setUserName(name: String) {
        fillBytesFromString(name, userName)
    }

    fun setUserPassword(password: String) {
        fillBytesFromString(password, userPassword)
    }

    fun getByteBuffer(): ByteBuffer {
        return createByteBuffer(sourceId, ip, port, userName, userPassword)
    }

    fun getSize(): Int {
        return sourceId.size + ip.size + port.size + userName.size + userPassword.size
    }
}