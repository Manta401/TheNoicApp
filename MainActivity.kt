package com.example.thenoikapp

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setBackgroundDrawableResource(android.R.color.transparent)

        mediaPlayer = MediaPlayer.create(this, R.raw.beep)
        mediaPlayer.setOnCompletionListener {
            it.release()
            finish()
        }

        mediaPlayer.start()

        moveTaskToBack(true)
    }
}
