package com.example.flash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.flash.R
@Composable
fun LoginUi(flashViewModel: FlashViewModel){
    Column(
        modifier= Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.cart),
            contentDescription = "Icon",
            modifier = Modifier
                .padding(
                    top = 50.dp,
                    bottom = 10.dp
                )
                .size(100.dp)
            )
        NumberScreen(flashViewModel = flashViewModel)
    }

}