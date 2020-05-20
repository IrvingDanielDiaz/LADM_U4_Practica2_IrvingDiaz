package mx.edu.ittepic.ladm_u4_practica2_irvingdiaz

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), SensorEventListener {
    var lienzo  : Lienzo ?= null

    lateinit var sensorManager : SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lienzo = Lienzo(this)
        setContentView(lienzo!!)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.registerListener(this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_GAME)

        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY),
        SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent) {
        if(event.sensor.type == Sensor.TYPE_ACCELEROMETER){
            if(lienzo!!.metroidF.x.toInt()-(event.values[0].toInt()*5)<=0){

            }else if(lienzo!!.metroidF.x.toInt()-(event.values[0].toInt()*5)<(1080-lienzo!!.metroidF.ancho)){
                lienzo!!.metroidF.asignarX(lienzo!!.metroidF.x.toInt()-(event.values[0].toInt()*5))
                lienzo!!.invalidate()
            }
        }
        if(event.sensor.type == Sensor.TYPE_PROXIMITY){
            if(event.values[0]<5){
                lienzo!!.metroidF.hacerInvisible()
                lienzo!!.puntero.setTitle("                     Cerca! Desapareció")
            }
            if(event.values[0]>=5){
                    lienzo!!.metroidF.deshacerInvisible()
                lienzo!!.puntero.setTitle("                                    . . .")
                }
        }

    }
}
