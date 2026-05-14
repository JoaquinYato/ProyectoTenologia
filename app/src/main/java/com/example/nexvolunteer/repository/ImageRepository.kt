package com.example.nexvolunteer.repository

import android.content.Context
import android.net.Uri
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import okhttp3.MediaType.Companion.toMediaTypeOrNull

class ImageRepository {

    fun uploadImage(

        context: Context,

        uri: Uri,

        onSuccess: (String) -> Unit,

        onError: (String) -> Unit
    ) {

        try {

            val inputStream =
                context.contentResolver
                    .openInputStream(uri)

            val bytes =
                inputStream?.readBytes()

            if (bytes == null) {

                onError("Error imagen")
                return
            }

            val requestBody =
                MultipartBody.Builder()

                    .setType(
                        MultipartBody.FORM
                    )

                    .addFormDataPart(

                        "image",

                        "image.jpg",

                        RequestBody.create(

                            "image/*"
                                .toMediaTypeOrNull(),

                            bytes
                        )
                    )

                    .build()

            val request = Request.Builder()

                .url(

                    "https://api.imgbb.com/1/upload?key=cc2a9af3f30131c5e081cb5c60f0d88e"
                )

                .post(requestBody)

                .build()

            OkHttpClient()

                .newCall(request)

                .enqueue(

                    object : Callback {

                        override fun onFailure(

                            call: Call,

                            e: IOException
                        ) {

                            onError(
                                e.message ?: "Error"
                            )
                        }

                        override fun onResponse(

                            call: Call,

                            response: Response
                        ) {

                            val body =
                                response.body?.string()

                            if (body != null) {

                                val json =
                                    JSONObject(body)

                                val url =
                                    json.getJSONObject("data")
                                        .getString("url")

                                onSuccess(url)
                            }
                        }
                    }
                )

        } catch (e: Exception) {

            onError(e.message ?: "Error")
        }
    }
}