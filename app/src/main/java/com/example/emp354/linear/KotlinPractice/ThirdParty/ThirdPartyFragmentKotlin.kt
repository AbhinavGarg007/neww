package com.example.emp354.linear.KotlinPractice.ThirdParty

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.emp354.linear.R
import kotlinx.android.synthetic.main.gridview_thirdparty.*

class ThirdPartyFragmentKotlin : Fragment()
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view:View=inflater.inflate(R.layout.gridview_thirdparty,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val c:Bundle?=this.arguments
        val images:Array<String>?=c?.getStringArray("send_images")

        recycler_view_third_party.adapter=ThirdPartyRecyclerViewAdapterKotlin(images)
        recycler_view_third_party.layoutManager=GridLayoutManager(activity,2,LinearLayoutManager.VERTICAL,false)
    }
}
