class Inheritance {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val child = Child()
            if (child is Parent) {
                println("child is parent")
                child.parentBehavior()
                child.childBehavior()
            }
            val childAsParent = child as Parent
            childAsParent.parentBehavior()
        }
    }
}

open class Parent() {
    open fun parentBehavior() {
        println("parent behavior")
    }
}

class Child() : Parent() {
    override fun parentBehavior() {
        super.parentBehavior()
    }

    fun childBehavior() {
        println("child behavior")
    }

}