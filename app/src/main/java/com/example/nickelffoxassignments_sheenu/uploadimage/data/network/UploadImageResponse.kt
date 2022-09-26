package com.example.nickelffoxassignments_sheenu.uploadimage.data.network

import com.example.nickelffoxassignments_sheenu.uploadimage.data.local.Data

data class UploadImageResponse(
    val `data`: Data,
    val status: Int,
    val success: Boolean
)