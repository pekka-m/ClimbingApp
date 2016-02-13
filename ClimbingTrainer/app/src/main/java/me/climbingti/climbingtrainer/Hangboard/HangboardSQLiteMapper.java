package me.climbingti.climbingtrainer.hangboard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import me.climbingti.climbingtrainer.hangboard.Domain.HangboardCollection;
import me.climbingti.climbingtrainer.hangboard.Domain.HangboardEntity;
import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.common.Entity;
import me.climbingti.climbingtrainer.util.IntegerConverter;
import me.climbingti.climbingtrainer.common.Mapper;
import me.climbingti.climbingtrainer.common.SQLiteHelper;

/**
 * Created by Pekka Melgin on 23.10.2015.
 */
public class HangboardSQLiteMapper implements Mapper {

    private SQLiteDatabase db;

    public HangboardSQLiteMapper(Context context) {
        this.db = new SQLiteHelper(context).getWritableDatabase();
    }

    @Override
    public int store(Entity entity) throws Exception {
        HangboardEntity hangboardEntity = (HangboardEntity) entity;
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.KEY_TIME, hangboardEntity.getHangTime());
        values.put(SQLiteHelper.KEY_PRACTICE_ID, hangboardEntity.getPracticeId());
        int id = IntegerConverter.safeLongToInt(this.db.insert(SQLiteHelper.TABLE_HANGBOARD, null, values));
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
        HangboardEntity hangboardEntity = new HangboardEntity();

        Cursor cursor =
                this.db.rawQuery(
                        "SELECT * FROM " + SQLiteHelper.TABLE_HANGBOARD +
                                " ORDER BY " + SQLiteHelper.KEY_DATETIME +
                                " DESC LIMIT 1", null);
        if (cursor.moveToFirst()){
            hangboardEntity.setPracticeId(cursor.getInt(SQLiteHelper.PRACTICE_ID_INDEX));
            hangboardEntity.setDate(cursor.getString(SQLiteHelper.DATETIME_INDEX));
            hangboardEntity.setHangTime(cursor.getInt(SQLiteHelper.TIME_INDEX));
        }
        cursor.close();
        return hangboardEntity;

    }

    @Override
    public Collection fetchByPracticeId(int practiceId) throws Exception {
        HangboardCollection hangboardCollection = new HangboardCollection();
        HangboardEntity hangboardEntity;

        Cursor cursor = this.db.rawQuery(
                "SELECT * FROM " + SQLiteHelper.TABLE_HANGBOARD +
                        " WHERE " +SQLiteHelper.KEY_PRACTICE_ID+ " = " + practiceId +
                        " ORDER BY " + SQLiteHelper.KEY_DATETIME +
                        " DESC", null);

        if (cursor.moveToFirst()) {
            do {
                hangboardEntity = new HangboardEntity();
                hangboardEntity.setPracticeId(cursor.getInt(SQLiteHelper.PRACTICE_ID_INDEX));
                hangboardEntity.setDate(cursor.getString(SQLiteHelper.DATETIME_INDEX));
                hangboardEntity.setHangTime(cursor.getInt(SQLiteHelper.TIME_INDEX));
                hangboardCollection.add(hangboardEntity);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return hangboardCollection;

    }

    @Override
    public Collection fetchAll() throws Exception {
        Cursor cursor = this.db.rawQuery(
                "SELECT * FROM " + SQLiteHelper.TABLE_HANGBOARD +
                        " ORDER BY " + SQLiteHelper.KEY_DATETIME +
                        " DESC", null);
        Collection entities = new HangboardCollection();
        if (cursor.moveToFirst()) {
            do {
                HangboardEntity entity = new HangboardEntity();
                entity.setPracticeId(cursor.getInt(SQLiteHelper.PRACTICE_ID_INDEX));
                entity.setDate(cursor.getString(SQLiteHelper.DATETIME_INDEX));
                entity.setHangTime(cursor.getInt(SQLiteHelper.TIME_INDEX));
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