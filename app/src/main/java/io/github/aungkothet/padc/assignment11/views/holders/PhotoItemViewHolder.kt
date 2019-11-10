package io.github.aungkothet.padc.assignment11.views.holders

import android.view.View
import coil.api.load
import io.github.aungkothet.padc.assignment11.views.items.ItemImageView
import io.github.aungkothet.padc.assignment11.data.vos.PhotoVO
import io.github.aungkothet.padc.assignment11.delegates.PhotoItemDelegate
import kotlinx.android.synthetic.main.photo_item_view.view.*

class PhotoItemViewHolder(itemView: View, private val delegate: PhotoItemDelegate) :
    BaseViewHolder<PhotoVO>(itemView) {

    private val photoView: ItemImageView = itemView.ivPhoto

    init {
        itemView.ivPhoto.setOnClickListener {
            data?.id?.let { id ->
                delegate.onItemClicked(id)
            }
        }
    }

    override fun bindData(data: PhotoVO) {
        photoView.heightRatio = data.heightRatio
        photoView.load(data.photoUrlVO.regular)
    }
}