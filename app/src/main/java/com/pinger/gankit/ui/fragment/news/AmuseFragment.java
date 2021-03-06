package com.pinger.gankit.ui.fragment.news;

import android.view.LayoutInflater;

import com.pinger.gankit.R;
import com.pinger.gankit.base.BaseFragment;
import com.pinger.gankit.presenter.news.NewsAmusePresenter;
import com.pinger.gankit.ui.view.news.AmuseView;

import butterknife.BindView;


/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.ui.fragment.news
 *  @文件名:   AmuseFragment
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/13 18:46
 *  @描述：    TODO
 */

public class AmuseFragment extends BaseFragment {

    @BindView(R.id.amuseView)
    AmuseView mAmuseView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_amuse;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        mPresenter = new NewsAmusePresenter(mAmuseView);
    }

    @Override
    protected void loadData() {
        ((NewsAmusePresenter) mPresenter).onRefresh();
    }
}
