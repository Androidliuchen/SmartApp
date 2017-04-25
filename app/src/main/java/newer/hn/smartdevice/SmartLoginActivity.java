package newer.hn.smartdevice;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
public class SmartLoginActivity extends Activity {
    private int index=-1;
    private String str_ipvalue;
    private String str_portvalue;
    private EditText  edt_iptxt;
    private EditText  edt_porttxt;
    private CommondFun  comfun;
    private Socket  clientsck=null;
    private int errno=-1;//错误编号
    private DefineAppication defineAppication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_login);
        getIntentValue();
        findEditText();
        comfun=new CommondFun();
        defineAppication=(DefineAppication)getApplication();
    }

    public void  getIntentValue() {
        Bundle bundle = getIntent().getExtras();
        index = bundle.getInt("index");
        //Toast.makeText(SmartLoginActivity.this, ""+index, Toast.LENGTH_SHORT).show();
    }

    public void  findEditText(){
        edt_iptxt=(EditText)findViewById(R.id.edt_ipaddr);
        edt_porttxt=(EditText)findViewById(R.id.edt_port);
    }

    public void  getInputValue(){
        str_ipvalue=edt_iptxt.getText().toString();
        str_portvalue=edt_porttxt.getText().toString();
    }

    public void onClick(View view){
       switch(view.getId()){
           case R.id.btn_ok:
               getInputValue();
               if(comfun.valueIsEmpty(str_ipvalue)){
                  errno=1;
                   showMessageInfo("IP网络地址为空",this);
                   return;
               }
               if(comfun.valueIsEmpty(str_portvalue)) {
                   errno = 2;
                   showMessageInfo("端口值为空", this);
                   return;
               }
               if(!comfun.ipFormatIsOK(str_ipvalue)){
                  errno=3;
                   showMessageInfo("IP网络地址输入错误",this);
                   return;
               }
               if(!comfun.portIsOk(str_portvalue)){
                   errno=4;
                   showMessageInfo("端口值输入错误",this);
                   return;
               }
               ConnetServer  connetServer=new ConnetServer();
               connetServer.execute(str_ipvalue,str_portvalue);
               break;
           case R.id.btn_cancel:
               edt_iptxt.setText("");
               edt_porttxt.setText("");
               edt_iptxt.setFocusable(true);
               edt_iptxt.requestFocus();
               break;
       }
    }


    public void showMessageInfo(String mesage, Context context){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("错误提示");
        builder.setMessage(mesage);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 if(errno==1){
                     edt_iptxt.setFocusable(true);
                     edt_iptxt.requestFocus();
                 }
                if(errno==2){
                    edt_porttxt.setFocusable(true);
                    edt_porttxt.requestFocus();
                }
                if(errno==3){
                    edt_iptxt.setText("");
                    edt_iptxt.setFocusable(true);
                    edt_iptxt.requestFocus();
                }
                if(errno==4)
                {
                    edt_porttxt.setText("");
                    edt_porttxt.setFocusable(true);
                    edt_porttxt.requestFocus();
                }
                if(errno==5){
                    edt_iptxt.setText("");
                    edt_porttxt.setText("");
                    edt_iptxt.setFocusable(true);
                    edt_iptxt.requestFocus();
                    return;
                }
            }
        });
        builder.show();
    }

    //网络连接内部类
   public class ConnetServer extends AsyncTask<String,Void,Object> {
        private boolean isconnected=false;
        @Override
        protected Object doInBackground(String... params) {
            String ip_value=params[0];
            String port_value=params[1];
            connetToHost(ip_value,port_value);
            if(isconnected){
                return new Integer(1);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            Integer ret=(Integer)o;
            if(ret==null){
                errno=5;
                showMessageInfo("网络连接失败",SmartLoginActivity.this);
                return;
            }
            if(isconnected==false){
                errno=5;
                showMessageInfo("网络连接失败",SmartLoginActivity.this);
                return;
            }
            Intent intent=new Intent(SmartLoginActivity.this,SmartLightActivity.class);
            startActivity(intent);
        }

        public  void  connetToHost(String ipvalue, String portvalue){
            try{
                SocketAddress socAddress = new InetSocketAddress(ipvalue,Integer.parseInt(portvalue));
               // clientsck=new Socket(ipvalue,Integer.parseInt(portvalue));
                clientsck=new Socket();
                clientsck.connect(socAddress,5000);//设置超时处理
                defineAppication.setSocket("activesocket",clientsck);
                isconnected=true;
            }catch( Exception ex){
              ex.printStackTrace();
            }
        }
    }
}
