package com.example.assessmentthiago

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var score = 0
    private var answer = ""
    private var questionNumber = 0
    private lateinit var correctAnswerRing: MediaPlayer
    private lateinit var wrongAnswerRing: MediaPlayer

    private val questionLibrary = QuestionLibrary()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        configureRadioGroup()
        onBtnNextClicked()
    }

    private fun showWrongAnswerMsg(correctAnswer: String) {
        txt_result.setTextColor(Color.RED)
        val wrongAnswerTxt = "Errado! a resposta certa é $correctAnswer"
        txt_result.text = wrongAnswerTxt
    }

    private fun showCorrectAnswerMsg() {
        txt_result.setTextColor(Color.GREEN)
        txt_result.text = "Resposta correta!"
    }

    private fun init() {
        correctAnswerRing = MediaPlayer.create(this, R.raw.correct)
        wrongAnswerRing = MediaPlayer.create(this, R.raw.wrong)
        btn_next.isEnabled = false
        updateImage()
        updateQuestion()
        updateChoices()
        updateAnswer()
        questionNumber++
    }

    private fun playCorrectAnswerRingtone() {
        correctAnswerRing.start()
    }

    private fun playWrongAnswerRingtone() {
        wrongAnswerRing.start()
    }

    private fun disableAllRadioButtons() {
        for (i in 0 until radioGroup.childCount) {
            radioGroup.getChildAt(i).isEnabled = false
        }
    }

    private fun enableAllRadioButtons() {
        for (i in 0 until radioGroup.childCount) {
            radioGroup.getChildAt(i).isEnabled = true
        }
    }

    private fun updateRadioGroup() {
        radioGroup.clearCheck()
    }

    private fun enableNextButton() {
        btn_next.isEnabled = true
    }


    private fun configureRadioGroup() {
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {

                R.id.rb_option_1 -> {
                    if (rb_option_1.text == answer) {
                        score++
                        showCorrectAnswerMsg()
                        playCorrectAnswerRingtone()
                    } else {
                        showWrongAnswerMsg(answer)
                        playWrongAnswerRingtone()
                    }

                    updateUiOnRadioButtonClicked()
                }

                R.id.rb_option_2 -> {
                    if (rb_option_2.text == answer) {
                        score++
                        showCorrectAnswerMsg()
                        playCorrectAnswerRingtone()
                    } else {
                        showWrongAnswerMsg(answer)
                        playWrongAnswerRingtone()
                    }

                    updateUiOnRadioButtonClicked()
                }

                R.id.rb_option_3 -> {
                    if (rb_option_3.text == answer) {
                        score++
                        showCorrectAnswerMsg()
                        playCorrectAnswerRingtone()
                    } else {
                        showWrongAnswerMsg(answer)
                        playWrongAnswerRingtone()
                    }

                    updateUiOnRadioButtonClicked()
                }
            }
        }
    }

    private fun onBtnNextClicked() {
        btn_next.setOnClickListener {
            updateImage()
            updateQuestion()
            updateChoices()
            updateAnswer()
            questionNumber++
            updateUiOnBtnClicked()
        }
    }

    private fun updateUiOnRadioButtonClicked() {
        disableAllRadioButtons()
        enableNextButton()
        updateRadioGroup()
    }

    private fun updateUiOnBtnClicked() {
        enableAllRadioButtons()
        txt_result.text = ""
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

    private fun updateImage() {
        img.setImageResource(questionLibrary.getImage(questionNumber))
    }

    private fun goToResultActivity() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("score", score)
        startActivity(intent)
    }

}
