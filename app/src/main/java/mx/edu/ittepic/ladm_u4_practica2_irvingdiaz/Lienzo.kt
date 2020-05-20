package mx.edu.ittepic.ladm_u4_practica2_irvingdiaz

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat.getSystemService

class Lienzo(p:MainActivity) : View(p){
    var puntero = p
    var metroid = BitmapFactory.decodeResource(resources,R.drawable.metroid)
    var fondo = BitmapFactory.decodeResource(resources,R.drawable.fondo)
    var fondoF = FiguraGeometrica(0,0,fondo)
    var metroidF = FiguraGeometrica(0,800,metroid)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var paint = Paint()
        fondoF.pintar(canvas,paint)
        metroidF.pintar(canvas,paint)


    }
}