package com.example.nickelffoxassignments_sheenu.uploadimage.data.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.nickelffoxassignments_sheenu.uploadimage.data.network.UploadImage
import com.example.nickelffoxassignments_sheenu.uploadimage.data.local.UploadImageResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class UploadImageRepository @Inject constructor(@ApplicationContext var context: Context, private var mUploadImage: UploadImage) {

    val uploadImageLiveData=MutableLiveData<UploadImageResponse>()

    suspend fun uploadSelectedImage(uri: Uri) {

        try {
            val file=copyStreamToFile(uri)

            val filePart= MultipartBody.Part.createFormData("image", file?.name,file!!.asRequestBody())
            val titlePart= "this is a new image".toRequestBody("text/plain".toMediaTypeOrNull())

            withContext(Dispatchers.IO){
                uploadAllImage(filePart,titlePart)
            }

        }catch (e:Exception){
            Log.d("RESP", "uploadSelectedImage: ${e.message}")
        }
    }


    private suspend fun uploadAllImage(filePart: MultipartBody.Part, titlePart: RequestBody) {

            val response= mUploadImage.uploadImage(filePart, titlePart)
            uploadImageLiveData.postValue(response.body())








    }

    private fun copyStreamToFile(uri: Uri): File? {

        val outputFile=File.createTempFile("temp",null)
        this.context.contentResolver.openInputStream(uri)?.let { inputStream ->

            val fileOutputStream=FileOutputStream(outputFile)
            inputStream.copyTo(fileOutputStream)
            inputStream.close()
            fileOutputStream.close()
            return  outputFile

        }
        return null
    }

}
