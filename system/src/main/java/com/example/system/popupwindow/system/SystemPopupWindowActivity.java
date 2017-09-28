package com.example.system.popupwindow.system;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.system.R;
import com.example.system.dialog.system.SimpleAdapter;


public class SystemPopupWindowActivity extends AppCompatActivity {

    private View button07;
    private View contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_popup_window);
        initViews();
    }

    private void initViews() {
        button07=findViewById(R.id.button_07);
        contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_layout, null, false);
    }

    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.button_01) {
            button01(view);

        } else if (i == R.id.button_02) {
            button02(view);

        } else if (i == R.id.button_03) {
            button03();

        } else if (i == R.id.button_04) {
            button04();

        } else if (i == R.id.button_05) {
            button05();

        } else if (i == R.id.button_06) {
            button06();

        } else if (i == R.id.button_07) {
            button07();

        } else if (i == R.id.button_08) {
            button08();

        } else if (i == R.id.button_09) {
            button09(view);

        } else if (i == R.id.button_10) {
            button10();

        } else if (i == R.id.button_11) {
            button11();

        }
    }

    //在指定控件下方弹出
    private void button01(View view) {
        PopupWindow popupWindow = new PopupWindow(contentView, -1, -2, true);
        popupWindow.showAsDropDown(view);
    }

    //偏移量
    private void button02(View view) {
        PopupWindow popupWindow = new PopupWindow(contentView, -1, -2, true);
        PopupWindowCompat.showAsDropDown(popupWindow, view, 100, 1000, Gravity.BOTTOM);
    }

    //相对于父控件弹出
    private void button03() {
        PopupWindow popupWindow = new PopupWindow(contentView, -2, -2, true);
        popupWindow.showAtLocation(getContentView(this), Gravity.CENTER, 0, 0);
    }

    //setOutsideTouchable 方法的使用
    private void button04() {
        PopupWindow popupWindow = new PopupWindow(contentView, -2, -2);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.showAtLocation(getContentView(this), Gravity.CENTER, 0, 0);

    }

    //update方法的使用
    private void button05() {
        final PopupWindow popupWindow = new PopupWindow(contentView, -2, -2, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color._3C9FFC)));
        popupWindow.showAtLocation(getContentView(this), Gravity.BOTTOM, 0, 0);

        contentView.findViewById(R.id.popupwindow_text1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.update(400, 800);
            }
        });

    }

    //添加动画
    private void button06() {
        final PopupWindow popupWindow = new PopupWindow(contentView, -2, -2, true);
        popupWindow.setAnimationStyle(R.style.pop_anim);
        popupWindow.showAtLocation(getContentView(this), Gravity.CENTER, 0, 0);
    }

    //弹出时Activity背景色为半透明
    private void button07() {
        final PopupWindow popupWindow = new PopupWindow(contentView, -2, -2, true);
        setBackgroundAlpha(this, 0.5F);
        popupWindow.showAtLocation(getContentView(this), Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(SystemPopupWindowActivity.this, 1.0F);
            }
        });
    }

    //弹出一个占满屏幕的,背景为半透明
    private void button08() {
        View view = LayoutInflater.from(this).inflate(R.layout.popupwindow_layout_2, null, false);
        final PopupWindow popupWindow = new PopupWindow(view, -1, -1, true);
        popupWindow.showAtLocation(getContentView(this), Gravity.CENTER, 0, 0);

    }

    //在指定控件上方显示
    private void button09(View view) {
        final int[] location = new int[2];
        view.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(contentView, 200, 200, true);
        popupWindow.showAtLocation(getContentView(this), Gravity.NO_GRAVITY, location[0], location[1] - popupWindow.getHeight());

    }

    //点击外部不消失
    private void button10() {
        View pupInput = LayoutInflater.from(this).inflate(R.layout.pup_input, null);
        final PopupWindow popupWindow =
                new PopupWindow(pupInput, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //x,y的起始坐标为View的左上角坐标
                final int x = (int) event.getX();//获取点击位置的x坐标
                final int y = (int) event.getY();//获取点击位置的y坐标
                //返回true 表示对事件的消耗,返回false不对事件进行操作 可以参考

                return (event.getAction() == MotionEvent.ACTION_DOWN) && ((x < 0) || (x >= v.getWidth()) || (y < 0) || (y >= v.getHeight())) || event.getAction() == MotionEvent.ACTION_OUTSIDE;
            }
        });

        popupWindow.showAtLocation(getContentView(this), Gravity.CENTER, 0, 0);


        final EditText editText = pupInput.findViewById(R.id.pup_input_edit);
        pupInput.findViewById(R.id.pup_input_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SystemPopupWindowActivity.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
    }

    //LisetPopupWindow
    private void button11() {
        ListPopupWindow listPopupWindow = new ListPopupWindow(this);
        //设置宽度
        listPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置高度
        listPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置Adapter
        listPopupWindow.setAdapter(new SimpleAdapter());
        //点击外部后事件是否向下传递
        listPopupWindow.setModal(true);
        //设置相对于哪个控件下方弹出
        listPopupWindow.setAnchorView(button07);
        //设置List点击事件
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SystemPopupWindowActivity.this, "点击了第 " + position + " 条", Toast.LENGTH_SHORT).show();
            }
        });
        //设置竖直方向的偏移
        listPopupWindow.setVerticalOffset(100);
        //设置水平方向的偏移
        listPopupWindow.setHorizontalOffset(100);
        //弹出
        listPopupWindow.show();
    }

    //获取Acticity的根布局
    public View getContentView(Activity ac) {
        ViewGroup view = (ViewGroup) ac.getWindow().getDecorView();
        FrameLayout content = view.findViewById(android.R.id.content);
        return content.getChildAt(0);
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        activity.getWindow().setAttributes(lp);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
