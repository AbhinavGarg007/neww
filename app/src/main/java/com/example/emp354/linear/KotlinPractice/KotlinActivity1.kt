package com.example.emp354.linear.KotlinPractice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.View.OnClickListener
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import com.example.emp354.linear.R
import kotlinx.android.synthetic.main.activity_multithreading.*


class KotlinActivity1 : AppCompatActivity(), OnClickListener {
    override fun onClick(v: View?) {
        /*TODO("not implemented") //To change body of created functions use File | Settings | File Templates.*/

        when (v?.id) {

            R.id.btn_click -> {
                Toast.makeText(this, "You have clicked on click me button", Toast.LENGTH_SHORT).show()
            }
            R.id.btn_submit -> {
                tv_display.setText("You have to wait for " + tv_timer.getText().toString() + " seconds.")
                mHandler.postDelayed(run,1000)
            }
        }
    }


    /*var etTimer: EditText?=null*/
    val mHandler=Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multithreading)

        //no use of findViewById
        //etTimer=findViewById(R.id.tv_timer)as EditText

        btn_click.setOnClickListener(this)
        btn_submit.setOnClickListener(this)
    }




    var run= Runnable {
        updateTime()
    }


    fun updateTime(){
        val text:String= ("" + (Integer.parseInt(tv_timer.getText().toString()) - 1))
        tv_timer.setText(text)
        if (Integer.parseInt(tv_timer.getText().toString()) == 0)
        {
            btn_click.visibility= View.VISIBLE
        }
        else
        {
            mHandler.postDelayed(run,1000)

            }
        }
    }




