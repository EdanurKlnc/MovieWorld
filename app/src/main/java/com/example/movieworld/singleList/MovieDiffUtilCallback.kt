package com.example.movieworld.singleList

import androidx.recyclerview.widget.DiffUtil
import com.example.movieworld.model.Movie

class MovieDiffUtilCallback(
    private val oldList: List<Movie>,
    private val newList: List<Movie>

) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition] != newList[newItemPosition]-> {
                false
            }
            oldList[oldItemPosition] != newList[newItemPosition]-> {
                false
            }
            else -> true

        }
    }
}
