package com.example.tt_bssd5250_122

import android.annotation.SuppressLint
import android.graphics.Color
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.tt_bssd5250_122.MainActivity.Extra
import java.io.IOException

class MoodActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    var path: String? = null
    private val Extra = "extra"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val intent = intent
        val choice = intent.getStringExtra(Extra)
        if (choice == "Blue") {
            findViewById<View>(R.id.mood_layout).setBackgroundColor(Color.BLUE)
            path = "android.resource://" + this.packageName + "/raw/melody"
            imageView.setImageResource(R.drawable.blue)
        } else if (choice == "Yellow") {
            findViewById<View>(R.id.mood_layout).setBackgroundColor(Color.YELLOW)
            path = "android.resource://" + this.packageName + "/raw/piano"
            imageView.setImageResource(R.drawable.yellow)
        } else {
            findViewById<View>(R.id.mood_layout).setBackgroundColor(Color.GREEN)
            path = "android.resource://" + this.packageName + "/raw/step"
            imageView.setImageResource(R.drawable.green)
        }
        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
        val uri = Uri.parse(path)
        try {
            mediaPlayer!!.setDataSource(applicationContext, uri)
            mediaPlayer!!.prepare()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        mediaPlayer!!.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer!!.stop()
        mediaPlayer!!.release()
        mediaPlayer = null
    }
}