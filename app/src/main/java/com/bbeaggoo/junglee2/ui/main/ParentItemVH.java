package com.bbeaggoo.junglee2.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bbeaggoo.junglee2.R;

/**
 * Created by junyoung on 2017. 8. 21..
 */

public class ParentItemVH extends RecyclerView.ViewHolder {
    public RelativeLayout regularLayout;
    public RelativeLayout swipeLayout;
    TextView name;
    EditText editName;
    ImageButton edit;
    ImageButton add;
    ImageButton arrow;

    TextView undo;
    TextView delete;

    public ParentItemVH(View itemView) {
        super(itemView);
        regularLayout = (RelativeLayout)itemView.findViewById(R.id.regularParentLayout);
        swipeLayout = (RelativeLayout)itemView.findViewById(R.id.swipeParentLayout);
        edit = (ImageButton)itemView.findViewById(R.id.item_edit);
        editName = (EditText)itemView.findViewById(R.id.edit_item_name);
        name = (TextView)itemView.findViewById(R.id.item_name);
        add = (ImageButton)itemView.findViewById(R.id.item_add);;
        arrow = (ImageButton)itemView.findViewById(R.id.item_arrow);

        undo = (TextView)itemView.findViewById(R.id.undo);
        delete = (TextView)itemView.findViewById(R.id.delete);
    }
}
