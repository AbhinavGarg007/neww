package com.example.emp354.linear.KotlinPractice.ThirdParty

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.emp354.linear.R

class ThirdPartyAssignment_1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_party_assignment_1)
    }

   private class AyncTaskLoadImages(): AsyncTask<Void, Void, ObjectPojoThirdParty>()
    {

        override fun doInBackground(vararg params: Void?): ObjectPojoThirdParty {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onPostExecute(result: ObjectPojoThirdParty?) {
            super.onPostExecute(result)
        }
    }
}
