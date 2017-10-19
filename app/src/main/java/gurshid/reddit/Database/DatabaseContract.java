package gurshid.reddit.Database;

/**
 * Created by Gurshid on 12/2/2016.
 */
public class DatabaseContract {

    public  static final String DB_NAME = "reddit_database.db";

    public abstract class PostTable{

        public static final String TABLE_NAME = "post_table";

        public static final String TITLE = "title";
        public static final String LINK = "link";
        public static final String IMAGELINK = "imagelink";

    }

}
