package com.zxdmjr.filestorageaccessdemo.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.zxdmjr.storagektx.FileSelectionEntryPoint
import com.zxdmjr.storagektx.StorageAccessInteractor
import java.io.FileDescriptor

class FileTestFragment : Fragment(), FileSelectionEntryPoint {

    private lateinit var fileSelection: StorageAccessInteractor
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fileSelection = StorageAccessInteractor.create(this, this)


    }

    override fun onFileCreated(fileDescriptor: FileDescriptor?) {
        TODO("Not yet implemented")
    }

    override fun onFileSelected(fileDescriptor: FileDescriptor?, fileName: String) {
        TODO("Not yet implemented")
    }

}