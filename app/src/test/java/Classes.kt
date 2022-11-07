class Classes {
    lateinit var place: Place

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val constr = Constr("some message")
            constr.getString()

            val holder = Holder("value string", 15)
            println(holder.value + " " + holder.intValue)

            val classes = Classes()
            repeat(3) {
                val random = (1..2).random()
                if (random == 1) {
                    classes.place = Place.Holder("hello", 21)
                }else{
                    classes.place = Place.Holder2(true)
                }
                when(classes.place){
                    is Place.Holder2 -> println((classes.place as Place.Holder2).simple)
                    is Place.Holder -> println((classes.place as Place.Holder).value.toString()
                            +" "+(classes.place as Place.Holder).intValue)
                }
            }
        }
    }
}

class Constr(private val string: String) {
    fun getString() {
        println(string)
    }
}

data class Holder(
    val value: String,
    var intValue: Int
)

sealed class Place {

    data class Holder(
        val value: String,
        var intValue: Int
    ) : Place()

    class Holder2(val simple: Boolean = false) : Place()
}