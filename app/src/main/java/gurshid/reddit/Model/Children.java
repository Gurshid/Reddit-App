package gurshid.reddit.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Gurshid on 8/7/2016.
 */
public class Children {

    @SerializedName("data")
    private Post post;

    public Post getPost(){
        return post;
    }
}
