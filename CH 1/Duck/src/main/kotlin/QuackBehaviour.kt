interface QuackBehaviour{
    fun quack()
}

class Quack: QuackBehaviour{
    override fun quack(){
        println("Quack! Quack!")
    }
}

class MuteQuack: QuackBehaviour{
    override fun quack(){
        println("<< Silence >>")
    }
}

class Squeak: QuackBehaviour{
    override fun quack(){
        println("Squeak")
    }
}