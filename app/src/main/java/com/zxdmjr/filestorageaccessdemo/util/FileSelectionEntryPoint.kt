package com.zxdmjr.filestorageaccessdemo.util


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import java.io.FileDescriptor

interface FileSelectionEntryPoint {

    val fileSelectionOwner: AppCompatActivity

    fun onFileCreated(fileDescriptor: FileDescriptor?)

    fun onFileSelected(fileDescriptor: FileDescriptor?, fileName: String)
}