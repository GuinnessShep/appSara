package com.example.appsara

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class pantalla2() : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla2)

        //texto que aparecerá al entrar en la pantalla 2.
        Toast.makeText(this,"¡BIENVENIDO!",Toast.LENGTH_SHORT).show()

    }

    fun onClickRegistro(view: android.view.View) {

        //esto se va a realizar cuando pulse el boton Siguiente
        //intent es una clase que recibe dos parametros, donde estamos y a donde vamos
        //creamos un objeto de la clase intent

        val pulsarBoton = Intent(this, registro::class.java)

        /* empezamos el intento */

        startActivity(pulsarBoton)
    }

    fun onClickLogin(view: android.view.View) {

        //esto se va a realizar cuando pulse el boton Siguiente
        //intent es una clase que recibe dos parametros, donde estamos y a donde vamos
        //creamos un objeto de la clase intent

        val pulsarBoton = Intent(this, login::class.java)

        /* empezamos el intento */

        startActivity(pulsarBoton)
    }

    fun onClickHome(view: android.view.View) {

        //esto se va a realizar cuando pulse el boton Siguiente
        //intent es una clase que recibe dos parametros, donde estamos y a donde vamos
        //creamos un objeto de la clase intent

        val pulsarBoton = Intent(this, MainActivity::class.java)

        /* empezamos el intento */

        startActivity(pulsarBoton)
    }


}

