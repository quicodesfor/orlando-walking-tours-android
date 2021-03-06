package com.codefororlando.orlandowalkingtours.ui;

import android.view.View;

import com.codefororlando.orlandowalkingtours.R;
import com.codefororlando.orlandowalkingtours.data.model.HistoricLandmarkDistanceSelect;
import com.codefororlando.orlandowalkingtours.util.ViewUtil;

import butterknife.OnClick;

public class LandmarkSelectViewHolder extends LandmarkViewHolder {
    public interface OnLandmarkItemListener extends OnAdapterPositionPressListener {
        void showInfo(int position);
    }

    private final OnLandmarkItemListener itemListener;

    public LandmarkSelectViewHolder(View itemView, final OnLandmarkItemListener listener) {
        super(itemView);

        itemListener = listener;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemPress(getAdapterPosition());
            }
        });
    }

    public void bind(HistoricLandmarkDistanceSelect landmarkSelect, boolean disable) {
        super.bind(landmarkSelect);

        itemView.setSelected(landmarkSelect.isSelected);

        ViewUtil.enable(itemView, !disable);
    }

    @OnClick(R.id.info)
    public void onInfoPress() {
        itemListener.showInfo(getAdapterPosition());
    }
}
