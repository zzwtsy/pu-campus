import java.io.File

fun main() {
    println(File("test/db").exists())
    File("test/").mkdirs()
}