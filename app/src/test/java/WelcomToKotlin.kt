const val STRING = "STRING"

class WelcomeToKotlin {
    val a = 4 // can't change
    var b = 5
    lateinit var c: String
    fun changeValue() {
        b = 10
        c = "some message"
        println(STRING)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val welcomeToKotlin = WelcomeToKotlin()
            welcomeToKotlin.changeValue()
            println("${welcomeToKotlin.a},${welcomeToKotlin.b},${welcomeToKotlin.c}")
        }
    }

}
