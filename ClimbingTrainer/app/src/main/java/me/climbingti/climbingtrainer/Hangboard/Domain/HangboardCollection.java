package me.climbingti.climbingtrainer.hangboard.Domain;

/**
 * Created by Aleksi on 12.11.2015.
 */



public class HangboardCollection extends me.climbingti.climbingtrainer.common.Collection {

    public int getTotalHangTime() {
        int totalHang = 0;
        for(int i = 0; i < list.size(); i++){
            HangboardEntity entity =(HangboardEntity) list.get(i);
            totalHang += entity.getHangTime();
        }
        return totalHang;
    }
}

