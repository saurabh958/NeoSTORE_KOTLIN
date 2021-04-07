package com.example.neostore_kotlin.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PatternMatcher
import android.os.PatternMatcher.PATTERN_SIMPLE_GLOB
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.neostore_kotlin.R
import com.example.neostore_kotlin.model.LoginModel
import com.example.neostore_kotlin.view.Register.Register
import com.example.neostore_kotlin.view.forget.Forget
import com.example.neostore_kotlin.viewmodel.LoginVM
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.regex.Pattern

class Login : AppCompatActivity() {
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var loginibtn: Button
    lateinit var registerbtn: FloatingActionButton
    lateinit var forgopass: TextView
    val emailpattern:String="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username=findViewById(R.id.username)
        password=findViewById(R.id.passwrd)
        loginibtn=findViewById(R.id.login)
        registerbtn=findViewById(R.id.floatng)
        forgopass=findViewById(R.id.forgo)

        val viewModel=ViewModelProviders.of(this).get(LoginVM::class.java)
        viewModel.getLoginListObserver().observe(this,Observer<LoginModel>{

            if(it!=null)
            {
                Log.d("annu","success in onchnaged")
            }
        })




        registerbtn.setOnClickListener(View.OnClickListener {
            val intent=Intent(this,Register::class.java)
            startActivityForResult(intent,2)
        })

        forgopass.setOnClickListener(View.OnClickListener {

            val intent=Intent(this,Forget::class.java)
            startActivityForResult(intent,2)
        })

        loginibtn.setOnClickListener(View.OnClickListener {
            var usrname:String=username.text.toString()
            var passwrd:String=password.text.toString()

            if (username.text.toString().isEmpty()||password.text.toString().isEmpty()||
                    password.text.toString().length in 1..5 ||
                    (!Pattern.matches(emailpattern,usrname)))
            {
                if(username.text.toString().isEmpty())
                {
                    username.requestFocus()
                    username.setError("Please enter Email")
                }
                else if((!Pattern.matches(emailpattern,usrname)))
                {
                    username.requestFocus()
                    username.setError("Please enter Valid Email Id")
                }
                if(password.text.toString().isEmpty())
                {
                    password.requestFocus()
                    password.setError("Please enter Password")

                }
                if(password.text.toString().length in 1..5)
                {
                    password.requestFocus()
                    password.error = "Password Minimum Length is 6"
                }

            }
            else
            {
                Log.d("annu","username is $usrname")
                Log.d("annu","password is $passwrd")
                //Toast.makeText(applicationContext,"Success",Toast.LENGTH_LONG).show()
                viewModel.makeLoginApiCall(usrname,passwrd)
                Log.d("annu","api called")

            }



        })





    }
}