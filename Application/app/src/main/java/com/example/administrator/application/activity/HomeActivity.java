package com.example.administrator.application.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.administrator.application.R;
import com.example.administrator.application.commons.ActivityUtils;
import com.example.administrator.application.fragment.HomeFragment;
import com.example.administrator.application.fragment.MeFragment;
import com.example.administrator.application.fragment.UnLoginFragment;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @BindViews({R.id.tv_shop, R.id.tv_message, R.id.tv_mail_list, R.id.tv_me})
    TextView[] textViews;

    @BindView(R.id.main_toobar)
    Toolbar toolbar;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.main_title)
    TextView tv_title;
    //点击2次返回，退出程序
    private boolean isExit = false;
    private ActivityUtils mActivityUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mActivityUtils = new ActivityUtils(this);
        init();
    }

    private void init() {
        //刚进来默认选择市场
        textViews[0].setSelected(true);


        viewPager.setAdapter(unLoginAdapter);

        //viewPager添加滑动监听，用于控制TextView的展示
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //textView全部未选择
                for (TextView textView : textViews){
                    textView.setSelected(false);
                }
                //更改title，设置选择效果
                tv_title.setText(textViews[position].getText());
                textViews[position].setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private FragmentStatePagerAdapter unLoginAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                //市场
                case 0:
                    return new HomeFragment();
                //消息
                case 1:
                    return new UnLoginFragment();
                //通讯录
                case 2:
                    return new UnLoginFragment();
                //我的
                case 3:
                    return new MeFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }
    };


    //textview点击事件
    @OnClick({R.id.tv_shop,R.id.tv_message,R.id.tv_mail_list,R.id.tv_me})
    public void onClick(TextView view){
        for(int i = 0;i<textViews.length;i++){
            textViews[i].setSelected(false);
            textViews[i].setTag(i);
        }
        //设置选择效果
        view.setSelected(true);
        //参数false代表瞬间切换，而不是平滑过渡
        viewPager.setCurrentItem((Integer) view.getTag(),false);
        //设置一下toolbar的title
        tv_title.setText(textViews[(Integer) view.getTag()].getText());
    }

    //点击两次返回退出程序
    @Override
    public void onBackPressed() {
        if (!isExit) {
            isExit = true;
            mActivityUtils.showToast("再摁一次退出程序");
            //两秒内再次点击返回则退出
            //如果两秒内，用户没有再次点击，则把isExit设置为false
            viewPager.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            finish();
        }
    }

}
