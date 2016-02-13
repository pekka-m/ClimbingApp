package me.climbingti.climbingtrainer.bouldering;


import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.common.Mapper;
import me.climbingti.climbingtrainer.practice.PracticeInteractor;

/**
 * Created by Pekka Melgin on 22.10.2015.
 */
public class ClimbInteractor {

    private Mapper climbMapper;
    private PracticeInteractor interactor;

    public ClimbInteractor(Mapper climbMapper, PracticeInteractor practiceInteractor) {
        this.climbMapper = climbMapper;
        interactor = practiceInteractor;
    }

    public long addClimb(int grade) throws Exception {
        int testi = interactor.getPracticeId();
        ClimbEntity climbEntity = new ClimbEntity();
        climbEntity.setGrade(grade);
        climbEntity.setPracticeId(testi);
        return climbMapper.store(climbEntity);
    }

    public Collection fetchAll() throws Exception{
      return climbMapper.fetchAll();
    }

    public Collection fetchByPracticeId(int practiceId) {
        ClimbCollection collection = new ClimbCollection();
        try {
           collection = (ClimbCollection) climbMapper.fetchByPracticeId(practiceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return collection;
    }
}