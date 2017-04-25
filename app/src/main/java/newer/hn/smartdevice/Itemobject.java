package newer.hn.smartdevice;

/**
 * Created by Administrator on 2016/6/13 0013.
 * 定义对象,主要与listview显示行数据映射
 */
public class Itemobject {
    private int  mimageSrcId;
    private String mstrName;

    public Itemobject() {
    }

    public Itemobject(String mstrName) {
        this.mstrName = mstrName;
    }

    public Itemobject(int mimageSrcId, String mstrName) {
        this.mimageSrcId = mimageSrcId;
        this.mstrName = mstrName;
    }

    public int getMimageSrcId() {
        return mimageSrcId;
    }

    public String getMstrName() {
        return mstrName;
    }
}
