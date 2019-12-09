package com.example.assessmentthiago

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    private lateinit var victorySound: MediaPlayer
    private lateinit var losingSound: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val bundle = intent.extras
        val finalScore = bundle?.get("score") as Int
        txt_score.text = finalScore.toString()

        configureResultText(finalScore)
        playFinalSound(finalScore)
        setResultImage(finalScore)

        btn_play_again.setOnClickListener {
            goToMainActivity()
            victorySound.stop()
            losingSound.stop()
        }
    }

    private fun configureResultText(score: Int) {
        val wrongAnswersNumber = 5 - score
        val resultText: String
        if (score > 1) {
            resultText = "Você acertou $score perguntas e errou $wrongAnswersNumber!"
            txt_result.text = resultText
        } else {
            resultText = "Você acertou $score pergunta e errou $wrongAnswersNumber!"
            txt_result.text = resultText
        }
    }

    private fun playFinalSound(score: Int) {
        victorySound = MediaPlayer.create(this, R.raw.victory)
        losingSound = MediaPlayer.create(this, R.raw.fail)

        if (score >= 3) victorySound.start()
        else losingSound.start()
    }

    private fun setResultImage(score: Int) {
        if (score >= 3) imageView.setImageResource(R.drawable.goku2)
        else imageView.setImageResource(R.drawable.gokusad)
    }

    private fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}
