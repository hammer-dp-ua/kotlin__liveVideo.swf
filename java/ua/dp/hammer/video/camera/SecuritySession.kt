package ua.dp.hammer.video.camera

class SecuritySession {
    @ExperimentalUnsignedTypes
    fun sendLoginCommand() {
        val user = User()
        user.setUserName("admin")
        user.setUserPassword("admin")
        val loginCommand = LoginCommand(user)

        TCPTransfer().sendCommand(loginCommand.getByteBuffer())
    }
}