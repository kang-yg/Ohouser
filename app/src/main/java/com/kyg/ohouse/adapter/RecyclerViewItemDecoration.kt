package com.kyg.ohouse.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewItemDecoration() : RecyclerView.ItemDecoration() {
    private var itemSpacingDP: Int = 0
    private var top = false
    private var bottom = false
    private var start = false
    private var end = false

    constructor(itemSpacingDP: Int, vararg directions: Direction) : this() {
        this.itemSpacingDP = itemSpacingDP

        for (direction in directions) {
            when (direction) {
                Direction.ALL -> {
                    start = true
                    end = true
                    top = true
                    bottom = true
                    break
                }
                Direction.BOTTOM -> {
                    bottom = true
                }
                Direction.TOP -> {
                    top = true
                }
                Direction.START -> {
                    start = true
                }
                Direction.END -> {
                    end = true
                }
            }
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount

        if (start) {
            outRect.left = itemSpacingDP
        }
        if (end) {
            outRect.right = itemSpacingDP
        }
        if (top) {
            outRect.top = itemSpacingDP
        }
        if (bottom) {
            outRect.bottom = itemSpacingDP
        }

        if (position == itemCount - 1) {
            outRect.bottom = 0
        }
    }

    enum class Direction {
        START, END, TOP, BOTTOM, ALL
    }
}