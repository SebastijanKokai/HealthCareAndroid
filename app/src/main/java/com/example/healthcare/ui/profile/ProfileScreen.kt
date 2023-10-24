package com.example.healthcare.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.healthcare.data.room.entities.UserEntity

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val userState: UserEntity? by profileViewModel.userData.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (userState?.profilePictureUrl != null) {
            AsyncImage(
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                model = userState?.profilePictureUrl,
                contentDescription = "Profile picture",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        if (userState?.email != null) {
            Text(
                text = userState?.email!!,
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        if (userState?.firstName != null && userState?.lastName != null) {
            Text(
                text = userState!!.firstName + " " + userState!!.lastName,
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}