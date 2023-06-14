package com.example.temantanam.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.TemanTanamTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadingDialog() {
    AlertDialog(
        modifier = Modifier
            .size(height = 100.dp, width = 200.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.tertiaryContainer),
        onDismissRequest = {}
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Loading...",
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun LoadingDialogPreview() {
    TemanTanamTheme {
        LoadingDialog()
    }
}