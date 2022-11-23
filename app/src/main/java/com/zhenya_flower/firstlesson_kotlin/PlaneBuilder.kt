package com.zhenya_flower.firstlesson_kotlin

class PlaneBuilder(val builder: Builder) {

    fun howMuchEngine() = builder.engine
    fun howMuchPass() = builder.passengers
    fun hasWeapon() = builder.weapon
    fun planeType() = builder.type
    fun plainSpeed() = builder.speed

    companion object Builder {
        var engine: Int = 0
        var passengers: Int = 0
        var weapon: Boolean = false
        var type: String = ""
        var speed: Double = 0.0

        fun setEngine(engine: Int) = apply { this.engine = engine }
        fun setPassengers(passengers: Int) = apply { this.passengers = passengers }
        fun setWeapon(weapon: Boolean) = apply { this.weapon = weapon }
        fun setType(type: String) = apply { this.type = type }
        fun setSpeed(speed: Double) = apply { this.speed = speed }

        fun buildPlane(): PlaneBuilder {
            return PlaneBuilder(this)
        }
    }
}