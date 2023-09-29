package com.qwict.studyplanetandroid.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onStartExploringButtonClicked: () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
//        Row(
//            modifier = modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.Center
//        ) {
//            Column(
//                Modifier.fillMaxWidth(),
//                verticalArrangement = Arrangement.Center
//            ) {
                OutlinedButton(onClick = {onStartExploringButtonClicked()}) {
                    Text(text = "Start Exploring")
                }
            }

        }
//    }
//}