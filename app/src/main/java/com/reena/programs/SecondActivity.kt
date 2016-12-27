package com.reena.programs

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        tvSecondActivity.setText("Hey Kotlin Direct Access")
        tvSecondActivity.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)}
    }



    override fun onStart() {
        super.onStart()
        printLog(localClassName, "onStart")
    }

    override fun onResume() {
        super.onResume()
        printLog(localClassName, "onResume")
    }

    override fun onPause() {
        super.onPause()
        printLog(localClassName, "onPause")
    }

    override fun onStop() {
        super.onStop()
        printLog(localClassName, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        printLog(localClassName, "onDestroy")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        printLog(localClassName, "onNewIntent")
    }

    override fun onRestart() {
        super.onRestart()
        printLog(localClassName, "onRestart")
    }

    fun printLog(className: String, errMsg: String) {
        Log.e(className, errMsg)
    }
}
