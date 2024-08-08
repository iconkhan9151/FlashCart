package com.example.flash.ui

import android.inputmethodservice.Keyboard.Row
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.flash.R
import com.example.flash.data.InternetItem
import com.example.flash.data.InternetItemWithQuantity

@Composable
fun CartScreen(flashViewModel: FlashViewModel,
               onHomeButtonClicked:() ->Unit
               ){

    val cartItems by flashViewModel.cartItems.collectAsState()
    val cartItemsWithQuantity= cartItems.groupBy { it }
        .map{
            (item,cartItems)-> InternetItemWithQuantity(
                item,
                cartItems.size
            )
        }
    if (cartItems.isNotEmpty()){
        LazyColumn(contentPadding= PaddingValues(horizontal=10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            item {
                Image(painter = painterResource(id = R.drawable.grocery1), contentDescription = "Offer", modifier= Modifier.fillMaxSize())
            }
            item{
                Text(
                    text ="Review items",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

            }
            items(cartItemsWithQuantity){
                CartCard(
                    it.item,
                    flashViewModel,
                    it.quantity
                )
            }
            item{
                Text(
                    text ="Bill Details",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            }

            val totalPrice= cartItems.sumOf {
                it.itemPrice*75/100
            }
            val handlingCharge= totalPrice*1/100
            val deliverCharge= 30
            val grandTotal= totalPrice+handlingCharge+deliverCharge
            item{
                Card(
                    colors= CardDefaults.cardColors(
                        containerColor = Color(236,236,236,255)
                    ),
                    modifier= Modifier.fillMaxWidth()
                ){
                    Column(
                        modifier= Modifier.padding(10.dp)
                    ) {
                        BillRow(itemName = "Item Total", itemPrice = totalPrice, fontWeight = FontWeight.Normal)
                        BillRow(itemName = "Handling Charge", itemPrice = handlingCharge, fontWeight =  FontWeight.Light)
                        BillRow(itemName ="Delivery Fee" , itemPrice = deliverCharge, fontWeight = FontWeight.Light)
                        Divider(thickness= 1.dp, modifier = Modifier.padding(vertical=5.dp), color = Color.LightGray)
                        BillRow(itemName = "To Pay", itemPrice = grandTotal, fontWeight = FontWeight.ExtraBold)

                    }
                }
            }
        }

    }
    else{
        Column(
            modifier= Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = R.drawable.cart), contentDescription ="Empty Cart" ,
                modifier= Modifier.size(70.dp)
                )
            Text(text = "Your cart is Empty",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(20.dp)
            )
            FilledTonalButton(onClick = {
                onHomeButtonClicked()
            }) {
                Text(text = "Browse Products")
                
            }


        }
    }


}

@Composable
fun CartCard(
    cartItem:InternetItem,
    flashViewModel: FlashViewModel,
    cartItemQuantity: Int
    ){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        verticalAlignment = Alignment.CenterVertically
        
    ){
        AsyncImage(model = cartItem.imageUrl, contentDescription ="Item Image",
            modifier= Modifier
                .fillMaxHeight()
                .padding(start = 5.dp)
                .weight(4f)
        )
        Column(
            modifier= Modifier
                .padding(horizontal = 5.dp)
                .fillMaxHeight()
                .weight(4f),
            verticalArrangement = Arrangement.SpaceEvenly
        )
        {
            Text(text = cartItem.itemName,
                fontSize = 14.sp,
                maxLines=1
                )
            Text(text = cartItem.itemQuantity,
                fontSize = 14.sp,
                maxLines = 1
                )

        }
        Column(
            modifier= Modifier
                .padding(horizontal = 5.dp)
                .fillMaxHeight()
                .weight(3f),
            verticalArrangement = Arrangement.SpaceEvenly
        )
        {
            Text(text = "Rs.${cartItem.itemPrice}",
                fontSize = 12.sp,
                maxLines=1,
                color= Color.Gray,
                textDecoration = TextDecoration.LineThrough
            )
            Text(text = "Rs. ${cartItem.itemPrice * 75/100}",
                fontSize = 18.sp,
                maxLines = 1,
                color = Color(254,116,105,255)

            )

        }
        Column(modifier= Modifier
            .fillMaxHeight()
            .weight(3f),
            verticalArrangement = Arrangement.SpaceEvenly
            ) {
            Text(text = "Quantity:$cartItemQuantity",
                fontSize = 11.sp,
                textAlign = TextAlign.Center,
                modifier= Modifier.fillMaxWidth()
                )
            Card(modifier= Modifier
                .clickable {
                    flashViewModel.removeFromCart(oldItem = cartItem)
                }
                .fillMaxWidth(),
                colors= CardDefaults.cardColors(containerColor = Color(254,116,105,255))

            ) {
                Text(
                    text ="Remove",
                    color= Color.White,
                    fontSize = 11.sp,
                    textAlign = TextAlign.Center,
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)

                    )

            }

        }

    }
}
@Composable
fun BillRow(
    itemName:String,
    itemPrice: Int,
    fontWeight: FontWeight
    ){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier= Modifier.fillMaxWidth()
    ){
        Text(text = itemName, fontWeight= fontWeight)
        Text(text = "Rs. $itemPrice", fontWeight= fontWeight)
        
    }
}
