package io.github.aungkothet.padc.assignment11.views.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var data: T? = null
        set(value) {
            field = value

            value?.let {
                bindData(value)
            }
        }

    protected abstract fun bindData(data: T)
}