package me.climbingti.climbingtrainer.util;

/**
 * Created by Pekka Melgin on 24.10.2015.
 */
public class GradeConverter {

    private static String[] gradingScale;

    public static String[] getGradingScale() {
        return gradingScale;
    }

    public static void setGradingScale(String[] gradingScale) {
        GradeConverter.gradingScale = gradingScale;
    }

    public static int gradeToInt(String grade) {
        for (int i = 0; i < gradingScale.length; i++) {
                if (gradingScale[i].equals(grade)) {
                    return i;
                }
        }
        return  -1;
    }

    public static String intToGrade(int grade) {
        try {
            return gradingScale[grade];
        } catch (Exception e) {
            return "";
        }
    }
}