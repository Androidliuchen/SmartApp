package newer.hn.smartdevice;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.View;

public class SmartLightActivity extends Activity {
    private ControlSCK controlSCK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_light);
        controlSCK=new ControlSCK(SmartLightActivity.this);
        controlSCK.getSocket();
    }

    public void onClick(View view){
     switch (view.getId()){
         case R.id.btn1:
             controlSCK.sendControlValue(1);
             break;
         case R.id.btn2:
             controlSCK.sendControlValue(2);
             break;
         case R.id.btn3:
             controlSCK.sendControlValue(3);
             break;
         case R.id.btn4:
             controlSCK.sendControlValue(4);
             break;
         case R.id.btn5:
             controlSCK.sendControlValue(0);
             break;
     }
    }
}
