package me.climbingti.climbingtrainer.bouldering;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.common.Entity;
import me.climbingti.climbingtrainer.util.IntegerConverter;
import me.climbingti.climbingtrainer.common.Mapper;
import me.climbingti.climbingtrainer.common.SQLiteHelper;

/**
 * Created by Pekka Melgin on 22.10.2015.
 */
public class ClimbSQLiteMapper implements Mapper {

    private SQLiteDatabase db;

    public ClimbSQLiteMapper(Context context) {
        this.db = new SQLiteHelper(context).getWritableDatabase();
    }



    @Override
    public int store(Entity entity) throws Exception {
        ClimbEntity climbEntity = (ClimbEntity) entity;
        ContentValues values = new ContentValues();
        Log.d("ClimbMapperi", "store: practiceId  value: " + climbEntity.getPracticeId());
        values.put(SQLiteHelper.KEY_GRADE, climbEntity.getGradeInt());
        values.put(SQLiteHelper.KEY_PRACTICE_ID, climbEntity.getPracticeId());
        int id = IntegerConverter.safeLongToInt(this.db.insert(SQLiteHelper.TABLE_CLIMB, null, values));

        if (id == -1) {
            throw new Exception();
        }
        Log.d("mapperi", "dataa meni");
        return id;
    }

    @Override
    public Entity fetch(int id) throws Exception {
        return null;
    }

    @Override
    public Entity fetchLatest() throws Exception {
        ClimbEntity climb = new ClimbEntity();

        Cursor cursor = this.db.rawQuery(
                "SELECT * FROM " + SQLiteHelper.TABLE_CLIMB +
                        " ORDER BY " + SQLiteHelper.KEY_DATETIME +
                        " DESC LIMIT 1", null);

        if (cursor.moveToFirst()){
            climb.setPracticeId(cursor.getInt(SQLiteHelper.PRACTICE_ID_INDEX));
            climb.setDate(cursor.getString(SQLiteHelper.DATETIME_INDEX));
            climb.setGrade(cursor.getInt(SQLiteHelper.GRADE_INDEX));
        }
        cursor.close();
        return climb;
    }

    @Override
    public Collection fetchByPracticeId(int practiceId) throws Exception {
        ClimbCollection climbCollection = new ClimbCollection();
        ClimbEntity climb;

        Cursor cursor = this.db.rawQuery(
                "SELECT * FROM " + SQLiteHelper.TABLE_CLIMB +
                        " WHERE " + SQLiteHelper.KEY_PRACTICE_ID + " = " + practiceId +
                        " ORDER BY " + SQLiteHelper.KEY_DATETIME +
                        " DESC", null);

        if (cursor.moveToFirst()) {
            do {
                climb = new ClimbEntity();
                climb.setPracticeId(cursor.getInt(SQLiteHelper.PRACTICE_ID_INDEX));
                climb.setDate(cursor.getString(SQLiteHelper.DATETIME_INDEX));
                climb.setGrade(cursor.getInt(SQLiteHelper.GRADE_INDEX));
                climbCollection.add(climb);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return climbCollection;
    }

    @Override
    public Collection fetchAll() throws Exception {
        Cursor cursor = this.db.rawQuery(
                "SELECT * FROM " + SQLiteHelper.TABLE_CLIMB +
                        " ORDER BY " + SQLiteHelper.KEY_DATETIME +
                        " DESC", null);
        Collection entities = new ClimbCollection();
        if (cursor.moveToFirst()) {
            do {
                ClimbEntity entity = new ClimbEntity();
                entity.setPracticeId(cursor.getInt(SQLiteHelper.PRACTICE_ID_INDEX));
                entity.setDate(cursor.getString(SQLiteHelper.DATETIME_INDEX));
                entity.setGrade(cursor.getInt(SQLiteHelper.GRADE_INDEX));
                entities.add(entity);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return entities;
    }

    @Override
    public Collection fetchLatest(int numberOfPractices) throws Exception {
        return null;
    }
}