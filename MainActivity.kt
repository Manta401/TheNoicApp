package com.example.thenoikapp

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button

class MainActivity : Activity() {

    private var mediaPlayer: MediaPlayer? = null
    private var isPaused = false
    private var currentTrackResId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inizializza i pulsanti musicali
        for (i in 1..34) {
            val buttonId = resources.getIdentifier("beep${i}Button", "id", packageName)
            val soundId = resources.getIdentifier("beep$i", "raw", packageName)

            val button = findViewById<Button>(buttonId)
            button?.setOnClickListener {
                playSoundInBackground(soundId)
            }
        }

        // Pulsante Play/Pausa
        val playPauseButton = findViewById<Button>(R.id.playPauseButton)
        val stopButton = findViewById<Button>(R.id.stopButton)

        playPauseButton.setOnClickListener {
            if (mediaPlayer != null) {
                if (mediaPlayer!!.isPlaying) {
                    mediaPlayer?.pause()
                    isPaused = true
                } else {
                    mediaPlayer?.start()
                    isPaused = false
                }
            }
        }

        // Pulsante Stop
        stopButton.setOnClickListener {
            stopPlayback()
        }
    }

    private fun playSoundInBackground(soundResId: Int) {
        if (currentTrackResId == soundResId && isPaused) {
            mediaPlayer?.start()
            isPaused = false
            return
        }

        stopPlayback()

        mediaPlayer = MediaPlayer.create(this, soundResId)
        currentTrackResId = soundResId
        isPaused = false

        mediaPlayer?.isLooping = true  // Loop automatico del brano

        mediaPlayer?.start()

        moveTaskToBack(true)
    }

    private fun stopPlayback() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        isPaused = false
        currentTrackResId = null
    }

    override fun onDestroy() {
        super.onDestroy()
        stopPlayback()
    }
}
