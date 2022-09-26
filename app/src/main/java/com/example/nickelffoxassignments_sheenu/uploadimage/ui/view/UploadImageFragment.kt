
package com.example.nickelffoxassignments_sheenu.uploadimage.ui.view


import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nickelffoxassignments_sheenu.R
import com.example.nickelffoxassignments_sheenu.news.utils.ConnectionLiveData
import com.example.nickelffoxassignments_sheenu.uploadimage.ui.viewmodel.ImageViewModel
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
    lateinit var connectionLiveData: ConnectionLiveData
    lateinit var uri: Uri

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view=inflater.inflate(R.layout.fragment_upload_image, container, false)

        imageSuccessResponse=view.findViewById(R.id.tvSuccess)
        imageStatusCode=view.findViewById(R.id.tvStatus)
        connectionLiveData= ConnectionLiveData(requireActivity().applicationContext)
        imageViewModel= ViewModelProvider(this@UploadImageFragment)[ImageViewModel::class.java]

        val imageSelected=registerForActivityResult(ActivityResultContracts.OpenDocument()) {

            capturedImage.setImageURI(it)
            if (it != null) {
                uri=it
            }else{
                Toast.makeText(activity,"No Image Selecgted",Toast.LENGTH_SHORT).show()
            }
//            CoroutineScope(Dispatchers.IO).launch {
//                imageViewModel.imageUpload(it!!)
//            }
//
        }
        connectionLiveData.observe(viewLifecycleOwner, Observer {  availableNetwork->
            if(availableNetwork){
                CoroutineScope(Dispatchers.IO).launch {
                    imageViewModel.imageUpload(uri)
                }

            }else{
                capturedImage.setImageResource(R.drawable.no_internet)

            }

        })

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
