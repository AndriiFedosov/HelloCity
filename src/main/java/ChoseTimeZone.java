import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

class ChoseTimeZone {
    private static Logger logger = Logger.getLogger(ChoseTimeZone.class);


    StringBuilder setContinent(String continent){
        if(continent.length()<=3 && !continent.equalsIgnoreCase("Ect")){
            continent = continent.toUpperCase();
        }
        else {
            continent = continent.substring(0,1).toUpperCase() + continent.substring(1);
        }
        logger.info("Your continent is:"+continent);
        return new StringBuilder(continent);
    }

    StringBuilder setCity(String city){

        if(!city.equals("")){
            city = city.substring(0,1).toUpperCase() + city.substring(1);
            char [] arr = {' '};
            if (!String.valueOf(city).equals("")){
                StringBuilder cityTemp = new StringBuilder(city);
                for (int i = 1; i < city.length() - 1; i++) {
                    for (char ch : arr) {
                        if (city.charAt(i) == ch) {
                            String up = String.valueOf(city.substring(i+1,i+2).toUpperCase());
                            cityTemp.setCharAt(i,'_');
                            cityTemp.replace(i+1,i+2,up);

                        }
                    }
                }
                city=String.valueOf(cityTemp);
            }
            ;
        }
        else city="";
        logger.info("Your city is:"+city);
        return new StringBuilder(city);
    }


    String clearTimezone(StringBuilder continent, StringBuilder city){
        char []arr = {' '};
        for (int i = 1; i < continent.length() - 1; i++) {
            for (char ch : arr) {
                if (continent.charAt(i) == ch) {
                    continent.deleteCharAt(i);
                }
            }
        }


        StringBuilder st= new StringBuilder();
        if(!String.valueOf(city).equals("")){
            st.append(continent).append("/").append(city);
        }
        else {
            st.append(continent);
        }
        logger.info("Clear timezone name:"+st);
        return new String(st);
    }


    String equalsZone(String zone,String city){
        String [] strings = TimeZone.getAvailableIDs();
        String utc = null;
        p:for (int i =0; i<=strings.length-1; i++){
            if(strings[i].equals(zone)){
                utc=strings[i];
            }
            else {
                StringBuilder st = new StringBuilder(strings[i]);
                for (int j = 0 ;j<=st.length()-1;j++ ){
                    if(String.valueOf(st.charAt(j)).equals("/")){
                        String stTemp = st.substring(j+1);
                        if(stTemp.equals(city)){
                           utc = strings[i];
                           break p;
                        }
                    }

                }
            }
        }
        if(utc==null){
            utc="";
        }
        System.out.println("Maybe you mean :"+utc);
        logger.info("Current timezone is:"+utc);
        return utc;
    }

    String equalsGMT(int gtm){

        String timezone= "";
        if(gtm>=0&&gtm<=12) {
            timezone = "GMT+"+gtm;
            logger.info("Getting GTM:"+timezone);
            return timezone;
        }
        else if(-12<=gtm && gtm<=0) {
            timezone= "GMT"+gtm;
            logger.info("Getting GTM:"+timezone);
            return timezone;
        }
        else {

            return "Wrong UTC,enter current UTC";
        }

    }

    int choseTimeZone(String utc){
        logger.info("Chose Timezone for list:"+utc);
        String time ="";
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH");

        dateFormat.setTimeZone(TimeZone.getTimeZone(utc));
        time = dateFormat.format(new Date());
        return Integer.parseInt(String.valueOf(time));
    }



    String localRuEn(int result, StringBuilder city){

        String [] daynight;
        String text="";

        if((Locale.getDefault().getDisplayLanguage().equals("русский")||Locale.getDefault().getDisplayLanguage().equals("українська"))){
           daynight = new String[]{"Доброго дня", "Доброго дня", "Доброго вечера", "Доброй ночи"};
            logger.info("Current language on computer Russia or Ukraine, create message hello on russian");
        }
        else{
            daynight =new String[] {"Good morning","Good day","Good evening","Good night"};
            logger.info("Current language on computer is russian or ukrainian,crete message hello on english");
        }
        if(6<=result &&result<=9)

        {
            text = daynight[0] + "," + city + "!";
        }
        else if(9<=result&&result<=19)

        {
            text = daynight[1] + "," + city + "!";
        }
        else if(19<=result&&result<=23)

        {
            text = daynight[2] + "," + city + "!";
        }
        else if(23<=result &&result<=24)

        {
            text = daynight[3] + "," + city + "!";
        }
        else if(0<=result &&result<=6)

        {
            text = daynight[3] + "," + city + "!";
        }
        logger.info(text+"\n");

        return text;
    }
}
