package ua.dp.hammer.video.camera

import java.nio.ByteBuffer

class LoginCommand(user: User) {
    private val byteBuffer: ByteBuffer

    init {
        val commandHead = CommandHead()

        commandHead.setPayloadLength(user.getSize())
        commandHead.setCommandId(CommandConst.CONST_COMMANDID_LOGIN)
        byteBuffer = ByteBuffer
            .allocate(user.getSize() + CommandHead.getSize())
            .put(commandHead.getByteBuffer().array())
            .put(user.getByteBuffer().array())
    }

    fun getByteBuffer(): ByteBuffer {
        return byteBuffer
    }
}