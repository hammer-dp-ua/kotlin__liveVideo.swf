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

        val socket = Socket("192.168.0.200", 3001)
        val dataOutputStream = DataOutputStream(socket.getOutputStream())
        val bufferedReader = BufferedReader(InputStreamReader(socket.getInputStream()))

        dataOutputStream.write(packet.getByteBuffer().array())
        dataOutputStream.flush()

        Log.i("", "Response:")

        val charArray = CharArray(1024)
        bufferedReader.read(charArray)

        dataOutputStream.close()
        bufferedReader.close()
    }
}