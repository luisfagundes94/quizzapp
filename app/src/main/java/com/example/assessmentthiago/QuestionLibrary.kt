package com.example.assessmentthiago

class QuestionLibrary {
    private var questions =
        listOf(
            "1. Quem é o irmão de Gohan?",
            "2. Qual o nome do mago que tenta abrir a cápsula de Majin Boo?",
            "3. Qual o nome da esposa de Vegeta?",
            "4. Trunks é filho de quem?",
            "5. Qual é o nome do treinador de Beerus?"
        )

    private var choices =
        listOf(
            listOf("Goten", "Trunks", "Goku"),
            listOf("Bibidi", "Rei Dabura", "Babidi"),
            listOf("Chichi", "Pan", "Bulma"),
            listOf("Vegeta e Chichi", "Goku e Chichi", "Bulma e Vegeta"),
            listOf("Uis", "Bis", "Whis")
        )

    private var correctAnswers =
        listOf("Goten", "Babidi", "Bulma", "Bulma e Vegeta", "Whis")

    private var images =
        listOf(R.drawable.gohan, R.drawable.boo, R.drawable.vegeta,
            R.drawable.trunks, R.drawable.beerus)


    fun getQuestion(questionNumber: Int): String {
        return questions[questionNumber]
    }

    fun getChoice1(questionNumber: Int): String {
        return choices[questionNumber][0]
    }

    fun getChoice2(questionNumber: Int): String {
        return choices[questionNumber][1]
    }

    fun getChoice3(questionNumber: Int): String {
        return choices[questionNumber][2]
    }

    fun getCorrectAnswer(correctAnswerNumber: Int): String {
        return correctAnswers[correctAnswerNumber]
    }

    fun getImage(questionNumber: Int): Int {
        return images[questionNumber]
    }
}