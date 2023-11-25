package com.nexter.application.retrofit

import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


object RetrofitUtils {

    private const val MULTIPART_FORM_DATA = "multipart/form-data"

    fun stringToRequestBody(string: String): RequestBody {
        return string.toRequestBody(MULTIPART_FORM_DATA.toMediaTypeOrNull())
    }

    fun intToRequestBody(string: String): RequestBody {
        return string.toRequestBody(
            "text/plain".toMediaTypeOrNull()
        )
    }

    fun intRequest(string: Int): RequestBody {
        return  RequestBody.create(
            okhttp3.MultipartBody.FORM, Gson().toJson(string));
    }



    fun imageToRequestBody(file: File): RequestBody {
        return file.asRequestBody(MULTIPART_FORM_DATA.toMediaTypeOrNull())
    }

    fun createPartFromFile(partName: String, file: File): MultipartBody.Part {
        // create RequestBody instance from file
        val requestFile = file.asRequestBody(MULTIPART_FORM_DATA.toMediaTypeOrNull())

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.name, requestFile)
    }

}