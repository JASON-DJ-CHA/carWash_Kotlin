package com.jason.example.toy_carwash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

        binding.btnLogin.setOnClickListener { login() } //
        binding.btnMoveMain.setOnClickListener {  moveActivivy() } //익명 로그인
        binding.btnSignupInLogin.setOnClickListener {  }
    }

    private fun login() {
        val email = binding.etEmailAddress.text.toString()
        val password = binding.etPassword.text.toString()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    startActivity(Intent(this, MainActivity::class.java))

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    // 익명 로그인 코드 시작
    private fun moveActivivy(){
        auth.signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    startActivity(Intent(this, MainActivity::class.java))

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"익명로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show()

                }
            }
    }
    // 익명 로그인 코드 끝


}