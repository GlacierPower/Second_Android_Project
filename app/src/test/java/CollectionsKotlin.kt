class CollectionsKotlin {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var words = arrayListOf<String>()
            words.add("Hello")
            words.add("Bye")
            words.add("World")
            words.removeAt(1)
            words.forEach { word-> println(word) }

            val words2 = mutableListOf<String>()
            words2.add("Forehand")
            words2.add("Backhand")
            words2.add("Slice")
            words2.removeAt(2)
            words2.forEach{word -> println(word) }

            val words3 = listOf<String>(
                "Nike",
                "Adidas",
                "New balance"
            )
            words3.forEach { word-> println(word) }
        }
    }
}