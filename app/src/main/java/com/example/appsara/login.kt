package com.example.appsara

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class login() : AppCompatActivity() {
    private val GOOGLE_SIGN_IN = 100
    private val callbackManager = CallbackManager.Factory.create()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // Para ver los logings en la consola de Firebase
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Integracion de firebase completa")
        analytics.logEvent("initScreen", bundle)

        // función para comprobar si ya hay un usuario logueado

        checkSession()

        // google
        val googleConf = GoogleSignInOptions.
        Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()

        val gClient = GoogleSignIn.getClient(this, googleConf)
        val registerButton : Button = findViewById(R.id.buttonGoogle)
        registerButton.setOnClickListener{
            gClient.signOut()
            val intent = gClient.getSignInIntent()
            startActivityForResult(intent, 100 )
        }

        //face

        val registerFacebook : Button = findViewById(R.id.buttonFacebook);
        registerFacebook.setOnClickListener{
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))
            LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(result: LoginResult?) {
                        result?.let {
                            val token = it.accessToken
                            val credential = FacebookAuthProvider.getCredential(token.token)

                            FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                                if(it.isSuccessful){
                                    irLogin()

                                } else {
                                    showError()
                                }
                            }
                        }

                    }

                    override fun onCancel() {
                        TODO("Not yet implemented")

                    }

                    override fun onError(error: FacebookException?) {
                        showError()
                    }
                })

        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode,resultCode,data) //linea facebook
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                if(account != null){
                    val credential = GoogleAuthProvider.
                    getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().
                    signInWithCredential(credential)
                        .addOnCompleteListener{
                            if(it.isSuccessful){
                                // Navegamos a la siguiente
                                irLogin()
                            }
                            else{
                                // Error
                            }
                        }
                }
            } catch (e: ApiException) {
                // Google Sign In failed
            }
        }
    }
    fun onClickLogin(view: android.view.View) {

        //obtenemos los campos de texto y los guardamos en objetos
        val email: EditText = findViewById(R.id.email)
        val passw: EditText = findViewById(R.id.password)

        if (email != null && passw != null) {
            if (email.text.toString() != "" && passw.text.toString() != "") {

                var emailString = email.text.toString()
                var passwordString = passw.text.toString()

                if (emailString != null && passwordString != null) {

                    FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(emailString, passwordString)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                irLogin()
                            } else {
                                //error
                                showError()
                            }
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


        val pulsarBotonLogin = Intent(this, reserva::class.java)

        /* empezamos el intento */
        startActivity(pulsarBotonLogin)

        //para que salga el mensaje de registro completado

        val myToast = Toast.makeText(applicationContext, "¡has accedido con exito!", Toast.LENGTH_SHORT)
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
    private fun checkSession() {
        val preferences =
            getSharedPreferences(getString(R.string.preferences), Context.MODE_PRIVATE)

        //recogemos el email
        val email = preferences.getString("email", null)
        if (email != null) {
            irLogin()
        }


    }
}
