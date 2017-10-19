package gurshid.reddit.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Gurshid on 8/7/2016.
 */
public class ChildrenArray {

    @SerializedName("children")
    private List<Children> childrenList;

    public  List<Children> getChildrenList(){
        return  childrenList;
    }
}
