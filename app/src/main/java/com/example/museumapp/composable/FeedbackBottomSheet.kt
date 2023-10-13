package com.example.museumapp.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.museumapp.util.ShakeDetector
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackBottomSheet(shakeDetector: ShakeDetector?, onSendFeedback: (String) -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    var feedbackContent by remember { mutableStateOf(TextFieldValue()) }

    LaunchedEffect(key1 = shakeDetector) {
        shakeDetector?.setOnShakeListener {
            showBottomSheet = true
            if (!sheetState.isVisible) {
                scope.launch {
                    sheetState.show()
                }
            }
        }
    }

    val imeInsets = WindowInsets.ime
    val density = LocalDensity.current

    val keyboardHeightDp = with(density) { imeInsets.getBottom(this).toDp() }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 16.dp, 16.dp, 125.dp + keyboardHeightDp)
            ) {
                Text(text = "Feedback", style = MaterialTheme.typography.headlineMedium)

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = feedbackContent,
                    onValueChange = { feedbackContent = it },
                    label = { Text("Let us know about your experience!") },
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    maxLines = 5,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(onClick = {
                        scope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    }) {
                        Text("Discard")
                    }

                    Button(onClick = {
                        onSendFeedback(feedbackContent.text)
                        scope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    }) {
                        Text("Send")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun FeedbackBottomSheetPreview() {
    FeedbackBottomSheet(shakeDetector = null, onSendFeedback = {})
}