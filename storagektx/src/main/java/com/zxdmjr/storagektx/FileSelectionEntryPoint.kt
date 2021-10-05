package com.zxdmjr.storagektx


import java.io.FileDescriptor

interface FileSelectionEntryPoint {

    fun onFileCreated(fileDescriptor: FileDescriptor?)

    fun onFileSelected(fileDescriptor: FileDescriptor?, fileName: String)
}