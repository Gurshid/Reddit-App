package gurshid.reddit.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gurshid on 12/2/2016.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    /**
     * DATABASE VERSION
     */
    public static final int DATABASE_VERSION = 1;

    /**
     * TABLE STRINGS
     */
    public static final String TEXT_TYPE = "TEXT";
    public static final String COMMA = ",";

    /**
     * SQL CREATE TABLE SENTENCE
     */
    private static final String CREATE_POST_TABLE = "CREATE TABLE"
            +DatabaseContract.PostTable.TABLE_NAME + "("
            +DatabaseContract.PostTable.TITLE + TEXT_TYPE + COMMA
            +DatabaseContract.PostTable.LINK + TEXT_TYPE + COMMA
            +DatabaseContract.PostTable.IMAGELINK + TEXT_TYPE + ")";

    /**
     * SQL DROP TABLE SENTENCE
     */
    private static final String DROP_POST_TABLE = "DROP TABLE IF EXISTS " +DatabaseContract.PostTable.TABLE_NAME;

    public DBOpenHelper(Context context) {
        super(context, DatabaseContract.DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_POST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_POST_TABLE);
        onCreate(db);
    }
}
