package ua.dp.hammer.video.camera

class SecuritySession {
    private var sessionId: Short = 0

    fun sendLoginCommand() {
        val user = User()
        user.setUserName("admin")
        user.setUserPassword("admin")

        val loginCommand = LoginCommand(user)
        val response = TCPTransfer().sendCommand(loginCommand.getByteBuffer(), 0)

        processResponse(response)
    }

    fun sendHeartbeatCommand() {
        val sessionHeartbeatCommand = SessionHeartbeatCommand(sessionId)
        val response = TCPTransfer().sendCommand(sessionHeartbeatCommand.getByteBuffer(), sessionId)

        processResponse(response)
    }

    fun getSessionId(): Short {
        return sessionId
    }

    private fun processResponse(response: ByteArray) {
        val parsedPacket = Packet()
        val readBytes = parsedPacket.parsePacketHead(response)
        val responseCommand = ResponseCommand()
        responseCommand.parse(response, readBytes)

        sessionId = parsedPacket.getSessionId()
    }
}