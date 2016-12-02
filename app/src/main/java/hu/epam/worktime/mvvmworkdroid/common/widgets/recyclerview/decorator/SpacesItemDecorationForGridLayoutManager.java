package hu.epam.worktime.mvvmworkdroid.common.widgets.recyclerview.decorator;


import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Decorator class for set offsets at the recyclerview grid items.
 * Use with gridlayout manager only.
 * Created by Mate_Csaba_Vajda on 3/17/2016.
 */
public class SpacesItemDecorationForGridLayoutManager extends RecyclerView.ItemDecoration {
    private int space;
    private int numColumns;
    private int start;
    private int end;

    public SpacesItemDecorationForGridLayoutManager(int space, int numColumns) {
        this.space = space;
        this.numColumns = numColumns;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = space;
        outRect.top = 0;
        start = 0;
        end = 0;
        if (numColumns == 2) {
            setOffsetForTwoColumn(view, parent);
        } else if (numColumns == 3) {
            setOffsetForThreeColumn(view, parent);
        }
        outRect.right = start;
        outRect.left =  end ;
    }

    private void setOffsetForTwoColumn(View view, RecyclerView parent) {
        int position = parent.getChildLayoutPosition(view);
        int spanSizeLookup = ((GridLayoutManager) parent.getLayoutManager()).getSpanSizeLookup().getSpanIndex(position, 2);
        switch (spanSizeLookup) {
            case 0:
                end = space / 2;
                break;
            case 1:
                start = space / 2;
                break;
            default:
        }
    }

    private void setOffsetForThreeColumn(View view, RecyclerView parent) {
        int position = parent.getChildLayoutPosition(view);
        int spanSizeLookup = ((GridLayoutManager) parent.getLayoutManager()).getSpanSizeLookup().getSpanIndex(position, 3);
        switch (spanSizeLookup) {
            case 0:
                end = space * 2 / 3;
                break;
            case 1:
                end = space / 3;
                start = space / 3;
                break;
            case 2:
                start = space * 2 / 3;
                break;
            default:
        }
    }

}
