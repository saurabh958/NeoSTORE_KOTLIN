package com.example.neostore_kotlin.view.forget

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.neostore_kotlin.R
import com.example.neostore_kotlin.model.LoginModel
import com.example.neostore_kotlin.view.login.Login
import com.example.neostore_kotlin.viewmodel.ForgetVM
import java.util.regex.Pattern

class Forget : AppCompatActivity() {

    private lateinit var emailz:EditText
    private lateinit var submitbtn:Button
    private val emailpattern:String="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget)

        emailz=findViewById(R.id.emailid)
        submitbtn=findViewById(R.id.Submit)

        val viewModel=ViewModelProviders.of(this).get(ForgetVM::class.java)
        viewModel.getForgetListObserver().observe(this,Observer<LoginModel>{
            if(it!=null)
            {
                Log.d("annu","success")
                val intent=Intent(this,Login::class.java)
                setResult(2,intent)
                finish()

            }
        })



        submitbtn.setOnClickListener {

            val emailid:String=emailz.text.toString()

            if(emailz.text.toString().isEmpty()||!Pattern.matches(emailpattern, emailz.text.toString()))
            {
                if(emailz.text.toString().isEmpty())
                {
                    emailz.requestFocus()
                    emailz.error="Enter Email ID"
                }

                if(!Pattern.matches(emailpattern, emailz.text.toString()))
                {
                    emailz.requestFocus()
                    emailz.error="Enter Valid Email Id"
                }

            }
            else
            {

                viewModel.makeForgetApiCall(emailid)

            }

        }


    }
}