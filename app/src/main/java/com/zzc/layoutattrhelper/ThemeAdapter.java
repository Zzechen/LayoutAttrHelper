package com.zzc.layoutattrhelper;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzc.layoutattrhelper.databinding.ItemThemeBinding;

/**
 * auth zzc
 * date 2018/4/8
 * desc ${desc}
 */

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ThemeHolder> {

    @Override
    public ThemeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theme, parent, false);
        return new ThemeHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ThemeHolder holder, int position) {
        holder.mBinding.text.setText("item " + position);
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public static class ThemeHolder extends RecyclerView.ViewHolder {

        private final ItemThemeBinding mBinding;

        public ThemeHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
