package ua.dp.hammer.video.camera

class ResponseCommand {
    private val commandHead = CommandHead()
    private val response = Response()

    fun parse(responseParam: ByteArray, startIndex: Int): Int {
        var parsedBytes = commandHead.parse(responseParam, startIndex)
        parsedBytes += response.parse(responseParam, parsedBytes + startIndex)
        return parsedBytes
    }

    private class Response {
        var iRequestId: Int = 0
        var iSucceedFlag: Int = 0
        var iErrorNo: Int = 0

        fun parse(response: ByteArray, startIndex: Int): Int {
            var readBytes = 0

            iRequestId = parseInt(response, startIndex)
            readBytes += 4
            iSucceedFlag = parseInt(response, startIndex + readBytes)
            readBytes += 4
            iErrorNo = parseInt(response, startIndex + readBytes)
            readBytes += 4
            return readBytes
        }
    }
}