package proj.kolot.photosocial;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class NonScrollableGridView extends GridView {
    public NonScrollableGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightSpec;

        if (getLayoutParams().height == LayoutParams.WRAP_CONTENT) {
            // The great Android "hackatlon", the love, the magic.
            // The two leftmost bits in the height measure spec have
            // a special meaning, hence we can't use them to describe height.
/*
            final int widthSpec1 = View.MeasureSpec.makeMeasureSpec(getWidth(), View.MeasureSpec.EXACTLY);
            final int heightSpec1= View.MeasureSpec.makeMeasureSpec(getHeight(), View.MeasureSpec.EXACTLY);
          *//*  measureChildWithDecorationsAndMargin(view, widthSpec, heightSpec);
            getvilayoutDecorated(view, 0, 0, getWidth(), getHeight());*//*
            Log.e("my test",heightMeasureSpec + "HEIGHT " + getHeight()  + "  " + getMeasuredHeight() + "   "  + getMinimumHeight() +  "  "+ getMeasuredHeightAndState());
           */
            heightSpec = MeasureSpec.makeMeasureSpec(
                    Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

        }
        else {
            // Any other height should be respected as is.
            heightSpec = heightMeasureSpec;
        }

        super.onMeasure(widthMeasureSpec, heightSpec);
    }
}
