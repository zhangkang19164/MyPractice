package com.example.dependencies;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.dependencies.gif.GifDrawableActivity;
import com.example.dependencies.glide.GlideActivity;
import com.example.dependencies.greendao.GreenDaoActivity;
import com.example.dependencies.lottie.SplashActivity;
import com.example.dependencies.matisse.MatisseMainActivity;
import com.example.dependencies.qrcode.QRCodeActivity;
import com.example.dependencies.rxjava.RxJavaMainActivity;
import com.example.dependencies.wheelview.WheelviewMainActivity;
import com.example.dependencies.xrecyclerview.XRecyclerViewActivity;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;


public class DependenciesMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependencies_main);
    }


    public void onViewClicked(View view) {
        Class<? extends Activity> toActivity;
        int i = view.getId();
        if (i == R.id.to_lottie) {
            toActivity = SplashActivity.class;
        } else if (i == R.id.to_wheelview) {
            toActivity = WheelviewMainActivity.class;
        } else if (i == R.id.to_x_recycler_view) {
            toActivity = XRecyclerViewActivity.class;
        } else if (i == R.id.to_rx_java) {
            toActivity = RxJavaMainActivity.class;
        } else if (i == R.id.to_android_gif_drawable) {
            toActivity = GifDrawableActivity.class;
        } else if (i == R.id.to_glide) {
            toActivity = GlideActivity.class;
        } else if (i == R.id.to_qr_code) {
            toActivity = QRCodeActivity.class;
        } else if (i == R.id.to_green_dao) {
            toActivity = GreenDaoActivity.class;
        } else if (i == R.id.to_matisse) {
            toActivity = MatisseMainActivity.class;
        } else if (i == R.id.to_permissions) {
            toActivity = PermissionsActivity.class;
        } else {
            toActivity = DependenciesMainActivity.class;
        }
        startActivity(new Intent(view.getContext(), toActivity));
    }

}
