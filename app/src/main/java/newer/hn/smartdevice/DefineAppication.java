package newer.hn.smartdevice;

import android.app.Application;
import android.content.Context;

import java.net.Socket;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/6/17 0017.
 */
public class DefineAppication extends Application {
    private HashMap<String,Socket> hashMap;

    @Override
    public void onCreate() {
        super.onCreate();
        hashMap=new HashMap<String,Socket>();
    }

    public  void  setSocket(String key,Socket sckvalue){
        hashMap.put(key,sckvalue);
    }

    public Socket  getSocket(String key){
         if(hashMap.get(key)!=null){
             return hashMap.get(key);
         }
        return null;
    }
}
