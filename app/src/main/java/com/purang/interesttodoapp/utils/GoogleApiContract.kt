package com.purang.interesttodoapp.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task

class GoogleApiContract : ActivityResultContract<Int, Task<GoogleSignInAccount>?>() {
    override fun createIntent(context: Context, input: Int): Intent {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestScopes(Scope(Scopes.DRIVE_APPFOLDER))
            .requestIdToken(BuildConfig.GOOGLE_OAUTH_CLIENT_ID)
            .requestServerAuthCode(BuildConfig.GOOGLE_OAUTH_CLIENT_ID)
            .requestEmail()
            .build()

        val intent = GoogleSignIn.getClient(context, gso)
        return intent.signInIntent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Task<GoogleSignInAccount>? {
        Log.d("GoogleApiContract", "parseResult called with resultCode: $resultCode")
        return when (resultCode) {
            Activity.RESULT_OK -> {
                val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
                if (task.isSuccessful) {
                    Log.d("GoogleApiContract", "Google sign-in successful")
                } else {
                    Log.e("GoogleApiContract", "Google sign-in failed with exception: ${task.exception}")
                }
                task
            }
            else -> {
                Log.e("GoogleApiContract", "Google sign-in canceled or failed")
                null
            }
        }
    }
}