package com.zxdmjr.storagektx

data class CreateFileParams(
    val fileMimeType: String,
    val fileExtension: String,
    val suggestedName: String
)