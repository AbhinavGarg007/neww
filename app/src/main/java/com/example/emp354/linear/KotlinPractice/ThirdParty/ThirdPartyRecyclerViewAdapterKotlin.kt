package com.example.emp354.linear.KotlinPractice.ThirdParty

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.emp354.linear.R
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.imageview_thirdparty.view.*

class ThirdPartyRecyclerViewAdapterKotlin(val mImages:Array<String>?):RecyclerView.Adapter<ThirdPartyRecyclerViewAdapterKotlin.KotlinHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KotlinHolder {
       return KotlinHolder(LayoutInflater.from(parent.context).inflate(R.layout.imageview_thirdparty,parent,false))
    }

    override fun getItemCount(): Int {
        return mImages!!.size
    }

    override fun onBindViewHolder(holder: KotlinHolder, position: Int) {
       var imageLoader=ImageLoader.getInstance()
        imageLoader.displayImage(mImages!![position],holder.imageView)
    }


    class KotlinHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        val imageView=itemView?.imageView_Third_Party

    }

}
