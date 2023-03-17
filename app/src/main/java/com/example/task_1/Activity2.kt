package com.example.task_1
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class Activity2 : AppCompatActivity() {

    lateinit var eNum1: EditText
    lateinit var eNum2: EditText
    lateinit var resBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        supportActionBar?.title = "Activity 2"
        val process = intent.getStringExtra("process").toString()
        eNum1 = findViewById(R.id.eNum1)
        eNum2 = findViewById(R.id.eNum2)
        resBtn = findViewById(R.id.resBtn)
        resBtn.text = process

        resBtn.setOnClickListener {
            getResult(process)
        }

    }


    private fun getResult(process: String) {

        if (eNum1.text.toString().trim().isNotEmpty() && eNum2.text.toString().trim().isNotEmpty()) {
            val i = Intent()
            val num1: Float = eNum1.text.toString().toFloat()
            val num2: Float = eNum2.text.toString().toFloat()

            val ans = when(process) {
                "Add" -> (num1 + num2)
                "Subtract" -> (num1 - num2)
                "Multiply" -> (num1 * num2)
                "Division" -> (num1/num2)
                else -> null!!
            }

            val format = DecimalFormat("0.#")
            i.putExtra("num1", format.format(num1))
            i.putExtra("num2", format.format(num2))
            i.putExtra("ans", format.format(ans))
            i.putExtra("action", process)
            setResult(RESULT_OK, i)
            finish()
        }
        else {
            Toast.makeText(this,"Enter Input",Toast.LENGTH_SHORT).show()
            setResult(RESULT_CANCELED,null)
        }
    }
}