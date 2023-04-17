fun main(){

}

class SimplePizzaFactory {
    fun createPizza(type: String): Pizza? {
        when (type) {
            "cheese" -> {return CheesePizza()}
            "pepperoni" -> {return PepperoniPizza()}
            "clam" -> {return ClamPizza()}
            "veggie" -> {return VeggiePizza()}
        }
        return null
    }
}

abstract class Pizza{
    abstract fun prepare()
    abstract fun bake()
    abstract fun cut()
    abstract fun box()
}

class CheesePizza: Pizza(){
    private val name: String = "Cheese Pizza"
    override fun prepare(){
        println("Preparing $name")
    }
    override fun bake(){
        println("baking $name")
    }
    override fun cut(){
        println("Cutting $name")
    }
    override fun box(){
        println("Boxing $name")
    }
}

class PepperoniPizza: Pizza(){
    private val name: String = "Pepperoni Pizza"
    override fun prepare(){
        println("Preparing $name")
    }
    override fun bake(){
        println("baking $name")
    }
    override fun cut(){
        println("Cutting $name")
    }
    override fun box(){
        println("Boxing $name")
    }
}

class ClamPizza: Pizza(){
    private val name: String = "Clam Pizza"
    override fun prepare(){
        println("Preparing $name")
    }
    override fun bake(){
        println("baking $name")
    }
    override fun cut(){
        println("Cutting $name")
    }
    override fun box(){
        println("Boxing $name")
    }
}

class VeggiePizza: Pizza(){
    private val name: String = "Veggie Pizza"
    override fun prepare(){
        println("Preparing $name")
    }
    override fun bake(){
        println("baking $name")
    }
    override fun cut(){
        println("Cutting $name")
    }
    override fun box(){
        println("Boxing $name")
    }
}

class PizzaStore(private val factory: SimplePizzaFactory){
    fun orderPizza(type: String): Pizza {
        val pizza: Pizza = factory.createPizza(type)!!
        pizza.prepare()
        pizza.bake()
        pizza.cut()
        pizza.box()
        return pizza
    }
}

