package com.example.quizgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.quizgame.databinding.FragmentQuestionBinding
import java.nio.file.Files.size


class QuestionFragment : Fragment(),View.OnClickListener {
    private lateinit var binding:FragmentQuestionBinding
    private var count = 0
    var userAnswer = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionBinding.inflate(inflater,container,false)

        binding.button1.setOnClickListener(this)
        binding.button2.setOnClickListener(this)
        binding.button3.setOnClickListener(this)
        binding.button4.setOnClickListener(this)
        binding.restartBtn.setOnClickListener {
            startGame()
            updateUI()
        }
        startGame()
        updateUI()
        return binding.root
    }

    private fun updateUI() {
        binding.questionTV.text = "${count + 1}. ${questionList.get(count).question}"
        binding.button1.text = questionList.get(count).answers.get(0)
        binding.button2.text = questionList.get(count).answers.get(1)
        binding.button3.text = questionList.get(count).answers.get(2)
        binding.button4.text = questionList.get(count).answers.get(3)
    }

    private fun startGame() {
        count = 0
        userAnswer.clear()
        binding.questionTV.visibility = View.VISIBLE
        binding.questionButtonLayout.visibility = View.VISIBLE
        binding.resultTv.visibility = View.GONE
        binding.restartBtn.visibility = View.GONE
    }

    override fun onClick(p0: View?) {
        userAnswer.add((p0 as Button).text.toString())
        count++
        if (count < questionList.size){
            updateUI()
        }else {
            endGame()
            showResult()
        }
    }

    private fun showResult() {
        var correct = 0
        userAnswer.forEachIndexed { index, s ->
            if (s == questionList.get(index).correctAnswer) {
                correct++
            }
        }
        binding.resultTv.text = "Correct Answers: $correct"
    }

    private fun endGame() {
        binding.questionTV.visibility = View.GONE
        binding.questionButtonLayout.visibility = View.GONE
        binding.resultTv.visibility = View.VISIBLE
        binding.restartBtn.visibility = View.VISIBLE
    }

}

data class QuestionSet(
    val question:String,
    val answers:List<String>,
    val correctAnswer:String
)
val questionList = listOf(
    QuestionSet("What is Android?", listOf("OS","Language","File","Game"),"OS"),
    QuestionSet("What is Java?", listOf("OS","Language","File","Game"),"Language"),
    QuestionSet("Android is based on which of the following language?", listOf("C","C++","Java","None"),"Java"),
    QuestionSet("Which of the following kernel is used in Android?", listOf("MAC","Windows","Linux","Redhat"),"Linux"),
    QuestionSet("Does android support other languages than java?", listOf("Yes","No","Can't say","Maybe"),"Yes"),
    QuestionSet("Which of the following is contained in the src folder?", listOf("XML","Java source code","Manifest","None of the above"),"Java source code"),
    QuestionSet("Which of the following method is used to handle what happens after clicking a button?", listOf("onClick","onCreate","onSelect","None of the above"),"onClick"),
    QuestionSet("Which of the following android component displays the part of an activity on screen?", listOf("View","Manifest","Intent","Fragment"),"Fragment"),
    QuestionSet("Which of the following is the parent class of Activity?", listOf("context","object","contextThemeWrapper","None of the above"),"contextThemeWrapper"),
    QuestionSet("In which of the following tab an error is shown?", listOf("CPU","Memory","ADB Logs","Logcat"),"Logcat")
)


