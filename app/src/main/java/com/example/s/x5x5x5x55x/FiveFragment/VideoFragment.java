package com.example.s.x5x5x5x55x.FiveFragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.s.x5x5x5x55x.BrowserActivity;
import com.example.s.x5x5x5x55x.R;
import com.example.s.x5x5x5x55x.VideoFragment_LoopView.LoopViewPager;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment {
    ImageView image_aiyiqi;
    ImageView image_tengxun;
    ImageView image_souhu;
    ImageView image_youku;
    ImageView image_mangguo;
    ImageView image_tudou;
    ImageView image_m1905;
    ImageView image_leshi;
    ImageView image_ppshipin;
    ImageView image_lishipin;
    ImageView image_dongman;
    ImageView image_dianshi;
    private LoopViewPager viewPager;

    private List<String> images = new ArrayList<>();

    private long exittime = 0;

    public VideoFragment() {

    }

    public static Fragment newInstance() {
        VideoFragment fragment = new VideoFragment();
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        viewPager = (LoopViewPager) view.findViewById(R.id.viewpager);

        images.add("https://wx2.sinaimg.cn/mw690/b0653590gy1fv0zcugl7ij20hs0bv0t5.jpg");
        images.add("https://wx2.sinaimg.cn/mw690/b0653590gy1fv0zcuob7jj20k008cae7.jpg");
        images.add("https://wx2.sinaimg.cn/mw690/b0653590gy1fv0zcuvuzsj20k008cn1q.jpg");
        images.add("https://wx1.sinaimg.cn/mw690/b0653590gy1fv0zcv2qpmj20u00itwkt.jpg");
        images.add("https://wx2.sinaimg.cn/mw690/b0653590gy1fv0zcvgxw4j20jt078q5c.jpg");
        viewPager.setData(images);

        image_aiyiqi = (ImageView) view.findViewById(R.id.imageview_aiqiyi);
        image_tengxun = (ImageView) view.findViewById(R.id.imageview_tengxun);
        image_souhu = (ImageView) view.findViewById(R.id.imageview_souhu);
        image_youku = (ImageView) view.findViewById(R.id.imageview_youku);
        image_mangguo = (ImageView) view.findViewById(R.id.imageview_mangguo);
        image_tudou = (ImageView) view.findViewById(R.id.imageview_tudou);
        image_m1905 = (ImageView) view.findViewById(R.id.imageview_m1905);
        image_leshi = (ImageView) view.findViewById(R.id.imageview_leshi);
        image_ppshipin = (ImageView) view.findViewById(R.id.imageview_ppshipin);
        image_lishipin = (ImageView) view.findViewById(R.id.imageview_lishipin);
        image_dongman = (ImageView) view.findViewById(R.id.imageview_dongman);
        image_dianshi = (ImageView) view.findViewById(R.id.imageview_dianshi);

        urlEvent(image_aiyiqi, "http://m.iqiyi.com/");
        urlEvent(image_tengxun, "http://m.v.qq.com");
        urlEvent(image_souhu, "https://m.tv.sohu.com/");
        urlEvent(image_youku, "https://www.youku.com/");
        urlEvent(image_mangguo, "https://m.mgtv.com/");
        urlEvent(image_tudou, "http://compaign.tudou.com/?");
        urlEvent(image_m1905, "http://m.1905.com/");
        urlEvent(image_leshi, "http://m.le.com/");
        urlEvent(image_ppshipin, "http://m.pptv.com/?f=pptv");
        urlEvent(image_lishipin, "http://www.pearvideo.com/?from=intro");
        urlEvent(image_dongman, "http://m.iqiyi.com/dongman/");
        urlEvent(image_dianshi, "http://wx.iptv789.com/?act=home");



        return view;
    }

    public void urlEvent(ImageView imageView, final String dataurl) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), BrowserActivity.class);
                intent.putExtra("url", dataurl);
                getActivity().startActivity(intent);

            }
        });
    }


}



