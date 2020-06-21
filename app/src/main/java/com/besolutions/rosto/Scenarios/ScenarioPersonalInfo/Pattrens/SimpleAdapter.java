package com.besolutions.rosto.Scenarios.ScenarioPersonalInfo.Pattrens;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioPersonalInfo.Model.Question;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int UNSELECTED = -1;

    private RecyclerView recyclerView;
    private int selectedItem = UNSELECTED;
    List<Question> mMainList;

    public SimpleAdapter(RecyclerView recyclerView, List<Question> faqList) {
        this.recyclerView = recyclerView;
        this.mMainList = faqList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.faq_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder) holder;
        boolean isSelected = position == selectedItem;
        final Question questions = mMainList.get(position);

        viewHolder.expandButton.setText(questions.getTitle());
        viewHolder.txtdiscription.setText(questions.getDescription());
        viewHolder.expandButton.setSelected(isSelected);
        viewHolder.expandableLayout.setExpanded(isSelected, false);

    }


    @Override
    public int getItemCount() {
        return mMainList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ExpandableLayout.OnExpansionUpdateListener {
        private ExpandableLayout expandableLayout;
        private TextView expandButton, txtdiscription;
        ImageView imgarrow;

        public ViewHolder(View itemView) {
            super(itemView);

            expandableLayout = itemView.findViewById(R.id.expandable_layout);
            expandableLayout.setInterpolator(new OvershootInterpolator());
            expandableLayout.setOnExpansionUpdateListener(this);
            expandButton = itemView.findViewById(R.id.expand_button);
            txtdiscription = itemView.findViewById(R.id.txtDescription);
            expandButton.setOnClickListener(this);
            imgarrow = itemView.findViewById(R.id.imgArrow);

        }

        public void bind() {

        }

        @Override
        public void onClick(View view) {
            ViewHolder holder = (ViewHolder) recyclerView.findViewHolderForAdapterPosition(selectedItem);
            if (holder != null) {
                holder.expandButton.setSelected(false);
                holder.expandableLayout.collapse();

            }

            int position = getAdapterPosition();
            if (position == selectedItem) {
                selectedItem = UNSELECTED;
            } else {
                expandButton.setSelected(true);
                expandableLayout.toggle();
                selectedItem = position;
            }
        }

        @Override
        public void onExpansionUpdate(float expansionFraction, int state) {
            Log.d("ExpandableLayout", "State: " + state);
            if (state == ExpandableLayout.State.EXPANDING) {
                recyclerView.smoothScrollToPosition(getAdapterPosition());
                imgarrow.setRotation(expansionFraction * 180);
            } else {
                imgarrow.setRotation(expansionFraction * 180);

            }
        }
    }
}


