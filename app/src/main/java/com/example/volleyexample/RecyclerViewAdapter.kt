package com.example.volleyexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.volleyexample.RecyclerViewAdapter.MyViewHolder


class RecyclerViewAdapter(
    private val mContext: Context,
    private val mData: List<Anime>
) :
    RecyclerView.Adapter<MyViewHolder>() {
    var option: RequestOptions
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View
        val inflater = LayoutInflater.from(mContext)
        view = inflater.inflate(R.layout.anime_row_item, parent, false)
        val viewHolder = MyViewHolder(view)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv_name.text = mData[position].name
        holder.tv_rating.text = mData[position].rating
        holder.tv_studio.text = mData[position].studio
        holder.tv_category.text = mData[position].categorie
        // Load Image from the internet and set it into Imageview using Glide
        Glide.with(mContext).load(mData[position].image_url).apply(option)
            .into(holder.img_thumbnail)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tv_name: TextView
        var tv_rating: TextView
        var tv_studio: TextView
        var tv_category: TextView
        var img_thumbnail: ImageView

        init {
            tv_name = itemView.findViewById(R.id.anime_name)
            tv_category = itemView.findViewById(R.id.categorie)
            tv_rating = itemView.findViewById(R.id.rating)
            tv_studio = itemView.findViewById(R.id.studio)
            img_thumbnail = itemView.findViewById(R.id.thumbnail)
        }
    }

    init {
        // Request option for Glide
        option = RequestOptions().centerCrop().placeholder(R.drawable.loading_shape)
            .error(R.drawable.loading_shape)
    }
}