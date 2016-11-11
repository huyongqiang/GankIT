package com.pinger.gankit.ui.activity;/*
 *  @项目名：  GankIT 
 *  @包名：    ui.activity
 *  @文件名:   SearchActivity
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/3 15:20
 *  @描述：    搜索页面
 */

import android.os.Bundle;

import com.pinger.gankit.R;
import com.pinger.gankit.base.SwipeBackActivity;
import com.pinger.gankit.model.bean.VideoInfo;

import java.util.List;

public class SearchActivity extends SwipeBackActivity {

    private List<VideoInfo> mVideoInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // 获取首页传递过来的数据
        mVideoInfos = (List<VideoInfo>) getIntent().getSerializableExtra("videoInfos");

    }
}
