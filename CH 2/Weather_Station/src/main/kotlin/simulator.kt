fun main(){
    val weatherData = WeatherData()
    val currentDisplay = CurrentConditionsDisplay(weatherData)
    val statisticsDisplay = StatisticsDisplay(weatherData)
    weatherData.setMeasurements(80f,65f,30.4f)
    weatherData.setMeasurements(82f, 70f, 29.2f)
    weatherData.setMeasurements(78f, 90f, 29.2f)
}

interface Subject {
    fun registerObserver(o: Observer)
    fun removeObserver(o: Observer)
    fun notifyObservers()
}

class WeatherData: Subject {
    private var observers = mutableListOf<Observer>()
    private var temperature: Float = 0f
    private var humidity: Float = 0f
    private var pressure: Float = 0f

    override fun registerObserver(o: Observer){
        this.observers.add(o)
    }

    override fun removeObserver(o: Observer){
        this.observers.remove(o)
    }

    override fun notifyObservers(){
        for (o in observers){
            o.update(this.temperature, this.humidity, this.pressure)
        }
    }

    private fun measurementsChanged(){
        notifyObservers()
    }

    fun setMeasurements(temperature: Float, humidity: Float, pressure: Float){
        this.temperature = temperature
        this.humidity = humidity
        this.pressure = pressure
        measurementsChanged()
    }
}

interface Observer {
    fun update(temperature: Float, humidity: Float, pressure: Float){

    }
}

interface DisplayElement {
    fun display()
}

class CurrentConditionsDisplay(private val weatherData: Subject): Observer, DisplayElement{
    private var temperature: Float? = null
    private var humidity: Float? = null

    init{
        this.weatherData.registerObserver(this)
    }

    override fun update(temperature: Float, humidity: Float, pressure: Float){
        this.temperature = temperature
        this.humidity = humidity
        display()
    }

    override fun display(){
        println("Current conditions: ${this.temperature} F degree and humidity ${this.humidity}")
    }
}

class StatisticsDisplay(private val weatherData: Subject): Observer, DisplayElement{
    private var temperature: Float = 0f
    private var min: Float = 0f
    private var max: Float = 0f
    private var ave: Double = 0.0
    private var tmpList = mutableListOf<Float>()
    init{
        this.weatherData.registerObserver(this)
    }

    override fun update(temperature: Float, humidity: Float, pressure: Float){
        this.temperature = temperature
        this.tmpList.add(temperature)
        calculateStatistics()
        display()
    }

    private fun calculateStatistics(){
        this.min= tmpList.min()
        this.max = tmpList.max()
        this.ave = tmpList.average()
    }

    override fun display(){
        println("Avg/Max/Min temperatures = ${this.ave}/${this.max}/${this.min}")
    }
}