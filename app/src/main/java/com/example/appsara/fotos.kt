package com.example.appsara

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fotos.*

class fotos: AppCompatActivity() {

var sampleImages = intArrayOf(
        R.drawable.foto2,
        R.drawable.foto3,
        R.drawable.foto4,
        R.drawable.foto5,
        R.drawable.foto6
    )

    var gimanastas = arrayOf(
        "foto2",
        "foto3",
        "foto4",
        "foto5",
        "foto6"

        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fotos)

        carrousel.pageCount = gimanastas.size

        carrousel.setImageListener { position, imageView ->
            imageView.setImageResource(sampleImages[position])
        }
        carrousel.setImageClickListener {position ->
            Toast.makeText(applicationContext, gimanastas[position], Toast.LENGTH_SHORT).show()
        }

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
