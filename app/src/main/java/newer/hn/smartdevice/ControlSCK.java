package newer.hn.smartdevice;

import android.app.Activity;
import android.content.Context;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Administrator on 2016/6/17 0017.
 */
public class ControlSCK {
    private Context  context;
    private DefineAppication defineAppication;
    private DataOutputStream  dostream;
    private  Socket sck;

    public ControlSCK(Context context){
        this.context= context;
    }


    public void  getSocket(){
        defineAppication=(DefineAppication)((Activity)context).getApplication();
        sck=defineAppication.getSocket("activesocket");
        if(sck!=null){
            try{
                OutputStream ostream=sck.getOutputStream();
                dostream=new DataOutputStream(ostream);
            }catch(Exception ex){
                ex.printStackTrace();;
            }
        }
    }

    public   void sendControlValue(int value){
        try{
            dostream.writeInt(value);
            dostream.flush();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void freeSck(){
        if(dostream!=null){
            try{
                dostream.close();
                dostream=null;
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }

        if(sck!=null){
            try{
                sck.close();
                sck=null;
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
