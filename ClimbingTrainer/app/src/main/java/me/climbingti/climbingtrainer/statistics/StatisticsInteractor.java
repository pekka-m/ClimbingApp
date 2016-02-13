package me.climbingti.climbingtrainer.statistics;

import android.support.v4.util.ArrayMap;
import java.util.Date;
import me.climbingti.climbingtrainer.bouldering.ClimbCollection;
import me.climbingti.climbingtrainer.hangboard.Domain.HangboardCollection;
import me.climbingti.climbingtrainer.campus.CampusCollection;
import me.climbingti.climbingtrainer.common.Collection;
import me.climbingti.climbingtrainer.util.DateConverter;
import me.climbingti.climbingtrainer.common.Mapper;
import me.climbingti.climbingtrainer.common.SQLiteHelper;
import me.climbingti.climbingtrainer.practice.PracticeEntity;
import me.climbingti.climbingtrainer.practice.PracticeOverViewEntity;

/**
 * Created by Pekka Melgin on 4.11.2015.
 */
public class StatisticsInteractor {

    private Collection allEntities;
    private ArrayMap<Date, Collection> allEntitiesGrouped;
    private Collection dayEntities;
    private DateConverter dateConverter;
    private Mapper climbMapper;
    private Mapper campusMapper;
    private Mapper hangboardMapper;
    private Mapper practiceMapper;

    public StatisticsInteractor(Mapper practiceMapper,
                                DateConverter dateConverter,
                                Mapper climbMapper,
                                Mapper campusMapper,
                                Mapper hangboardMapper) {
        this.practiceMapper = practiceMapper;
        this.dateConverter = dateConverter;
        this.climbMapper = climbMapper;
        this.campusMapper = campusMapper;
        this.hangboardMapper = hangboardMapper;
    }

    private void setCollectionType(String table){
        switch (table){
            case SQLiteHelper.TABLE_CLIMB:
                dayEntities = new ClimbCollection();
                break;
            case SQLiteHelper.TABLE_CAMPUS:
                dayEntities= new CampusCollection();
                break;
            case SQLiteHelper.TABLE_HANGBOARD:
                dayEntities = new HangboardCollection();
                break;
            default:
                break;
        }

    }

    private void setEntityType(String table)throws  Exception{
        switch (table){
            case SQLiteHelper.TABLE_CLIMB:
                allEntities = climbMapper.fetchAll();
                break;
            case SQLiteHelper.TABLE_CAMPUS:
                allEntities = campusMapper.fetchAll();
                break;
            case SQLiteHelper.TABLE_HANGBOARD:
                allEntities = hangboardMapper.fetchAll();
                break;
            default:
                break;
        }
    }

    /**
     * Returns an ArrayMap String, Collection in which key is day and value is day's exercise entities
     * @param table which entities to get
     * @return ArrayMap&:lt;String, Collection&:gt; key: dateString value: EntityCollection
     * @throws Exception
     */
    public ArrayMap<Date, Collection> getEntitiesGroupedByDay(String table) throws Exception {
        allEntitiesGrouped = new ArrayMap<>();
        int previousDate = 0;

        setEntityType(table);
        setCollectionType(table);

        for (int i = 0; i < allEntities.size(); i++) {
            int currentDate = Integer.parseInt(dateConverter.convertIntoDate(allEntities.get(i).getDate()));
            if (currentDate >= previousDate) {
                dayEntities.add(allEntities.get(i));
            }
            else {
                allEntitiesGrouped.put(dayEntities.get(0).getDate(), dayEntities);
                setCollectionType(table);
                dayEntities.add(allEntities.get(i));
            }
            previousDate = currentDate;
        }
        if (!dayEntities.isEmpty()){
            //lisätään viimenen paketti
            allEntitiesGrouped.put(dayEntities.get(0).getDate(), dayEntities);
        }
        return allEntitiesGrouped;
    }

    public PracticeOverViewEntity getAllTimeStats() throws Exception{
        ClimbCollection climbCollection = (ClimbCollection) climbMapper.fetchAll();
        CampusCollection campusCollection = (CampusCollection) campusMapper.fetchAll();
        HangboardCollection hangboardCollection = (HangboardCollection) hangboardMapper.fetchAll();
        PracticeOverViewEntity practiceOverViewEntity = new PracticeOverViewEntity();
        practiceOverViewEntity.setNumberofSends(climbCollection.size());
        practiceOverViewEntity.setMaxGrade(climbCollection.getMaxGrade());
        practiceOverViewEntity.setTotalHangtime(hangboardCollection.getTotalHangTime());
        practiceOverViewEntity.setCampusSteps(campusCollection.getTotalSteps());
        practiceOverViewEntity.setCampusDistance(campusCollection.getDistanceCampused());
        return practiceOverViewEntity;
    }

    public Collection getDataForPracticeCards(int numberOfPractices) throws Exception{
        Collection practiceCollection = practiceMapper.fetchLatest(numberOfPractices);
        PracticeOverViewEntity practiceOverViewEntity;

        for(int i = 0; i < practiceCollection.size(); i++){
            ClimbCollection climbCollection = (ClimbCollection) climbMapper.fetchByPracticeId(practiceCollection.get(i).getPracticeId());
            CampusCollection campusCollection = (CampusCollection) campusMapper.fetchByPracticeId(practiceCollection.get(i).getPracticeId());
            HangboardCollection hangboardCollection = (HangboardCollection) hangboardMapper.fetchByPracticeId(practiceCollection.get(i).getPracticeId());

            practiceOverViewEntity = new PracticeOverViewEntity();
            practiceOverViewEntity.setPracticeId(practiceCollection.get(i).getPracticeId());
            practiceOverViewEntity.setDate(practiceCollection.get(i).getDate());
            practiceOverViewEntity = populatePracticeOverviewEntity(climbCollection, campusCollection, hangboardCollection, practiceOverViewEntity);

            // ?????
            //   practiceOverViewEntity.setPracticeId(practiceCollection.get(i).getPracticeId());
            practiceCollection.set(i, practiceOverViewEntity);
        }

        return practiceCollection;

    }


    public Collection getDataForPracticeCards() throws Exception{
        Collection practiceCollection = practiceMapper.fetchAll();
        PracticeOverViewEntity practiceOverViewEntity;

        for(int i = 0; i < practiceCollection.size(); i++){
            ClimbCollection climbCollection = (ClimbCollection) climbMapper.fetchByPracticeId(practiceCollection.get(i).getPracticeId());
            CampusCollection campusCollection = (CampusCollection) campusMapper.fetchByPracticeId(practiceCollection.get(i).getPracticeId());
            HangboardCollection hangboardCollection = (HangboardCollection) hangboardMapper.fetchByPracticeId(practiceCollection.get(i).getPracticeId());

            practiceOverViewEntity = (PracticeOverViewEntity) practiceCollection.get(i);
            practiceOverViewEntity = populatePracticeOverviewEntity(climbCollection, campusCollection, hangboardCollection, practiceOverViewEntity);

            // ?????
         //   practiceOverViewEntity.setPracticeId(practiceCollection.get(i).getPracticeId());
            practiceCollection.set(i, practiceOverViewEntity);
        }

        return practiceCollection;
    }

    private PracticeOverViewEntity populatePracticeOverviewEntity(ClimbCollection climbCollection, CampusCollection campusCollection, HangboardCollection hangboardCollection, PracticeOverViewEntity entity){
        entity.setMaxGrade(climbCollection.getMaxGrade());
        entity.setNumberofSends(climbCollection.size());
        entity.setProjectingGrades(new String[]{"6B+"});
        entity.setCampusSteps(campusCollection.getTotalSteps());
        entity.setCampusDistance(campusCollection.getDistanceCampused());
        entity.setTotalHangtime(hangboardCollection.getTotalHangTime());
        return entity;
    }





    public PracticeOverViewEntity getDataForPracticeDetail(int practiceId) throws Exception {
        ClimbCollection climbCollection = (ClimbCollection) climbMapper.fetchByPracticeId(practiceId);
        CampusCollection campusCollection = (CampusCollection) campusMapper.fetchByPracticeId(practiceId);
        HangboardCollection hangboardCollection = (HangboardCollection) hangboardMapper.fetchByPracticeId(practiceId);
        PracticeEntity practiceEntity = (PracticeEntity) practiceMapper.fetch(practiceId);
        PracticeOverViewEntity practiceOverViewEntity = new PracticeOverViewEntity();
        practiceOverViewEntity.setDate(dateConverter.convertIntoISO8601(practiceEntity.getDate()));
        practiceOverViewEntity.setNumberofSends(climbCollection.size());
        practiceOverViewEntity.setMaxGrade(climbCollection.getMaxGrade());
        practiceOverViewEntity.setProjectingGrades(new String[]{"9A", "8A"});
        practiceOverViewEntity.setTotalHangtime(hangboardCollection.getTotalHangTime());
        practiceOverViewEntity.setCampusSteps(campusCollection.getTotalSteps());
        practiceOverViewEntity.setCampusDistance(campusCollection.getDistanceCampused());
        return practiceOverViewEntity;
    }

}