package com.example.assessmentthiago

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val bundle = intent.extras
        val finalScore = bundle?.get("score") as Int
        txt_score.text = finalScore.toString()

        configureResultText(finalScore)

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
}
