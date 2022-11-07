fun funIsNotInClass() {
    println("I'm not in a class")
}

class FunInKotlin {
    fun funIsInClass() {
        println("I'm in a class")
    }

    fun realReturnType(): Unit {
        println("I'm return nothing")
    }

    fun withReturnType(): String {
        return ("I'm return String")
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
        val funInKotlin = FunInKotlin()
            funInKotlin.funIsInClass()
            funInKotlin.realReturnType()
            println(funInKotlin.withReturnType())
            funIsNotInClass()
        }
    }
}