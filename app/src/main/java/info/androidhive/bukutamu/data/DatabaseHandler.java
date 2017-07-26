package info.androidhive.bukutamu.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 16-Jul-17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "bukutamu";

    // Contacts table name
    private static final String TABLE_REG = "registrasi";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TAMU = "tamu";
    private static final String KEY_SIAPA = "siapa";
    private static final String KEY_UNIT = "unit";
    private static final String KEY_TUJUAN = "tujuan";
    private static final String KEY_USAHA = "usaha";
    private static final String KEY_HP = "handphone";
    private static final String KEY_ALAMAT = "alamat";
    private static final String KEY_CATATAN = "catatan";
    private static final String KEY_TGLDTG = "tgldtg";
    private static final String KEY_JMDTG = "jmdtg";
    private static final String KEY_JMKELUAR = "jmkeluar";
    private static final String KEY_GAMBAR = "gambar";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_REG_TABLE = "CREATE TABLE " + TABLE_REG + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TAMU + " VARCHAR,"
                + KEY_SIAPA + " VARCHAR," + KEY_UNIT + " VARCHAR," + KEY_TUJUAN + " VARCHAR,"
                + KEY_USAHA + " VARCHAR," + KEY_HP + " VARCHAR," + KEY_ALAMAT + " VARCHAR,"
                + KEY_CATATAN + " VARCHAR," + KEY_JMDTG + " VARCHAR," + KEY_JMKELUAR + " VARCHAR,"
                + KEY_TGLDTG + " VARCHAR," + KEY_GAMBAR + " VARCHAR" + ")";
        db.execSQL(CREATE_REG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REG);

        // Create tables again
        onCreate(db);
    }

    public void regTamu(Registrasi registrasi){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TAMU, registrasi.getTamu()); // Contact Name
        values.put(KEY_SIAPA, registrasi.getSiapa());
        values.put(KEY_UNIT, registrasi.getUnit());
        values.put(KEY_TUJUAN, registrasi.getTujuan());
        values.put(KEY_USAHA, registrasi.getUsaha());
        values.put(KEY_HP, registrasi.getHp());
        values.put(KEY_ALAMAT, registrasi.getAlamat());
        values.put(KEY_CATATAN, registrasi.getCatatan());
        values.put(KEY_JMDTG, registrasi.getJmDtg());
        values.put(KEY_JMKELUAR, registrasi.getjmKeluar());
        values.put(KEY_TGLDTG, registrasi.getTgldtg());
        values.put(KEY_GAMBAR, registrasi.getGambar());

        // Inserting Row
        db.insert(TABLE_REG, null, values);
        db.close(); // Closing database connection
    }

    public List<Registrasi> getData() {
        List<Registrasi> dataReg = new ArrayList<Registrasi>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_REG;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Registrasi registrasi = new Registrasi();
                registrasi.setID(Integer.parseInt(cursor.getString(0)));
                registrasi.setTamu(cursor.getString(1));
                registrasi.setSiapa(cursor.getString(2));
                registrasi.setUnit(cursor.getString(3));
                registrasi.setTujuan(cursor.getString(4));
                registrasi.setUsaha(cursor.getString(5));
                registrasi.setHp(cursor.getString(6));
                registrasi.setAlamat(cursor.getString(7));
                registrasi.setCatatan(cursor.getString(8));
                registrasi.setJmDtg(cursor.getString(9));
                registrasi.setjmKeluar(cursor.getString(10));
                registrasi.setTgldtg(cursor.getString(11));
                registrasi.setGambar(cursor.getString(12));
                // Adding contact to list
                dataReg.add(registrasi);
            } while (cursor.moveToNext());
        }

        // return contact list
        return dataReg;
    }

    public Cursor getCursor(){
        String where = null;
        String selectQuery = "SELECT  * FROM " + TABLE_REG;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c =  db.rawQuery(selectQuery, null);

        if(c != null){
            c.moveToFirst();
        }
        return c;
    }
}
