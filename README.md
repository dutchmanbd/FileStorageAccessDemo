# FileStorageAccess
[![Version](https://jitpack.io/v/dutchmanbd/storagektx.svg?label=lastest)](https://jitpack.io/#dutchmanbd/storagektx)

How to Select Files in Android Easily? Connecting Storage Access Framework and Activity Result API

## Get Started
Add it in your root build.gradle at the end of repositories:

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Add the dependency

```
dependencies {
	implementation "com.github.dutchmanbd:storagektx:<latest_version>"
}
```
### Use in Activity
```
class MainActivity : AppCompatActivity(), FileSelectionEntryPoint{
    private lateinit var fileSelection: StorageAccessInteractor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ..........
        fileSelection = StorageAccessInteractor.create(this, this)
        binding.btnPickFile.setOnClickListener {
            fileSelection.beginSelectingFile(
                SelectFileParams("*/*")
            )
        }

        binding.btnCreateFile.setOnClickListener {
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
        // Write your code here
    }

    override fun onFileSelected(fileDescriptor: FileDescriptor?, fileName: String) {
        // Write your code here
    }
}
```

### Use in fragment
```
class FileTestFragment : Fragment(), FileSelectionEntryPoint{
    private lateinit var fileSelection: StorageAccessInteractor
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fileSelection = StorageAccessInteractor.create(this, this)
        binding.btnPickFile.setOnClickListener {
            fileSelection.beginSelectingFile(
                SelectFileParams("*/*")
            )
        }

        binding.btnCreateFile.setOnClickListener {
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
        // Write your code here
    }

    override fun onFileSelected(fileDescriptor: FileDescriptor?, fileName: String) {
        // Write your code here
    }
}
```
