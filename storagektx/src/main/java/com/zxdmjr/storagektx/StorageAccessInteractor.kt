package com.zxdmjr.storagektx

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class StorageAccessInteractor private constructor() {

    companion object {
        private lateinit var fileSelectionEntryPoint: FileSelectionEntryPoint
        private var activityOwner: AppCompatActivity? = null
        private var fragmentOwner: Fragment? = null

        @JvmStatic
        fun create(
            activity: AppCompatActivity,
            fileSelectionEntryPoint: FileSelectionEntryPoint
        ): StorageAccessInteractor {
            activityOwner = activity
            this.fileSelectionEntryPoint = fileSelectionEntryPoint
            return StorageAccessInteractor()
        }

        @JvmStatic
        fun create(
            fragment: Fragment,
            fileSelectionEntryPoint: FileSelectionEntryPoint
        ): StorageAccessInteractor {
            fragmentOwner = fragment
            this.fileSelectionEntryPoint = fileSelectionEntryPoint
            return StorageAccessInteractor()
        }
    }


    private val createFileLauncher: ActivityResultLauncher<CreateFileParams> =
        if (activityOwner != null) {
            activityOwner!!.registerForActivityResult(CreateFileResultContract()) { uri ->
                onFileCreationFinished(uri)
            }
        } else {
            fragmentOwner!!.registerForActivityResult(CreateFileResultContract()) { uri ->
                onFileCreationFinished(uri)
            }
        }

    private val selectFileLauncher: ActivityResultLauncher<SelectFileParams> =
        if (activityOwner != null) {
            activityOwner!!.registerForActivityResult(SelectFileResultContract()) { uri ->
                onFileSelectionFinished(uri)
            }
        } else {
            fragmentOwner!!.registerForActivityResult(SelectFileResultContract()) { uri ->
                onFileSelectionFinished(uri)
            }
        }

    fun beginCreatingFile(createFileParams: CreateFileParams) =
        createFileLauncher.launch(createFileParams)

    private fun onFileCreationFinished(fileUri: Uri?) {
        val contentResolver =
            if (activityOwner != null) activityOwner!!.contentResolver else fragmentOwner!!.requireContext().contentResolver
        val fileDescriptor = fileUri?.let { uri ->
            contentResolver
                .openFileDescriptor(uri, "w")
                ?.fileDescriptor
        }

        fileSelectionEntryPoint.onFileCreated(fileDescriptor)
    }

    fun beginSelectingFile(selectFileParams: SelectFileParams) =
        selectFileLauncher.launch(selectFileParams)

    private fun onFileSelectionFinished(fileUri: Uri?) {
        val contentResolver =
            if (activityOwner != null) activityOwner!!.contentResolver else fragmentOwner!!.requireContext().contentResolver
        val fileDescriptor = fileUri?.let { uri ->
            contentResolver
                .openFileDescriptor(uri, "r")
                ?.fileDescriptor
        }

        val fileName = fileUri?.let {
            contentResolver.getFileName(it)
        } ?: ""

        fileSelectionEntryPoint.onFileSelected(
            fileDescriptor,
            fileName
        )
    }
}