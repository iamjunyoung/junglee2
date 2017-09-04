package com.bbeaggoo.junglee2.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bbeaggoo.junglee2.R;

/**
 * Created by junyoung on 2017. 8. 21..
 */

public class GridItemVH extends RecyclerView.ViewHolder {
    RelativeLayout regularLayout;
    RelativeLayout swipeLayout;
    LinearLayout contentsView;//gridItemContent
    TextView headlineView;
    ImageView imageView;
    ImageView imageViewCheck;
    ImageView imageViewCircle;

    TextView categoryOrFolder;
    ImageView imageViewForMore;

    public GridItemVH(View itemView) {
        super(itemView);
        regularLayout = (RelativeLayout)itemView.findViewById(R.id.itemgrid);
        swipeLayout = (RelativeLayout)itemView.findViewById(R.id.swipeParentLayout);
        contentsView = (LinearLayout) itemView.findViewById((R.id.gridItemContent));
        headlineView = (TextView) itemView.findViewById(R.id.title);

        imageView = (ImageView) itemView.findViewById(R.id.thumbImage);
        imageViewCheck = (ImageView) itemView.findViewById(R.id.imageView2);
        imageViewCircle = (ImageView) itemView.findViewById(R.id.imageView3);

        categoryOrFolder = (TextView) itemView.findViewById(R.id.textForCategory);
        imageViewForMore = (ImageView) itemView.findViewById(R.id.imageForMore);
        imageViewForMore.setScaleType(ImageView.ScaleType.CENTER_CROP);

    }
}