package io.github.aungkothet.padc.assignment11.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.aungkothet.padc.assignment11.R
import io.github.aungkothet.padc.assignment11.data.vos.PhotoVO
import io.github.aungkothet.padc.assignment11.delegates.PhotoItemDelegate
import io.github.aungkothet.padc.assignment11.views.holders.PhotoItemViewHolder

class PhotoItemAdapter(private val delegate: PhotoItemDelegate) :
    BaseAdapter<PhotoItemViewHolder, PhotoVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.photo_item_view, parent, false)
        return PhotoItemViewHolder(
            layout,
            delegate
        )
    }
}