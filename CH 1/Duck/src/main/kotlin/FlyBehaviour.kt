interface FlyBehaviour{
    fun fly()
}

class FlyWithWings: FlyBehaviour{
    override fun fly(){
        println("I'm flying")
    }
}

class FlyNoWay: FlyBehaviour{
    override fun fly(){
        println("I can't fly")
    }
}

class FlyRocketPowered: FlyBehaviour{
    override fun fly(){
        println("I'm flying with a rocket")
    }
}