package com.example.pickerselector;


import com.bigkoo.pickerview.MyOptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.OnDismissListener;
import com.bigkoo.pickerview.view.BasePickerView;
import com.example.pickerselector.Common.AreaManager;
import com.example.pickerselector.Model.City;
import com.example.pickerselector.Model.JsonBean;
import com.example.pickerselector.Model.Province;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    private TextView tvTime, singleTVOptions, twoTVOptions, threeTVOptions;
    TimePickerView pvTime;
    MyOptionsPickerView singlePicker, twoPicker, threePicker;
    View vMasker;
    Province province;
    private ArrayList<String> twoItemsOptions1 = new ArrayList<String>();
    private ArrayList<String> twoItemsOptions2 = new ArrayList<String>();
    private final String SELECTOR_SUBMIT = "完成";
    private final String SELECTOR_CANCEL = "取消";
    private Button btnGsonJson, btnObjectJson,btnAreaManger;
    private City city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vMasker = findViewById(R.id.vMasker);
        tvTime = (TextView) findViewById(R.id.tvTime);
        singleTVOptions = (TextView) findViewById(R.id.tvsingleOptions);
        twoTVOptions = (TextView) findViewById(R.id.tvTwoOptions);
        threeTVOptions = (TextView) findViewById(R.id.tvThreeOptions);
        btnGsonJson = findViewById(R.id.btnGsonJson);
        btnObjectJson = findViewById(R.id.btnObjectJson);
        btnAreaManger = findViewById(R.id.btnAreaManger);



        btnGsonJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    loadGsonJson();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnObjectJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String cityJson = getReadFile("newcity.json");
//                    loadObjectJson(cityJson);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        twoPicker = new MyOptionsPickerView(MainActivity.this);

        singleTVOptions.setOnClickListener(this);
        twoTVOptions.setOnClickListener(this);
        threeTVOptions.setOnClickListener(this);
        btnAreaManger.setOnClickListener(this);


        try {
//            init();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        Log.v("dateee", "" + new Date());
        pvTime.setTime(new Date(),TimePickerView.Type.YEAR_MONTH_DAY);
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        //DatePicker
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                Log.v("selected Date", "" + date);
                tvTime.setText(getTime(date));
            }
        });*/
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.show();
            }
        });

        //Single String Picker
        singlePicker = new MyOptionsPickerView(MainActivity.this);
        final ArrayList<String> items = new ArrayList<String>();
        items.add("A");
        items.add("B");
        items.add("C");
        items.add("D");
        items.add("E");
        singlePicker.setPicker(items);
        singlePicker.setTitle("Single Picker");
        singlePicker.setCyclic(false);
        singlePicker.setSelectOptions(0);
        singlePicker.setOnoptionsSelectListener(new MyOptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //  singleTVOptions.setText("Single Picker " + items.get(options1));
                Toast.makeText(MainActivity.this, "" + items.get(options1), Toast.LENGTH_SHORT).show();
//                vMasker.setVisibility(View.GONE);
            }
        });
        singleTVOptions.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                singlePicker.show();
            }
        });


//        //Two Options PickerView
//        twoPicker = new MyOptionsPickerView(MainActivity.this);
//        final ArrayList<String> twoItemsOptions1 = new ArrayList<String>();
//        twoItemsOptions1.add("AA");
//        twoItemsOptions1.add("BB");
//        twoItemsOptions1.add("CC");
//        twoItemsOptions1.add("DD");
//        twoItemsOptions1.add("EE");
//        final ArrayList<String> twoItemsOptions2 = new ArrayList<String>();
//        twoItemsOptions2.add("00");
//        twoItemsOptions2.add("11");
//        twoItemsOptions2.add("22");
//        twoItemsOptions2.add("33");
//        twoItemsOptions2.add("44");
//
//        twoPicker.setPicker(twoItemsOptions1, twoItemsOptions2, false);
//        twoPicker.setTitle("Picker");
//        twoPicker.setCyclic(false, false, false);
//        twoPicker.setSelectOptions(0, 0);
//        twoPicker.setOnoptionsSelectListener(new MyOptionsPickerView.OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int option2, int options3) {
//                // twoTVOptions.setText("Two Options " + twoItemsOptions1.get(options1) + " " + twoItemsOptions2.get(option2));
//                vMasker.setVisibility(View.GONE);
//                Toast.makeText(MainActivity.this, "" + twoItemsOptions1.get(options1) + " " + twoItemsOptions2.get(option2), Toast.LENGTH_SHORT).show();
//            }
//        });
//        twoTVOptions.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                twoPicker.show();
//            }
//        });


        //Three Options PickerView
        threePicker = new MyOptionsPickerView(MainActivity.this);
        final ArrayList<String> threeItemsOptions1 = new ArrayList<String>();
        threeItemsOptions1.add("AA");
        threeItemsOptions1.add("BB");
        threeItemsOptions1.add("CC");
        threeItemsOptions1.add("DD");
        threeItemsOptions1.add("EE");
        final ArrayList<String> threeItemsOptions2 = new ArrayList<String>();
        threeItemsOptions2.add("00");
        threeItemsOptions2.add("11");
        threeItemsOptions2.add("22");
        threeItemsOptions2.add("33");
        threeItemsOptions2.add("44");

        final ArrayList<String> threeItemsOptions3 = new ArrayList<String>();
        threeItemsOptions3.add("FF");
        threeItemsOptions3.add("GG");
        threeItemsOptions3.add("HH");
        threeItemsOptions3.add("II");
        threeItemsOptions3.add("JJ");

        threePicker.setPicker(threeItemsOptions1, threeItemsOptions2, threeItemsOptions3, false);
        threePicker.setTitle("Picker");
        threePicker.setCyclic(false, false, false);
        threePicker.setSelectOptions(0, 0, 0);
        threePicker.setOnoptionsSelectListener(new MyOptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
//                vMasker.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "" + threeItemsOptions1.get(options1) + " " + threeItemsOptions2.get(option2) + " " + threeItemsOptions3.get(options3), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvsingleOptions:
                singlePicker.show();
                break;
            case R.id.tvTwoOptions:
                twoPicker.show();
                break;
            case R.id.tvThreeOptions:
                threePicker.show();
                break;
            case R.id.btnAreaManger:
                loadAreaMager();
                break;

        }
    }
  //[{"key":"110000","value": "北京" }, { "key":"120000","value": "天津" }
//    public void loadAreaMager() {
//        List<AreaManager.KeyValuePair<String, String>> provincesList = AreaManager.getInstance(getBaseContext()).getProvinces();
//        for (int i = 0; i < provincesList.size(); i++) {
//            provincesList.get(i);
//            Log.v("hank", "provincesList.get(i):" + provincesList.get(i));
//            Iterator<AreaManager.KeyValuePair<String, String>> iterator = provincesList.iterator();
//
//            while (iterator.hasNext()) {
//                AreaManager.KeyValuePair<String, String> keyValuePair = iterator.next();
//                String key = keyValuePair.key;
//                String value = keyValuePair.value;
//                Log.v("hank", "keyValuePair:" +"key:"+ key +"/value:" + value);
//            }
//
//
//        }
//
//    }


    //用資料結構讀取檔案方式
    public void loadAreaMager() {
        //陣列,物件 [{}] => List<KeyValuePair<String, String>>
        //解Provinces[{ key":"110000","value": "北京" }, { "key":"120000","value": "天津"}

        //1.取得Provinces解析好的資料
        List<AreaManager.KeyValuePair<String, String>> provincesList = AreaManager.getInstance(getBaseContext()).getProvinces();


        //2.利用intetator蝶帶氣撈出來
        Iterator<AreaManager.KeyValuePair<String, String>> iterator = provincesList.iterator();
        while (iterator.hasNext()) { //當裡面有值時,
            AreaManager.KeyValuePair<String, String> keyValuePair = iterator.next();//當還有下一筆資料時
            String key = keyValuePair.key;//取得key
            String value = keyValuePair.value; //取得value
            twoItemsOptions1.add(value); //添加到ArrayList

            Log.v("hank", "Provinces:" + "key:" + key + "/value:" + value);
        }
        //物件,陣列,物件 =>  HashMap<1.String(key == 11000),2.value List<KeyValuePair<key:11000, value:北京市>>
        //解city{"110000":[{"key":"110000","value": "北京市" }],"120000": [{"key":"120000","value": "天津市" }]}

        //1.取得city解析好的資料
        HashMap<String, List<AreaManager.KeyValuePair<String, String>>> cityMaps = AreaManager.getInstance(getBaseContext()).getProvinceCityHashMap();

        //2.物件開頭Map結構撈出第一筆String (key欄位)
        for (Map.Entry<String, List<AreaManager.KeyValuePair<String, String>>> cityMap : cityMaps.entrySet()) {
            List<AreaManager.KeyValuePair<String, String>> cityList = cityMap.getValue();
        //3.轉成蝶帶器準備撈List (value資料){}
            Iterator<AreaManager.KeyValuePair<String, String>> iteratorCity = cityList.iterator();
            Log.v("hank", "iteratorCity:" + iteratorCity);
         //4.撈KeyValuePair的 key:value資料
            while (iteratorCity.hasNext()) {
                AreaManager.KeyValuePair<String, String> cityValuePair = iteratorCity.next();
                String cityKey = cityValuePair.key;
                String cityValue = cityValuePair.value;
                twoItemsOptions2.add(cityValue);
                Log.v("hank", "city =>" + "/cityKey:" + cityKey + "/cityValue:" + cityValue);
            }
        }

        //SpinnerSelector設定
        twoPicker.setPicker(twoItemsOptions1, twoItemsOptions2, false);
        twoPicker.setTitle("所在地区");
        twoPicker.setCancelButtonText(SELECTOR_CANCEL);
        twoPicker.setSubmitButtonText(SELECTOR_SUBMIT);
        twoPicker.setCyclic(false, false, false);
        twoPicker.setSelectOptions(0, 0);



        twoPicker.setOnoptionsSelectListener(new MyOptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                // twoTVOptions.setText("Two Options " + twoItemsOptions1.get(options1) + " " + twoItemsOptions2.get(option2));
//                    vMasker.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "" + twoItemsOptions1.get(options1) + " " + twoItemsOptions2.get(option2), Toast.LENGTH_SHORT).show();


                Log.v("hank","onOptionsSelect=> options1:" + options1 +"/option2:" + option2 + "options3:" + options3);
            }
        });
        twoTVOptions.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                twoPicker.show();
                Log.v("hank","onClick=> View:" + v.getId());
            }
        });
        //取消時
        twoPicker.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(Object o) {
                Log.v("hank","onDismiss=> object:" + o.toString());
                twoPicker.show();
            }
        });

    }



    public void loadTxt(View view) throws IOException {

        String provinceJson = getReadFile("province.json");
        String cityJson = getReadFile("newcity.json");
        parseJsonSelector(provinceJson, cityJson);

    }

    public void init() throws Exception{
        String provinceJson = getReadFile("province.json");
        String cityJson = getReadFile("newcity.json");
        parseJsonSelector(provinceJson, cityJson);
    }


    private String getReadFile(String fileName) throws IOException {
        //讀取
        InputStream inputStream = getAssets().open(fileName); //打開Assets/資源檔案
        int intSize = inputStream.available(); //直接取得這個檔案的大小
        byte[] buf = new byte[intSize];
        inputStream.read(buf);
        inputStream.close();

        String json = new String(buf, "UTF-8");
        Log.v("hank", "json:" + json);
        return json;
    }

    private void initSelector(){

    }

    public void parseJsonSelector(String buf, String buf2) {
        try {
            JSONArray root = new JSONArray(buf);
            for (int i = 0; i < root.length(); i++) {
                JSONObject row = root.getJSONObject(i);
                String key = row.getString("key");
                String value = row.getString("value");

                Province province = new Province();
                province.setKey(key);
                province.setValue(value);
                Log.v("hank", "key:" + province.getKey() + "/value:" + province.getValue());

                twoItemsOptions1.add(province.getValue());

            }
            JSONObject dataJson = new JSONObject(buf2); //解第一層{}物件
            for (int index = 1; index < dataJson.length(); index++) {
                String name = String.valueOf(index);
                dataJson = new JSONObject(buf2); //解第一層{}物件
                JSONArray key1 = dataJson.getJSONArray(name); //解第二層[] ,dataJson.getJSONArray(name) 這邊name是欄位名1
                Log.v("hank", "key1:" + key1.toString()); //得到key1:[{"key":"110000","value":"北京市"}]
                for (int i = 0; i < key1.length(); i++) { //{"key":"110000","value": "北京市" }解第三層
                    JSONObject row = key1.getJSONObject(i);
                    String key = row.getString("key");
                    String value = row.getString("value");
                    Log.v("hank", "key:" + key + "/value:" + value); //得到:key:110000/value:北京市

                    city = new City();
                    city.setKey(key);
                    city.setValue(value);
                    Log.v("hank", "key:" + city.getKey() + "/value:" + city.getValue());
                    twoItemsOptions2.add(city.getValue());
                }

            }


            twoPicker.setPicker(twoItemsOptions1, twoItemsOptions2, false);
            twoPicker.setTitle("所在地区");
            twoPicker.setCancelButtonText(SELECTOR_CANCEL);
            twoPicker.setSubmitButtonText(SELECTOR_SUBMIT);
            twoPicker.setCyclic(false, false, false);
            twoPicker.setSelectOptions(0, 0);
            twoPicker.setOnoptionsSelectListener(new MyOptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3) {
                    // twoTVOptions.setText("Two Options " + twoItemsOptions1.get(options1) + " " + twoItemsOptions2.get(option2));
//                    vMasker.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "" + twoItemsOptions1.get(options1) + " " + twoItemsOptions2.get(option2), Toast.LENGTH_SHORT).show();
                }
            });
            twoTVOptions.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    twoPicker.show();
                }
            });

        } catch (Exception e) {
            Log.v("hank","錯誤:" + e.toString());
        }

    }

    public void parseJsonSelector2(String buf, String buf2) throws IOException {


        twoPicker.setPicker(twoItemsOptions1, twoItemsOptions2, false);
        twoPicker.setTitle("所在地区");
        twoPicker.setCancelButtonText(SELECTOR_CANCEL);
        twoPicker.setSubmitButtonText(SELECTOR_SUBMIT);
        twoPicker.setCyclic(false, false, false);
        twoPicker.setSelectOptions(0, 0);

        twoPicker.setOnoptionsSelectListener(new MyOptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                // twoTVOptions.setText("Two Options " + twoItemsOptions1.get(options1) + " " + twoItemsOptions2.get(option2));
//                vMasker.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "" + twoItemsOptions1.get(options1) + " " + twoItemsOptions2.get(option2), Toast.LENGTH_SHORT).show();

                Log.v("hank","onOptionsSelect:" + options1 );
            }
        });
        twoTVOptions.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                twoPicker.show();
                Log.v("hank","onClick");
            }
        });

        twoPicker.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(Object o) {
                Log.v("hank","setOnDismissListener");
            }
        });

    }

    //解多層Json
    private void loadGsonJson() throws IOException {
        String cityJson = getReadFile("text.json");
        gsonJson(cityJson);
    }

    private void gsonJson(String json) throws IOException {
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<JsonBean>() {
        }.getType();
        JsonBean jsonBean = gson.fromJson(json, type);

        for (int i = 0; i < jsonBean.b.size(); i++) {
            jsonBean.b.get(i);
            Log.v("hank", "jsonBean:" + jsonBean.b);
        }

    }


    //    3.
//    private void loadObjectJson(String json){
//        try {
//            JSONObject dataJson = new JSONObject(json);
//            JSONArray provinces = dataJson.getJSONArray("110000");
//            for(int i = 0; i < provinces.length(); i++){
//                JSONObject province = provinces.getJSONObject(i);
//                String key = province.getString("key");  //得到省
//                JSONArray value = province.getJSONArray("value");
//                for(int j = 0; j < value.length(); j++){
//                    //得到城市
//                   String data = value.getString(j);
//                   Log.v("hank","key:" + key +"/value:" + value);
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
    //解多重Json{"110000": [{"key":"110000","value": "北京市" }]
//    private void loadObjectJson(String json) {
//        try {
//
//            for (int index = 1; index < json.length(); index++) {
//                String name = String.valueOf(index);
//                Log.v("hank", "name:" + name);
//                JSONObject dataJson = new JSONObject(json); //解第一層{}物件
//                JSONArray key1 = dataJson.getJSONArray(name); //解第二層[]
//                Log.v("hank", "key1:" + key1.toString()); //得到key1:[{"key":"110000","value":"北京市"}]
//                for (int i = 0; i < key1.length(); i++) { //{"key":"110000","value": "北京市" }解第三層
//                    JSONObject row = key1.getJSONObject(i);
//                    String key = row.getString("key");
//                    String value = row.getString("value");
//                    Log.v("hank", "key:" + key + "/value:" + value); //得到:key:110000/value:北京市
//
//                    city = new City();
//                    city.setKey(key);
//                    city.setValue(value);
//                    Log.v("hank", "key:" + city.getKey() + "/value:" + city.getValue());
//                }
//
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
}




