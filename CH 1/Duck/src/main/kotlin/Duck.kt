abstract class Duck{
    open var flyBehaviour: FlyBehaviour? = null
    open var quackBehaviour: QuackBehaviour? = null
    abstract fun display()

    fun performFly(){
        flyBehaviour?.fly()
    }

    fun performQuack(){
        quackBehaviour?.quack()
    }

    fun swim(){
        println("All ducks float, even decoys!")
    }
}

class MallardDuck: Duck(){
    override var quackBehaviour: QuackBehaviour? = Quack()
    override var flyBehaviour: FlyBehaviour? = FlyWithWings()

    override fun display() {
        println("I'm a real Mallard duck")
    }
}

class ModelDuck: Duck(){
    override var flyBehaviour: FlyBehaviour? = FlyNoWay()
    override var quackBehaviour: QuackBehaviour? = Quack()
    override fun display(){
        println("Im a model duck")
    }
}