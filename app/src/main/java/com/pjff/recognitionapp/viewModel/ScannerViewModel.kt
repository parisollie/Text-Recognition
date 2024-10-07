package com.pjff.recognitionapp.viewModel

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.io.IOException

class ScannerViewModel:ViewModel() {

    //vid 219
    var recognizedText by mutableStateOf("")
        private set

    //para que se nos limpie el texto
    fun cleanText(){
        recognizedText = ""
    }
    //Vid 219,funcion,para leer el texto
    fun onRecognizedText(text: Any?, context: Context){
        var image: InputImage? = null
        try {
            //buscamos la ruta de la imagen
            image = InputImage.fromFilePath(context, text as Uri)
        } catch (e: IOException){
            e.printStackTrace()
        }

        //Si existe nuestra imagén
        image?.let {
            TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS).process(it)
                .addOnSuccessListener { text ->
                    recognizedText = text.text
                }.addOnFailureListener{
                    showToast(context, "Error al leer la imagen")
                }
        }
    }
    //Vid 219,
    fun showToast(context: Context, message : String){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}