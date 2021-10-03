package com.zxdmjr.filestorageaccessdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.zxdmjr.filestorageaccessdemo.R
import com.zxdmjr.filestorageaccessdemo.util.FileSelectionEntryPoint
import com.zxdmjr.filestorageaccessdemo.util.StorageAccessFrameworkInteractor
import java.io.File
import java.io.FileDescriptor
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : AppCompatActivity(), FileSelectionEntryPoint {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        StorageAccessFrameworkInteractor(this)
    }

    override val fileSelectionOwner: AppCompatActivity = this

    override fun onFileCreated(fileDescriptor: FileDescriptor?) {

    }

    override fun onFileSelected(fileDescriptor: FileDescriptor?) {
        fileDescriptor?.let {
            val stream = FileInputStream(it)
            val file = File(cacheDir, "UploadFile")
        }
    }
}