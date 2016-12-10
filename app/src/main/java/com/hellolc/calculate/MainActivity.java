package com.hellolc.calculate;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_c;
    private Button btn_del;
    private Button btn_div;
    private Button btn_multi;
    private Button btn_sub;
    private Button btn_add;
    private Button btn_equal;
    private Button btn_point;
    private EditText editText;
    private String[] btnNameArr = {"btn_0", "btn_1", "btn_2", "btn_3", "btn_4", "btn_5", "btn_6", "btn_7", "btn_8", "btn_9", "btn_c", "btn_del", "btn_div", "btn_multi", "btn_sub", "btn_add", "btn_equal", "btn_point"};
    private Map<String, Button> btnMap;
    private String inputStr;
    private String handleOption;
    private double lastNum;
    private double currNum;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btn_0 = (Button) findViewById(R.id.btn_0);
        this.btn_1 = (Button) findViewById(R.id.btn_1);
        this.btn_2 = (Button) findViewById(R.id.btn_2);
        this.btn_3 = (Button) findViewById(R.id.btn_3);
        this.btn_4 = (Button) findViewById(R.id.btn_4);
        this.btn_5 = (Button) findViewById(R.id.btn_5);
        this.btn_6 = (Button) findViewById(R.id.btn_6);
        this.btn_7 = (Button) findViewById(R.id.btn_7);
        this.btn_8 = (Button) findViewById(R.id.btn_8);
        this.btn_9 = (Button) findViewById(R.id.btn_9);
        this.btn_c = (Button) findViewById(R.id.btn_c);
        this.btn_del = (Button) findViewById(R.id.btn_del);
        this.btn_div = (Button) findViewById(R.id.btn_div);
        this.btn_multi = (Button) findViewById(R.id.btn_multi);
        this.btn_sub = (Button) findViewById(R.id.btn_sub);
        this.btn_add = (Button) findViewById(R.id.btn_add);
        this.btn_equal = (Button) findViewById(R.id.btn_equal);
        this.btn_point = (Button) findViewById(R.id.btn_point);
        this.editText = (EditText) findViewById(R.id.editText);
        this.editText.setFocusable(false);
        this.handleOption = "=";

        this.btn_0.setOnClickListener(this);
        this.btn_1.setOnClickListener(this);
        this.btn_2.setOnClickListener(this);
        this.btn_3.setOnClickListener(this);
        this.btn_4.setOnClickListener(this);
        this.btn_5.setOnClickListener(this);
        this.btn_6.setOnClickListener(this);
        this.btn_7.setOnClickListener(this);
        this.btn_8.setOnClickListener(this);
        this.btn_9.setOnClickListener(this);
        this.btn_c.setOnClickListener(this);
        this.btn_del.setOnClickListener(this);
        this.btn_div.setOnClickListener(this);
        this.btn_multi.setOnClickListener(this);
        this.btn_sub.setOnClickListener(this);
        this.btn_add.setOnClickListener(this);
        this.btn_equal.setOnClickListener(this);
        this.btn_point.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        Toast.makeText(this, ((Button)view).getText(), Toast.LENGTH_SHORT).show();
//        Log.i("startOption: ", this.option);
        this.inputStr = String.valueOf(((Button)view).getText());
        switch (view.getId()) {
            case R.id.btn_c:
                this.clear2Zero();
                break;
            case R.id.btn_del:
                this.handleDel();
                break;
            case R.id.btn_div:
            case R.id.btn_multi:
            case R.id.btn_sub:
            case R.id.btn_add:
            case R.id.btn_equal:
                this.handleOption();
                break;
            case R.id.btn_point:
            default:
                this.handleCurrNum();
                break;
        }
        Log.i("lastNum:", String.valueOf(this.lastNum));
        Log.i("currNum:", String.valueOf(this.currNum));
        Log.i("option:", this.handleOption);
        Log.i("str:", this.inputStr);
    }

    /**
     * 数字显示在屏幕上
     */
    private void handleCurrNum() {
//        Log.i("handleCurrNum: ", this.option);
        String tmpStr;
        if (flag) {
            tmpStr = ".".equals(this.inputStr) ? "0." : this.inputStr;
//            关闭清空标识
            this.flag = false;
        } else {
            tmpStr = this.editText.getText() + this.inputStr;
        }
        this.editText.setText(tmpStr);
        this.currNum = Double.parseDouble(tmpStr);
    }

    /**
     * 处理操作符
     */
    private void handleOption() {
//        保存操作符
        if ("=".equals(this.inputStr)) {
            Log.i("handleOption: ", "====");
            this.handleEqual();
        } else {
            Log.i("handleOption: ", "!====");
            this.handleOption = this.inputStr;
            this.lastNum = this.currNum;
        }
//        下次点击数字时清空标识
        this.flag = true;
    }

    /**
     * 处理等于号
     */
    private void handleEqual() {
        Log.i("handleEqual: ", String.valueOf(this.handleOption));
        switch (this.handleOption) {
            case "+":
                this.currNum += this.lastNum;
                break;
            case "-":
                this.currNum = this.lastNum - this.currNum;
                break;
            case "×":
                this.currNum *= this.lastNum;
                break;
            case "÷":
                this.currNum = this.lastNum / this.currNum;
                break;
            case "=":
                break;
            default:
                Log.i("default: ", this.handleOption);
                break;
        }

        this.setEditText(String.valueOf(this.currNum));
    }

    /**
     * 将结果展现到显示屏上
     */
    private void setEditText(String tmpStr) {
        this.editText.setText(String.valueOf(tmpStr));
    }

    /**
     * 获取屏幕上的值
     * @return
     */
    private String getDisplayStr() {
        return String.valueOf(this.editText.getText());
    }

    /**
     * 减去一个字符
     */
    private void handleDel() {
        String tmpStr = this.getDisplayStr();
        if (tmpStr.length() > 1) {
            tmpStr = tmpStr.substring(0, tmpStr.length() - 1);
        } else {
            tmpStr = "0";
        }

        this.currNum = Double.valueOf(tmpStr);
        this.setEditText(tmpStr);
    }

    /**
     * 清空
     */
    private void clear2Zero() {
        this.currNum = 0;
        this.lastNum = 0;
        this.handleOption = "=";
        this.flag = true;
        this.setEditText(String.valueOf(this.currNum));
    }
}
