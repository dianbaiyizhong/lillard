package com.nntk.nba.watch.lillard.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ResourceUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.nntk.nba.watch.lillard.R;
import com.nntk.nba.watch.lillard.entity.TeamEntity;

import java.util.List;

public class NbaLogoAdapter extends BaseQuickAdapter<TeamEntity, BaseViewHolder> {
    public NbaLogoAdapter(@Nullable List<TeamEntity> data) {
        super(R.layout.list_item, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, TeamEntity teamEntity) {

        baseViewHolder.setIsRecyclable(false);

        ImageView imageView = baseViewHolder.getView(R.id.image);

        imageView.setImageResource(ResourceUtils.getMipmapIdByName("espn_" + teamEntity.getTeamName()));

    }
}
