package ua.dp.hammer.video.camera

import android.util.Log
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.Socket
import java.nio.ByteBuffer

class TCPTransfer {
    fun sendCommand(commandContent: ByteBuffer) {
        val packet = Packet()
        packet.payload = commandContent

        val socket = Socket("192.168.0.200", 30001)
        val dataOutputStream = DataOutputStream(socket.getOutputStream())
        val socketInputStream = socket.getInputStream()

        dataOutputStream.write(packet.getByteBuffer().array())
        dataOutputStream.flush()

        Log.i("", "Response:")

        val byteArray = ByteArray(1024)
        socketInputStream.read(byteArray)

        val parsedPacket = Packet()
        var readBytes = parsedPacket.parsePacketHead(byteArray)
        val commandHead = CommandHead()
        readBytes += commandHead.parse(byteArray, readBytes)

        dataOutputStream.close()
        socketInputStream.close()
    }
}