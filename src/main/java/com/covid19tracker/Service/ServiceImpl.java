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
import com.covid19tracker.Model.DaywiseResponse;
import com.covid19tracker.Model.Monthly;
import com.covid19tracker.Model.Total;
import com.covid19tracker.Model.TotalResponse;
import com.covid19tracker.Model.WeeklyResponse;
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
	public List<DaywiseResponse> getDaywise(String countryname, String dates,String month,String year) throws ParseException {
		if(countryname.equalsIgnoreCase("all") && dates.equalsIgnoreCase("empty") && month.equalsIgnoreCase("empty"))
		{
			System.out.println("Dates:: "+dates);
			List<Daywise> getDaily= datewiseRepository.findAll();
			List<DaywiseResponse> dayResponse=setDayResponse(getDaily);
			
			return dayResponse;
		}	
		
		else if(dates.equalsIgnoreCase("empty") && month.equalsIgnoreCase("empty")) {
			System.out.println("Country: "+countryname);
//			return datewiseRepository.findByCountry(countryname);
			
			List<Daywise> getDaily=datewiseRepository.findByCountry(countryname);
			List<DaywiseResponse> dayResponse=setDayResponse(getDaily);
			
			return dayResponse;
		}
		else if(dates.equalsIgnoreCase("empty") && countryname.equalsIgnoreCase("all")){
			
			System.out.println(" month: "+month);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date=new SimpleDateFormat("yyyy-MMM-dd").parse(year+"-"+month+"-01");
			Date date2=new SimpleDateFormat("yyyy-MMM-dd").parse(year+"-"+month+"-31");
			String date1=sdf.format(date);
			String date21=sdf.format(date2);
				
				System.out.println(date1);
//			return datewiseRepository.findByMonth(date1, date21);
				
				List<Daywise> getDaily=datewiseRepository.findByMonth(date1, date21);
				List<DaywiseResponse> dayResponse=setDayResponse(getDaily);
				
				return dayResponse;
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
//			return datewiseRepository.findByCountryAndMonth(countryname,date1,date21);
				
			List<Daywise> getDaily=datewiseRepository.findByCountryAndMonth(countryname,date1,date21);
			List<DaywiseResponse> dayResponse=setDayResponse(getDaily);
			
			return dayResponse;
		}
		
		else if(countryname.equalsIgnoreCase("all") && month.equalsIgnoreCase("empty")){
		
			System.out.println("Dates: "+dates);
//			return datewiseRepository.findByDates(dates);
			
			List<Daywise> getDaily=datewiseRepository.findByDates(dates);
			List<DaywiseResponse> dayResponse=setDayResponse(getDaily);
			
			return dayResponse;
		}
		else if(countryname.equalsIgnoreCase("all")) {
			
			System.out.println("Dates:::: "+dates);
//			return datewiseRepository.findByDates(dates);
			
			List<Daywise> getDaily=datewiseRepository.findByDates(dates);
			List<DaywiseResponse> dayResponse=setDayResponse(getDaily);
			
			return dayResponse;
		}
		else
		{
			System.out.println("Dates::: "+dates);
//			return datewiseRepository.findByCountryAndDates(countryname, dates);
			
			List<Daywise> getDaily=datewiseRepository.findByCountryAndDates(countryname, dates);
			List<DaywiseResponse> dayResponse=setDayResponse(getDaily);
			
			return dayResponse;
		}
		
	}

	
	public List<DaywiseResponse> setDayResponse(List<Daywise> getDaily) {
		List<DaywiseResponse> totalResponse=new ArrayList<DaywiseResponse>();
		for(Daywise daywise: getDaily)
		{
			DaywiseResponse theDaywiseResponse=new DaywiseResponse();
			theDaywiseResponse.setCountryName(daywise.getDaywiseid().getCountry());
			theDaywiseResponse.setDate(daywise.getDaywiseid().getDates());
			theDaywiseResponse.setCases(daywise.getCases());
			theDaywiseResponse.setRecovered(daywise.getRecovered());
			theDaywiseResponse.setDeaths(daywise.getDeaths());
			theDaywiseResponse.setTotalCasesFrmBegining(daywise.getTotalCasesBegining());
			theDaywiseResponse.setTotalRecoveredFrmBegining(daywise.getTotalRecoveredBegining());
			theDaywiseResponse.setTotalDeathsFrmBegining(daywise.getTotalDeathsBegining());
			totalResponse.add(theDaywiseResponse);
		}
		return totalResponse;
	}
	@Override
	@Transactional
	public Monthly getMonthly(String countryname, String month,String year) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date=new SimpleDateFormat("yyyy-MMM-dd").parse(year+"-"+month+"-01");
		Date date2=new SimpleDateFormat("yyyy-MMM-dd").parse(year+"-"+month+"-31");
		String date1=sdf.format(date);
		String date21=sdf.format(date2);
		System.out.println(date1);
		System.out.println(date21);
		List<Daywise> monthly =datewiseRepository.findByCountryAndMonth(countryname,date1,date21);
		Monthly finalMonth=setMonthRecord(monthly,month,countryname);
		return finalMonth;
		
	}
	
	public Monthly setMonthRecord(List<Daywise> listMon,String month,String countryname){
	List<Integer> daywiseCases=new ArrayList<Integer>();
	List<Integer> daywiseRecovered=new ArrayList<Integer>();
	List<Integer> daywiseDeaths=new ArrayList<Integer>();
//	List<String> countryName=new ArrayList<String>();
//	String countryName ="";
	
	 
			for(Daywise daywise: listMon)
			{	
				
				daywiseCases.add(daywise.getCases());
				daywiseRecovered.add(daywise.getRecovered());
				daywiseDeaths.add(daywise.getDeaths());
//				countryName.add(daywise.getDaywiseid().getCountry());
			
			}
			
			
	 Monthly monthTotal=calculateMonth(daywiseCases,daywiseRecovered,daywiseDeaths,month,countryname);
	
	return monthTotal;
	}
	public Monthly calculateMonth(List<Integer> daywiseCases,List<Integer> daywiseRecovered,List<Integer> daywiseDeaths,String month,String countryname){
	
		
		int q=0,q1=0,q2=0;
		int p=0,p1=0,p2=0;
		for(int j=0; j<daywiseCases.size(); j++)
		{
			p=daywiseCases.get(j)+q;
			p1=daywiseRecovered.get(j)+q1;
			p2=daywiseDeaths.get(j)+q2;
			
			q=p;
			q1=p1;
			q2=p2;
			}
//		List<Monthly> listMonthly=new ArrayList<Monthly>();	
	
//		for(int j=0; j<casesmdif.size(); j++)
//		{
			Monthly theMonth=new Monthly();
			theMonth.setCountryName(countryname);
			theMonth.setMonth(month);
			theMonth.setThisMonthCases(p);
			theMonth.setThisMonthRecovered(p1);
			theMonth.setThisMonthDeaths(p2);
//			listMonthly.add(theMonth);
//		}
	return theMonth;

	}

	@Override
	@Transactional
	public TotalResponse getAllTotal() {
		List<Total> totalResponse=totalsRepository.findFirstByOrderByLocalDateTimeDesc();
		TotalResponse theTotalResponse = null;
		for(Total theTotal:totalResponse) {
			theTotalResponse=new TotalResponse();
			theTotalResponse.setLocalDateTime(theTotal.getLocalDateTime());
			theTotalResponse.setTodayCases(theTotal.getTodayCases());
			theTotalResponse.setTodayRecovered(theTotal.getTodayRecovered());
			theTotalResponse.setTodayDeaths(theTotal.getTodayDeaths());
			theTotalResponse.setTotalCases(theTotal.getCases());
			theTotalResponse.setTotalActive(theTotal.getActive());
			theTotalResponse.setTotalRecovered(theTotal.getRecovered());
			theTotalResponse.setTotalDeaths(theTotal.getDeaths());
		}
		
		return theTotalResponse;
	}

	@Override
	@Transactional
	public List<WeeklyResponse> getWeekly(String countryname, int month, int year, int weekno) throws ParseException {
		
		String a,b;
		List<String> weekStartDates =new ArrayList<String>();
		List<String> weekEndDates = new ArrayList<String>();
		List<Daywise> weekly;
		List<WeeklyResponse> finalWeek = null;
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
			finalWeek=setWeekRecord(weekly,month,weekno,a,b,countryname);

		return finalWeek;
	}

	public List<WeeklyResponse> setWeekRecord(List<Daywise> weekly,int month,int weekno,String weekStart,String weekEnd,String countryname){
		List<Integer> weeklyCases=new ArrayList<Integer>();
		List<Integer> weeklyRecovered=new ArrayList<Integer>();
		List<Integer> weeklyDeaths=new ArrayList<Integer>();
//		List<String> countryName=new ArrayList<String>();
		
		 
				for(Daywise daywise: weekly)
				{	
					
					weeklyCases.add(daywise.getCases());
					weeklyRecovered.add(daywise.getRecovered());
					weeklyDeaths.add(daywise.getDeaths());
//					countryName.add(daywise.getDaywiseid().getCountry());
				
				}
				
				
		List<WeeklyResponse> weeklyTotal=calculateWeek(weeklyCases,weeklyRecovered,weeklyDeaths,month,weekno,weekStart,weekEnd,countryname);
		
		return weeklyTotal;
		}
	
	public List<WeeklyResponse> calculateWeek(List<Integer> weeklyCases,List<Integer> weeklyRecovered,List<Integer> weeklyDeaths,int month,int weekno,String weekStart,String weekEnd,String countryname){
		
		
		int q=0,q1=0,q2=0;
		int p=0,p1=0,p2=0;
		for(int j=0; j<weeklyCases.size(); j++)
		{
			p=weeklyCases.get(j)+q;
			p1=weeklyRecovered.get(j)+q1;
			p2=weeklyDeaths.get(j)+q2;
			q=p;
			q1=p1;
			q2=p2;
			}
		List<WeeklyResponse> listWeekly=new ArrayList<WeeklyResponse>();	
		
//		for(int j=0; j<casesmdif.size(); j++)
//		{
			WeeklyResponse theWeekly=new WeeklyResponse();
			theWeekly.setCountryName(countryname);
			theWeekly.setMonth(month);
			theWeekly.setWeekNo(weekno);
			theWeekly.setWeekStartDate(weekStart);
			theWeekly.setWeekEndDate(weekEnd);
			theWeekly.setTotalCases(p);
			theWeekly.setTotalRecovered(p1);
			theWeekly.setTotalDeaths(q2);
			listWeekly.add(theWeekly);
//		}
	return listWeekly;

	}

	

}
