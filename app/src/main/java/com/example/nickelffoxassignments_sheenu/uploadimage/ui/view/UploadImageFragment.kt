
package com.example.nickelffoxassignments_sheenu.uploadimage.ui.view


import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.nickelffoxassignments_sheenu.R
import com.example.nickelffoxassignments_sheenu.news.utils.ConnectionLiveData
import com.example.nickelffoxassignments_sheenu.uploadimage.ui.viewmodel.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class UploadImageFragment : Fragment(){

    private lateinit var capturedImage: ImageView
    private lateinit var selectButton:Button
    private lateinit var imageViewModel: ImageViewModel
    private lateinit var imageSuccessResponse:TextView
    private lateinit var imageStatusCode:TextView
    private lateinit var uploadButton:Button
    private lateinit var connectedLiveData: ConnectionLiveData
    private lateinit var sucessHeadline:TextView
    private lateinit var statusHeadline:TextView
    var uri: Uri? =null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view=inflater.inflate(R.layout.fragment_upload_image, container, false)

        imageSuccessResponse=view.findViewById(R.id.tvSuccess)
        imageStatusCode=view.findViewById(R.id.tvStatus)
        connectedLiveData= ConnectionLiveData(requireActivity().application)

        imageViewModel= ViewModelProvider(this@UploadImageFragment)[ImageViewModel::class.java]

        uploadButton=view.findViewById(R.id.btnUpload)
        capturedImage=view.findViewById(R.id.ivCapturedImage)
        selectButton=view.findViewById(R.id.btnSelect)
        sucessHeadline=view.findViewById(R.id.tvSuccessHeadline)
        statusHeadline=view.findViewById(R.id.tvStatusHeadline)
        selectButton.isEnabled=false
        uploadButton.isEnabled=false
        capturedImage.setImageResource(R.drawable.select_image)

        connectedLiveData.observe(viewLifecycleOwner) { availableNetwork ->
            if (availableNetwork) {
                selectButton.isEnabled = true
                uploadButton.isEnabled = true
//                capturedImage.setImageResource(R.drawable.select_image)

            } else {
                selectButton.isEnabled = false
                uploadButton.isEnabled = false
                capturedImage.setImageResource(R.drawable.no_internet)
            }
        }




        val imageSelected=registerForActivityResult(ActivityResultContracts.OpenDocument()) {


            if (it!=null) {
                capturedImage.setImageURI(it)
                uri=it
            }else{
                Toast.makeText(activity,"No Image Selected",Toast.LENGTH_SHORT).show()
            }
        }

        selectButton.setOnClickListener {
            imageSelected.launch(arrayOf("image/*"))
        }


        uploadButton.setOnClickListener {

            imageSuccessResponse.visibility=View.GONE
            imageStatusCode.visibility=View.GONE
            if(uri!=null){

                CoroutineScope(Dispatchers.IO).launch {
                    imageViewModel.imageUpload(uri!!)
                }
            }else{
                Toast.makeText(activity,"No Image Selected",Toast.LENGTH_SHORT).show()
            }
        }



        imageViewModel.imageLiveData.observe(viewLifecycleOwner) {

            if(it!=null){
                sucessHeadline.visibility=View.VISIBLE
                imageSuccessResponse.visibility = View.VISIBLE
                imageSuccessResponse.text = it.success.toString()
                statusHeadline.visibility=View.VISIBLE
                imageStatusCode.visibility = View.VISIBLE
                imageStatusCode.text = it.status.toString()
            }else{
                Toast.makeText(activity,"Select Again by taping Select Option",Toast.LENGTH_SHORT).show()
            }

        }



        return view
    }





}
