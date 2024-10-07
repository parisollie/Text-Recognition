package com.pjff.recognitionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pjff.recognitionapp.ui.theme.RecognitionAppTheme
import com.pjff.recognitionapp.viewModel.ScannerViewModel
import com.pjff.recognitionapp.views.TabsView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //Vid 217
        val viewModel : ScannerViewModel by viewModels()
        super.onCreate(savedInstanceState)
        setContent {
            RecognitionAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    TabsView(viewModel)
                }
            }
        }
    }
}

