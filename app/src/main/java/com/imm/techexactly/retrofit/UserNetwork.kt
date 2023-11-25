package com.taxi.rideapp.retrofit

import com.example.farmfooddeliveryapp.utils.common.Constant
import com.example.farmfooddeliveryapp.utils.common.SharedPref
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object UserNetwork {
var sharePref= SharedPref
    val retrofit: ApiInterface by lazy {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor)
            .addNetworkInterceptor(header!!)

            .connectTimeout(50, TimeUnit.MINUTES)
            .writeTimeout(50, TimeUnit.MINUTES)
            .readTimeout(50, TimeUnit.MINUTES)

            .build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(Constant.BASEURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)

            .build()



            .create(ApiInterface::class.java)



    }


    private var header: Interceptor? = Interceptor { chain ->

        val newRequest: Request = chain.request().newBuilder()
            .header("Authorization", "Bearer ${sharePref.getString(Constant.TOKEN, "").toString()}")
            .build()


        chain.proceed(newRequest)

    }
}