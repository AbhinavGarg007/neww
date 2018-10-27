package com.example.emp354.linear.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {

    public static HashMap<String,List<String>> getData()
    {
        HashMap<String,List<String>> expandableListDetail=new HashMap<String,List<String>>();

        List<String> cricket=new ArrayList<String>();
        cricket.add("INDIA");
        cricket.add("PAKISTAN");
        cricket.add("AUS");
        cricket.add("ENG");
        cricket.add("SA");

        List<String> football=new ArrayList<String>();
        football.add("Brazil");
        football.add("Spain");
        football.add("Germany");
        football.add("Netherlands");
        football.add("Italy");

        List<String> basketBall=new ArrayList<String>();
        basketBall.add("US");
        basketBall.add("SPAIN");
        basketBall.add("ARG");
        basketBall.add("FRANCE");
        basketBall.add("RUSSIA");

        expandableListDetail.put("CRICKET TEAMS",cricket);
        expandableListDetail.put("FOOTBALL TEAMS",football);
        expandableListDetail.put("BASKETBALL TEAMS",basketBall);
        return expandableListDetail;
    }
}
