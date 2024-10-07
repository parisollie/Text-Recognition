package com.pjff.recognitionapp.views

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.pjff.recognitionapp.R
import com.pjff.recognitionapp.viewModel.ScannerViewModel

@Composable
fun GalleryView(viewModel: ScannerViewModel) {
    val context = LocalContext.current
    //Vid 221
    val clipboard = LocalClipboardManager.current

    //Vid 218,any porque no sabemos que extension es
    var image: Any? by remember { mutableStateOf(R.drawable.gallery) }

    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ){
        if (it != null){
            image = it
            viewModel.onRecognizedText(image, context)
        }else{
            //Vid 220
            viewModel.showToast(context,"No se ha seleccionado ninguna imagen")
        }
    }

    //Vid 218,
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .clickable {
                    photoPicker.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                }
                .padding(16.dp, 8.dp),
            painter = rememberAsyncImagePainter(image),
            contentDescription = null
        )

        //Vid 220
        Spacer(modifier = Modifier.height(25.dp))

        val scrollState = rememberScrollState()
        Text(text = viewModel.recognizedText,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
                .verticalScroll(scrollState)
                .clickable {
                    //Vid 221, copiar las letras
                    clipboard.setText(AnnotatedString(viewModel.recognizedText))
                    viewModel.showToast(context,"Copiado")
                }
            )

    }

}










