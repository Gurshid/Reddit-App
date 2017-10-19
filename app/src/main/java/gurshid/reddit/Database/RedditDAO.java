package gurshid.reddit.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import gurshid.reddit.Model.Post;

/**
 * Created by Gurshid on 12/2/2016.
 */
public class RedditDAO {

    /**
     * Singelton Pattern
     */
    private static RedditDAO sInstance = null;

    /**
     * Get the instance of a Database Access Object
     * @return
     */
    public static RedditDAO getsInstance (){
        if (sInstance == null){
            sInstance = new RedditDAO();
        }

        return sInstance;
    }

    public boolean storePosts (Context context, List<Post> postList){

        try{
            SQLiteDatabase db = new DBOpenHelper(context).getWritableDatabase();

            db.beginTransaction();

            for (Post post : postList){

                ContentValues cv = new ContentValues();
                cv.put(DatabaseContract.PostTable.TITLE,post.getTitle());
                cv.put(DatabaseContract.PostTable.LINK,post.getPermalink());
                cv.put(DatabaseContract.PostTable.IMAGELINK,post.getThumbnail());

                db.insert(DatabaseContract.PostTable.TABLE_NAME,null,cv);
            }

            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();

            } catch (Exception e){
            return false;
            }

        return true;
    }

    public List<Post> getPostsFromDB(Context context){

        SQLiteDatabase db = new  DBOpenHelper(context).getWritableDatabase();

        Cursor cursor = db.query(DatabaseContract.PostTable.TABLE_NAME,null,null,null,null,null,null,null);

        cursor.moveToFirst();

        List<Post> postList = new ArrayList<>();

        while (cursor.moveToNext()){

            String title = cursor.getString(cursor.getColumnIndex(DatabaseContract.PostTable.TITLE));
            String link = cursor.getString(cursor.getColumnIndex(DatabaseContract.PostTable.LINK));
            String imageLink = cursor.getString(cursor.getColumnIndex(DatabaseContract.PostTable.IMAGELINK));

            Post post = new Post(link,imageLink,title);

            postList.add(post);

        }

        cursor.close();
        db.close();

        return postList;
    }

}
