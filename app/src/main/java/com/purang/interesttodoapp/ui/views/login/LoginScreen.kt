package com.purang.interesttodoapp.ui.views.login

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.purang.interesttodoapp.ui.viewmodels.LoginViewModel
import com.purang.interesttodoapp.utils.GoogleApiContract

sealed class ApiState {
    data object Idle : ApiState()
    data object Loading : ApiState()
    data class Success(val message: String) : ApiState()
    data class Error(val message: String) : ApiState()
}

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginState by viewModel.loginApiState.collectAsState()

    val authResultLauncher = rememberLauncherForActivityResult(
        contract = GoogleApiContract()
    ) { task ->
        viewModel.handleGoogleSignInResult(task)
    }

    Button(onClick = { authResultLauncher.launch(1) }) {
        Text("Google로 로그인")
    }


    when (loginState) {
        is ApiState.Loading -> Text("로그인 중...")
        is ApiState.Success -> Text("로그인 성공!")
        is ApiState.Error -> Text("로그인 실패: ${(viewModel.loginApiState as ApiState.Error).message}")
        else -> {}
    }
}