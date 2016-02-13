package me.climbingti.climbingtrainer.campus;

import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.common.Mapper;
import me.climbingti.climbingtrainer.practice.PracticeInteractor;

/**
 * Created by Pekka Melgin on 22.10.2015.
 */
public class CampusInteractor {

    private Mapper campusMapper;
    private PracticeInteractor practiceInteractor;

    public CampusInteractor(Mapper campusMapper, PracticeInteractor practiceInteractor) {
        this.campusMapper = campusMapper;
        this.practiceInteractor = practiceInteractor;
    }

    public long addCampus(String steps, String campusType) throws Exception {
        CampusEntity campusEntity = new CampusEntity();
        int [] stepArray = getStepsFromString(steps);
        int testiIntti = convertIntoSteps(stepArray);
        campusEntity.setSteps(testiIntti);
        campusEntity.setPracticeId(practiceInteractor.getPracticeId());
        campusEntity.setType(campusType);
        return campusMapper.store(campusEntity);
    }

    private int[] getStepsFromString(String steps) throws Exception{
        // stringi muotoa 1-4-6
        // 1-4 3 steppiä
        // 4-6 2 steppiä
        String temp = steps;
        if (temp.endsWith("-")){
            temp = temp.substring(0, temp.length()-1);
        }

        String[] splitted = temp.split("-");

        int[] results= new int[splitted.length];

        for (int i = 0; i < splitted.length; i++) {
            try{
                results[i] = Integer.parseInt(splitted[i]);
            } catch (NumberFormatException e){throw new Exception();}
        }
        return results;
    }



    private int convertIntoSteps(int[] array){
        int steps = 0;
        int index = array.length-1;
        do {
            steps += Math.abs(array[index] - array[index-1]);
            index--;
        }while (index > 0);
        return steps;
    }

    public Collection fetchAll() throws Exception{
        return campusMapper.fetchAll();
    }

    public Collection fetchByPracticeId(int practiceId){
        CampusCollection collection = new CampusCollection();
        try {
           collection = (CampusCollection) campusMapper.fetchByPracticeId(practiceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return collection;
    }

}