package com.example.cailu.control.Function.RatingView;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


import com.deanguo.ratingview.RatingView;
import com.example.cailu.control.R;

/**
 * Created by cailu on 2016/6/7.
 */
public class Activityratingview  extends AppCompatActivity implements View.OnClickListener {
    private  RatingView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myratingview);
        initview();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
  /*  也可以使用下面方法深度定义Ratingbar样式
    Using below method to create your own Ratingbar.
    setRatedColor(int color)
    setUnRatedColor(int color)
    setTitleColor(int color)
    setOutlineColor(int color)
    setShadowColor(int color)
    setRate(int rate)
    setMaxRate(int maxRate)
    自定义视图 Custom View
可以再RatingView中间放置任意视图，比如文字，图片，按钮，布局，控件等
*/

    private void initview() {
        view = (RatingView) this.findViewById(R.id.rating_view);
        TextView textView= (TextView) view.findViewById(R.id.center_btn);
        textView.setOnClickListener(this);
        view.addRatingBar(new com.deanguo.ratingview.RatingBar(8, "HARD"));
        view.addRatingBar(new com.deanguo.ratingview.RatingBar(8, "VIEW"));
        view.addRatingBar(new com.deanguo.ratingview.RatingBar(8, "WEATHER"));
        view.addRatingBar(new com.deanguo.ratingview.RatingBar(8, "TRAFFIC"));
        view.setDefaultColor(getResources().getColor(R.color.colorAccent));
        view.setAnimatorListener(new RatingView.AnimatorListener() {
            @Override
            public void onRotateStart() {
                //进入试图图片旋转时。
            }

            @Override
            public void onRotateEnd() {

            }

            @Override
            public void onRatingStart() {
            }

            @Override
            public void onRatingEnd() {
            }
        });
        view.show();
    }

    @Override
    public void onClick(View v) {
        view.setDefaultColor(getResources().getColor(R.color.colorPrimary));

    }
}
