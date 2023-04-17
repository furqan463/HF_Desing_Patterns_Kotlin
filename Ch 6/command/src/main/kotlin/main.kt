fun main(){
    val remote = SimpleRemoteControl()
    val light = Light("General")
    val garageDoor = GarageDoor()
    val lightOn = LightOnCommand(light)
    val garageOpen = GarageDoorOpenCommand(garageDoor)

    remote.setCommand(lightOn)
    remote.buttonWasPressed()
    remote.setCommand(garageOpen)
    remote.buttonWasPressed()

    println("New Remote")
    val remoteControl = RemoteControl()

    val livingRoomLight = Light("Living Room")
    val kitchenLight = Light("Kitchen")
    val stereo = Stereo()

    val livingRoomLightOn = LightOnCommand(livingRoomLight)
    val livingRoomLightOff = LightOffCommand(livingRoomLight)
    val kitchenLightOn = LightOnCommand(kitchenLight)
    val kitchenLightOff = LightOffCommand(kitchenLight)
    val stereoOnWithCd = StereoOnWithCDCommand(stereo)
    val stereoOff = StereoOffCommand(stereo)

    remoteControl.setCommand(0, livingRoomLightOn,livingRoomLightOff)
    remoteControl.setCommand(1, kitchenLightOn,kitchenLightOff)
    remoteControl.setCommand(3, stereoOnWithCd,stereoOff)

    println(remoteControl)

    remoteControl.onButtonWasPushed(0)
    remoteControl.offButtonWasPushed(0)
    remoteControl.onButtonWasPushed(1)
    remoteControl.offButtonWasPushed(1)
    remoteControl.onButtonWasPushed(2)
    remoteControl.offButtonWasPushed(2)
    remoteControl.onButtonWasPushed(3)
    remoteControl.offButtonWasPushed(3)

}

interface Command{
    fun execute()
    fun undo()
}

class NoCommand: Command{
    override fun execute(){
    }
    override fun undo(){}
}

class LightOnCommand(private val light: Light): Command{
    override fun execute(){
        light.on()
    }
    override fun undo(){
        light.off()
    }
}

class LightOffCommand(private val light: Light): Command{
    override fun execute() {
        light.off()
    }
    override fun undo(){
        light.on()
    }
}

class StereoOnWithCDCommand(private val stereo: Stereo): Command{
    override fun execute(){
        stereo.on()
        stereo.setCD()
        stereo.setVolume(11)
    }
    override fun undo(){
        stereo.off()
    }
}

class StereoOffCommand(private val stereo: Stereo): Command{
    override fun execute(){
        stereo.off()
    }
    override fun undo(){
        stereo.on()
    }
}

class Light(private val name: String){
    fun on(){
        println("$name Light turned on")
    }
    fun off(){
        println("$name Light turned off")
    }
}

class Stereo{
    private var volume: Int = 0
    fun on(){

    }
    fun off(){

    }
    fun setCD(){

    }
    fun setDVD(){

    }
    fun setRadio(){

    }
    fun setVolume(volume: Int){
        this.volume = volume
    }
}

class GarageDoor{
    fun up(){
        println("GarageDoor door Going up")
    }
    fun down(){
        println("GarageDoor door Going down")
    }
    fun stop(){
        println("GarageDoor door stopped")
    }
    fun lightOn(){
        println("GarageDoor light on")
    }
    fun lightOff(){
        println("GarageDoor light off")
    }
}

class GarageDoorOpenCommand(private val garageDoor: GarageDoor): Command{
    override fun execute() {
        garageDoor.lightOn()
        garageDoor.up()
    }
    override fun undo(){
        garageDoor.down()
        garageDoor.lightOff()
    }
}

class SimpleRemoteControl {
    private var slot: Command? = null
    fun setCommand(command: Command){
        slot = command
    }

    fun buttonWasPressed(){
        slot!!.execute()
    }
}

class RemoteControl(private val onCommands: MutableList<Command> = mutableListOf(NoCommand(),NoCommand(),NoCommand(),NoCommand(),NoCommand(),NoCommand(),NoCommand()),
                    private val offCommands: MutableList<Command> = mutableListOf(NoCommand(),NoCommand(),NoCommand(),NoCommand(),NoCommand(),NoCommand(),NoCommand()),
                    private var undoCommand: Command = NoCommand()) {
    fun setCommand(slot:Int, onCommand: Command, offCommand: Command) {
        onCommands[slot] = onCommand
        offCommands[slot] = offCommand
    }
    fun onButtonWasPushed(slot:Int){
        onCommands[slot].execute()
        undoCommand = onCommands[slot]
    }
    fun offButtonWasPushed(slot:Int){
        offCommands[slot].execute()
        undoCommand = offCommands[slot]
    }
    fun undoButtonWasPushed(){
        undoCommand.undo()
    }
    override fun toString(): String{
        var str: String = "------------- Remote Control -------------\n"
        for(i in 0..6){
            str+= "[slot $i] ${onCommands[i].toString()} ${offCommands[i].toString()}\n"
        }
        return str
    }
}