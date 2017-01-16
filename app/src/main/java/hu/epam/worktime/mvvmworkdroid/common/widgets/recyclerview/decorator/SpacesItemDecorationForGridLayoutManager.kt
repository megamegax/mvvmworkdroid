package hu.epam.worktime.mvvmworkdroid.common.widgets.recyclerview.decorator


import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Decorator class for set offsets at the recyclerview grid items.
 * Use with gridlayout manager only.
 * Created by Mate_Csaba_Vajda on 3/17/2016.
 */
class SpacesItemDecorationForGridLayoutManager(private val space: Int, private val numColumns: Int) : RecyclerView.ItemDecoration() {
    private var start: Int = 0
    private var end: Int = 0

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = space
        outRect.top = 0
        start = 0
        end = 0
        if (numColumns == 2) {
            setOffsetForTwoColumn(view, parent)
        } else if (numColumns == 3) {
            setOffsetForThreeColumn(view, parent)
        }
        outRect.right = start
        outRect.left = end
    }

    private fun setOffsetForTwoColumn(view: View, parent: RecyclerView) {
        val position = parent.getChildLayoutPosition(view)
        val spanSizeLookup = (parent.layoutManager as GridLayoutManager).spanSizeLookup.getSpanIndex(position, 2)
        when (spanSizeLookup) {
            0 -> end = space / 2
            1 -> start = space / 2
        }
    }

    private fun setOffsetForThreeColumn(view: View, parent: RecyclerView) {
        val position = parent.getChildLayoutPosition(view)
        val spanSizeLookup = (parent.layoutManager as GridLayoutManager).spanSizeLookup.getSpanIndex(position, 3)
        when (spanSizeLookup) {
            0 -> end = space * 2 / 3
            1 -> {
                end = space / 3
                start = space / 3
            }
            2 -> start = space * 2 / 3
        }
    }

}
