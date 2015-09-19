package kms.test;

import android.os.StrictMode;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by KMS on 2015-02-15.
 */
public class Parser {
    private ArrayList<MapList> wtlist;


    public ArrayList<MapList> weatherList(){
        String address = "http://14.39.145.16:8080/moneyball/weatherXml2.jsp";
        ArrayList<String> temper = new ArrayList<String>();
        ArrayList<String> humi = new ArrayList<String>();
        ArrayList<String> cloud = new ArrayList<String>();
        temper.add("example:5");
        humi.add("example:30%");
        cloud.add("example:sunny");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            URL url = new URL(address);
            String tag;
            String context;
            int eventType=0 ;
            XmlPullParserFactory k = XmlPullParserFactory.newInstance();
            XmlPullParser parser = k.newPullParser();

            if (parser != null) {
                wtlist = new ArrayList<MapList>();
                Log.d("XMLParser", "Paser Object is OK");
                InputStream stream = url.openStream();
                Log.d("XMLParser", "InputStream OK");
                parser.setInput(stream, "UTF-8");
                Log.d("XMLParser", "setinput OK");
            } else {
                wtlist = null;
                Log.d("XMLParser", "Paser Object is null");
            }
            assert parser != null;
            eventType = parser.getEventType();
            Log.d("XMLParser", "getEventType OK");
            boolean identifyTag1=false,identifyTag2=false,identifyTag3=false;

            while(eventType != XmlPullParser.END_DOCUMENT){
                Log.d("XMLParser", "Entering While sentence ");
                switch(eventType){
                    case XmlPullParser.START_DOCUMENT:
                        Log.d("XMLParser", "Start Doc");
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        Log.d("XMLParser", "End Doc");
                        break;
                    case XmlPullParser.START_TAG:{
                        tag = parser.getName();
                        Log.d("XMLParser", "Start_Tag Enter");
                        if (tag.equals("temper")) {
                            identifyTag1 = true;
                            Log.d("XMLParser", "temper_Tag Find");
                        } else if (tag.equals("humidity")) {
                            identifyTag2 = true;
                        } else if (tag.equals("clouds")) {
                            identifyTag3 = true;
                        }
                        break;}
                    case XmlPullParser.END_TAG:
                        identifyTag1=false;
                        identifyTag2=false;
                        identifyTag3=false;
                        break;
                    case XmlPullParser.TEXT:{
                        Log.d("XMLParser", "In Text Event ");
                        context=parser.getText();
                        if(identifyTag1){
                            temper.add(context);
                            Log.d("XMLParser", "Get text in temper");}
                        else if(identifyTag2)
                            humi.add(context);
                        else if(identifyTag3)
                            cloud.add(context);
                        break;}
                    }
                    eventType = parser.next();
                }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            Log.d("XMLParser", "XmlPullParserException");
        } catch(Exception e){
            e.printStackTrace();
            Log.d("XMLParser", "Exception");
        }

        for (int loop = 0; loop < temper.size(); loop++) {
            wtlist.add(new MapList(temper.get(loop), humi.get(loop), cloud.get(loop)));
        }
        Log.d("XMLParser", "End of Parser");


        return wtlist;
    }
}
