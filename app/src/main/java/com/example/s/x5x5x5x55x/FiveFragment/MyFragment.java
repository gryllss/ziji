package com.example.s.x5x5x5x55x.FiveFragment;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s.x5x5x5x55x.Bmob.MyUser;
import com.example.s.x5x5x5x55x.ContactMeActivity;
import com.example.s.x5x5x5x55x.LoginActivity;
import com.example.s.x5x5x5x55x.QuestionActivity;
import com.example.s.x5x5x5x55x.R;
import com.example.s.x5x5x5x55x.TimeExchangeActivity;
import com.example.s.x5x5x5x55x.utils.CleanMessageUtil;
import com.example.s.x5x5x5x55x.utils.DateAndString;
import com.example.s.x5x5x5x55x.utils.MyOneLineView;

import org.json.JSONObject;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Date;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.GetCallback;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyFragment extends Fragment implements MyOneLineView.OnRootClickListener, MyOneLineView.OnArrowClickListener {

    private CircleImageView mMineAvatar;
    private TextView mSignUpLogin, mMineTime;
    LinearLayout ll_mine_item;
    private String currentTime;
    private boolean isAddExitView = false;
    private boolean isLogin = false;


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
        Log.d("RightFragment", "onCreate");


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("RightFragment", "onActivityCreated");
        initView();
    }



    public void initView() {
        ll_mine_item = (LinearLayout) getActivity().findViewById(R.id.ll_mine_item);
        mMineAvatar = (CircleImageView) getActivity().findViewById(R.id.iv_mine_avatar);
        mSignUpLogin = (TextView) getActivity().findViewById(R.id.tv_singup_login);
        mMineTime = (TextView) getActivity().findViewById(R.id.tv_mine_time);


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
//
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
        ll_mine_item.getChildAt(5).setVisibility(View.INVISIBLE);





//        ll_mine_item.removeViewAt(5);


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

            case 4:

                MyUser myUser = BmobUser.getCurrentUser(getActivity(), MyUser.class);
                BmobQuery<MyUser> query = new BmobQuery<MyUser>();
                query.getObject(getActivity(), myUser.getObjectId(), new GetListener<MyUser>() {

                    @Override
                    public void onSuccess(MyUser myUser) {
                        currentTime = myUser.getCurrentTimeMillisVer();
                        Toast.makeText(getActivity(),currentTime,Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(int i, String s) {

                    }
                });

//                Toast.makeText(getActivity(), (String) myUser.getCurrentTimeMillisVer(), Toast.LENGTH_SHORT).show();
//                BmobUser newUser = new BmobUser();
//                newUser = BmobUser.getCurrentUser(getActivity());
//                newUser.setEmail("169567512@163.com");
////                BmobUser bmobUser = BmobUser.getCurrentUser(getActivity());
//                newUser.update(getActivity(), new UpdateListener() {
//                    @Override
//                    public void onSuccess() {
//                        Toast.makeText(getActivity(), "yes", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onFailure(int i, String s) {
//                        Toast.makeText(getActivity(), "no", Toast.LENGTH_SHORT).show();
//                    }
//                });


                break;

            case 5:
                CleanMessageUtil.clearAllCache(getActivity().getApplicationContext());//这个是可以用于清楚缓存的

                Toast.makeText(getActivity(), "缓存已清理", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                TextView title = new TextView(getActivity());
//                title.setText("提示");
//                title.setTextSize(20);
//                builder.setCustomTitle(title);
//                TextView message = new TextView(getActivity());
//                message.setText("是否退出登陆");
//                message.setGravity(Gravity.CENTER);
//                builder.setView(message);
                builder.setTitle("提示").setMessage("是否退出登陆");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BmobUser.logOut(getActivity());//这个是可以用于清楚缓存的
                        Toast.makeText(getActivity(), "已退出", Toast.LENGTH_SHORT).show();
                        ll_mine_item.getChildAt(5).setVisibility(View.INVISIBLE);
                        mSignUpLogin.setText("登陆/注册");
                        mMineTime.setText("");
                        mMineAvatar.setClickable(true);
                        mSignUpLogin.setClickable(true);
                    }
                });
                builder.setNegativeButton("取消",null);
                builder.create().show();
                break;
            default:
                break;

        }

    }

    @Override
    public void onArrowClick(View view) {

    }

    @Override
    public void onStart() {
        super.onStart();

        MyUser myUser = BmobUser.getCurrentUser(getActivity(), MyUser.class);
        if (myUser != null ) {
            ll_mine_item.getChildAt(5).setVisibility(View.VISIBLE);
                mMineAvatar.setClickable(false);
                mSignUpLogin.setClickable(false);
                String phone = myUser.getUsername();
                String time = myUser.getOutTime();
                StringBuilder sb = new StringBuilder(phone);
                sb.replace(3, 7, "****");
                mSignUpLogin.setText(sb.toString());
                StringBuilder sb2 = new StringBuilder(time);
                sb2.replace(10,19,"");
                mMineTime.setText("体验时长：" + sb2+"到期");

        }else {
            ll_mine_item.getChildAt(5).setVisibility(View.INVISIBLE);
        }




    }



}
