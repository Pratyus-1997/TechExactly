package com.kare.support.retrofit

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.kare.support.common.InternetConnection
import com.google.gson.JsonElement
import com.imm.techexactly.MainActivity

import com.sueep.retrofit.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ParseApiData : Callback<JsonElement> {

    private lateinit var apiResponse: ApiResponse
    private lateinit var context: Context

    fun hitApi(
        call: Call<JsonElement>,
        showProgress: Boolean,
        context: Context,
        listener: ApiResponse
    ) {
        this.context = context
        if (InternetConnection.checkConnection(context)) {


            if (showProgress)
            {
                (context as MainActivity).showProgress()

            }

            call.enqueue(this)
            apiResponse = listener
        } else {
            Toast.makeText(context, "No Internet Available", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
        (context as MainActivity).dismissProgress()


        if (response.isSuccessful) {
            Log.e("response>>", response.body().toString())
            apiResponse.onSuccess(call, response.code(), response.body()!!.toString())
        } else {

            if (response.code() == 400) {
                apiResponse.onError(
                    call,
                    response.code(),
                    response.errorBody()!!.string().toString()
                )
            } else if (response.code() == 426) {

            } else if (response.code() == 500) {
                (context as MainActivity).ToastMsg("Server Error Please Try Later!!")
            } else {
                if (response.code() != 405) {

                }
            }
        }
    }

    override fun onFailure(call: Call<JsonElement>, t: Throwable) {
        Log.e("exception>>", t.message!!)
        (context as MainActivity).ToastMsg("Server Error Please Try Later!!")
        (context as MainActivity).dismissProgress()


    }


}