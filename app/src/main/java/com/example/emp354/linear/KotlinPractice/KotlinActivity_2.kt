package com.example.emp354.linear.KotlinPractice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import com.example.emp354.linear.R
import kotlinx.android.synthetic.main.activity_multithreading_2.*
import java.lang.Exception

class KotlinActivity_2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multithreading_2)
    }

    fun startProgress(v: View)
    {

        var runnable= Runnable {
            for (i in 0..9)
            {
                var value:Int=i
                fakeWork()
                progressBar.post(Runnable {
                    textView.setText("Updating")
                    progressBar.setProgress(value)
                })
            }
        }

        Thread(runnable).start()
    }

    fun fakeWork()
    {
        try {
            SystemClock.sleep(1000)
        }
        catch (e:Exception)
        {
            e.printStackTrace()
        }
    }
}
