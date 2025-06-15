package com.example.thenoikapp

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button

class MainActivity : Activity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonIds = listOf(
            R.id.beep1Button, R.id.beep2Button, R.id.beep3Button, R.id.beep4Button, R.id.beep5Button,
            R.id.beep6Button, R.id.beep7Button, R.id.beep8Button, R.id.beep9Button, R.id.beep10Button
        )

        val soundIds = listOf(
            R.raw.beep1, R.raw.beep2, R.raw.beep3, R.raw.beep4, R.raw.beep5,
            R.raw.beep6, R.raw.beep7, R.raw.beep8, R.raw.beep9, R.raw.beep10
        )

        buttonIds.zip(soundIds).forEach { (buttonId, soundId) ->
            findViewById<Button>(buttonId).setOnClickListener {
                playSoundInBackground(soundId)
            }
        }
    }

    private fun playSoundInBackground(soundResId: Int) {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null

        mediaPlayer = MediaPlayer.create(this, soundResId)
        mediaPlayer?.setOnCompletionListener {
            it.release()
            mediaPlayer = null
        }
        mediaPlayer?.start()

        moveTaskToBack(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
