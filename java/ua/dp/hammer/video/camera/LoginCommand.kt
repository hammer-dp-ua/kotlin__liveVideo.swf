package ua.dp.hammer.video.camera

import java.nio.ByteBuffer

class LoginCommand(user: User) {
    private val commandHead = CommandHead()
    private val byteBuffer: ByteBuffer

    init {
        commandHead.setPayloadLength(user.getSize())
        commandHead.setCommandId(CommandConst.CONST_COMMANDID_LOGIN)
        byteBuffer = ByteBuffer
            .allocate(user.getSize() + commandHead.getSize())
            .put(commandHead.getByteBuffer())
            .put(user.getByteBuffer())
    }

    fun getByteBuffer(): ByteBuffer {
        return byteBuffer
    }
}