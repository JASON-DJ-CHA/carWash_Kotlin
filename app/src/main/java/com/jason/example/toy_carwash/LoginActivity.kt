package com.jason.example.toy_carwash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jason.example.toy_carwash.databinding.ActivityJoinBinding
import com.jason.example.toy_carwash.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.btnLogin.setOnClickListener {  }
        binding.btnMoveMain.setOnClickListener {  } //익명 로그인
        binding.btnSignupInLogin.setOnClickListener {  }
    }


    private fun moveActivivy(){
        startActivity(Intent(this, MainActivity::class.java))
    }
}