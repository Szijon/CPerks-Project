package test.server.ririyo.cPerks.perks.features;

public class TimeHandler {

        ///USED FOR FORMATTING TIME FOR THE FLIGHT TIME FEATURE
    public static int[] getTime(int timeInSeconds){
        int days = timeInSeconds / 86400;
        int hours = timeInSeconds % 86400 / 3600;
        int minutes = (timeInSeconds % 3600) / 60;
        int seconds = timeInSeconds % 60;

        return new int[]{days, hours, minutes, seconds};
    }
}
