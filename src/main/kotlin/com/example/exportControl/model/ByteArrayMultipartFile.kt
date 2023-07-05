import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets

class ByteArrayMultipartFile(private val content: ByteArray, private val originalFilename: String) : MultipartFile {
    override fun getName(): String {
        return ""
    }

    override fun getOriginalFilename(): String {
        return originalFilename
    }

    override fun getContentType(): String {
        // Provide the appropriate content type based on your needs
        return "application/octet-stream"
    }

    @Throws(IOException::class)
    override fun getInputStream(): InputStream {
        return ByteArrayInputStream(content)
    }

    @Throws(IOException::class)
    override fun getBytes(): ByteArray {
        return content
    }

    override fun isEmpty(): Boolean {
        return content.isEmpty()
    }

    override fun getSize(): Long {
        return content.size.toLong()
    }

    override fun transferTo(dest: java.io.File) {
        throw UnsupportedOperationException("Transfer to file not supported.")
    }
}
