package me.climbingti.climbingtrainer.practice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Date;

import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.util.DateConverter;
import me.climbingti.climbingtrainer.common.Entity;
import me.climbingti.climbingtrainer.util.IntegerConverter;
import me.climbingti.climbingtrainer.common.PracticeMapper;
import me.climbingti.climbingtrainer.common.SQLiteHelper;

/**
 * Created by Pekka Melgin on 5.11.2015.
 */
public class PracticeSQLiteMapper implements PracticeMapper{

    private SQLiteDatabase db;

    public PracticeSQLiteMapper(Context context) {
        db = new SQLiteHelper(context).getWritableDatabase();
    }


    /**
     * stores new practiceID in to the database along with the endTime.
     * PracticeID and date is automatically generated in database thus does not need to be inserted.
     * @param practice PracticeEntity which should contain the current endTime
     * @return returns the current practiceID
     * @throws Exception throws Exception if adding to database failed or entity does not contain
     *         endTime
     */
    @Override
    public int store(Entity practice) throws Exception {
        PracticeEntity practiceEntity = (PracticeEntity) practice;
        ContentValues contentValues = new ContentValues();
        if (practiceEntity.getEndTime() != null){
            DateConverter converter = new DateConverter();
            String date = converter.convertIntoISO8601(practiceEntity.getEndTime());
            contentValues.put(SQLiteHelper.KEY_ENDTIME, date);
            int id = IntegerConverter.safeLongToInt(this.db.insert(SQLiteHelper.TABLE_PRACTICE, null, contentValues));
            return id;
        }
        else {
            throw new Exception();
        }
    }

    @Override
    public Entity fetch(int id) throws Exception {
        PracticeEntity practice = new PracticeEntity();
        Cursor cursor = this.db.rawQuery(
                "SELECT * FROM " + SQLiteHelper.TABLE_PRACTICE +
                        " WHERE " + SQLiteHelper.KEY_PRACTICE_ID +
                        " = " + id, null);
        if (cursor.moveToFirst()) {
            practice.setPracticeId(cursor.getInt(SQLiteHelper.ID_INDEX));
            practice.setDate(cursor.getString(SQLiteHelper.STARTTIME_INDEX));
            practice.setEndTime(cursor.getString(SQLiteHelper.ENDTIME_INDEX));
        }
        cursor.close();
        return practice;
    }

    @Override
    public void updatePracticeLatestExerciseDateTime(Date date) throws Exception {
        PracticeEntity entity = (PracticeEntity) this.fetchLatest();
        DateConverter dateConverter = new DateConverter();
        ContentValues contentValues = new ContentValues();
        Log.d("PracticeSQLiteMapper", "updatePracticeLatestExerciseDateTime: date valueta: " + date);
        Log.d("PracticeSQLiteMapper", "updatePracticeLatestExerciseDateTime: date value parsittuna: " + dateConverter.convertIntoISO8601(date));
        contentValues.put(SQLiteHelper.KEY_ENDTIME, dateConverter.convertIntoISO8601(date));
        String id = SQLiteHelper.KEY_PRACTICE_ID +"="+entity.getPracticeId();
        this.db.update(SQLiteHelper.TABLE_PRACTICE, contentValues,id,null);
    }

    /**
     * fetches the latest row from practice table and returns it as practiceEntity object
     * @return returns Entity which is PracticeEntity
     * @throws Exception
     */
    @Override
    public Entity fetchLatest() throws Exception {
        PracticeEntity practice = new PracticeEntity();
        Cursor cursor = this.db.rawQuery(
                "SELECT * FROM " + SQLiteHelper.TABLE_PRACTICE +
                        " ORDER BY " + SQLiteHelper.KEY_PRACTICE_ID +
                        " DESC LIMIT 1", null);
        if (cursor.moveToFirst()) {
            practice.setPracticeId(cursor.getInt(SQLiteHelper.ID_INDEX));
            Log.d("PMapperi", "fetchLatest: startTime: " + cursor.getString(SQLiteHelper.STARTTIME_INDEX));
            practice.setDate(cursor.getString(SQLiteHelper.STARTTIME_INDEX));
            Log.d("PMapperi", "fetchLatest: endTime: " + cursor.getString(SQLiteHelper.ENDTIME_INDEX));
            practice.setEndTime(cursor.getString(SQLiteHelper.ENDTIME_INDEX));
        }
        cursor.close();
        return practice;
    }

    @Override
    public Collection fetchByPracticeId(int practiceId) throws Exception {
        return null;
    }

    @Override
    public Collection fetchAll() throws Exception{
        Collection practiceCollection = new Collection();
        Entity practice;
        Cursor cursor = this.db.rawQuery("SELECT * FROM " + SQLiteHelper.TABLE_PRACTICE + " ORDER BY " + SQLiteHelper.KEY_PRACTICE_ID, null);
        if (cursor.moveToFirst()) {
            do {
                practice = new PracticeOverViewEntity();
                practice.setPracticeId(cursor.getInt(SQLiteHelper.ID_INDEX));
                practice.setDate(cursor.getString(SQLiteHelper.STARTTIME_INDEX));
                practiceCollection.add(practice);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return practiceCollection;
    }

    @Override
    public Collection fetchLatest(int numberOfPractices) throws Exception {
        Collection collection = new Collection();
        Cursor cursor = this.db.rawQuery(
                "SELECT * FROM " + SQLiteHelper.TABLE_PRACTICE +
                        " ORDER BY " + SQLiteHelper.KEY_PRACTICE_ID +
                        " DESC LIMIT " + numberOfPractices , null);
        if (cursor.moveToFirst()) {
            do {
                PracticeEntity practice = new PracticeEntity();
                practice.setPracticeId(cursor.getInt(SQLiteHelper.ID_INDEX));
                practice.setDate(cursor.getString(SQLiteHelper.STARTTIME_INDEX));
                practice.setEndTime(cursor.getString(SQLiteHelper.ENDTIME_INDEX));
                collection.add(practice);
            } while(cursor.moveToNext());}
        cursor.close();
        return collection;

    }
}