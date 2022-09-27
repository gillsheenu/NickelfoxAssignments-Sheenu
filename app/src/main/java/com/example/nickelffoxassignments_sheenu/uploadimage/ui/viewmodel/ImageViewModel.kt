package com.example.nickelffoxassignments_sheenu.uploadimage.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.nickelffoxassignments_sheenu.uploadimage.data.repository.UploadImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(private var repository: UploadImageRepository):ViewModel() {

    var imageLiveData=repository.uploadImageLiveData

   suspend fun imageUpload(uri: Uri){

        repository.uploadSelectedImage(uri)
    }
}