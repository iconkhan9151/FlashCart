package com.example.flash.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp

@Composable
fun NumberScreen(
    flashViewModel: FlashViewModel
){
    val phoneNumber by flashViewModel.phoneNumber.collectAsState()
    Text(text = "LOGIN",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
        )
    Text(text = "Enter Your Phone Number To Proceed",
        fontSize = 20.sp,
        modifier = Modifier.fillMaxWidth()
        )
    Text(text = "This Phone number will be used for the purpose of all communication.You Will Receive an SMS with a Code for Verification",
        fontSize = 12.sp,
        color= Color(105,103,100)
        )
    TextField(
        value =phoneNumber ,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        onValueChange ={
            flashViewModel.setPhoneNumber(it)

        },
        label= {
            Text(text = "Your Number")
        },
        modifier = Modifier.fillMaxWidth(),
        singleLine= true
    )
    Button(
        onClick = { /*TODO*/ },
        modifier= Modifier.fillMaxWidth()
        ) {
        Text(text = "Send OTP")
    }


}