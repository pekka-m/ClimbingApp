package me.climbingti.climbingtrainer.bouldering;

/**
 * Created by H4198 on 19.11.2015.
 */
public interface ClimbView<DataType> {
    void setData(DataType param);
    void onResult(String result);
}
