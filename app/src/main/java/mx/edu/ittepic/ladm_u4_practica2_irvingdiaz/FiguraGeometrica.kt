package mx.edu.ittepic.ladm_u4_practica2_irvingdiaz

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent

class FiguraGeometrica () {
    var x = 0f
    var y = 0f
    var tipo = 1 //1 circulo 2 rectangulo 3 BITMAP 4 Texto
    var radio = 0f
    var ancho = 0f
    var alto = 0f
    var incX = 5
    var incY = 5
    var res :Resources?=null
    var icono : Bitmap?=null
    var texto = ""
    var invisible = false

    constructor(x:Int, y:Int, radio:Int) : this(){
        this.x = x.toFloat()
        this.y = y.toFloat()
        this.radio = radio.toFloat()
        // dando valor a ancho y alto para tratar circulo como cuadrado
        ancho = this.radio*2
        alto = ancho

    }

    constructor(x:Int, y:Int, ancho:Int, alto:Int) : this(){
        this.x = x.toFloat()
        this.y = y.toFloat()
        this.ancho = ancho.toFloat()
        this.alto = alto.toFloat()
        tipo = 2
    }

    constructor(x:Int, y:Int,b : Bitmap) : this(){
        this.icono = b
        this.x = x.toFloat()
        this.y = y.toFloat()
        ancho = icono!!.width.toFloat()
        alto = icono!!.height.toFloat()
        tipo = 3
    }


    fun pintar(c: Canvas, p: Paint){
        if(!this.invisible){
            when(tipo){
                1->{
                    c.drawCircle(x+radio,y+radio,radio,p)
                }
                2->{
                    c.drawRect(x,y,x+ancho,y+alto,p)
                }
                3->{
                    c.drawBitmap(icono!!,x,y,p)
                }
            }
        }
    }

    fun estaEnArea(event: MotionEvent):Boolean{
        if(event.x >= x && event.x<=x+ancho){
            if(event.y >= y && event.y<=y+alto){
                return true
            }
        }
        return false
    }

    fun estaEnArea(posX : Float, posY : Float):Boolean{
        if(posX >= x && posX<=x+ancho){
            if(posY >= y && posY<=y+alto){
                return true
            }
        }
        return false
    }

    fun arrastrar(event: MotionEvent){
        x = event.x - (ancho/2)
        y = event.y - (alto/2)
    }

    fun rebote(ancho:Int, alto:Int){
        x+= incX
        if(x<=-100 || x>=ancho){
            incX *= -1
        }
        y+= incY
        if(y<=-100 || y>=alto){
            incY *= -1
        }

    }

    fun hacerInvisible(){
        this.invisible = true
    }
    fun deshacerInvisible(){
        this.invisible = false
    }

    fun asignarX(x: Int){
        this.x = x.toFloat()
    }

    fun asignarY(y: Int){
        this.y = y.toFloat()
    }

    fun colision(objetoB : FiguraGeometrica ) : Boolean {
        // Caso 1
        if(objetoB.estaEnArea(x+ancho,y+alto)){
            return true
        }

        //Caso 2
        if(objetoB.estaEnArea(x,y+alto)){
            return true
        }

        //Caso 3
        if(objetoB.estaEnArea(x+ancho,y)){
            return true
        }

        //Caso 4
        if(objetoB.estaEnArea(x,y)){
            return true
        }

        /*----------------------------------*/

        // Caso 1
        if(objetoB.estaEnArea(x+ancho,(y+alto)/2)){ //derecha
            return true
        }

        //Caso 2
        if(objetoB.estaEnArea(x,(y+alto)/2)){ //izquierda
            return true
        }

        //Caso 3
        if(objetoB.estaEnArea((x+ancho)/2,y)){ //arriba
            return true
        }

        //Caso 4
        if(objetoB.estaEnArea((x+ancho)/2,y+alto)){
            return true
        }

        return false
    }

}