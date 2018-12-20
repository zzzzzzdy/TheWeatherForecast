package activitytest.exmaple.com.theweatherforecast;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {


    List<Data> datas=new ArrayList<Data>();

    RecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;
    TextView textView;
    TextView textViewTwo;
    String city;
    String ganmao;
    String wendu;
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            ganmao = msg.getData().getString("ganmao");
            wendu = msg.getData().getString("wendu");
            textViewTwo = findViewById(R.id.tv_two);
            textViewTwo.setText("平均温度："+wendu+"度   "+"温馨提示:"+ganmao);

            return false;
        }
    });



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String getCity = intent.getStringExtra("city1");
        city = getCity;


        initdata();

        textView = findViewById(R.id.tv_one);
        textView.setText(city);

        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerAdapter=new RecyclerAdapter(datas,this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(manager);
    }
    private void initdata()
    {
        HttpConnect httpConnect=new HttpConnect("https://www.apiopen.top/weatherApi?city="+city);
        httpConnect.sendRequestWithHttpURLConnection(new HttpConnect.Callback() {
            @Override
            public void finish(String respone) {
                parseJSON(respone);//手解版

            }
        });
    }
    private void parseJSON(String respone) {
        try {
//            Log.d("bbbbb", respone);

            JSONObject jsonObject=new JSONObject(respone);

            JSONObject jsonObjectOne=new JSONObject(jsonObject.getString("data"));


            JSONArray jsonArray=new JSONArray(jsonObjectOne.getString("forecast"));
            String ganmao = jsonObjectOne.getString("ganmao");
            String wendu = jsonObjectOne.getString("wendu");
            Message message = Message.obtain();
            Bundle bundle = new Bundle();
            bundle.putString("ganmao",ganmao);
            bundle.putString("wendu",wendu);
            message.setData(bundle);
            handler.sendMessage(message);




            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
//                   Log.d("wwwww", jsonObject2.getString("date"));
//                    Log.d("rrrrr",jsonObject2.getString("date")+jsonObject2.getString("high")+jsonObject2.getString("fx"));
//                    Log.d("eeeee",jsonObject2.getString("date")+jsonObject2.getString("high")+jsonObject2.getString("fx")+
//                            jsonObject2.getString("low")+jsonObject2.getString("fl")+jsonObject2.getString("type"));
                Data data=new Data(jsonObject2.getString("date"),jsonObject2.getString("high"),
                        jsonObject2.getString("fengli"),jsonObject2.getString("low"),
                        jsonObject2.getString("fengxiang"),jsonObject2.getString("type"));


                datas.add(data);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recyclerAdapter.notifyDataSetChanged();
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
