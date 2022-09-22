
package com.example.nickelffoxassignments_sheenu


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.nickelffoxassignments_sheenu.uploadimage.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UploadImageFragment : Fragment(){

    private lateinit var capturedImage: ImageView
    private lateinit var selectButton:Button
    private lateinit var imageViewModel: ImageViewModel
    private lateinit var imageSuccessResponse:TextView
    private lateinit var imageStatusCode:TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view=inflater.inflate(R.layout.fragment_upload_image, container, false)
        imageSuccessResponse=view.findViewById(R.id.tvSuccess)
        imageStatusCode=view.findViewById(R.id.tvStatus)
        imageViewModel= ViewModelProvider(this@UploadImageFragment)[ImageViewModel::class.java]

        val imageSelected=registerForActivityResult(ActivityResultContracts.OpenDocument()) {

            capturedImage.setImageURI(it)
            CoroutineScope(Dispatchers.IO).launch {
                imageViewModel.imageUpload(it!!)
            }
           

        }
        imageViewModel.ImageLiveData.observe(viewLifecycleOwner) {
            imageSuccessResponse.text = it.success.toString()
            imageStatusCode.text = it.status.toString()
        }

        capturedImage=view.findViewById(R.id.ivCapturedImage)
        selectButton=view.findViewById(R.id.btnSelect)
        selectButton.setOnClickListener {
            imageSelected.launch(arrayOf("image/*"))
        }

        return view
    }



}
