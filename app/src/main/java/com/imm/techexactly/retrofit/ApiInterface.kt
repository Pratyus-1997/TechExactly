package com.taxi.rideapp.retrofit

import com.google.gson.JsonElement

import retrofit2.Call
import retrofit2.http.*
interface ApiInterface {

    @POST("list?kid_id=378")
     fun getAllApplication(
    ): Call<JsonElement>

}
