package com.zxdmjr.filestorageaccessdemo.util

data class CreateFileParams(
    val fileMimeType: String,
    val fileExtension: String,
    val suggestedName: String
)