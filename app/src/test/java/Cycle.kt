class Cycle {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            for (i in 2..10) {
                print("$i ")
            }
            print("\n")

            val words = arrayListOf<String>()
            words.add("Audi")
            words.add("BMW")
            words.add("Lada")
            for (word in words) {
                println("Word: $word")
            }

            for (i in 10 downTo 1 step 2){
                print("$i ")
            }

            print("\n")
            for (i in 1.. 10 ){
                if (i==2){
                    print("Two ")
                }else{
                    print("$i ")
                }
            }

            print("\n")
            repeat(5){
                println("Valhalla")
            }
        }
    }
}