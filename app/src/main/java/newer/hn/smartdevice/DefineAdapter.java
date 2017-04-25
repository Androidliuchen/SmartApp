package newer.hn.smartdevice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/13 0013.
 * 自定义数据适配器
 */
public class DefineAdapter  extends BaseAdapter{
    private ArrayList<Itemobject>  mlist;
    private Context context;
    private LayoutInflater layoutInflater;
    private  int layoutid;

    public DefineAdapter(Context context, int layoutid,ArrayList<Itemobject> mlist) {
        this.mlist = mlist;
        this.context = context;
        this.layoutid = layoutid;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
         return  mlist==null?0:mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold  viewHold=null;
        if(viewHold==null){
           convertView= layoutInflater.inflate(layoutid,null);
           viewHold=new ViewHold();
           //viewHold.imgview=(ImageView)convertView.findViewById(R.id.img1);
           viewHold.txtiew=(TextView)convertView.findViewById(R.id.tv_name);
           convertView.setTag(viewHold);
       }
        else {
           viewHold=(ViewHold) convertView.getTag();
       }
        Itemobject  itemobj=new Itemobject();
        itemobj=mlist.get(position);
        //viewHold.imgview.setImageResource(itemobj.getMimageSrcId());
        viewHold.txtiew.setText(itemobj.getMstrName());
        return convertView;
    }


    class ViewHold{
        private ImageView   imgview;
        private TextView    txtiew;
    }
}
