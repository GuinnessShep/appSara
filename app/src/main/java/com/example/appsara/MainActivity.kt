package com.example.appsara

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler


class MainActivity : AppCompatActivity() {
    lateinit var handler: Handler //splash

    //clase handler que sirve para manejar hilos o subprocesos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler = Handler()
        //constructor por defecto
        /*llamamos al metodo postdelayed. Este hará con retardo la funcion de ir a pantalla2. Tardará casi 2 segundos (1700) en ejecutarla
        */
        handler.postDelayed({
            val intent = Intent(this,pantalla2::class.java)
            startActivity(intent)
            finish()
        }, 1700) //2 segundos


    }

}

