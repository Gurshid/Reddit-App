package gurshid.reddit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;

import java.util.List;
import gurshid.reddit.Model.Post;

/**
 * Created by Gurshid on 11/29/2016.
 */
public class RedditAdapter extends RecyclerView.Adapter<RedditAdapter.MyViewHolder> {

    List <Post> mPostList;
    Context mContext;
    MyListItemClickListener mListener;

    public RedditAdapter(List<Post> postList, Context context, MyListItemClickListener listener){
        mPostList = postList;
        mContext = context;
        mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_post,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder, int position)  {
        Post currentPost = mPostList.get(position);
        holder.mTextViewPostName.setText(currentPost.getTitle());
        if(currentPost.getThumbnail().startsWith("http://")){
            holder.mPostImage.setVisibility(View.VISIBLE);
            holder.mPostImage.setImageUrl(currentPost.getThumbnail(),ConnectionManager.getsImageLoader(mContext));

        }
        else {
            ((View)holder.mPostImage.getParent()).setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView mTextViewPostName;
        public NetworkImageView mPostImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewPostName = (TextView)itemView.findViewById(R.id.rowTextViewName);
            mPostImage = (NetworkImageView) itemView.findViewById(R.id.rowNetworkImageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            mListener.OnItemClick(mPostList.get(getPosition()));
        }
    }

    public static interface MyListItemClickListener{
        public void OnItemClick(Post ItemClicked);
    }
}

