package com.zxdmjr.filestorageaccessdemo.util

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher

class StorageAccessFrameworkInteractor(private val fileSelectionEntryPoint: FileSelectionEntryPoint) {

    private val createFileLauncher: ActivityResultLauncher<CreateFileParams> =
        fileSelectionEntryPoint.fileSelectionOwner
            .registerForActivityResult(CreateFileResultContract()) { uri ->
                onFileCreationFinished(uri)
            }

    private val selectFileLauncher: ActivityResultLauncher<SelectFileParams> =
        fileSelectionEntryPoint.fileSelectionOwner
            .registerForActivityResult(SelectFileResultContract()) { uri ->
                onFileSelectionFinished(uri)
            }

    private fun beginCreatingFile(createFileParams: CreateFileParams) =
        createFileLauncher.launch(createFileParams)

    private fun onFileCreationFinished(fileUri: Uri?) {
        val fileDescriptor = fileUri?.let { uri ->
            fileSelectionEntryPoint.fileSelectionOwner
                .contentResolver
                .openFileDescriptor(uri, "w")
                ?.fileDescriptor
        }

        fileSelectionEntryPoint.onFileCreated(fileDescriptor)
    }

    private fun beginSelectingFile(selectFileParams: SelectFileParams) =
        selectFileLauncher.launch(selectFileParams)

    private fun onFileSelectionFinished(fileUri: Uri?) {
        val fileDescriptor = fileUri?.let { uri ->
            fileSelectionEntryPoint.fileSelectionOwner
                .contentResolver
                .openFileDescriptor(uri, "r")
                ?.fileDescriptor
        }

        fileSelectionEntryPoint.onFileSelected(fileDescriptor)
    }
}