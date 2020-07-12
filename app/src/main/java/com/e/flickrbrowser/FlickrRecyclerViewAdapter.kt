package com.e.flickrbrowser

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class FlickrImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var thumbnail: ImageView = view.findViewById(R.id.thumbnail)
    var title: TextView = view.findViewById(R.id.title)
}
class FlickrRecyclerViewAdapter(private var photoList : List<Photo>) : RecyclerView.Adapter<FlickrImageViewHolder>() {
    private val TAG = "FlickrRecyclerViewAdapt" //タグは23文字まで

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrImageViewHolder {
        Log.d(TAG, ".onCreateViewHolder new view requested")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.browse, parent, false)
        return FlickrImageViewHolder(view)
    }

    override fun getItemCount(): Int {
//        Log.d(TAG, ".getItemCount called")
        return if (photoList.isNotEmpty()) photoList.size else 1 //1にしないとviewが無いのでエラーが表示できない
    }

    override fun onBindViewHolder(holder: FlickrImageViewHolder, position: Int) {
        //新しいデータが求められるたびに呼ばれる
        if (photoList.isEmpty()) {
            holder.thumbnail.setImageResource(R.drawable.placeholder)
            holder.title.setText(R.string.empty_photo)
        } else {
            val photoItem = photoList[position]
//        Log.d(TAG, ".onBindViewHolder: ${photoitem.title} --> $position")
            Picasso.get().load(photoItem.image)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder) //エラーや読み込み時に表示する画像をdrawableから引っ張ってきている。
                .into(holder.thumbnail)

            holder.title.text = photoItem.title
        }

    }

    fun loadNewData(newPhotos: List<Photo>) { //新しいデータを得るメソッド。更新されたらnotifi~でrecyclerviewに通知される
        photoList = newPhotos
        notifyDataSetChanged()
    }

    fun getPhoto(position: Int): Photo? { //タップされた写真を取得できる
        return if (photoList.isNotEmpty()) photoList[position] else null
    }
}