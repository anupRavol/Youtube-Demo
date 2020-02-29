package com.example.youtubedemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.youtubedemo.Constants.API_KEY
import com.example.youtubedemo.data.Item
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_youtube.*


class YoutubeActivity : YouTubeFailureRecoveryActivity() {

    companion object {
        val EXTRA_DATA = "data"
        val EXTRA_ITEM = "item"
        fun getIntent(context: Context, item: Item): Intent {
            val intent = Intent(context, YoutubeActivity::class.java).apply {
                val bundle = Bundle()
                bundle.putParcelable(EXTRA_ITEM, item)
                putExtra(EXTRA_DATA, bundle)
            }

            return intent
        }
    }

    lateinit var id: String
    var player: YouTubePlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)
        youtube_view.initialize(API_KEY, this)
        val extraData = intent?.getBundleExtra(EXTRA_DATA)
        val extraItem = extraData?.get(EXTRA_ITEM) as Item
        id = extraItem.id ?: let {
            finish()
            Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show()
            ""
        }
        youtube_view.initialize(API_KEY, this)
    }

    override val youTubePlayerProvider: YouTubePlayer.Provider
        get() = youtube_view

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        if (!p2) {
            player = p1
            player?.cueVideo(id)
            val handler = Handler()
            handler.postDelayed({
                player?.play()
            }, 100)
        }
    }
}
