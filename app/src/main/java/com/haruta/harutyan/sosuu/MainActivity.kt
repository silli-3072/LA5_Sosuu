package com.haruta.harutyan.sosuu

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.haruta.harutyan.sosuu.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var random: Random = Random()
    var questions: IntArray = IntArray(QUESTION_COUNT)
    var point: Int = 0
    var answerCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        for (i in 0 until QUESTION_COUNT) {
            var number = random.nextInt(1000)
            Log.d("Number", "Question$number")
            questions[i] = number
        }

        binding.textView.text = questions[answerCount].toString()
        binding.textView.setTextColor(Color.BLACK)

        binding.maruButton.setOnClickListener {
            var answer = true
            var number = questions[answerCount]

            for (i in 2 until number) {
                if (number % i == 0) {
                    answer = false
                    break
                }
            }

            if (answer) {
                Toast.makeText(this, "正解", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "不正解",Toast.LENGTH_SHORT).show()
            }

            if (answer) {
                point = point + 1
                Log.d("maru","正解$number")
            } else {
                Log.d("maru", "不正解")
            }

            answerCount = answerCount + 1

            if (answerCount == QUESTION_COUNT) {
                binding.textView.text = point.toString() + "点"
                binding.textView.setTextColor(Color.RED)

                point = 0
                answerCount = 0
            } else {
                binding.textView.text = questions[answerCount].toString()
                binding.textView.setTextColor(Color.BLACK)
            }

        }

        binding.batuButton.setOnClickListener {
            var answer = true
            var number = questions[answerCount]

            for (i in 2 until number) {
                if (number % i == 0) {
                    answer = false
                    break
                }
            }

            if (answer) {
                Toast.makeText(this, "不正解",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "正解", Toast.LENGTH_SHORT).show()
            }

            if (answer) {
                Log.d("maru", "不正解")
            } else {
                point++
                Log.d("maru","正解$number")
            }

            answerCount++

            if (answerCount == QUESTION_COUNT) {
                binding.textView.text = point.toString() + "点"
                binding.textView.setTextColor(Color.RED)

                point = 0
                answerCount = 0
            } else {
                binding.textView.text = questions[answerCount].toString()
                binding.textView.setTextColor(Color.BLACK)
            }
        }

    }

    companion object {
        private  const val QUESTION_COUNT: Int = 10
    }

}