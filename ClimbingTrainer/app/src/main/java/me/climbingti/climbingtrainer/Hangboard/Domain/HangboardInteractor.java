package me.climbingti.climbingtrainer.hangboard.Domain;


import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.common.Mapper;
import me.climbingti.climbingtrainer.practice.PracticeInteractor;

/**
 * Created by Pekka Melgin on 22.10.2015.
 */
public class HangboardInteractor {

    private Mapper mapper;
    private PracticeInteractor practiceInteractor;

    public HangboardInteractor(Mapper hangboardMapper, PracticeInteractor practiceInteractor) {
        mapper = hangboardMapper;
        this.practiceInteractor = practiceInteractor;
    }


    public long addHang(int time) throws Exception {
        HangboardEntity hangboardEntity = new HangboardEntity();
        hangboardEntity.setHangTime(time);
        hangboardEntity.setPracticeId(practiceInteractor.getPracticeId());
        return mapper.store(hangboardEntity);
    }



    public Collection fetchAll() throws Exception {
      return  mapper.fetchAll();
    }

    public Collection fetchByPracticeId(int practiceId){
        HangboardCollection collection = new HangboardCollection();
        try {
            collection = (HangboardCollection) mapper.fetchByPracticeId(practiceId);
        } catch (Exception e){
            e.printStackTrace();
        }
        return collection;
    }

}