package gurshid.reddit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import java.util.List;
import gurshid.reddit.Database.RedditDAO;
import gurshid.reddit.Model.Listing;
import gurshid.reddit.Model.Post;

public class MainActivity extends AppCompatActivity implements RedditAdapter.MyListItemClickListener {

    public final String REDDIT_URL = "http://puppygifs.tumblr.com/api/read/json?limit=50";

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerListView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        StringRequest request = new StringRequest(Request.Method.GET, REDDIT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Listing listing = new Gson().fromJson(response,Listing.class);

                List<Post> postList = listing.getPostList();

                RedditAdapter adapter = new RedditAdapter(postList,MainActivity.this,MainActivity.this);

                RedditDAO.getsInstance().storePosts(MainActivity.this,postList);

                mRecyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                List<Post> postList = RedditDAO.getsInstance().getPostsFromDB(MainActivity.this);
                RedditAdapter adapter = new RedditAdapter(postList,MainActivity.this,MainActivity.this);
                mRecyclerView.setAdapter(adapter);
            }
        });
        //Add the request to the RequestQueue
        ConnectionManager.getInstance(this).add(request);
    }

    @Override
    public void OnItemClick(Post ItemClicked) {

        Intent webintent = new Intent(MainActivity.this, WebActivity.class);
        webintent.putExtra("URL",ItemClicked.getPermalink());
        startActivity(webintent);
    }
}
