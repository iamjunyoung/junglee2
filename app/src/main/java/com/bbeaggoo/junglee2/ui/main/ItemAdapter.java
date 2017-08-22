package com.bbeaggoo.junglee2.ui.main;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bbeaggoo.junglee2.R;
import com.bbeaggoo.junglee2.singletons.JeongleeItemManager;

import java.util.ArrayList;
import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmResults;

import static android.support.v7.widget.StaggeredGridLayoutManager.TAG;

/**
 * Created by wlsdud.choi on 2016-04-01.
 */
public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements ItemTouchHelperListener {

    //    static class MyBaseAdapter extends RecyclerView.Adapter<MyBaseAdapter.MyViewHolder> {

    public final String TAG = "[Simple][NewItemAdaptr]";
    private final int PARENT_ITEM_VIEW = 0;
    private final int CHILD_ITEM_VIEW = 1;

    public ArrayList<GridItem> items = null;
    public ArrayList<GridItem> visibleItems = null;

    // JYN for swipe and undo
    //private ArrayList<Item> itemsPendingRemoval;
    private ArrayList<String> itemsPendingRemoval;

    // JYN for long touch select
    public ArrayList<String> longTouchSelectedItems = null;
    Boolean isMultiSelect = false;


    private static final int PENDING_REMOVAL_TIMEOUT = 3000; // 3sec
    private Handler handler = new Handler(); // hanlder for running delayed runnables
    HashMap<Integer, Runnable> pendingRunnables = new HashMap<>(); // map of items to pending runnables, so we can cancel a removal if need be

    private Context mContext;
    private int lastEditedPosition = -1;

    InputMethodManager imm;

    Realm mRealm;

    public ItemAdapter(Context context){
        mContext = context;

        init();

        items = new ArrayList<>();
        visibleItems = new ArrayList<>();

        imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

        JeongleeItemManager.insertTestJeongleeItemData(mRealm);
        RealmResults<GridItem> gridItemList = JeongleeItemManager.getJeongleeItemList(mRealm);
        Log.i("JYN", "[ItemAdapter ] ItemAdapter is generated gridItemList : " + gridItemList);

        if (gridItemList != null) {
            for (GridItem gridItem: gridItemList) {
                items.add(gridItem);
                visibleItems.add(gridItem);
                Log.i("JYN", "[ItemAdapter] add gridItem : " + gridItem);

            }
        }

        // JYN for swipe and undo
        itemsPendingRemoval = new ArrayList<>();

        // JYN for long touch select
        longTouchSelectedItems = new ArrayList<>();
    }


    private void init() {
        mRealm = Realm.getDefaultInstance();
    }


    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount. count : "+ visibleItems.size());

        return visibleItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        Log.i(TAG, "getItemViewType. position : "+position+", viewType : "+visibleItems.get(position).getViewType()+", item : "+ visibleItems.get(position).getTitle());

        return visibleItems.get(position).getViewType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder. viewType : "+viewType);
        RecyclerView.ViewHolder viewHolder = null;

        switch(viewType){
            case PARENT_ITEM_VIEW:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
                viewHolder = new GridItemVH(view);
                break;

            case CHILD_ITEM_VIEW:
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder. position : "+position);
        if(holder instanceof GridItemVH) {
            Log.i(TAG, "onBindViewHolder. parentItem. "+visibleItems.get(position).getTitle());
            final GridItemVH parentItemVH = (GridItemVH)holder;

            if (itemsPendingRemoval.contains(visibleItems.get(position).getTitle())) {
                /** {show swipe layout} and {hide regular layout} */

                parentItemVH.regularLayout.setVisibility(View.GONE);
                parentItemVH.swipeLayout.setVisibility(View.VISIBLE);

                /*
                parentItemVH.undo.setTag(holder);
                parentItemVH.delete.setTag(holder);

                final int position2 = position;
                final String removingName = visibleItems.get(position).getTitle();
                Log.i("JYN", "pso : " + position + "    pos2 : " + position2);
                parentItemVH.undo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("JYN", "[ItemAdapter][onBindviewHolder] undo is clicked about " + visibleItems.get(position2).getTitle());
                        undoOpt(position2);
                    }
                });
                parentItemVH.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("JYN", "[ItemAdapter][onBindviewHolder] delete is clicked about " + visibleItems.get(position2).getTitle());
                        remove(removingName);
                    }
                });
                */
            } else {

                parentItemVH.regularLayout.setVisibility(View.VISIBLE);
                parentItemVH.swipeLayout.setVisibility(View.GONE);

                //parentItemVH.regularLayout.setTag(holder);
                //parentItemVH.swipeLayout.setTag(holder);

                parentItemVH.headlineView.setText(visibleItems.get(position).getTitle());

                /*
                //기존에 구현되어 있던 부분은 else로 넣어버렸다.
                parentItemVH.name.setTag(holder);
                parentItemVH.add.setTag(holder);
                parentItemVH.edit.setTag(holder);
                parentItemVH.editName.setTag(holder);
                //parentItemVH.arrow.setTag(holder);
                parentItemVH.itemView.setTag(holder);

                parentItemVH.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int holderPosition = ((GridItemVH) v.getTag()).getAdapterPosition();
                        Log.i("JYN", "add btn clicked : " + holderPosition);
                        String categoryName = visibleItems.get(holderPosition).getTitle();
                        ((CategoryEditActivity) mContext).showDialogForInputFolder(holderPosition, categoryName);
                        //여기선 dialog 띄워주는 것 만 하고
                        //dialog에서 입력되는 값이 있고 - 확인을 누르든, 취소를 누르든
                        //이후 로직은 dialog쪽에서 이쪽에 있는 method를 호출하도록 한다.
                    }
                });

                parentItemVH.editName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            Log.d("JYN", "focus loosed");
                            // Do whatever you want here
                        } else {
                            Log.d("JYN", "focused");
                        }
                    }
                });

                parentItemVH.edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int holderPosition = ((GridItemVH) v.getTag()).getAdapterPosition();
                        Log.i("JYN", "Edit btn clicked category : " + visibleItems.get(holderPosition).getTitle() +
                                "  position : " + holderPosition);
                        showDialogForEditCategoryName(1, visibleItems.get(holderPosition).getTitle(), holderPosition);

                        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                    }
                });

                int subItemSize = getVisibleChildItemSize(position);
                Log.i("JYN", "Category : " + visibleItems.get(position).getTitle() +
                        " 's sub folder size : " + subItemSize);
                //if (categoryManager.data_alkitab.get(visibleItems.get(position).name).size() == 0) {
                //여기선 아래와 같이 해줄 필요가 없을 것 같다
                //onBindViewHolder() 시에 보여주는 부분이므로
                //궂이 Runnable객체를 통해 UI를 수정할 필요가 없다.
                //Runnable객체를 전달하여 handler를 통해 UI를 수정하는 코드는
                //새롭게 add 또는 remove 시에 child개수를 채크하여 UI를 바꾸어 주는 부분에 추가하면 될 것이다.
                //그런데 현재 child remove시에 parent가 누군지 등 parent정보를 알 수 없다.
                //알 수 있도록 구현이 추가되어야겠다. (Todo)
                if (subItemSize == 0) {
                    parentItemVH.arrow.setVisibility(View.INVISIBLE);
                    /*
                    Runnable hideExpandIconRunnable = new Runnable() {
                        @Override
                        public void run() {
                            parentItemVH.arrow.setVisibility(View.INVISIBLE);
                        }
                    };
                    handler.post(hideExpandIconRunnable);
                    */
                /*
                } else {
                    parentItemVH.arrow.setVisibility(View.VISIBLE);
                    /*
                    Runnable hideExpandIconRunnable = new Runnable() {
                        @Override
                        public void run() {
                            parentItemVH.arrow.setVisibility(View.VISIBLE);
                        }
                    };
                    handler.post(hideExpandIconRunnable);
                    */
                }
                /*
                if (subItemSize >= 1) {
                    parentItemVH.arrow.setVisibility(View.VISIBLE);
                }*/

                /*
                parentItemVH.arrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int holderPosition = ((GridItemVH) v.getTag()).getAdapterPosition();
                        Log.i("JYN", "Category : " + visibleItems.get(holderPosition).getTitle() + " 's arrow is clicked");

                    }
                });

                parentItemVH.arrow.setTag(holder);

                parentItemVH.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int holderPosition = ((GridItemVH) v.getTag()).getAdapterPosition();
                        Log.i("JYN", "Category : " + visibleItems.get(holderPosition).getTitle() + " 's itemView is clicked");
                    }
                });

                if (parentItemVH.getItemViewType() == PARENT_ITEM_VIEW) {
                    parentItemVH.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            int holderPosition = ((GridItemVH) v.getTag()).getAdapterPosition();
                            Log.i("JYN", "Category : " + visibleItems.get(holderPosition).getTitle() + " 's itemView is long clicked");


                            // JYN for multi select
                            /*
                            if (!isMultiSelect) {
                                Log.i("JYN", "set isMultiSelect to true");
                                isMultiSelect = true;
                                longTouchSelectedItems.add(visibleItems.get(holderPosition).name);
                            } else {
                                Log.i("JYN", "set isMultiSelect to false");
                                isMultiSelect = false;
                                longTouchSelectedItems.remove(visibleItems.get(holderPosition).name);
                            }*/
                /*
                            longTouchSelectedItems.add(visibleItems.get(holderPosition).getTitle());
                            notifyItemChanged(holderPosition);

                            return true;
                        }
                    });
                }
            }*/

            /*
            // JYN for long touch select
            if (longTouchSelectedItems.contains(visibleItems.get(position).getTitle())) {
                Log.i("JYN_ViewHolder", "(if) set color to BLUE");
                //parentItemVH.itemView.setBackgroundColor(Color.BLUE);
                parentItemVH.headlineView.setTextColor(Color.BLUE);
            } else {
                //        card_view:cardBackgroundColor="#5cb88b">
                Log.i("JYN_ViewHolder", "(else) set color to Green");
                //parentItemVH.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.gridItem));
                parentItemVH.headlineView.setTextColor(Color.BLACK);
            }
            */
        }
    }

    private void undoOpt(int position) {
        Log.i(TAG, "[undoOpt] : "+visibleItems.get(position).getTitle());

        Runnable pendingRemovalRunnable = pendingRunnables.get(position);
        pendingRunnables.remove(position);
        if (pendingRemovalRunnable != null)
            handler.removeCallbacks(pendingRemovalRunnable);
        itemsPendingRemoval.remove(visibleItems.get(position).getTitle());
        // this will rebind the row in "normal" state
        notifyItemChanged(position);
    }

    public void pendingRemoval(final int position) {
        //이때만해도 position에 따른 visibleItem을 제대로 갖고있다.
        Log.i(TAG, "[pendingRemoval] pos : " + position +"    item " +visibleItems.get(position).getTitle());

        if (!itemsPendingRemoval.contains(visibleItems.get(position).getTitle())) {
            itemsPendingRemoval.add(visibleItems.get(position).getTitle());
            // this will redraw row in "undo" state
            notifyItemChanged(position);
            // let's create, store and post a runnable to remove the data
            final String removingName = visibleItems.get(position).getTitle();
            //여기서 받아온 removingName이 기준이 되어야 한다.
            //이 값이 지워야 할 값 그 자체이며
            //기존처러 position으로 값을 갖고오면, 여기서 얻어오는 값과, run객체에서 실행시 얻어지는 값이 달라지는 경우가 발생한다.
            //그 이유는 visibleItem.remove시에 이미 visibleItem 리스트가 업데이트 되고
            //업데이트된 visibleItem list에 position으로 접근하면 우리가 지우려 했던 값이 아니기 때문이다.
            Runnable pendingRemovalRunnable = new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, "inside [pendingRemoval] pos : " + position //+ "    item " +visibleItems.get(position).name
                            + "    removingName : " + removingName);
                    //remove(visibleItems.indexOf(visibleItems.get(position)));
                    remove(removingName);
                    //그냥 position으로 하면 안되는듯...
                }
            };
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT);
            pendingRunnables.put(position, pendingRemovalRunnable);
        }
    }

    //public void remove(int position) {
    public void remove(String removingName) {
        Log.i(TAG, "[remove] : " + removingName);

        if (itemsPendingRemoval.contains(removingName)) {
            itemsPendingRemoval.remove(removingName);

            //JYN modify
            //visibleItems.get(position).name
            Log.i("JYN", "onItemSwipe() Delete category : " + removingName);
            //categoryManager.deleteCategory(removingName);
            //JYN modify end

            GridItem foundedItem = null;
            for (int i = 0 ; i < visibleItems.size() ; i++) {
                if (removingName != null && removingName.equals(visibleItems.get(i).getTitle())) {
                    Log.i("JYN", "Found item : " + removingName);
                    foundedItem = visibleItems.get(i);
                }
            }

            //null 체크 추가 해야 할듯
            int newPosition = visibleItems.indexOf(foundedItem);
            Log.i("JYN",  "newPosition : " + newPosition);
            //아래는 다 position이었

            //필요 없는 코드일 듯.
            /*
            int childItemSize = getVisibleChildItemSize(newPosition);

            Log.i("JYN", "childItemSize : " + childItemSize);
            for(int i = 0; i <= childItemSize; i++){
                Log.i("JYN", "remove childItemSize also : " + visibleItems.get(newPosition).getTitle());
                visibleItems.remove(newPosition);
            }
            notifyItemRangeRemoved(newPosition, childItemSize + 1);
            */
            //child를 고려 안해도 되므로 위와 같이 notifyItemRangeRemoved()가 필요 없고 아래처럼 하면 될듯.
            notifyItemRemoved(newPosition);

        }

    }

    // not used
    public boolean isPendingRemoval(int position) {
        Log.i(TAG, "[isPendingRemoval] : "+visibleItems.get(position).getTitle());

        return itemsPendingRemoval.contains(visibleItems.get(position).getTitle());
    }

    private void focusCleanEditText() {
        if (lastEditedPosition != -1) {
            // 현재 focus된 editText가 있다면
            // focus되어 있는 editText를 다시 textView로 set해준다.
        }
    }

    /*
    public void newAddingFolder(String newAddingFolder, int holderPosition) {
        Log.i("JYN", "newAddingFolder : " + newAddingFolder);
        if(((ParentItem)visibleItems.get(holderPosition)).visibilityOfChildItems){
            if (newAddingFolder != null) {
                addChildItem(holderPosition, newAddingFolder);
            }
        }else{
            if (newAddingFolder != null) {
                expandChildItems(holderPosition);
                addChildItem(holderPosition, newAddingFolder);
            }
        }
        //Expand상태라면 현재와 같이 동작
        //addChildItem(holderPosition);
        //Expand상태가 아니라면 먼저 Expand를 시키고 addChildItem() 호출
    }
    */

    public void addGridItem(int position, String newAddingItem) {
        Log.i("JYN", "addGridItem to : " + position + "     folder : " + newAddingItem);

        position = -1;
        GridItem gridItem = new GridItem(newAddingItem, "https://");
        items.add(position + 1, gridItem);
        visibleItems.add(position + 1, gridItem);
        notifyItemInserted(position + 1);
    }

    /*
    public void addParentItem(int position, String newAddingCategory) {
        Log.i("JYN", "addParentItem to : " + position + "     folder : " + newAddingCategory);

        position = -1;
        items.add(position + 1, new ParentItem(newAddingCategory, PARENT_ITEM_VIEW));
        visibleItems.add(position + 1, new ParentItem(newAddingCategory, PARENT_ITEM_VIEW));
        notifyItemInserted(position + 1);
    }

    private void  addChildItem(int position, String newAddingFolder) {
        Log.i("JYN", "addChildItem to : " + position + "     folder : " + newAddingFolder);

        items.add(position + 1, new ChildItem(newAddingFolder, CHILD_ITEM_VIEW));
        visibleItems.add(position + 1, new ChildItem(newAddingFolder, CHILD_ITEM_VIEW));

        notifyItemInserted(position + 1);
        notifyItemChanged(position);
    }
    */

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Log.i(TAG, "onItemMove. fromPosition : "+fromPosition+", toPosition : "+toPosition);
        Log.i(TAG, "onItemMove. fromItem : "+visibleItems.get(fromPosition).getTitle()+", toItem : "+visibleItems.get(toPosition).getTitle());

        if(fromPosition < 0 || fromPosition >= visibleItems.size() || toPosition < 0 || toPosition >= visibleItems.size()){
            return false;
        }

        GridItem fromItem = visibleItems.get(fromPosition);

        if(visibleItems.get(fromPosition).getViewType() == CHILD_ITEM_VIEW){
            if(fromPosition <= 0 || toPosition <= 0){
                return false;
            }
            Log.i(TAG, "onItemMove. remove add");

            visibleItems.remove(fromPosition);
            visibleItems.add(toPosition, fromItem);

            notifyItemMoved(fromPosition, toPosition);

        }else{
            if(visibleItems.get(fromPosition).getViewType() == visibleItems.get(toPosition).getViewType()) {
                if(fromPosition > toPosition){
                    Log.i(TAG, "onItemMove. remove add");

                    visibleItems.remove(fromPosition);
                    visibleItems.add(toPosition, fromItem);

                    notifyItemMoved(fromPosition, toPosition);
                }else{
                    int toParentPosition = getParentPosition(toPosition);
                    //필요 없는 코드 일 듯
                    /*
                    int toLastchildSize = getVisibleChildItemSize(toParentPosition);
                    Log.i(TAG, "onItemMove. lastChild : "+toLastchildSize);
                    if(toLastchildSize == 0){
                        Log.i(TAG, "onItemMove. remove add");

                        visibleItems.remove(fromPosition);
                        visibleItems.add(toPosition, fromItem);

                        notifyItemMoved(fromPosition, toPosition);
                    }
                    */
                }
            }else{
                if(fromPosition < toPosition){
                    int toParentPosition = getParentPosition(toPosition);

                    //필요 없는 코드일 듯
                    /*
                    int toLastchildPosition = getVisibleChildItemSize(toParentPosition) + toParentPosition;
                    Log.i(TAG, "onItemMove. lastChild : "+toLastchildPosition);

                    if(toLastchildPosition == toPosition) {
                        Log.i(TAG, "onItemMove. remove add");

                        visibleItems.remove(fromPosition);
                        visibleItems.add(toPosition, fromItem);

                        notifyItemMoved(fromPosition, toPosition);
                    }
                    */
                }
            }
        }

        for(int i = 0; i < visibleItems.size(); i++){
            Log.i(TAG, "onItemMove : "+visibleItems.get(i).getTitle());
        }

        return true;
    }

    @Override
    public void onItemRemove(int position) {
        Log.i(TAG, "onItemRemove. pos : " + position + "    item : "+visibleItems.get(position).getTitle());
        switch(visibleItems.get(position).getViewType()){
            case PARENT_ITEM_VIEW:
                //여기서 분기를 하면 어떨까??
                pendingRemoval(position);

                //원래는 아래처럼 바로 remove를 수행하였으나
                //바로 remove하지 말고, 위 처럼 pendingRemoval을 호출하여 undo로 취소하거나 TINEOUT으로 삭제되도록 하자.
                //JYN modify
                /*
                Log.i("JYN", "onItemSwipe() Delete category : " + visibleItems.get(position).name);
                categoryManager.deleteCategory(visibleItems.get(position).name);
                //JYN modify end

                int childItemSize = getVisibleChildItemSize(position);

                for(int i = 0; i <= childItemSize; i++){
                    visibleItems.remove(position);
                }
                notifyItemRangeRemoved(position, childItemSize + 1);
                */
                break;
            case CHILD_ITEM_VIEW:
                //여기서 분기를 하면 어떨까?

                //JYN modify
                Log.i("JYN", "onItemSwipe() Delete folder : " + visibleItems.get(position).getTitle());
                //categoryManager.deleteFolder(visibleItems.get(position).getTitle());
                //JYN modify end


                visibleItems.remove(position);
                notifyItemRemoved(position);

                //아래처럼 해볼까?
                //notifyDataSetChanged();
                //위와같이 notifyDataSetChanged()를 추가하면
                //마지막 child remove시에 parent의 arrow이미지가 INVISIBLE 처리된다.
                //현재는 이 방법 말고는 방법이 없는데
                //Child가 parent의 정보를 알 수 있는 방식으로 구현된다면
                //이 시점에 parent의 child개수를 check하여 runnable 을 통해 UI 를 갱신하는게 가장 좋을 것 같다.
                break;
        }
    }

    @Override
    public void onItemSwipe(int position) {
        Log.i("JYN", "onItemSwipe. item : "+visibleItems.get(position).getTitle());
        if(visibleItems.get(position).getViewType() == PARENT_ITEM_VIEW){
            Log.i("JYN", "onItemSwipe() Delete category : " + visibleItems.get(position).getTitle());
            //categoryManager.deleteCategory(visibleItems.get(position).getTitle());
        }else{
            Log.i("JYN", "onItemSwipe() Delete folder : " + visibleItems.get(position).getTitle());
            //categoryManager.deleteFolder(visibleItems.get(position).getTitle());
        }
    }

    private int getParentPosition(int position){
        while(true){
            if(visibleItems.get(position).getViewType() == PARENT_ITEM_VIEW){
                break;
            }else{
                position--;
            }
        }
        return position;
    }
}
