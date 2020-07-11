package com.e.flickrbrowser

import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_photo_details.*

class PhotoDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_details)

        activateToolbar(true)

//        val photo = intent.getSerializableExtra(PHOTO_TRANSFER) as Photo
        val photo = intent.getParcelableExtra(PHOTO_TRANSFER) as Photo

//        photo_title.text = "Title: " + photo.title
//        photo_tags.text = "Tags: " + photo.tags
        photo_title.text = resources.getString(R.string.photo_title_text, photo.title)
        photo_tags.text = resources.getString(R.string.photo_tags_text, photo.tags)

        photo_author.text = photo.author

//        photo_author.text = resources.getString(R.string.photo_author_text, "my", "red", "car")
        Picasso.get().load(photo.link)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder) //エラーや読み込み時に表示する画像をdrawableから引っ張ってきている。
            .into(photo_image)
    }
}