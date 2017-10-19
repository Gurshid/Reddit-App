package gurshid.reddit.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gurshid on 8/7/2016.
 */
public class Listing {

    @SerializedName("data")
    private ChildrenArray childrenArray;

    public List<Post> getPostList() {
        List<Post> postList = new ArrayList<Post>();

        for(Children children : childrenArray.getChildrenList()){
            postList.add(children.getPost());
        }

        return  postList;
    }
}
