package newer.hn.smartdevice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private ArrayList<Itemobject>  mlist=new ArrayList<Itemobject>();
    private ListView  listView;
    private DefineAdapter  defineAdapter;
    private String[] deviceNames={"智    能    灯","智    能    车","智  能 窗 帘","智能电压锅"};
    private Class[]  action={SmartLoginActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=getApplicationContext();
        initGUI();
    }


    public  void  initGUI(){
        listView=(ListView)findViewById(R.id.lv_smartdevicename);
        if(mlist!=null) {
            mlist.clear();
        }
        for(int i=0;i<deviceNames.length;i++){
            Itemobject item=new Itemobject(deviceNames[i]);
            //Itemobject item=new Itemobject(R.drawable.bmp1,deviceNames[i]);
            mlist.add(item);
        }
        defineAdapter=new DefineAdapter(context,R.layout.activity_item,mlist);
        listView.setAdapter(defineAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,action[0]);
                intent.putExtra("index",position);
                startActivity(intent);
            }
        });
    }
}
