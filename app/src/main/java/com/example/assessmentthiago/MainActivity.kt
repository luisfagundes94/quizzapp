package com.example.assessmentthiago

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var score: Int = 0
    private var answer: String = ""
    private var userChoice: String = ""
    private var questionNumber: Int = 0
    private lateinit var correctAnswerRing: MediaPlayer
    private lateinit var wrongAnswerRing: MediaPlayer

    private val questionLibrary = QuestionLibrary()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        configureRadioGroup()

        btn_next.setOnClickListener {
            validateUserChoice()
            updateUi()
            questionNumber++
            updateTextResult()
        }
    }

    private fun init() {
        correctAnswerRing = MediaPlayer.create(this, R.raw.correct)
        wrongAnswerRing = MediaPlayer.create(this, R.raw.wrong)
        btn_next.isEnabled = false
        updateUi()
        questionNumber++
    }

    private fun updateUi() {
        updateImage()
        updateQuestion()
        updateChoices()
        updateAnswer()
    }

    private fun playCorrectAnswerRingtone() = correctAnswerRing.start()
    private fun playWrongAnswerRingtone() = wrongAnswerRing.start()

    private fun updateRadioGroup() = radioGroup.clearCheck()

    private fun enableNextButton() {
        btn_next.isEnabled = true
    }

    private fun disableNextButton() {
        btn_next.isEnabled = false
    }

    private fun validateUserChoice() {
        if (userChoice == answer) {
            score++
            playCorrectAnswerRingtone()
        } else playWrongAnswerRingtone()

        updateRadioGroup()
        disableNextButton()
    }

    private fun configureRadioGroup() {
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_option_1 -> {
                    enableNextButton()
                    userChoice = rb_option_1.text.toString()
                }
                R.id.rb_option_2 -> {
                    enableNextButton()
                    userChoice = rb_option_2.text.toString()
                }
                R.id.rb_option_3 -> {
                    enableNextButton()
                    userChoice = rb_option_3.text.toString()
                }
            }
        }
    }

    private fun updateTextResult() {
        val resultText = "Score: $score/5"
        txt_result.text = resultText
    }

    private fun updateQuestion() {
        if (questionNumber == 4) {
            btn_next.text = getString(R.string.txt_btn_see_final_result)
            btn_next.setOnClickListener {
                goToResultActivity()
            }
        }

        question.text = questionLibrary.getQuestion(questionNumber)
    }

    private fun updateChoices() {
        rb_option_1.text = questionLibrary.getChoice1(questionNumber)
        rb_option_2.text = questionLibrary.getChoice2(questionNumber)
        rb_option_3.text = questionLibrary.getChoice3(questionNumber)
    }

    private fun updateAnswer() {
        answer = questionLibrary.getCorrectAnswer(questionNumber)
    }

    private fun updateImage() = img.setImageResource(questionLibrary.getImage(questionNumber))

    private fun goToResultActivity() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("score", score)
        startActivity(intent)
    }

}
