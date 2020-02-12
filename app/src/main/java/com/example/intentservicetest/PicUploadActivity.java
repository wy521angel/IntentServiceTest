package com.example.intentservicetest;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PicUploadActivity extends AppCompatActivity {

    private LinearLayout mLyTaskContainer;

    private void handleResult(String path) {

        TextView tv = (TextView) mLyTaskContainer.findViewWithTag(path);
        if (tv != null) {
            tv.setText(path + " upload success ~~~ ");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_upload);
        mLyTaskContainer = (LinearLayout) findViewById(R.id.id_ll_taskcontainer);
        EventBus.getDefault().register(this);
    }

    int i = 0;

    public void addTask(View view) {
        //模拟路径
        String path = "/sdcard/imgs/" + (++i) + ".png";
        UploadImgService.startUploadImg(this, path);
        TextView tv = new TextView(this);
        mLyTaskContainer.addView(tv);
        tv.setText(path + "is uploading...");
        tv.setTag(path);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshRedIconForVersion(ImgEvent event) {
        handleResult(event.getPath());
    }
}