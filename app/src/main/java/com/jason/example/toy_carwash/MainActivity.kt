package com.jason.example.toy_carwash

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.jason.example.toy_carwash.databinding.ActivityJoinBinding
import com.jason.example.toy_carwash.databinding.ActivityMainBinding
import com.jason.example.toy_carwash.model.CarWashModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var binding : ActivityMainBinding

    val dataModelList = mutableListOf<CarWashModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Firebase.database

        val myRef = database.getReference("myMemo")


        auth = Firebase.auth

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.addRecord.setOnClickListener {

            val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)

            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("세차 메모 다이얼로그")

            val mAlertDialog =  mBuilder.show()

            val DateSelectBtn = mAlertDialog.findViewById<Button>(R.id.dateSelectBtn)


            var dateText = ""

            mAlertDialog.findViewById<Button>(R.id.dateSelectBtn)?.setOnClickListener {

                val today = GregorianCalendar()
                val year : Int = today.get(Calendar.YEAR)
                val month : Int = today.get(Calendar.MONTH)
                val date : Int = today.get(Calendar.DATE)

                val dlg = DatePickerDialog(this, object  : DatePickerDialog.OnDateSetListener{
                    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int
                    ) {
                        Log.d("main","${year}. ${month+1}. ${dayOfMonth}")
                        DateSelectBtn?.setText("${year}. ${month+1}. ${dayOfMonth}")

                        dateText = "${year}. ${month+1}. ${dayOfMonth}"
                    }

                },year, month,date)
                dlg.show()

                // db에 저장하기
                val saveBtn = mAlertDialog.findViewById<Button>(R.id.dateSaveBtn)
                saveBtn?.setOnClickListener {

                    val dietMemo = mAlertDialog.findViewById<EditText>(R.id.dietMemo)?.text.toString()

                    val database = Firebase.database
                    val myRef = database.getReference("myMemo").child(Firebase.auth.currentUser!!.uid)

                    val model = CarWashModel(dateText, dietMemo)

                    myRef.push().setValue(model)

                    mAlertDialog.dismiss()
                }

            }



        }
}
}