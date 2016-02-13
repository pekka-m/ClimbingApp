package me.climbingti.climbingtrainer.hangboard.addhangboard;

/**
 * Created by H3173 on 19.11.2015.
 */
public interface HangboardView<DataType> {
    void setData(DataType param);
    void onResult(String result);
}
