package me.climbingti.climbingtrainer.campus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.common.Entity;
import me.climbingti.climbingtrainer.util.IntegerConverter;
import me.climbingti.climbingtrainer.common.Mapper;
import me.climbingti.climbingtrainer.common.SQLiteHelper;

/**
 * Created by Pekka Melgin on 22.10.2015.
 */
public class CampusSQLiteMapper implements Mapper {

    private SQLiteDatabase db;

    public CampusSQLiteMapper(Context context) {
        this.db = new SQLiteHelper(context).getWritableDatabase();
    }

    @Override
    public int store(Entity entity) throws Exception {
        CampusEntity campusEntity = (CampusEntity) entity;
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.KEY_STEPS, campusEntity.getSteps());
        values.put(SQLiteHelper.KEY_PRACTICE_ID, campusEntity.getPracticeId());
        values.put(SQLiteHelper.KEY_CAMPUSTYPE, campusEntity.getType());
        int id = IntegerConverter.safeLongToInt(this.db.insert(SQLiteHelper.TABLE_CAMPUS, null, values));
        if (id == -1) {
            throw new Exception();
        }
        return id;
    }

    @Override
    public Entity fetch(int id) throws Exception {
        return null;
    }

    @Override
    public Entity fetchLatest() throws Exception {
        CampusEntity campus = new CampusEntity();
        Cursor cursor = this.db.rawQuery(
                " SELECT * FROM " + SQLiteHelper.TABLE_CAMPUS +
                " ORDER BY " + SQLiteHelper.KEY_DATETIME +
                " DESC LIMIT 1", null);
        if (cursor.moveToFirst()) {
           campus = returnEntityWithData(cursor);
        }
        cursor.close();
        return campus;
    }


    @Override
    public Collection fetchByPracticeId(int practiceId) throws Exception {
        Collection campusCollection = new CampusCollection();
        Cursor cursor = this.db.rawQuery(
                " SELECT * " +
                " FROM " + SQLiteHelper.TABLE_CAMPUS +
                " WHERE " +SQLiteHelper.KEY_PRACTICE_ID+ " = " + practiceId +
                " ORDER BY " + SQLiteHelper.KEY_DATETIME +
                " DESC", null);
        if(cursor.moveToFirst()) {
            do {
                campusCollection.add(returnEntityWithData(cursor));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return campusCollection;
    }

    @Override
    public Collection fetchAll() throws Exception {
        Cursor cursor = this.db.rawQuery(
                " SELECT * " +
                " FROM " + SQLiteHelper.TABLE_CAMPUS +
                " ORDER BY " + SQLiteHelper.KEY_DATETIME +
                " DESC", null);
        Collection entities = new CampusCollection();
        if (cursor.moveToFirst()) {
            do {
                entities.add(returnEntityWithData(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return entities;
    }

    private CampusEntity returnEntityWithData(Cursor cursor){
        CampusEntity entity = new CampusEntity();
        entity.setPracticeId(cursor.getInt(SQLiteHelper.PRACTICE_ID_INDEX));
        entity.setSteps(cursor.getInt(SQLiteHelper.STEPS_INDEX));
        entity.setDate(cursor.getString(SQLiteHelper.DATETIME_INDEX));
        return entity;
    }

    @Override
    public Collection fetchLatest(int numberOfPractices) throws Exception {
        return null;
    }
}