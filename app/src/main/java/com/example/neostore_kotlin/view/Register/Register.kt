package com.example.neostore_kotlin.view.Register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.neostore_kotlin.R
import com.example.neostore_kotlin.model.LoginModel
import com.example.neostore_kotlin.view.login.Login
import com.example.neostore_kotlin.viewmodel.RegisterVM
import java.util.regex.Pattern

class Register : AppCompatActivity() {

    lateinit var firstname: EditText
    lateinit var lastname: EditText
    lateinit var emailz: EditText
    lateinit var password: EditText
    lateinit var cnfrmpass: EditText
    lateinit var phoneno: EditText
    lateinit var registerbtn:Button
    lateinit var maler: RadioButton
    lateinit var femaler: RadioButton
    lateinit var radgrp: RadioGroup
    lateinit var invtext: TextView
    lateinit var gendr:String
    val emailpattern:String="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        firstname=findViewById(R.id.firstname)
        lastname=findViewById(R.id.lastname)
        emailz=findViewById(R.id.email)
        password=findViewById(R.id.password)
        cnfrmpass=findViewById(R.id.confrmpasswrd)
        phoneno=findViewById(R.id.phonenumber)
        registerbtn=findViewById(R.id.button)
        maler=findViewById(R.id.male)
        femaler=findViewById(R.id.female)
        radgrp=findViewById(R.id.radioGroup)
        invtext=findViewById(R.id.inv)

        val regViewModel=ViewModelProviders.of(this).get(RegisterVM::class.java)
        regViewModel.getRegisterObserver().observe(this, Observer<LoginModel> {
            if(it!=null)
            {
                Log.d("annu","success in onchnaged")
                val intent=Intent(this,Login::class.java)
                setResult(2,intent)
                finish()



            }
        })



        registerbtn.setOnClickListener {
            val phnno: String = phoneno.text.toString()
            var selectgendr:Int=radgrp.checkedRadioButtonId
            class Radgen : CompoundButton.OnCheckedChangeListener{
                override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                    if(maler.isChecked)
                    {
                        invtext.visibility=View.INVISIBLE
                        gendr=maler.text.toString().substring(0, 1)

                    }
                    else if(femaler.isChecked)
                    {
                        invtext.visibility=View.INVISIBLE
                        gendr=femaler.text.toString().substring(0, 1)

                    }
                }
            }

            if(firstname.text.toString().isEmpty()||lastname.text.toString().isEmpty()||
                    emailz.text.toString().isEmpty()||password.text.toString().isEmpty()||
                    cnfrmpass.text.toString().isEmpty()||phoneno.text.toString().isEmpty()||
                    !Pattern.matches("[a-zA-Z ]+", firstname.text.toString())||
                    !Pattern.matches("[a-zA-Z ]+", lastname.text.toString())||
                    phoneno.text.length<10||!Pattern.matches(emailpattern, emailz.text.toString())||
                    password.length() in 1..5 ||cnfrmpass.length() in 1..5)
            {

                if(firstname.text.toString().isEmpty())
                {
                    firstname.requestFocus()
                    firstname.error="please Enter First Name"
                }
                else if(!Pattern.matches("[a-zA-Z ]+", firstname.text.toString()))
                {
                    firstname.requestFocus()
                    firstname.error="Please Enter Alphabets"
                }

                if(lastname.text.toString().isEmpty())
                {
                    lastname.requestFocus()
                    lastname.error="Please Enter LastName"
                }
                else if(!Pattern.matches("[a-zA-Z ]+", lastname.text.toString()))
                {
                    lastname.requestFocus()
                    lastname.error="Please Enter Alphabets"
                }

                if(selectgendr==-1)
                {
                    invtext.visibility=View.VISIBLE
                    maler.setOnCheckedChangeListener(Radgen())
                    femaler.setOnCheckedChangeListener(Radgen())
                }

                if(phoneno.text.toString().isEmpty())
                {
                    phoneno.requestFocus()
                    phoneno.error="Please Enter Phone Number"

                }
                else if(phoneno.length()<10)
                {
                    phoneno.requestFocus()
                    phoneno.error="Enter Valid Phone Number"
                }

                if(emailz.text.toString().isEmpty())
                {
                    emailz.requestFocus()
                    emailz.error="Please Enter Email ID"
                }
                else if(!Pattern.matches(emailpattern, emailz.text.toString()))
                {
                    emailz.requestFocus()
                    emailz.error="Please Enter Valid Email ID"
                }

                if(password.text.toString().isEmpty())
                {
                    password.requestFocus()
                    password.error="Enter Password"
                }

                if(cnfrmpass.text.toString().isEmpty())
                {
                    cnfrmpass.requestFocus()
                    cnfrmpass.error="Enter Confirm Password"
                }

                if(password.length() in 1..5 )
                {
                    password.requestFocus()
                    password.error="Password Should be Greater than 6 "

                }
                if(cnfrmpass.length() in 1..5)
                {
                    cnfrmpass.requestFocus()
                    cnfrmpass.error="Password Should be Greater than 6 "
                }
                Log.d("annu","in end of if")
            }
            else if(cnfrmpass.text.toString() != password.text.toString())
            {
                Log.d("annu","in else if")

                cnfrmpass.requestFocus()
                cnfrmpass.error="Please Enter Same Password as Above"
            }
            else
            {
                Log.d("annu","in else")

                val fname:String=firstname.text.toString()
                val lname:String=lastname.text.toString()
                val eml:String=emailz.text.toString()
                val pass:String=password.text.toString()
                val cpass:String=cnfrmpass.text.toString()
                if(maler.isChecked)
                {
                    gendr=maler.text.toString().substring(0,1)
                }
                else if(femaler.isChecked)
                {
                    gendr=femaler.text.toString()
                }

                regViewModel.makeRegisterCall(fname,lname,eml,pass,cpass,gendr,phnno)
    





            }


        }


    }
}