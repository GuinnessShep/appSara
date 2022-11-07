package com.example.appsara

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.reserva.*

class reserva : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reserva)
    etDate.setOnClickListener{ showDatePickerDialog() }

        val miboton: Button = findViewById(R.id.bConfirmar)
        miboton.setOnClickListener{

            db.collection("citas2").document(etDate.text.toString()).set(
                hashMapOf("datoscita" to etDate.text.toString(),
                    )
            )
        }
    }

    private fun showDatePickerDialog() {
    val datePicker = DatePickerFragment{day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

fun onDateSelected(day:Int, month: Int, year:Int){
    val suma1 = month +1
etDate.setText("has reservado tu clase el día $day de $suma1")
}














    //para poner menu lateral
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.lateral_menu, menu)
        return true;
    }
    //para poner la funcion para cada item del menu lateral
    override fun onOptionsItemSelected(item : MenuItem): Boolean{
        val item_selected: Int = item.itemId

        if(item_selected == R.id.item1)
        {
            onClickHome()

        }
        else if (item_selected == R.id.item2){
            onClickFotos()
        }

        else if (item_selected == R.id.item3){
            onClickCerrarSesion()
        }

        return super.onOptionsItemSelected(item)

    }

    fun onClickHome() {


        val pulsarBotonHome = Intent(this, MainActivity::class.java)

        /* empezamos el intento */
        startActivity(pulsarBotonHome)
    }

    fun onClickFotos() {


        val pulsarBotonFotos = Intent(this, fotos::class.java)

        /* empezamos el intento */
        startActivity(pulsarBotonFotos)
    }

    fun onClickCerrarSesion() {


        val pulsarBotonHome = Intent(this, login::class.java)

        /* empezamos el intento */
        startActivity(pulsarBotonHome)
    }

    fun onClickLogout(view: android.view.View) {

        //esto se va a realizar cuando pulse el boton Siguiente
        //intent es una clase que recibe dos parametros, donde estamos y a donde vamos
        //creamos un objeto de la clase intent

        val pulsarBoton = Intent(this, login::class.java)

        /* empezamos el intento */

        startActivity(pulsarBoton)
    }

}