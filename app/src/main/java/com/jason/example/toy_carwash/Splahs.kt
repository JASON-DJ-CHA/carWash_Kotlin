package com.jason.example.toy_carwash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Splahs : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splahs)

        auth = Firebase.auth

        if(auth.currentUser?.uid ==null){
            // 회원가입이 안되어있으므로 , joinActi1vity
            Handler().postDelayed({
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            },3000)
        }else{
            // 회원가입이 되어있으므로 mainActivity
            Handler().postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            },3000)
        }

    }
}