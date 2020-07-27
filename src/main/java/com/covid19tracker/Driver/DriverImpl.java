package com.covid19tracker.Driver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import com.covid19tracker.Model.Countries;
import com.covid19tracker.Model.Daywise;
import com.covid19tracker.Model.Total;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Component
public class DriverImpl implements Driver {

	private List<Countries> list;
	private Total total;
	private StringBuffer response=new StringBuffer();
	private List<List<Daywise>> listOfListDaywise=new ArrayList<List<Daywise>>();
	
	@Override
	public List<Countries> driverCountries() {
			//System.out.println("***********in driver***********");
		try {	
			String url = "https://corona.lmao.ninja/v2/countries";
	        URL obj = new URL(url);
	        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	        con.setRequestMethod("GET");
	        con.connect();
	        int responseCode=con.getResponseCode();
	        StringBuffer response = new StringBuffer();
	        if(responseCode!=200)
	        	throw new RuntimeException("HttpResponseCode"+responseCode);        
	        else
	        { 
	        	BufferedReader in = new BufferedReader(
	        			new InputStreamReader(con.getInputStream()));
	        	String inputLine;
	        
	        	while ((inputLine = in .readLine()) != null)
	        	{
	        		response.append(inputLine);
	        	}
	        	in .close();
	        }
	       // System.out.println(response.toString());
	        
	        ObjectMapper mapper=new ObjectMapper();
	        list=mapper.readValue(response.toString(), TypeFactory.defaultInstance().constructCollectionType(List.class, Countries.class));
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
				URL o=new URL(url);
				HttpURLConnection con=(HttpURLConnection) o.openConnection();
				con.setRequestMethod("GET");
				con.connect();
				int responseCode=con.getResponseCode();
				
				if(responseCode!=200)
					throw new RuntimeException(" Http response Code: "+responseCode);
				else
				{
					BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
					String inputLine;
					while((inputLine=br.readLine()) != null)
					{
						response.append(inputLine);
					}
				//System.out.println(response.toString());
				}
				
				Object obj=new JSONParser().parse(response.toString());
				
				JSONArray ja= (JSONArray) obj;
				System.out.println(" Number of countries ="+ja.size());
				
				JSONObject joCountry;
				JSONObject joTimeline;
				List<String> listCountry;
				listCountry=new ArrayList<>();	
				List<String> listCases;
				List<String> listDates;
				List<String> listRecovered;
				List<String> listDeaths;
				
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
					
					listCases=new ArrayList<String>();			
					listDates=new ArrayList<String>();
					
					while(itr1.hasNext())
					{
						Map.Entry pair=itr1.next();						
						listDates.add(pair.getKey().toString());				
						listCases.add(pair.getValue().toString());
					}
					
					Map recovered=(Map) joTimeline.get("recovered");
					Iterator<Map.Entry> itr2= recovered.entrySet().iterator();
					
					// ************************Storing recovered data ************************
					
					listRecovered=new ArrayList<String>();
					
					while(itr2.hasNext())
					{
						Map.Entry pair=itr2.next();			
						listRecovered.add(pair.getValue().toString());
					}
					
					Map deaths=(Map) joTimeline.get("deaths");			
					Iterator<Map.Entry> itr3=deaths.entrySet().iterator();
					
					//******************************Storing death data*********************		
					
					listDeaths=new ArrayList<String>();			
					while(itr3.hasNext())
					{
						Map.Entry pair=itr3.next();			
						listDeaths.add(pair.getValue().toString());
					}
					System.out.println("i= "+i);
					List<Daywise> listDaywise=storeModel( i, listCountry, listDates, listCases, listRecovered, listDeaths);
					listOfListDaywise.add(listDaywise);
				}	
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}		
				return listOfListDaywise;
	}
	
	public List<Daywise> storeModel(int i, List<String> listCountry, List<String> listDates, List<String> listCases, List<String> listRecovered, List<String> listDeaths)
	{
		List<Daywise> listDaywise=new ArrayList<Daywise>();		
		Daywise[] daywise=new Daywise[30];
		
		for(int j=0; j<listDates.size(); j++)
		{
			daywise[j]=new Daywise();
			daywise[j].setCountry(listCountry.get(i));
			daywise[j].setCases(Integer.parseInt(listCases.get(j)));
			daywise[j].setDates(listDates.get(j));
			daywise[j].setRecovered(Integer.parseInt(listRecovered.get(j)));
			daywise[j].setDeaths(Integer.parseInt(listDeaths.get(j)));
			listDaywise.add(daywise[j]);
		}
		
		return listDaywise;
	}
	@Override
	public Total driverTotal()
	{
		System.out.println("***********in driver***********");
	try {	
		String url = "https://corona.lmao.ninja/v2/all";
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
        	String inputLine;
        
        	while ((inputLine = in .readLine()) != null)
        	{
        		response.append(inputLine);
        	}
        	in .close();
        }
//       System.out.println(response.toString());
        
        ObjectMapper mapper=new ObjectMapper();
        total=mapper.readValue(response.toString(), Total.class);
        System.out.println(total.toString());
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	System.out.println("*****************out driver*******************");
	return total;
	
	}
}
