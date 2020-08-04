package com.covid19tracker.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




import com.covid19tracker.Model.Countries;
import com.covid19tracker.Model.Daywise;
import com.covid19tracker.Model.Monthly;
import com.covid19tracker.Model.Total;
import com.covid19tracker.Model.Weekly;
import com.covid19tracker.repository.CountryRepository;
import com.covid19tracker.repository.DatewiseRepository;
import com.covid19tracker.repository.TotalsRepository;


@Service
public class ServiceImpl implements APIService {

	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private DatewiseRepository datewiseRepository;
	
	@Autowired
	private TotalsRepository totalsRepository;
	
	
	
	
	
	@Override
	@Transactional
	public void setAllCountries(List<Countries> listCountries) {
		
		for(Countries countries: listCountries)
			countryRepository.save(countries);
		
	}
	
	@Override
	@Transactional
	public void setAllDaywise(List<List<Daywise>> listOfListDaywise) {
		System.out.println("************in Service*************");
		for(List<Daywise> listDaywise: listOfListDaywise)
		{
			for(Daywise daywise: listDaywise)
			{	
				datewiseRepository.save(daywise);
			}
		}
		System.out.println("************out Service*************");

	}
	
	@Override
	@Transactional
	public void setAllTotal(Total total) {
		total.setLocalDateTime(LocalDateTime.now());
		totalsRepository.save(total);
	}
	
	@Override
	@Transactional
	public List<Countries> getAll(String parameter, String order, int pageNumber, int pageSize,String country) {
//		parameter=parameter.toLowerCase();
		Page<Countries> pagelist;
		if(country.contentEquals("all"))
		{
		if(order.equalsIgnoreCase("ASC"))
		{
			Sort sort=Sort.by(Sort.Direction.ASC, parameter);
			Pageable page=PageRequest.of(pageNumber, pageSize, sort);
			pagelist= countryRepository.findAll(page);
			return pagelist.getContent(); 
		}
		else
		{
			Sort sort=Sort.by(Sort.Direction.DESC, parameter);
			Pageable page=PageRequest.of(pageNumber, pageSize, sort);
			pagelist=countryRepository.findAll(page);
			return pagelist.getContent();
		}
		}
		else
		{	
			List<Countries> list=new ArrayList<>();
			countryRepository.findById(country).ifPresent(list::add);;
			return list;
		}
	}


	@Override
	@Transactional
	public List<Daywise> getDaywise(String countryname, String dates,String month,String year) throws ParseException {
		if(countryname.equalsIgnoreCase("all") && dates.equalsIgnoreCase("empty") && month.equalsIgnoreCase("empty"))
		{
			System.out.println("Dates:: "+dates);
			return datewiseRepository.findAll();
		}	
		
		else if(dates.equalsIgnoreCase("empty") && month.equalsIgnoreCase("empty")) {
			System.out.println("Country: "+countryname);
			return datewiseRepository.findByCountry(countryname);
		}
		else if(dates.equalsIgnoreCase("empty") && countryname.equalsIgnoreCase("all")){
			
			System.out.println(" month: "+month);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date=new SimpleDateFormat("yyyy-MMM-dd").parse(year+"-"+month+"-01");
			Date date2=new SimpleDateFormat("yyyy-MMM-dd").parse(year+"-"+month+"-31");
			String date1=sdf.format(date);
			String date21=sdf.format(date2);
				
				System.out.println(date1);
			return datewiseRepository.findByMonth(date1, date21);
		}
		else if(dates.equalsIgnoreCase("empty"))
		{
			System.out.println("Country and month: "+countryname+month);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date=new SimpleDateFormat("yyyy-MMM-dd").parse(year+"-"+month+"-01");
			Date date2=new SimpleDateFormat("yyyy-MMM-dd").parse(year+"-"+month+"-31");
			String date1=sdf.format(date);
			String date21=sdf.format(date2);
				
				System.out.println(date1);
			return datewiseRepository.findByCountryAndMonth(countryname,date1,date21);
		}
		
		else if(countryname.equalsIgnoreCase("all") && month.equalsIgnoreCase("empty")){
		
			System.out.println("Dates: "+dates);
			return datewiseRepository.findByDates(dates);
		}
		else if(countryname.equalsIgnoreCase("all")) {
			
			System.out.println("Dates:::: "+dates);
			return datewiseRepository.findByDates(dates);
		}
		else
		{
			System.out.println("Dates::: "+dates);
			return datewiseRepository.findByCountryAndDates(countryname, dates);
		}
		
	}


	@Override
	@Transactional
	public List<Monthly> getMonthly(String countryname, String month,String year) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date=new SimpleDateFormat("yyyy-MMM-dd").parse(year+"-"+month+"-01");
		Date date2=new SimpleDateFormat("yyyy-MMM-dd").parse(year+"-"+month+"-31");
		String date1=sdf.format(date);
		String date21=sdf.format(date2);
		System.out.println(date1);
		System.out.println(date21);
		List<Daywise> monthly =datewiseRepository.findByCountryAndMonth(countryname,date1,date21);
		List<Monthly> finalMonth=setMonthRecord(monthly,month);
		return finalMonth;
		
	}
	
	public List<Monthly> setMonthRecord(List<Daywise> listMon,String month){
	List<Integer> daywiseCases=new ArrayList<Integer>();
	List<Integer> daywiseRecovered=new ArrayList<Integer>();
	List<Integer> daywiseDeaths=new ArrayList<Integer>();
	List<String> countryName=new ArrayList<String>();
	
	 
			for(Daywise daywise: listMon)
			{	
				
				daywiseCases.add(daywise.getCases());
				daywiseRecovered.add(daywise.getRecovered());
				daywiseDeaths.add(daywise.getDeaths());
				countryName.add(daywise.getDaywiseid().getCountry());
			
			}
			
			
	List<Monthly> monthTotal=calculateMonth(daywiseCases,daywiseRecovered,daywiseDeaths,countryName,month);
	
	return monthTotal;
	}
	public List<Monthly> calculateMonth(List<Integer> daywiseCases,List<Integer> daywiseRecovered,List<Integer> daywiseDeaths,List<String> countryName,String month){
		List<Integer> monthCases=new ArrayList<Integer>();
		List<Integer> monthRecovered=new ArrayList<Integer>();
		List<Integer> monthDeaths=new ArrayList<Integer>();
		
		int q=0,q1=0,q2=0;
		int p=0,p1=0,p2=0;
		for(int j=0; j<daywiseCases.size(); j++)
		{
			p=daywiseCases.get(j)+q;
			p1=daywiseRecovered.get(j)+q1;
			p2=daywiseDeaths.get(j)+q2;
			monthCases.add(p);
			monthRecovered.add(p1);
			monthDeaths.add(p2);
			q=p;
			q1=p1;
			q2=p2;
			}
		List<Monthly> listMonthly=new ArrayList<Monthly>();	
		int size=monthCases.size()-1;
//		for(int j=0; j<casesmdif.size(); j++)
//		{
			Monthly theMonth=new Monthly();
			theMonth.setCountryName(countryName.get(0));
			theMonth.setMonth(month);
			theMonth.setThisMonthCases(monthCases.get(size));
			theMonth.setThisMonthRecovered(monthRecovered.get(size));
			theMonth.setThisMonthDeaths(monthDeaths.get(size));
			listMonthly.add(theMonth);
//		}
	return listMonthly;

	}

	@Override
	@Transactional
	public List<Total> getAllTotal() {
		return totalsRepository.findFirstByOrderByLocalDateTimeDesc();
	}

	@Override
	@Transactional
	public List<Weekly> getWeekly(String countryname, int month, int year, int weekno) throws ParseException {
		
		String a,b;
		List<String> weekStartDates =new ArrayList<String>();
		List<String> weekEndDates = new ArrayList<String>();
		List<Daywise> weekly;
		List<Weekly> finalWeek = null;
		YearMonth ym = YearMonth.of(year , month);
		System.out.println(ym);
		LocalDate firstOfMonth = ym.atDay(1);
		TemporalAdjuster ta = TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY);
		LocalDate previousOrSameMonday = firstOfMonth.with(ta) ;
		LocalDate endOfMonth = ym.atEndOfMonth();
		LocalDate weekStart = previousOrSameMonday; 
		do {
		    LocalDate weekStop = weekStart.plusDays(6);
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    System.out.println("Week: " + weekStart + " to " + weekStop) ;
		    weekStartDates.add(weekStart.format(formatter));
		    weekEndDates.add(weekStop.format(formatter));
		    weekStart = weekStart.plusWeeks(1) ;
		} while (! weekStart.isAfter(endOfMonth)) ;
		
			a=weekStartDates.get(weekno-1);
			b=weekEndDates.get(weekno-1);
			weekly=datewiseRepository.findByCountryAndMonth(countryname,a , b);
			finalWeek=setWeekRecord(weekly,month,weekno,a,b);

		return finalWeek;
	}

	public List<Weekly> setWeekRecord(List<Daywise> weekly,int month,int weekno,String weekStart,String weekEnd){
		List<Integer> weeklyCases=new ArrayList<Integer>();
		List<Integer> weeklyRecovered=new ArrayList<Integer>();
		List<Integer> weeklyDeaths=new ArrayList<Integer>();
		List<String> countryName=new ArrayList<String>();
		
		 
				for(Daywise daywise: weekly)
				{	
					
					weeklyCases.add(daywise.getCases());
					weeklyRecovered.add(daywise.getRecovered());
					weeklyDeaths.add(daywise.getDeaths());
					countryName.add(daywise.getDaywiseid().getCountry());
				
				}
				
				
		List<Weekly> weeklyTotal=calculateWeek(weeklyCases,weeklyRecovered,weeklyDeaths,countryName,month,weekno,weekStart,weekEnd);
		
		return weeklyTotal;
		}
	
	public List<Weekly> calculateWeek(List<Integer> weeklyCases,List<Integer> weeklyRecovered,List<Integer> weeklyDeaths,List<String> countryName,int month,int weekno,String weekStart,String weekEnd){
		List<Integer> weeklyTotalCases=new ArrayList<Integer>();
		List<Integer> weeklyTotalRecovered=new ArrayList<Integer>();
		List<Integer> weeklyTotalDeaths=new ArrayList<Integer>();
		
		int q=0,q1=0,q2=0;
		int p=0,p1=0,p2=0;
		for(int j=0; j<weeklyCases.size(); j++)
		{
			p=weeklyCases.get(j)+q;
			p1=weeklyRecovered.get(j)+q1;
			p2=weeklyDeaths.get(j)+q2;
			weeklyTotalCases.add(p);
			weeklyTotalRecovered.add(p1);
			weeklyTotalDeaths.add(p2);
			q=p;
			q1=p1;
			q2=p2;
			}
		List<Weekly> listWeekly=new ArrayList<Weekly>();	
		int size=weeklyTotalCases.size()-1;
//		for(int j=0; j<casesmdif.size(); j++)
//		{
			Weekly theWeekly=new Weekly();
			theWeekly.setCountryName(countryName.get(0));
			theWeekly.setMonth(month);
			theWeekly.setWeekNo(weekno);
			theWeekly.setWeekStartDate(weekStart);
			theWeekly.setWeekEndDate(weekEnd);
			theWeekly.setTotalCases(weeklyTotalCases.get(size));
			theWeekly.setTotalRecovered(weeklyTotalRecovered.get(size));
			theWeekly.setTotalDeaths(weeklyTotalDeaths.get(size));
			listWeekly.add(theWeekly);
//		}
	return listWeekly;

	}

}
