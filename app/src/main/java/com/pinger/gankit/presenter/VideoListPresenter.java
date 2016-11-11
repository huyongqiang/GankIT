package com.pinger.gankit.presenter;

import android.support.test.espresso.core.deps.guava.base.Preconditions;

import com.pinger.gankit.base.RxPresenter;
import com.pinger.gankit.manager.RequestManager;
import com.pinger.gankit.model.bean.VideoRes;
import com.pinger.gankit.model.net.VideoHttpResponse;
import com.pinger.gankit.presenter.contact.VideoListContact;
import com.pinger.gankit.utils.RxUtil;
import com.pinger.gankit.utils.StringUtil;

import rx.Subscription;
import rx.functions.Action1;


/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.presenter
 *  @文件名:   VideoListPresenter
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/8 16:41
 *  @描述：    视频列表的Presenter
 */

public class VideoListPresenter extends RxPresenter implements VideoListContact.Presenter {


    private final VideoListContact.View mView;
    private final String mCategoryId;
    private int mPage;

    public VideoListPresenter(VideoListContact.View addTaskView, String categoryId) {
        this.mView = Preconditions.checkNotNull(addTaskView);
        this.mCategoryId = categoryId;
        mView.setPresenter(this);
        // 关联的是父类，所以需要自己刷新
        onRefresh();
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        getVideoList(mCategoryId);
    }

    @Override
    public void loadMore() {
        mPage++;
        getVideoList(mCategoryId);
    }


    /**
     * 根据分类查找视频列表
     *
     * @param categoryId
     */
    private void getVideoList(String categoryId) {
        Subscription rxSubscription = RequestManager.getVideoApi().getVideoList(categoryId, mPage + "")
                .compose(RxUtil.<VideoHttpResponse<VideoRes>>rxSchedulerHelper())
                .compose(RxUtil.<VideoRes>handleResult())
                .subscribe(new Action1<VideoRes>() {
                    @Override
                    public void call(VideoRes res) {
                        if (res != null) {
                            if (mView.isActive()) {
                                if (mPage == 1) {
                                    mView.showContent(res.list);
                                } else {
                                    mView.showMoreContent(res.list);
                                }
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (mPage > 1) {
                            mPage--;
                        }
                        mView.refreshFail(StringUtil.getErrorMsg(throwable.getMessage()));
                    }
                });
        addSubscribe(rxSubscription);
    }

/*
    *//**
     * 根据关键字获取电影列表
     *
     * @param searchStr
     *//*
    private void getSearchVideoList(String searchStr) {
        Subscription rxSubscription = RequestManager.getVideoApi().getVideoListByKeyWord(searchStr, mPage + "")
                .compose(RxUtil.<VideoHttpResponse<VideoRes>>rxSchedulerHelper())
                .compose(RxUtil.<VideoRes>handleResult())
                .subscribe(new Action1<VideoRes>() {
                    @Override
                    public void call(VideoRes res) {
                        if (res != null) {
                            if (mView.isActive()) {
                                if (mPage == 1) {
                                    mView.showContent(res.list);
                                } else {
                                    mView.showMoreContent(res.list);
                                }
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (mPage > 1) {
                            mPage--;
                        }
                        mView.refreshFail(StringUtil.getErrorMsg(throwable.getMessage()));
                    }
                });
        addSubscribe(rxSubscription);
    }*/


}
