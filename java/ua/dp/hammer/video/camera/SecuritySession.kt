package ua.dp.hammer.video.camera

class SecuritySession {
    private var iSessionId: Short = 0

    fun sendLoginCommand() {
        val user = User()
        user.setUserName("admin")
        user.setUserPassword("admin")

        val loginCommand = LoginCommand(user)
        val response = TCPTransfer().sendCommand(loginCommand.getByteBuffer())

        processResponse(response)
    }

    fun sendHeartbeatCommand() {
        val sessionHeartbeatCommand = SessionHeartbeatCommand(iSessionId)
        val response = TCPTransfer().sendCommand(sessionHeartbeatCommand.getByteBuffer())

        processResponse(response)
    }

    private fun processResponse(response: ByteArray) {
        val parsedPacket = Packet()
        val readBytes = parsedPacket.parsePacketHead(response)
        val responseCommand = ResponseCommand()
        responseCommand.parse(response, readBytes)

        iSessionId = parsedPacket.getSessionId()
    }
}