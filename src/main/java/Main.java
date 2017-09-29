
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;


public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ChoseTimeZone.class);
    public static void main(String[] args) {

        //create all constructors
        ChoseTimeZone chose = new ChoseTimeZone();
        //Chose Continent
        System.out.println("Enter your continent:");
        String continent = sc.nextLine();
        StringBuilder continentBuild = new StringBuilder(chose.setContinent(continent));


        //Chose City
        System.out.println("Enter your city:");
        String city = sc.nextLine();
        StringBuilder cityBuild = new StringBuilder(chose.setCity(city));


        //build and clear time zone
        String timezone =  chose.clearTimezone(continentBuild,cityBuild);



        //chose current timezone from lib
        String currentTimeZone = chose.equalsZone(timezone,city);

        //review timezone
        String time="";
        if (currentTimeZone.equals("")){
            System.out.println("Oh,Okay...Enter your GMT on integer from -12 to +12:");
            try {
                int gtm = sc.nextInt();
                time = chose.equalsGMT(gtm);
            }
            catch (InputMismatchException e){
                logger.info("You entered wrong number");
                System.out.println("You entered wrong number\n" +
                        "We can`t locate your position");
                System.exit(0);
            }
        }
        else {

            time = currentTimeZone;
        }

        //Create timezone from lib
        int result = chose.choseTimeZone(time);
        //chose locale
        String hello = chose.localRuEn(result,cityBuild);

        System.out.println(hello);
    }
}
