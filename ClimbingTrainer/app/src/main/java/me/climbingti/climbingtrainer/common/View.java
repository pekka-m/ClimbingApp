package me.climbingti.climbingtrainer.common;

import android.content.Context;
import android.support.v4.util.ArrayMap;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Aleksi on 22.10.2015.
 */
public interface View<ReturnType> {
    void setResultMsg(String msg);
    void onSuccess();
    Context getContext();
    ReturnType getInput();
    void setData(ArrayMap<String, Collection> entities);
}
