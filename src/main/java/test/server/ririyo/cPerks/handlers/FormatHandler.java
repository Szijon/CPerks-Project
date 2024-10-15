package test.server.ririyo.cPerks.handlers;

public class FormatHandler {

        ///USED FOR FORMATTING TIME FOR THE FLIGHT TIME FEATURE
    public static int[] getTime(int timeInSeconds){
        int days = timeInSeconds / 86400;
        int hours = timeInSeconds % 86400 / 3600;
        int minutes = (timeInSeconds % 3600) / 60;
        int seconds = timeInSeconds % 60;

        return new int[]{days, hours, minutes, seconds};
    }
        ///USED TO CONVERT STRING EX: 'silk_touch', 'cave_spider' to 'Silk Touch', 'Cave Spider'
    public static String convertString(String initial){
        char underscore = '_';
        for(int i = 0; i < initial.length(); i++){
            ///NO INPUT WITH MORE THAN 2 WORDS EXPECTED.
            if(initial.charAt(i) == underscore){
                String firstWord = initial.substring(0, 1).toUpperCase() + initial.substring(1, i).toLowerCase();
                String secondWord = initial.substring(i + 1, i + 2).toUpperCase() + initial.substring(i + 2).toLowerCase();
                return firstWord + " " + secondWord;
            }
        }
        ///RETURNS FORMAT IF ONLY 1 WORD
        return initial.substring(0, 1).toUpperCase() + initial.substring(1).toLowerCase();
    }
}
