package com.example.youtubedemo

import android.content.Intent
import android.widget.Toast
import com.example.youtubedemo.Constants.API_KEY

import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

/**
 * An abstract activity which deals with recovering from errors which may occur during API
 * initialization, but can be corrected through user action.
 */
abstract class YouTubeFailureRecoveryActivity : YouTubeBaseActivity(),
    YouTubePlayer.OnInitializedListener {

    protected abstract val youTubePlayerProvider: YouTubePlayer.Provider

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider,
        errorReason: YouTubeInitializationResult
    ) {
        if (errorReason.isUserRecoverableError) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show()
        } else {
            val errorMessage =
                String.format(getString(R.string.error_player), errorReason.toString())
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            youTubePlayerProvider.initialize(API_KEY, this)
        }
    }

    companion object {

        private val RECOVERY_DIALOG_REQUEST = 1
    }

}
