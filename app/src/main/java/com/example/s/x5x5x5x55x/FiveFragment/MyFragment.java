package com.example.s.x5x5x5x55x.FiveFragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s.x5x5x5x55x.ContactMeActivity;
import com.example.s.x5x5x5x55x.LoginActivity;
import com.example.s.x5x5x5x55x.QuestionActivity;
import com.example.s.x5x5x5x55x.R;
import com.example.s.x5x5x5x55x.TimeExchangeActivity;
import com.example.s.x5x5x5x55x.utils.CleanMessageUtil;
import com.example.s.x5x5x5x55x.utils.MyOneLineView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyFragment extends Fragment implements MyOneLineView.OnRootClickListener, MyOneLineView.OnArrowClickListener {

    private CircleImageView mMineAvatar;
    private TextView mSignUpLogin,mMineTime;
    LinearLayout ll_mine_item;


    private long exittime = 0;

    public MyFragment() {

    }

    public static Fragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    public void initView() {
        ll_mine_item = (LinearLayout) getActivity().findViewById(R.id.ll_mine_item);
        mMineAvatar = (CircleImageView) getActivity().findViewById(R.id.iv_mine_avatar) ;
        mSignUpLogin = (TextView) getActivity().findViewById(R.id.tv_singup_login);
        mMineTime = (TextView)getActivity().findViewById(R.id.tv_mine_time);


        mMineAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        mSignUpLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        //使用示例，通过Java代码来创建MyOnelineView
        //icon + 文字 + 箭头
        ll_mine_item.addView(new MyOneLineView(getActivity())
                .initMine(R.mipmap.mine_timeexchange, "时长兑换", "", true)
                .setOnRootClickListener(this, 1));

        ll_mine_item.addView(new MyOneLineView(getActivity())
                .initMine(R.mipmap.mine_contact, "联系我们", "", true)
                .setOnRootClickListener(this, 2));

        ll_mine_item.addView(new MyOneLineView(getActivity())
                .initMine(R.mipmap.mine_question, "常见问题", "", true)
                .setOnRootClickListener(this, 3));

        ll_mine_item.addView(new MyOneLineView(getActivity())
                .initMine(R.mipmap.mine_update, "检查更新", "", true)
                .setDividerTopColor(R.color.gray2)
                .showDivider(true, true)
                .setDividerTopHigiht(10)
                .setOnRootClickListener(this, 4));

        ll_mine_item.addView(new MyOneLineView(getActivity())
                .initMine(R.mipmap.mine_clear, "清除缓存", "", true)
                .setOnRootClickListener(this, 5));

        ll_mine_item.addView(new MyOneLineView(getActivity())
                .initMine(R.mipmap.mine_exit, "退出登陆", "", true)
                .setOnRootClickListener(this, 6));
//        //icon + 文字 + 文字 + 箭头
//        ll_mine_item.addView(new MyOneLineView(getActivity())
//                .initMine(R.mipmap.ic_launcher, "第二行", "第二行", true)
//                .setOnArrowClickListener(this, 2));
//        //icon + 文字 + 输入框
//        ll_mine_item.addView(new MyOneLineView(getActivity())
//                .initItemWidthEdit(R.mipmap.ic_launcher, "第三行", "这是一个输入框")
//                .setRootPaddingTopBottom(20, 20));
    }

    @Override
    public void onRootClick(View view) {
        switch ((int) (view.getTag())) {
            case 1:
                Intent intent = new Intent(getActivity(), TimeExchangeActivity.class);
                startActivity(intent);
                break;
            case 2:
                Intent intent2 = new Intent(getActivity(), ContactMeActivity.class);
                startActivity(intent2);
                break;
            case 3:
                Intent intent3 = new Intent(getActivity(), QuestionActivity.class);
                startActivity(intent3);
                break;
            case 5:
                CleanMessageUtil.clearAllCache(getActivity().getApplicationContext());//这个是可以用于清楚缓存的
                Toast.makeText(getActivity(), "缓存已清理", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;

        }

    }

    @Override
    public void onArrowClick(View view) {

    }


}
