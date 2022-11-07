package com.example.appsara

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class registro() : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro)

    }

    fun onClickRegistro(view: android.view.View) {
        //obtenemos los campos de texto y los guardamos en objetos
        val email: EditText = findViewById(R.id.email2);
        val passw: EditText = findViewById(R.id.password2);
        val name: EditText = findViewById(R.id.name);
        val surname: EditText = findViewById(R.id.surname);
        val telefono: EditText = findViewById(R.id.telefono);

        //comprobamos que los campos no son vacios
        if (email != null && passw != null && name != null && surname != null && telefono != null) {
                var emailString = email.text.toString()
                var passwordString = passw.text.toString()
                var nameString = name.text.toString()
                var surnameString = surname.text.toString()
                var telefonoString = telefono.text.toString()

                if (emailString != "" && passwordString != "" && nameString != "" && surnameString != "" && telefonoString != "" ) {

                    FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(emailString, passwordString)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {

                                db.collection("users").document(email.text.toString())
                                    .set(hashMapOf(
                                        "name" to name.text.toString(),
                                        "surname" to surname.text.toString(),
                                        "passw" to passw.text.toString(),
                                        "telefono" to telefono.text.toString()

                                        ))

                                irLogin()
                            } else {
                                //error
                                showError()
                            }
                        }


                }

        } else {
            showError()


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

    private fun irLogin() {


        val pulsarBotonRegistro = Intent(this, login::class.java)

        /* empezamos el intento */
        startActivity(pulsarBotonRegistro)

        //para que salga el mensaje de registro completado

        val myToast = Toast.makeText(
            applicationContext,
            "¡registro completado con éxito!",
            Toast.LENGTH_SHORT
        )
        myToast.setGravity(Gravity.LEFT, 200, 200)
        myToast.show()
    }

    private fun showError() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error con Firebase")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }




}