package com.example.system.dialog.system;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.system.R;

import static android.os.Build.VERSION_CODES.N;

/**
 * 系统Dialog使用方法
 */
public class SystemDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_dialog);
    }
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.button_01) {
            button_01();

        } else if (i == R.id.button_02) {
            button_02();

        } else if (i == R.id.button_03) {
            button_03();

        } else if (i == R.id.button_04) {
            button_04();

        } else if (i == R.id.button_05) {
            button_05();

        } else if (i == R.id.button_06) {
            button_06();

        } else if (i == R.id.button_07) {
            button_07();

        } else if (i == R.id.button_08) {
            button_08();

        } else if (i == R.id.button_09) {
            button_09();

        } else if (i == R.id.button_10) {
            button_10();

        } else if (i == R.id.button_11) {
            button_11();

        }
    }

    public void button_01() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)        //设置图标
                .setTitle("我是标题")                //设置标题
                .setMessage("我是内容")//设置内容
                .setPositiveButton("积极的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SystemDialogActivity.this, "which = " + which, Toast.LENGTH_SHORT).show();
                    }
                })    //最右边的按钮
                .setNegativeButton("消极的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SystemDialogActivity.this, "which = " + which, Toast.LENGTH_SHORT).show();
                    }
                })    //右数第二个
                .setNeutralButton("中性的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SystemDialogActivity.this, "which = " + which, Toast.LENGTH_SHORT).show();
                    }
                })    //最左边的
                .setCancelable(false)                //设置点击四周是否会关闭
//				.setCustomTitle(titleView)			//设置自定义标题
                .create();                            //创建
        dialog.show();
    }

    public void button_02() {
        //设置单选列表
//		String[] strings = new String[]{"男", "女"};
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setItems(R.array.sex_items, new DialogInterface.OnClickListener() {//设置内容和监听
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //which 列表中的第几个
                        String result;
                        switch (which) {
                            case 0:
                                result = "男";
                                break;
                            case 1:
                                result = "女";
                                break;
                            case 2:
                                result = "外星人";
                                break;
                            default:
                                result = "我是哪来的";
                                break;
                        }
                        Toast.makeText(SystemDialogActivity.this, result, Toast.LENGTH_SHORT).show();
                    }
                })
                .create();//创建
        dialog.show();
    }

    public void button_03() {
        //单选框
//		String[] strings = new String[]{"男", "女"};
        final String[] result = new String[1];
        AlertDialog dialog = new AlertDialog.Builder(this)
                //内容
                //默认第几个 如果超出数量 默认不选中
                //监听
                .setSingleChoiceItems(R.array.sex_items, 3, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //which 列表中的第几个
                        switch (which) {
                            case 0:
                                result[0] = "男";
                                break;
                            case 1:
                                result[0] = "女";
                                break;
                            case 2:
                                result[0] = "外星人";
                                break;
                            default:
                                result[0] = "我是哪来的";
                                break;
                        }
                    }
                })
                .setNeutralButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //which 下方按钮的位置 -1 积极按钮 -2消极按钮 -3 中性按钮
                        Toast.makeText(SystemDialogActivity.this, result[0], Toast.LENGTH_SHORT).show();
                    }
                })
                .create();//创建
        dialog.show();
    }

    public void button_04() {
        //多选框
//		String[] strings = new String[]{"男", "女"};
        final String[] result = new String[4];
        AlertDialog dialog = new AlertDialog.Builder(this)
                //内容
                //默认选中	boolean数组 可以为null 为null默认不选中任何一个 如不为null 长度一定要为内容长度
                //监听
                .setMultiChoiceItems(R.array.sex_items, new boolean[]{false, false, false}, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        switch (which) {
                            case 0:
                                if (isChecked) {
                                    result[0] = "男";
                                } else {
                                    result[0] = "未选择";
                                }

                                break;
                            case 1:
                                if (isChecked) {
                                    result[1] = "女";
                                } else {
                                    result[1] = "未选择";
                                }
                                break;
                            case 2:
                                if (isChecked) {
                                    result[2] = "外星人";
                                } else {
                                    result[2] = "未选择";
                                }
                                break;
                            default:
                                result[3] = "我是哪来的";
                                break;
                        }
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String s = "1 = " + result[0] + "2 = " + result[1] + "3 = " + result[2];
                        Toast.makeText(SystemDialogActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                })
                .create();//创建
        Window window=dialog.getWindow();
        window.setWindowAnimations(R.style.pop_anim);
        dialog.show();
    }

    private void button_05() {
        //自定义ListView列表
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                //设置Adapter 监听	需要注意的是该方法点击后会关闭dialog
//				.setAdapter(new SimpleAdapter(), new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// which 点击的是第几个
//						Toast.makeText(MainActivity.this, "which = " + which, Toast.LENGTH_SHORT).show();
//					}
//				})
                //如果需要点击后不关闭 dialog 可以使用该方法
                .setSingleChoiceItems(new SimpleAdapter(), 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//						Toast.makeText(MainActivity.this, "which = " + which, Toast.LENGTH_SHORT).show();
                    }
                })
                .create();
        alertDialog.show();
    }

    private void button_06() {
//		Cursor 使用
       AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Cursor使用")
                .setMessage("暂时不会使用")
                .setCancelable(false)
                .setPositiveButton("确定", null)
                .create();
        alertDialog.show();
    }

    //圆形进度条
    private void button_07() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("我是标题");    //设置标题
        progressDialog.setMessage("我是内容");//设置内容
        progressDialog.setCancelable(false);//设置点击周围是否可取消
        progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//设置进度条样式 圆形和水平
//		progressDialog.setProgressDrawable();		// 进度条的样式

        progressDialog.show();
    }

    //水平进度条
    private void button_08() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("我是标题");
        progressDialog.setMessage("我是内容");
        progressDialog.setCancelable(false);
        //设置按钮 类型 文字 监听
        progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();

        //下面的方法只有在ProgressStyle为ProgressDialog.STYLE_HORIZONTAL时才有效
//		progressDialog.setMax(500);//设置进度条的最大值 默认100
        progressDialog.setProgress(50);//设置进度条的数值 必须要在show()方法调用之后调用才有效 如果大于最大值就会显示最大值
//		progressDialog.setIndeterminate(true);//设置不确定性 如果为true则progressbar 则会一直移动
        progressDialog.setSecondaryProgress(70);//设置缓冲区
//		progressDialog.setIndeterminateDrawable();//设置
        progressDialog.setProgressNumberFormat("现值:%1d 总值:%2d");//设置现值和总值显示方式
    }

    //时间选择器
    @TargetApi(N)
    private void button_09() {
//		startActivity(new Intent(this, PickerActivity.class));
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Toast.makeText(SystemDialogActivity.this, year + " 年 " + (month + 1) + " 月 " + dayOfMonth + " 日 ", Toast.LENGTH_SHORT).show();
            }
        }, year, month, day);
        dialog.show();
    }

    @TargetApi(N)
    private void button_10() {
        //需要注意的是 Calendar 默认时区的问题
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(SystemDialogActivity.this, hourOfDay + " 时 " + minute + " 分", Toast.LENGTH_SHORT).show();
            }
        }, hour, minute, true);
        dialog.show();
    }

    private void button_11() {
        startActivity(new Intent(this,PickerActivity.class));
    }
}
