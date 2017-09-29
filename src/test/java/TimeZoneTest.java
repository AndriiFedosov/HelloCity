
import org.junit.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Locale;
import java.util.logging.Logger;


public class TimeZoneTest {
    private ChoseTimeZone chose;
    @Before
    public void setUp(){
      chose = new ChoseTimeZone();
    }

    @Test
    public void testContinent() throws Exception{
        StringBuilder st = chose.setContinent("us");
        Assert.assertEquals("Must add continent", String.valueOf(st), "US");
    }
    @Test
    public void testCity() {
       StringBuilder st =chose.setCity("new york");
       Assert.assertEquals("some went wrong,rename city", String.valueOf(st), "New_York");
    }
    @Test
    public void testClearTimeZone(){
        StringBuilder st1 = new StringBuilder("America");
        StringBuilder st2 = new StringBuilder("New_York");
        String st = chose.clearTimezone(st1,st2);
        Assert.assertEquals("Search wrong string", st, "America/New_York");
    }
    @Test
    public void testEqualZone(){
        String city ="New_York";
        String zone = "us/New_York";
        String utc = chose.equalsZone(zone,city);
        Assert.assertEquals("Country is wrong",utc,"America/New_York");
    }
    @Test
    public void testEqualGMT(){
        int gmt = -5;
        String st = chose.equalsGMT(gmt);
        Assert.assertEquals("Wrong integer",st,"GMT-5");
    }
    @Test
    public void testChoseTimeZone(){
        String utc = "America/New_York";
        int result = chose.choseTimeZone(utc);
        Assert.assertEquals("Wrong timezone",result,result);
    }

    @Test
    public void helloEnRuTest(){
        int result = 13;
        String text = chose.localRuEn(13,new StringBuilder("New York"));
        if(Locale.getDefault().getDisplayLanguage().equals("русский")||Locale.getDefault().getDisplayLanguage().equals("українська")){
            Assert.assertEquals("Aller!!!",text,"Доброго дня,New York!");
        }
        else {
            Assert.assertEquals("Aller!!!",text,"Good day,New York!");
        }
    }
}
