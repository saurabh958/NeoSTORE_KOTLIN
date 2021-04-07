package com.example.neostore_kotlin.network

import com.example.neostore_kotlin.model.LoginModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Apiservice {

    @FormUrlEncoded
    @POST("login")
    fun checkLoginDetail(@Field("email") email:String ,
                         @Field("password") password: String):Call<LoginModel>

    @FormUrlEncoded
    @POST("register")
    fun createData(@Field("first_name") first_name:String,
                   @Field("last_name")  last_name:String,
                   @Field("email")  email:String,
                   @Field("password")  password:String,
                   @Field("confirm_password") confirm_password:String,
                   @Field("gender") gender:String,
                   @Field("phone_no") phone_no:String):Call<LoginModel>


    @FormUrlEncoded
    @POST("forgot")
    fun forgetData(@Field("email")email:String):Call<LoginModel>











}