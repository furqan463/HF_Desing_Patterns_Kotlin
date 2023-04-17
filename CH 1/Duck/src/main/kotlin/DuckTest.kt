
fun main() {
    val mallard: Duck = MallardDuck()
    mallard.performQuack()
    mallard.performFly()

    val model: Duck = ModelDuck()
    model.performFly()
    model.flyBehaviour = FlyRocketPowered()
    model.performFly()
    model.flyBehaviour = FlyNoWay()
    model.performFly()
    model.swim()
    model.performQuack()
    model.quackBehaviour = MuteQuack()
    model.performQuack()
    model.quackBehaviour = Squeak()
    model.performQuack()
}
