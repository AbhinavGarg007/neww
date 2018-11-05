package com.example.emp354.linear.KotlinPractice.ThirdParty

import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.example.emp354.linear.JSONUtils
import com.example.emp354.linear.KotlinPractice.ThirdParty.POJO.Images
import com.example.emp354.linear.KotlinPractice.ThirdParty.POJO.ObjectPojoThirdParty
import com.example.emp354.linear.R
import com.example.emp354.linear.R.id.viewpager_third_party
import kotlinx.android.synthetic.main.activity_third_party_assignment_1.*
import kotlinx.android.synthetic.main.custom_view_assignment_2.*
import java.io.IOException
import java.io.InputStream
import java.lang.ref.WeakReference
import java.nio.charset.StandardCharsets
import kotlin.text.Charsets.UTF_8

class ThirdPartyAssignment_1 : AppCompatActivity() {

    var dialog:ProgressDialog?=null
   /* var viewPager:ViewPager?=null*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_party_assignment_1)


        /*viewPager=findViewById(R.id.viewpager_third_party)*/
        AyncTaskLoadImages(this).execute()
        tablayout_third_party.setupWithViewPager(viewpager_third_party)
    }

    class AyncTaskLoadImages(activity: ThirdPartyAssignment_1): AsyncTask<Void, Void, ObjectPojoThirdParty>()
    {
        var weakReference:WeakReference<ThirdPartyAssignment_1>?=null
        init {
            weakReference= WeakReference(activity)
        }

        override fun doInBackground(vararg params: Void?): ObjectPojoThirdParty {
            return weakReference?.get()!!.loadImages()
        }

        override fun onPostExecute(result: ObjectPojoThirdParty) {
            weakReference?.get()?.dialog?.dismiss()
            var thirdPartyAdapter=ThirdPartyViewPagerAdapterKotlin(weakReference?.get(),weakReference?.get()?.supportFragmentManager,result)
            weakReference?.get()?.viewpager_third_party?.adapter=thirdPartyAdapter
        }

        override fun onPreExecute() {
            weakReference?.get()?.dialog= ProgressDialog.show(weakReference?.get(),"Loading Images","Pleasw Wait..")

        }
    }




    private fun loadImages():ObjectPojoThirdParty
    {
        var jsondata:String?=loadJSONFromAsset()
        var objectPojoThirdParty=ObjectPojoThirdParty()
        var jsonObject=JSONUtils.getJSONObject(jsondata)
        var jsonImagesArray=JSONUtils.getJSONArray(jsonObject,"images")
        var imageList=ArrayList<Images>()

        for (i in 0 until JSONUtils.getLengthOfJSONArray(jsonImagesArray))
        {
            var jsonImageObject=JSONUtils.getJSONObject(jsonImagesArray,i.toInt())
            var images=Images()
            images.name=JSONUtils.getStringfromJSON(jsonImageObject,"name")
            var jsonValuesArray= JSONUtils.getJSONArray(jsonImageObject, "values")
            var valuesList=ArrayList<String>()

            for(j in 0 until JSONUtils.getLengthOfJSONArray(jsonValuesArray))
            {
                valuesList.add(JSONUtils.getStringObject(jsonValuesArray,j.toInt()))
            }
            images.values=valuesList
            imageList.add(images)
        }
        objectPojoThirdParty.images=imageList
        try {
            Thread.sleep(1000)
        }
        catch (e:InterruptedException)
        {
            e.printStackTrace()
        }
        return objectPojoThirdParty
    }





    private fun loadJSONFromAsset():String?
    {

        var json :String?=null
        try {
            var input:InputStream=assets.open("images.json")
            var size=input.available()
            var buffer= ByteArray(size)
            input.read(buffer)
            input.close()
            json= String(buffer, UTF_8)
        }
        catch (e:IOException)
        {
            e.printStackTrace()
        }
        return json
    }
}
