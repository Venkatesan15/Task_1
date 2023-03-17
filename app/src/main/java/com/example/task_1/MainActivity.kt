package com.example.task_1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var addBtn: Button
    lateinit var subBtn: Button
    lateinit var multiplyBtn: Button
    lateinit var divBtn: Button
    lateinit var resetBtn: Button
    lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Activity 1"
        declaration()
        addBtn.setOnClickListener(this)
        subBtn.setOnClickListener(this)
        multiplyBtn.setOnClickListener(this)
        divBtn.setOnClickListener(this)
        resetBtn.setOnClickListener(this)
    }

    private fun declaration() {
        addBtn = findViewById(R.id.addBtn)
        subBtn = findViewById(R.id.subBtn)
        multiplyBtn = findViewById(R.id.mulBtn)
        divBtn = findViewById(R.id.divBtn)
        resetBtn = findViewById(R.id.resetBtn)
        resultText = findViewById(R.id.textView)
    }


    override fun onClick(v: View?) {
        val i = Intent(this,Activity2::class.java)
        when(v) {
            addBtn -> {
                i.putExtra("process",addBtn.text.toString())
                resultLaunch.launch(i)
            }
            subBtn -> {
                i.putExtra("process",subBtn.text.toString())
                resultLaunch.launch(i)
            }
            multiplyBtn -> {
                i.putExtra("process",multiplyBtn.text.toString())
                resultLaunch.launch(i)
            }
            divBtn -> {
                i.putExtra("process",divBtn.text.toString())
                resultLaunch.launch(i)
            }
            resetBtn -> {
                addBtn.visibility = View.VISIBLE
                subBtn.visibility = View.VISIBLE
                multiplyBtn.visibility = View.VISIBLE
                divBtn.visibility = View.VISIBLE
                resetBtn.visibility = View.GONE
                resultText.visibility = View.GONE
            }
        }
    }

    private var resultLaunch = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        receiveResult(it)
    }
    private fun receiveResult(activityResult: ActivityResult) {
        val num1: String?
        val num2: String?
        val ans: String?
        val action: String?

        if (activityResult.resultCode == RESULT_OK) {
            val data: Intent? = activityResult.data
            num1 = data?.getStringExtra("num1")
            num2 = data?.getStringExtra("num2")
            ans = data?.getStringExtra("ans")
            action = data?.getStringExtra("action")

            addBtn.visibility = View.GONE
            subBtn.visibility = View.GONE
            multiplyBtn.visibility = View.GONE
            divBtn.visibility = View.GONE
            resetBtn.visibility = View.VISIBLE
            resultText.visibility = View.VISIBLE

            resultText.text = "Your Result is $ans for inputs $num1 and $num2 with action $action"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("Save", resultText.text.toString())
        outState.putBoolean("Visible", resultText.visibility == View.VISIBLE)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.getBoolean("Visible") ) {
            addBtn.visibility = View.GONE
            subBtn.visibility = View.GONE
            multiplyBtn.visibility = View.GONE
            divBtn.visibility = View.GONE
            resetBtn.visibility = View.VISIBLE
            resultText.visibility = View.VISIBLE
            resultText.text = savedInstanceState.getString("Save")
        }
    }

}