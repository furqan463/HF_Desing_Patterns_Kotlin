fun main(){
    val beverage: Beverage = Espresso()
    println(beverage.getDescription1()+" $"+ beverage.cost())

    var beverage2: Beverage = DarkRoast()
    beverage2 = Mocha(beverage2)
    beverage2 = Mocha(beverage2)
    beverage2 = Whip(beverage2)
    println(beverage2.getDescription1()+" $"+beverage2.cost())

    var beverage3: Beverage = HouseBlend()
    beverage3 = Soy(beverage3)
    beverage3 = Mocha(beverage3)
    beverage3 = Whip(beverage3)
    println(beverage3.getDescription1()+" $"+beverage3.cost())

    var beverage4: Beverage = Decaf()
    beverage4 = SteamedMilk(beverage4)
    println(beverage4.getDescription1()+" $"+beverage4.cost())
}

abstract class Beverage {
    abstract val description: String
    open fun getDescription1(): String {
        return description
    }

    abstract fun cost(): Double
}

abstract class CondimentDecorator: Beverage()

class Espresso:Beverage(){
    override val description: String = "Espresso Coffee"
    override fun cost(): Double {
        return 1.99
    }
}

class HouseBlend:Beverage(){
    override val description: String = "House Blend Coffee"
    override fun cost(): Double {
        return 0.89
    }
}

class DarkRoast:Beverage(){
    override val description: String = "Dark Roast Coffee"
    override fun cost(): Double {
        return 0.99
    }
}

class Decaf:Beverage(){
    override val description: String = "Decaf Coffee"
    override fun cost(): Double {
        return 1.05
    }
}

class Mocha(private val beverage: Beverage): CondimentDecorator(){
    override val description = this.beverage.getDescription1() + ", Mocha"
    override fun cost(): Double {
        return 0.20 + this.beverage.cost()
    }
}

class Whip(private val beverage: Beverage): CondimentDecorator(){
    override val description = this.beverage.getDescription1() + ", Whip"
    override fun cost(): Double {
        return 0.10 + this.beverage.cost()
    }
}

class Soy(private val beverage: Beverage): CondimentDecorator(){
    override val description = this.beverage.getDescription1() + ", Soy"
    override fun cost(): Double {
        return 0.15 + this.beverage.cost()
    }
}

class SteamedMilk(private val beverage: Beverage): CondimentDecorator(){
    override val description = this.beverage.getDescription1() + ", Steamed Milk"
    override fun cost(): Double {
        return 0.10 + this.beverage.cost()
    }
}