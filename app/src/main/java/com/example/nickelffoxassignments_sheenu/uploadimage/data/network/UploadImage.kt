package com.example.nickelffoxassignments_sheenu.uploadimage.data.network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*



interface UploadImage {

    @Multipart
    @POST("3/upload")
   suspend fun uploadImage(
        @Part image:MultipartBody.Part?,
        @Part("name") name: RequestBody? =null
    ):Response<UploadImageResponse>


}
