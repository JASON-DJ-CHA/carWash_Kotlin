package com.jason.example.toy_carwash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jason.example.toy_carwash.databinding.ActivityJoinBinding

class JoinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var binding : ActivityJoinBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        auth = Firebase.auth

        binding = DataBindingUtil.setContentView(this, R.layout.activity_join)

        binding.btnSignUp.setOnClickListener {join()}
        binding.imgX.setOnClickListener { backToMain() }
    }

    private fun backToMain() {

    }

    private fun join(){
        val email = binding.etEmailAddress.text.toString()
        val password1 = binding.etPassword1.text.toString()
        val password2 = binding.etPassword2.text.toString()

        if(email.isNotEmpty() && password1.length > 7 && password2.length > 7  ){

            if(password1 == password2){

                auth.createUserWithEmailAndPassword(email, password1)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("JoinA", "createUserWithEmail:failure", task.exception)
                        }
                    }
            }else{
                imformMsg("비밀번호가 일치하지 않습니다.")
                binding.tvPassword.text = "비밀번호가 일치하지 않습니다."
            }
        }
    }
    private fun imformMsg(msg : String){ Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()}
}