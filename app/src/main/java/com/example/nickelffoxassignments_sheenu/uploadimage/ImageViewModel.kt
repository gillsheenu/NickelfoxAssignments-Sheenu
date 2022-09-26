package com.example.nickelffoxassignments_sheenu.uploadimage

import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(private var repository:UploadImageRepository):ViewModel() {

    var ImageLiveData=repository.UploadImageLiveData

   suspend fun imageUpload(uri: Uri){

        repository.uploadSelectedImage(uri)
    }
}