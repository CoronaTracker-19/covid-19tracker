package com.covid19tracker.Driver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.covid19tracker.Model.Countries;
import com.covid19tracker.Model.DaywiseId;
import com.covid19tracker.Model.Daywise;
import com.covid19tracker.Model.Total;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Component
public class DriverImpl implements Driver {

	private List<Countries> list;
	private Total total;
//	private Total total;
	

	
	private StringBuffer response=new StringBuffer();
	private List<List<Daywise>> listOfListDaywise=new ArrayList<List<Daywise>>();
	
	@Override
	public List<Countries> driverCountries() {
			//System.out.println("***********in driver***********");
		try {	

			String url = "https://corona.lmao.ninja/v2/countries";
			String info=jsonToString(url);
	        
	        ObjectMapper mapper=new ObjectMapper();
	        list=mapper.readValue(info, TypeFactory.defaultInstance().constructCollectionType(List.class, Countries.class));
	 /*       
	        for(Countries countries: list)
	        {
	        	System.out.println("Country: "+countries.getCountry());
	        	System.out.println("Cases: "+countries.getCases());
	        	System.out.println("TodayCases: "+countries.getTodayCases());
	        	System.out.println("Deaths: "+countries.getDeaths());
	        	System.out.println("Recovered: "+countries.getRecovered());
	        	System.out.println("TodayRecovered: "+countries.getTodayRecovered());
	        	System.out.println("Active: "+countries.getActive());
	        	System.out.println("*************");
	        }
	        System.out.println("Number of countries: "+list.size());
	*/        
		}
	    catch (Exception e) {
	        e.printStackTrace();
	    }
		//System.out.println("**************out of driver*********************");
		return list;
	}

	@Override
	public List<List<Daywise>> driverDaywise() {
		//********************* Reading JSON data from URL and getting String***************
			try
			{
				
				String url="https://corona.lmao.ninja/v3/covid-19/historical";
				String info=jsonToString(url);	
				
				Object obj=new JSONParser().parse(response.toString());
				
				JSONArray ja= (JSONArray) obj;
				System.out.println(" Number of countries ="+ja.size());
				
				JSONObject joCountry;
				JSONObject joTimeline;
				List<String> listCountry=new ArrayList<>();
				List<Integer> listCases;
				List<Date> listDates;
				List<Integer> listRecovered;
				List<Integer> listDeaths;
				
				for(int i=0; i<ja.size(); i++)
				{	
					joCountry=(JSONObject) ja.get(i);
					
					// *****************Storing country data in Daywise*******************
					
							
					listCountry.add(joCountry.get("country").toString());
					
					joTimeline= (JSONObject) joCountry.get("timeline");
					
					//********************Converting JSONObject to Map*****************
					
					Map cases=((Map)joTimeline.get("cases"));			
					Iterator<Map.Entry> itr1= cases.entrySet().iterator();
					
					//*********************Storing date and case data******************
					
					listCases=new ArrayList<Integer>();
					
					listDates=new ArrayList<Date>();

					while(itr1.hasNext())
					{
						
						Map.Entry pair=itr1.next();
					
						Date date1=new SimpleDateFormat("M/dd/yy").parse(pair.getKey().toString());	
						listDates.add(date1);
						listCases.add(Integer.parseInt((pair.getValue().toString())));

					}
					Collections.sort(listDates);
					
					List<Integer> listCasesDiff=calculateDiff(listCases);
					Map recovered=(Map) joTimeline.get("recovered");
					Iterator<Map.Entry> itr2= recovered.entrySet().iterator();
					
					// ************************Storing recovered data ************************
					
					listRecovered=new ArrayList<Integer>();
					
					while(itr2.hasNext())
					{
						Map.Entry pair=itr2.next();			
						listRecovered.add(Integer.parseInt(pair.getValue().toString()));
					}
					List<Integer> listRecoveredDiff=calculateDiff(listRecovered);
					Map deaths=(Map) joTimeline.get("deaths");			
					Iterator<Map.Entry> itr3=deaths.entrySet().iterator();
					
					//******************************Storing death data*********************		
					
					listDeaths=new ArrayList<Integer>();			
					while(itr3.hasNext())
					{
						Map.Entry pair=itr3.next();			
						listDeaths.add(Integer.parseInt(pair.getValue().toString()));
					}
					List<Integer> listDeathsDiff=calculateDiff(listDeaths);
					Collections.sort(listCases);
					Collections.sort(listRecovered);
					Collections.sort(listDeaths);
					List<Daywise> listDaywise=storeModel( i, listCountry, listDates, listCasesDiff, listRecoveredDiff, listDeathsDiff,listCases,listRecovered,listDeaths);
					listOfListDaywise.add(listDaywise);
				}	
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}		
				return listOfListDaywise;
	}
	
	public List<Integer> calculateDiff(List<Integer> listSort){
		
		Collections.sort(listSort);
		List<Integer> listDiff=new ArrayList<Integer>();
		int m=0,n=0,p=0;
		for(int j=0; j<listSort.size(); j++) {
			
			m=listSort.get(j);
			n=m-p;
			listDiff.add(n);
			p=listSort.get(j);
		}
		return listDiff;
	}
	
	public List<Daywise> storeModel(int i, List<String> listCountry, List<Date> listDates, List<Integer> listCasesDiff, List<Integer> listRecoveredDiff, List<Integer> listDeathsDiff,List<Integer> listCases,List<Integer> listRecovered,List<Integer> listDeaths)
	{
		List<Daywise> listDaywise=new ArrayList<Daywise>();	
//		int k=0;
//		int q=0;
//		int p=0;
//		Date date=java.util.Calendar.getInstance().getTime();
//		System.out.println("***********start loop***********"+date);
		for(int j=1; j<=listDates.size()-1; j++)
		{
//			p=listCasesDiff.get(j)+q;
//			System.out.println("***********in loop***********"+date);
			Daywise daywise=new Daywise();
			DaywiseId daywiseid=new DaywiseId();
//			SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yy");
			daywise=new Daywise();
			
			daywiseid.setCountry(listCountry.get(i));
			daywiseid.setDates(listDates.get(j));
			daywise.setDaywiseid(daywiseid);
			daywise.setCases(listCasesDiff.get(j));	
			daywise.setRecovered(listRecoveredDiff.get(j));
			daywise.setDeaths(listDeathsDiff.get(j));
			daywise.setTotalCasesBegining(listCases.get(j));
			daywise.setTotalRecoveredBegining(listRecovered.get(j));
			daywise.setTotalDeathsBegining(listDeaths.get(j));
			
			listDaywise.add(daywise);
//			k=k+1;
//			q=p;
		}
//		System.out.println("***********end loop***********"+date);
		System.out.println("listCasesDiff :"+listCasesDiff);
		return listDaywise;
	}
	@Override
	public Total driverTotal()
	{
		System.out.println("***********in driver***********");
	try {	

		String url = "https://corona.lmao.ninja/v2/all";
		String info=jsonToString(url);

        
        ObjectMapper mapper=new ObjectMapper();
        total=mapper.readValue(info, Total.class);

	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	System.out.println("*****************out driver*******************");
	return total;
	
	}
	
	public String jsonToString(String url)
	{
		try
		{
		
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.connect();
        int responseCode=con.getResponseCode();
        response = new StringBuffer();
        if(responseCode!=200)
        	throw new RuntimeException("HttpResponseCode"+responseCode);        
        else
        { 
        	BufferedReader in = new BufferedReader(
        			new InputStreamReader(con.getInputStream()));
        	
        	String inputLine="";
        	while ((inputLine = in .readLine()) != null)
        	{
        		response.append(inputLine);
        	}
        	in .close();
        }
    //  System.out.println(response.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
      return response.toString();
	}
}
