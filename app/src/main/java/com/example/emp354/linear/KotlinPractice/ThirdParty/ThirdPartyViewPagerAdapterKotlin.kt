package com.example.emp354.linear.KotlinPractice.ThirdParty

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.emp354.linear.KotlinPractice.ThirdParty.POJO.Images
import com.example.emp354.linear.KotlinPractice.ThirdParty.POJO.ObjectPojoThirdParty
import com.example.emp354.linear.R


class ThirdPartyViewPagerAdapterKotlin(context:Context?,fm: FragmentManager?,objectPojoThirdParty: ObjectPojoThirdParty) : FragmentPagerAdapter(fm) {
    var imagesList: MutableList<Images>?=null
    var mContext:Context?=null

    init {
        mContext=context
        imagesList=objectPojoThirdParty.images
    }

    override fun getItem(position: Int): Fragment {

        /*var image:List<String>?=null*/
        var images:Images
        var size:Int

        val image_array= arrayOfNulls<String>(10) //for string array


        //performing operation based on the operation we are getting
        images=imagesList!!.get(position)
        var image=images.values
        size=image!!.size

        for(i in 0 until size) {
            image_array[i] = image.get(i)
        }


        var thirdPartyFragmentKotlin=ThirdPartyFragmentKotlin()
        var b=Bundle()
        b.putStringArray("send_images",image_array)
        thirdPartyFragmentKotlin.arguments=b
        return thirdPartyFragmentKotlin

    }

    override fun getCount(): Int {
        return imagesList!!.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position)
        {
            0-> return mContext?.getString(R.string.monuments)
            1-> return mContext?.getString(R.string.flowers)
            2-> return mContext?.getString(R.string.landscapes)
            3-> return mContext?.getString(R.string.waterfalls)
            4-> return mContext?.getString(R.string.foods)
            else->
            {
                return mContext?.getString(R.string.monuments)
            }
        }
    }
}
