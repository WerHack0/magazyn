package com.example.magazyn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var loginButton: Button
    private lateinit var loginET: EditText
    private lateinit var passwordET: EditText
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth
        loginButton = findViewById(R.id.loginbutton)
        loginET = findViewById(R.id.login_text)
        passwordET = findViewById(R.id.pass_text)

        val password = passwordET.text
        val username = loginET.text

        loginButton.setOnClickListener {
            auth.signInWithEmailAndPassword(username.toString(), password.toString()).addOnCompleteListener {
                task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "Log in!", Toast.LENGTH_LONG).show()
                    val intentReg: Intent = Intent(this, home_screen::class.java)
                    startActivity(intentReg)
                }
                else {
                    Toast.makeText(this, "Unknown user", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}