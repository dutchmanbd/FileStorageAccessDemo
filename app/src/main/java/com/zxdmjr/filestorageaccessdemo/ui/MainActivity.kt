package com.zxdmjr.filestorageaccessdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.zxdmjr.filestorageaccessdemo.databinding.ActivityMainBinding
import com.zxdmjr.storagektx.CreateFileParams
import com.zxdmjr.storagektx.FileSelectionEntryPoint
import com.zxdmjr.storagektx.SelectFileParams
import com.zxdmjr.storagektx.StorageAccessInteractor
import java.io.File
import java.io.FileDescriptor
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : AppCompatActivity(), FileSelectionEntryPoint {

    private lateinit var binding: ActivityMainBinding

    private lateinit var fileSelection: StorageAccessInteractor

    private var fileContent = ""

    companion object {
        private const val TAG = "MainActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fileSelection = StorageAccessInteractor.create(this, this)
        binding.btnPickFile.setOnClickListener {
            fileSelection.beginSelectingFile(
                SelectFileParams("*/*")
            )
        }

        binding.btnCreateFile.setOnClickListener {
            fileContent = binding.etFileContent.text.toString()

            if(fileContent.isEmpty()){
                Toast.makeText(this, "Enter file content", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            fileSelection.beginCreatingFile(
                CreateFileParams(
                    fileMimeType = "text/*",
                    fileExtension = "txt",
                    suggestedName = "MyTextFile"
                )
            )
        }


    }

    override fun onFileCreated(fileDescriptor: FileDescriptor?) {
        fileDescriptor?.let { descriptor ->
            FileOutputStream(descriptor).use {
                it.write(fileContent.toByteArray())
                it.flush()
            }
        }
    }

    override fun onFileSelected(fileDescriptor: FileDescriptor?, fileName: String) {
        fileDescriptor?.let {
            val inputStream = FileInputStream(it)
            val file = File(cacheDir, fileName)
            val outputStream = FileOutputStream(file)
            inputStream.copyTo(outputStream)
            inputStream.close()
            outputStream.close()
            // you can use file to upload your server using multipart
        }
    }
}