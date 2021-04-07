package com.example.neostore_kotlin.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.neostore_kotlin.model.LoginModel
import com.example.neostore_kotlin.network.Apiservice
import com.example.neostore_kotlin.network.RetroInstance
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

class ForgetVM(application: Application) : AndroidViewModel(application)
{

    var forgetlist : MutableLiveData<LoginModel> = MutableLiveData()

    fun getForgetListObserver(): MutableLiveData<LoginModel>{
        return forgetlist
    }

    fun makeForgetApiCall(email: String)
    {
        val retroInstance=RetroInstance.getRetroInstance().create(Apiservice::class.java)
        val call=retroInstance.forgetData(email)
        call.enqueue(object :retrofit2.Callback<LoginModel>{
            override fun onResponse(call: Call<LoginModel>?, response: Response<LoginModel>?) {
                if(response!=null)
                {
                    if(response.isSuccessful)
                    {
                        forgetlist.postValue(response.body())
                        Toast.makeText(getApplication(),response.body().message,Toast.LENGTH_LONG).show()

                    }
                    else
                    {
                        try {
                            val jObjError = JSONObject(response.errorBody().string())
                            Toast.makeText(getApplication(), jObjError.getString("user_msg"), Toast.LENGTH_SHORT).show()
                            Log.d("annu","in try block")
                        }
                        catch (e:Exception){
                            Log.d("annu","in catch block")

                            Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<LoginModel>?, t: Throwable?) {
                Log.d("annu","in on Failure")
                if (t != null) {
                    Log.e("annu","${t.message}")
                }
                Toast.makeText(getApplication(), "Check Internet Connection", Toast.LENGTH_SHORT).show()

            }

        })

    }



}