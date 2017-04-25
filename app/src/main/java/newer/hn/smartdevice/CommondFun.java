package newer.hn.smartdevice;

import android.util.Log;

/**
 * Created by Administrator on 2016/6/17 0017.
 */
public class CommondFun {


    public  boolean valueIsEmpty(String value){
        if(value==null){
            return true;
        }
        if(value.length()==0){
            return true;
        }
        return false;
    }


    public boolean ipFormatIsOK(String ipvalue){
        String[]  valuearray=ipvalue.split("\\.");
        if(valuearray.length!=4){
            return false;
        }
        int i;
        for(i=0;i<valuearray.length;i++){
            try{
                int tmpdata=Integer.parseInt(valuearray[i]);
                if(tmpdata<=0 || tmpdata>=255){
                    return false;
                }
            }catch (Exception ex){
                return false;
            }
        }
        return true;
    }

    public boolean portIsOk(String  portvalue) {
        try {
            int tmpdata = Integer.parseInt(portvalue);
            if (tmpdata <= 1024 || tmpdata > 65535) {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
