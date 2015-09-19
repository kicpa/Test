package kms.test;

/**
 * Created by KMS on 2015-02-15.
 */
public class MapList {
    private String temper, humi, cloud;

    public MapList(){}

    public MapList(String temper, String humi, String cloud){
        this.temper = temper;
        this.humi = humi;
        this.cloud = cloud;


    }
    public void setTemper(String temper){
        this.temper = temper;
    }
    public String getTemper(){
        return temper;
    }

    public void setHumi(String humi){
        this.humi = humi;
    }
    public String getHumi(){
        return humi;
    }


    public void setColud(String cloud){
        this.cloud = cloud;
    }
    public String getCloud(){
        return cloud;
    }



}



