package com.example.museumapp.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun InfoDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(28.dp))
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "About",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "This is the Museum App, where you can explore various collections from renowned Finnish museums. Dive in to discover the wonders of art and history!",
                    modifier = Modifier.align(Alignment.Start),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                AnnotatedString.Builder("Authors: Ainara Larrañaga Flores, Ying Zhang, Anjan Shakya, Ivan Gromov").apply {
                    addStyle(SpanStyle(fontWeight = FontWeight.SemiBold), 0, 8)
                }.let {
                    Text(
                        text = it.toAnnotatedString(),
                        modifier = Modifier.align(Alignment.Start),
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                AnnotatedString.Builder("Feedback: Shake your phone to open the feedback form and let us know about your experience!").apply {
                    addStyle(SpanStyle(fontWeight = FontWeight.SemiBold), 0, 8)
                }.let {
                    Text(
                        text = it.toAnnotatedString(),
                        modifier = Modifier.align(Alignment.Start),
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                AnnotatedString.Builder("Version: 1.0 (October 2023)").apply {
                    addStyle(SpanStyle(fontWeight = FontWeight.SemiBold), 0, 8)
                }.let {
                    Text(
                        text = it.toAnnotatedString(),
                        modifier = Modifier.align(Alignment.Start),
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = onDismiss) {
                    Text("Close")
                }
            }
        }
    }
}