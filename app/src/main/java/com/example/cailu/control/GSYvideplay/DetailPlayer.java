package com.example.cailu.control.GSYvideplay;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.cailu.control.R;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.transitionseverywhere.TransitionManager;

import static com.example.cailu.control.GSYvideplay.utils.CommonUtil.setViewHeight;


public class DetailPlayer extends AppCompatActivity {


    NestedScrollView postDetailNestedScroll;

    StandardGSYVideoPlayer detailPlayer;

    RelativeLayout activityDetailPlayer;

    private boolean isFull;

    private OrientationUtils orientationUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_gsyvideo_detailplayer);
        detailPlayer = (StandardGSYVideoPlayer) findViewById(R.id.detail_player);
        activityDetailPlayer = (RelativeLayout) findViewById(R.id.activity_detail_player);
        postDetailNestedScroll = (NestedScrollView) findViewById(R.id.post_detail_nested_scroll);
        String url = "http://baobab.wdjcdn.com/14564977406580.mp4";
        detailPlayer.setUp(url, true, "");

        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.fenjing);
        detailPlayer.setThumbImageView(imageView);

        resolveNormalVideoUI();

        orientationUtils = new OrientationUtils(this, detailPlayer);
        orientationUtils.setEnable(false);

        detailPlayer.setIsTouchWiget(true);

        detailPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDo();
            }
        });

        detailPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNormal();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (isFull) {
            detailPlayer.getBackButton().performClick();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoPlayer.releaseAllVideos();
    }

    private void toFull() {
        isFull = true;

        TransitionManager.beginDelayedTransition(activityDetailPlayer);

        setViewHeight(detailPlayer, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        resolveFullVideoUI();
        orientationUtils.setEnable(true);
    }

    private void toNormal() {
        isFull = false;
        orientationUtils.setEnable(false);
        int delay = orientationUtils.backToProtVideo();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TransitionManager.beginDelayedTransition(activityDetailPlayer);
                setViewHeight(detailPlayer, ViewGroup.LayoutParams.MATCH_PARENT,
                        (int) getResources().getDimension(R.dimen.post_media_height));
            }
        }, delay);
    }

    private void toDo() {
        if (isFull) {
            toNormal();
        } else {
            toFull();
        }
    }

    private void resolveNormalVideoUI() {
        //增加title
        detailPlayer.getTitleTextView().setVisibility(View.GONE);
        detailPlayer.getTitleTextView().setText("测试视频");
        detailPlayer.getBackButton().setVisibility(View.GONE);
    }

    private void resolveFullVideoUI() {
        detailPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        detailPlayer.getTitleTextView().setText("测试视频");
        detailPlayer.getBackButton().setVisibility(View.VISIBLE);
    }

}
