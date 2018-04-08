package com.zzc.layoutattrhelper;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzc.layoutattrhelper.databinding.ItemThemeABinding;
import com.zzc.layoutattrhelper.databinding.ItemThemeBBinding;

/**
 * auth zzc
 * date 2018/4/8
 * desc ${desc}
 */

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ThemeHolder> {
    private static final int TYPE_ONE = 1;
    private static final int TYPE_TWO = 2;

    @Override
    public ThemeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TYPE_ONE:
                View inflate = from.inflate(R.layout.item_theme_a, parent, false);
                return new ThemeAHolder(inflate);
            case TYPE_TWO:
            default:
                View inflateB = from.inflate(R.layout.item_theme_b, parent, false);
                return new ThemeBHolder(inflateB);
        }
    }

    @Override
    public void onBindViewHolder(ThemeHolder holder, int position) {
        holder.setText("item " + position);
    }

    @Override
    public int getItemViewType(int position) {
        return position > 10 && position % 2 == 0 ? TYPE_ONE : TYPE_TWO;
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public abstract static class ThemeHolder extends RecyclerView.ViewHolder {

        public ThemeHolder(View itemView) {
            super(itemView);
        }

        public abstract void setText(String text);
    }

    public static class ThemeAHolder extends ThemeHolder {

        private final ItemThemeABinding mBinding;

        public ThemeAHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }

        @Override
        public void setText(String text) {
            mBinding.text.setText(text);
        }
    }

    public static class ThemeBHolder extends ThemeHolder {

        private final ItemThemeBBinding mBinding;

        public ThemeBHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }

        @Override
        public void setText(String text) {
            mBinding.text.setText(text);
        }
    }
}
