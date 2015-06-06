// import statements
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.text.DecimalFormat;
import java.time.LocalDate;
import javax.swing.JOptionPane;
/**
 * This class handles all the operations required to generate
 * a new random person (name, height, etc.).
 * @author		Richard Barney
 * @since		1.8
 * @version		1.0.0 June 2015
 */
public class RandomPersonGenerator {
	/** List of Strings for male first names. */
	private List<String> maleFirstNamesArr;
	/** List of Strings for female first names. */
	private List<String> femaleFirstNamesArr;
	/** List of Strings for last names. */
	private List<String> lastNamesArr;
	/** List of Strings for occupations. */
	private List<String> occupationsArr;
	/** Random object. */
	private Random rand;
	/** String to hold the first name. */
	private String sFirstName;
	/** String to hold the last name. */
	private String sLastName;
	/** String to hold the person's age. */
	private String sAge;
	/** String to hold the person's city. */
	private String sCity;
	/** Boolean to determine if the person is Canadian. */
	private boolean countryCanada;
	/** Boolean to determine if the person is male. */
	private boolean genderMale;
	/** Boolean to determine if the person is single. */
	private boolean neverMarried;
	
	/**
	 * Default constructor.
	 */
	public RandomPersonGenerator() {
		maleFirstNamesArr = new ArrayList<String>();
		femaleFirstNamesArr = new ArrayList<String>();
		lastNamesArr = new ArrayList<String>();
		occupationsArr = new ArrayList<String>();
		rand = new Random();
		populateArrays();
	}
	
	/**
	 * Get method that returns the person's age.
	 * @return the person's age as a String.
	 */
	public String getAge() {
		return sAge;
	}
	
	/**
	 * Void method that populates big arrays. The arrays are loaded with text
	 * from text files.
	 */
	private void populateArrays() {
		// use InputStreams for files for JAR use
		InputStream is1 = getClass().getResourceAsStream("male_first_names.txt");
		InputStream is2 = getClass().getResourceAsStream("female_first_names.txt");
		InputStream is3 = getClass().getResourceAsStream("last_names.txt");
		InputStream is4 = getClass().getResourceAsStream("occupations.txt");
		// use BufferedReader to get text from InputStream objects
		BufferedReader bfMaleFirstNames = new BufferedReader(new InputStreamReader(is1));
		BufferedReader bfFemaleFirstNames = new BufferedReader(new InputStreamReader(is2));
		BufferedReader bfLastNames = new BufferedReader(new InputStreamReader(is3));
		BufferedReader bfOccupations = new BufferedReader(new InputStreamReader(is4));
		StringBuilder out = new StringBuilder();
		String line;
		try {
			// each array is filled by getting the current line of the text file
			// and appending that line to the StringBuilder object, then adding
			// the StringBuilder object to the array. Note I delete everything
			// in the StringBuilder object before appending it to ensure there's
			// nothing left over from the last line
			while ((line = bfMaleFirstNames.readLine()) != null) {
				out.delete(0, out.length());
				out.append(line);
				maleFirstNamesArr.add(out.toString());
			}
			while ((line = bfFemaleFirstNames.readLine()) != null) {
				out.delete(0, out.length());
				out.append(line);
				femaleFirstNamesArr.add(out.toString());
			}
			while ((line = bfLastNames.readLine()) != null) {
				out.delete(0, out.length());
				out.append(line);
				lastNamesArr.add(out.toString());
			}
			while ((line = bfOccupations.readLine()) != null) {
				out.delete(0, out.length());
				out.append(line);
				occupationsArr.add(out.toString());
			}
			// close BufferedReader objects
			bfMaleFirstNames.close();
			bfFemaleFirstNames.close();
			bfLastNames.close();
			bfOccupations.close();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error - Files not properly loaded. Quiting program...", "Error!", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	} // end method populateArrays
	
	/**
	 * String method that generates a person's full name.
	 * @param	gender		String containing "female" or "male" to
	 * 						determine the person's gender.
	 * @return the full name as a String.
	 */
	public String generateFullName(String gender) {
		if (gender.equals("female")) {
			genderMale = false;
			sFirstName = femaleFirstNamesArr.get(rand.nextInt(femaleFirstNamesArr.size()));
		}
		else {
			genderMale = true;
			sFirstName = maleFirstNamesArr.get(rand.nextInt(maleFirstNamesArr.size()));
		}
		sLastName = lastNamesArr.get(rand.nextInt(lastNamesArr.size()));
		return sFirstName +" " +sLastName;
	} // end method generateFullName
	
	/**
	 * String method that returns an e-mail address.
	 * @return the e-mail address as a String in the form email99999@email.com.
	 */
	public String generateEmail() {
		int nRandomEmailNum = rand.nextInt(99999) + 1;
		String[] emailAddressesArr = { "@outlook.com", "@hotmail.com", "@yahoo.com", "@gmail.com", "@msn.com", "@aol.com" };		
		String sEmailAddress = emailAddressesArr[rand.nextInt(emailAddressesArr.length)];
		return sFirstName +"." +sLastName +nRandomEmailNum +sEmailAddress;
	} // end method generateEmail
	
	/**
	 * String method that generates the birthday.
	 * @return the birthday as a String in the format Month Day, Year.
	 */
	public String generateBirthday() {
		int nDay = 1;
		// create LocalDate object to determine current year, month, and day of month,
		// note that this class was introduced in 1.8
		LocalDate now = LocalDate.now();
		int nMaxYear = now.getYear() - 12;
		int nMinYear = now.getYear() - 90;
		// year born can only between the current year - 90 or the current year
		// - 12 (minimum 13 years old)
		int nYear = rand.nextInt(nMaxYear - nMinYear) + nMinYear;
		String[] monthsArr = { "January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December" };
		int nRandomMonth = rand.nextInt(monthsArr.length);
		String sMonth = monthsArr[nRandomMonth];
		// January, March, May, July, August, October, December have 31 days
		if (sMonth.equals("January") || sMonth.equals("March") || sMonth.equals("May") || sMonth.equals("July")
				|| sMonth.equals("August") || sMonth.equals("October") || sMonth.equals("December")) {
			nDay = rand.nextInt(31) + 1;
		}
		// April, June, September, November have 30 days
		if (sMonth.equals("April") || sMonth.equals("June") || sMonth.equals("September") || sMonth.equals("November")) {
			nDay = rand.nextInt(30) + 1;
		}
		// determine if the year is a leap year, if so February has 29 days,
		// if not, February has 28 days
		if (sMonth.equals("February") && nYear%4 == 0) {
			nDay = rand.nextInt(29) + 1;
		}
		if (sMonth.equals("February") && nYear%4 != 0) {
			nDay = rand.nextInt(28) + 1;
		}
		// determine age, using LocalDate to help make sure the age is correct (e.g. if person is born 
		// January 2, 2000, and the current date is January 1, 2016, then the person is 15 years old,
		// not 16		
		if (((now.getMonthValue() - (nRandomMonth + 1)) > 0) && (now.getDayOfMonth() - nDay > 0) || ((now.getMonthValue() - (nRandomMonth + 1)) <= 0)) {
			sAge = Integer.toString(now.getYear() - nYear - 1);
		}
		if (((now.getMonthValue() - (nRandomMonth + 1)) > 0) && (now.getDayOfMonth() - nDay <= 0)) {
			sAge = Integer.toString(now.getYear() - nYear);
		}
		return sMonth +" " +Integer.toString(nDay) +", " +Integer.toString(nYear);
	} // end method generateBirthday
	
	/**
	 * String method that generates an occupation.
	 * @return the person's occupation as a String.
	 */
	public String generateOccupation() {
		String[] studentArr = { "College Student", "University Student", "Unemployed", "NEET" };
		String sOccupation = "";
		// under 18 people are unemployed in this program
		if (Integer.parseInt(sAge) < 18) {
			sOccupation = "N/A";
		}
		// ppl between 18 and 23 will be students, unemployed, or NEETs
		else if (Integer.parseInt(sAge) > 18 && Integer.parseInt(sAge) < 23) {
			sOccupation = studentArr[rand.nextInt(studentArr.length)];
		}
		// ppl over 70 will be retired with their former occupation listed
		else if (Integer.parseInt(sAge) > 70) {
			sOccupation = "Retired, former " +occupationsArr.get(rand.nextInt(occupationsArr.size()));
		}
		// everyone else will have an occupation
		else {
			sOccupation = occupationsArr.get(rand.nextInt(occupationsArr.size()));
		}
		return sOccupation;
	} // end method generateOccupation
	
	/**
	 * String method that generates a birthplace contaning the city, state/province, 
	 * and country, currently only the US or Canada.
	 * @return the birthplace in format City, State/Province, Country as a String.
	 */
	public String generateBirthplace() {
		String sBirthplace = "";
		String[] countriesArr = { "Canada", "United States" };
		String sCountry = countriesArr[rand.nextInt(countriesArr.length)];
		switch(sCountry) {
			// Canada-born
			case "Canada":
				countryCanada = true;
				// String arrays containing provinces and a few cities in each province
				String[] canadaProvincesArr = { "Yukon", "Northwest Territories", "Nunavut", "British Columbia", "Alberta", 
						"Saskatchewan", "Manitoba", "Ontario", "Quebec", "New Brunswick", "P.E.I.", "Nova Scotia", 
						"Newfoundland and Labrador" };
				String[] yukonCities = { "Whitehorse", "Dawson" };
				String[] nwtCities = { "Yellowknife", "Hay River" };
				String[] nunavutCities = { "Iqaluit", "Arviat", "Rankin Inlet" };
				String[] bcCities = { "Vancouver", "Surrey", "Burnaby", "Richmond", "Abbotsford", "Coquitlam", "Kelowna", 
						"Saanich", "Langley", "Delta", "Kamloops", "North Vancouver", "Nanaimo", "Victoria", "Chilliwack", 
						"Maple Ridge", "Prince George", "New Westminster", "Port Coquitlam" };
				String[] albertaCities = { "Calgary", "Edmonton", "Red Deer", "Lethbridge", "St. Albert", "Medicine Hat", 
						"Grande Prairie" };
				String[] saskCities = { "Saskatoon", "Regina", "Prince Albert", "Moose Jaw", "Yorkton" };
				String[] manitobaCities = { "Winnipeg", "Brandon", "Steinbach", "Portage la Prairie", "Thompson", "Winkler", 
						"Selkirk", "Dauphin", "Morden" };
				String[] ontarioCities = { "Toronto", "Ottawa", "Mississauga", "Brampton", "Hamilton", "London", "Markham", 
						"Vaughan", "Kitchener", "Windsor", "Richmond Hill", "Oakville", "Burlington", "Greater Sudbury", 
						"Oshawa", "Barrie", "St. Catharines", "Cambridge", "Kingston", "Whitby", "Guelph", "Ajax", "Thunder Bay", 
						"Chatham-Kent", "Waterloo", "Brantford", "Pickering", "Clarington", "Milton", "Niagra Falls", "Newmarket", 
						"Sault Ste. Marie", "Sarnia", "Caledon", "Halton Hills", "North Bay", "Aurora", "Welland", "Belleville" };
				String[] quebecCities = { "Montreal", "Quebec City", "Laval", "Gatineau", "Longueuil", "Sherbrooke", "Saguenay", 
						"Lévis", "Trois-Rivières", "Terrebonne", "Repentigny", "Brossard", "Drummondville", "Saint-Jérôme", 
						"Granby", "Blainville", "Shawinigan", "Dollard-des-Ormeaux" };
				String[] nbCities = { "Saint John", "Moncton", "Fredericton", "Dieppe", "Miramichi", "Edmundston", "Bathurst" };
				String[] peiCities = { "Charlottetown", "Summerside", "Stratford", "Cornwall" };
				String[] nsCities = { "Halifax", "Cape Breton" };
				String[] newfieCities = { "St. John's", "Mount Pearl", "Corner Brook" };
				String sProvince = canadaProvincesArr[rand.nextInt(canadaProvincesArr.length)];
				// determine city based on province
				switch(sProvince) {
					case "Yukon":
						sCity = yukonCities[rand.nextInt(yukonCities.length)];
						break;
					case "Northwest Territories":
						sCity = nwtCities[rand.nextInt(nwtCities.length)];
						break;
					case "Nunavut":
						sCity = nunavutCities[rand.nextInt(nunavutCities.length)];
						break;
					case "British Columbia":
						sCity = bcCities[rand.nextInt(bcCities.length)];
						break;
					case "Alberta":
						sCity = albertaCities[rand.nextInt(albertaCities.length)];
						break;
					case "Saskatchewan":
						sCity = saskCities[rand.nextInt(saskCities.length)];
						break;
					case "Manitoba":
						sCity = manitobaCities[rand.nextInt(manitobaCities.length)];
						break;
					case "Ontario":
						sCity = ontarioCities[rand.nextInt(ontarioCities.length)];
						break;
					case "Quebec":
						sCity = quebecCities[rand.nextInt(quebecCities.length)];
						break;
					case "New Brunswick":
						sCity = nbCities[rand.nextInt(nbCities.length)];
						break;
					case "P.E.I.":
						sCity = peiCities[rand.nextInt(peiCities.length)];
						break;
					case "Nova Scotia":
						sCity = nsCities[rand.nextInt(nsCities.length)];
						break;
					case "Newfoundland and Labrador":
						sCity = newfieCities[rand.nextInt(newfieCities.length)];
						break;
				}
				sBirthplace = sCity +", " +sProvince +", " +sCountry;
				break;
				
			// USA-born
			case "United States":
				countryCanada = false;
				// String arrays containing states and a few cities in each state
				String[] usStatesArr = { "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado",
						"Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana",
						"Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts",
						"Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada",
						"New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota",
						"Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
						"Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia",
						"Wisconsin", "Wyoming" };
				String[] alabamaCities = { "Birmingham", "Montgomery", "Mobile" };
				String[] hawaiiCities = { "Honolulu" };
				String[] massachusettsCities = { "Boston", "Worcester", "Springfield"  };
				String[] nmCities = { "Albuquerque", "Las Cruces", "Rio Rancho" };
				String[] sdCities = { "Sioux Falls", "Rapid City", "Aberdeen" };
				String[] alaskaCities = { "Anchorage", "Fairbanks", "Juneau" };
				String[] idahoCities = { "Boise", "Nampa", "Meridian" };
				String[] michiganCities = { "Detroit", "Grand Rapids", "Warren" };
				String[] nyCities = { "New York", "Buffalo", "Rochester" };
				String[] tennesseeCities = { "Memhpis", "Nashville", "Knoxville" };
				String[] arizonaCities = { "Phoenix", "Tucson", "Mesa" };
				String[] illinoisCities = { "Chicago", "Aurora", "Rockford" };
				String[] minnesotaCities = { "Minneapolis", "Saint Paul", "Bloomington" };
				String[] ncCities = { "Charlotte", "Raleigh", "Greensboro" };
				String[] texasCities = { "Houston", "San Antonio", "Dallas" };
				String[] arkansasCities = { "Little Rock", "Fort Smith", "Fayetteville" };
				String[] indianaCities = { "Indianapolis", "Fort Wayne", "Evansville" };
				String[] mississippiCities = { "Jackson", "Gulfport", "Southaven" };
				String[] ndCities = { "Fargo", "Bismarck", "Grand Forks" };
				String[] utahCities = { "Salt Lake City", "West Valley City", "Provo" };
				String[] californiaCities = { "Los Angeles", "San Diego", "San Jose" };
				String[] iowaCities = { "Des Moines", "Cedar Rapids", "Davenport" };
				String[] missouriCities = { "Kansas City", "St. Louis", "Independence" };
				String[] ohioCities = { "Columbus", "Cleveland", "Cincinnati" };
				String[] vermontCities = { "Burlington", "Rutland", "Barre" };
				String[] coloradoCities = { "Denver", "Colorado Springs", "Fort Collins" };
				String[] kansasCities = { "Wichita", "Overland Park", "Olathe" };
				String[] montanaCities = { "Billings", "Missoula", "Great Falls" };
				String[] oklahomaCities = { "Oklahoma City", "Tulsa", "Norman" };
				String[] virginiaCities = { "Virginia Beach", "Norfolk", "Chesapeake" };
				String[] connecticutCities = { "Bridgeport", "New Haven", "Hartford" };
				String[] kentuckyCities = { "Louisville", "Lexington", "Bowling Green" };
				String[] nebraskaCities = { "Omaha", "Lincoln", "Bellevue" };
				String[] oregonCities = { "Portland", "Salem", "Eugene" };
				String[] washingtonCities = { "Seattle", "Spokane", "Tacoma" };
				String[] delawareCities = { "Wilmington", "Dover", "Middleton" };
				String[] louisianaCities = { "New Orleans", "Baton Rouge", "Shreveport" };
				String[] nevadaCities = { "Las Vegas", "Henderson", "Reno" };
				String[] pennsylvaniaCities = { "Philadelphia", "Pittsburgh", "Allentown" };
				String[] wvCities = { "Charleston", "Huntington", "Parkersburg" };
				String[] floridaCities = { "Jacksonville", "Miami", "Tampa" };
				String[] maineCities = { "Lewiston", "Bangor", "South Portland" };
				String[] nhCities = { "Manchester", "Nashua", "Concord" };
				String[] riCities = { "Providence", "Warwick", "Cranston" };
				String[] wisconsinCities = { "Milwaukee", "Madison", "Green Bay" };
				String[] georgiaCities = { "Atlanta", "Augusta", "Macon" };
				String[] marylandCities = { "Baltimore", "Frederick", "Rockville" };
				String[] njCities = { "Newark", "Jersey City", "Paterson" };
				String[] scCities = { "Columbia", "Charleston", "Mount Pleasant" };
				String[] wyomingCities = { "Cheyenne", "Casper", "Laramie" };
				String sUSState = usStatesArr[rand.nextInt(usStatesArr.length)];
				// determine city based on state
				switch(sUSState) {
					case "Alabama":
						sCity = alabamaCities[rand.nextInt(alabamaCities.length)];
						break;
					case "Hawaii":
						sCity = hawaiiCities[rand.nextInt(hawaiiCities.length)];
						break;
					case "Massachusetts":
						sCity = massachusettsCities[rand.nextInt(massachusettsCities.length)];
						break;
					case "New Mexico":
						sCity = nmCities[rand.nextInt(nmCities.length)];
						break;
					case "South Dakota":
						sCity = sdCities[rand.nextInt(sdCities.length)];
						break;
					case "Alaska":
						sCity = alaskaCities[rand.nextInt(alaskaCities.length)];
						break;
					case "Idaho":
						sCity = idahoCities[rand.nextInt(idahoCities.length)];
						break;
					case "Michigan":
						sCity = michiganCities[rand.nextInt(michiganCities.length)];
						break;
					case "New York":
						sCity = nyCities[rand.nextInt(nyCities.length)];
						break;
					case "Tennessee":
						sCity = tennesseeCities[rand.nextInt(tennesseeCities.length)];
						break;
					case "Arizona":
						sCity = arizonaCities[rand.nextInt(arizonaCities.length)];
						break;
					case "Illinois":
						sCity = illinoisCities[rand.nextInt(illinoisCities.length)];
						break;
					case "Minnesota":
						sCity = minnesotaCities[rand.nextInt(minnesotaCities.length)];
						break;
					case "North Carolina":
						sCity = ncCities[rand.nextInt(ncCities.length)];
						break;
					case "Texas":
						sCity = texasCities[rand.nextInt(texasCities.length)];
						break;
					case "Arkansas":
						sCity = arkansasCities[rand.nextInt(arkansasCities.length)];
						break;
					case "Indiana":
						sCity = indianaCities[rand.nextInt(indianaCities.length)];
						break;
					case "Mississippi":
						sCity = mississippiCities[rand.nextInt(mississippiCities.length)];
						break;
					case "North Dakota":
						sCity = ndCities[rand.nextInt(ndCities.length)];
						break;
					case "Utah":
						sCity = utahCities[rand.nextInt(utahCities.length)];
						break;
					case "California":
						sCity = californiaCities[rand.nextInt(californiaCities.length)];
						break;
					case "Iowa":
						sCity = iowaCities[rand.nextInt(iowaCities.length)];
						break;
					case "Missouri":
						sCity = missouriCities[rand.nextInt(missouriCities.length)];
						break;
					case "Ohio":
						sCity = ohioCities[rand.nextInt(ohioCities.length)];
						break;
					case "Vermont":
						sCity = vermontCities[rand.nextInt(vermontCities.length)];
						break;
					case "Colorado":
						sCity = coloradoCities[rand.nextInt(coloradoCities.length)];
						break;
					case "Kansas":
						sCity = kansasCities[rand.nextInt(kansasCities.length)];
						break;
					case "Montana":
						sCity = montanaCities[rand.nextInt(montanaCities.length)];
						break;
					case "Oklahoma":
						sCity = oklahomaCities[rand.nextInt(oklahomaCities.length)];
						break;
					case "Virginia":
						sCity = virginiaCities[rand.nextInt(virginiaCities.length)];
						break;
					case "Connecticut":
						sCity = connecticutCities[rand.nextInt(connecticutCities.length)];
						break;
					case "Kentucky":
						sCity = kentuckyCities[rand.nextInt(kentuckyCities.length)];
						break;
					case "Nebraska":
						sCity = nebraskaCities[rand.nextInt(nebraskaCities.length)];
						break;
					case "Oregon":
						sCity = oregonCities[rand.nextInt(oregonCities.length)];
						break;
					case "Washington":
						sCity = washingtonCities[rand.nextInt(washingtonCities.length)];
						break;
					case "Delaware":
						sCity = delawareCities[rand.nextInt(delawareCities.length)];
						break;
					case "Louisiana":
						sCity = louisianaCities[rand.nextInt(louisianaCities.length)];
						break;
					case "Nevada":
						sCity = nevadaCities[rand.nextInt(nevadaCities.length)];
						break;
					case "Pennsylvania":
						sCity = pennsylvaniaCities[rand.nextInt(pennsylvaniaCities.length)];
						break;
					case "West Virginia":
						sCity = wvCities[rand.nextInt(wvCities.length)];
						break;
					case "Florida":
						sCity = floridaCities[rand.nextInt(floridaCities.length)];
						break;
					case "Maine":
						sCity = maineCities[rand.nextInt(maineCities.length)];
						break;
					case "New Hampshire":
						sCity = nhCities[rand.nextInt(nhCities.length)];
						break;
					case "Rhode Island":
						sCity = riCities[rand.nextInt(riCities.length)];
						break;
					case "Wisconsin":
						sCity = wisconsinCities[rand.nextInt(wisconsinCities.length)];
						break;
					case "Georgia":
						sCity = georgiaCities[rand.nextInt(georgiaCities.length)];
						break;
					case "Maryland":
						sCity = marylandCities[rand.nextInt(marylandCities.length)];
						break;
					case "New Jersey":
						sCity = njCities[rand.nextInt(njCities.length)];
						break;
					case "South Carolina":
						sCity = scCities[rand.nextInt(scCities.length)];
						break;
					case "Wyoming":
						sCity = wyomingCities[rand.nextInt(wyomingCities.length)];
						break;
				}
				sBirthplace = sCity +", " +sUSState +", " +sCountry;
				break;
		}
		return sBirthplace;
	} // end method generateBirthplace
	
	/**
	 * String method that generates a phone number based on the city and country,
	 * using actual area codes relative to the city.
	 * @return the phone number as a String.
	 */
	public String generatePhone() {
		String sPhone = "";
		// 7 random numbers for phone number
		int nRandomNum1 = rand.nextInt(9) + 1;
		int nRandomNum2 = rand.nextInt(9) + 1;
		int nRandomNum3 = rand.nextInt(9) + 1;
		int nRandomNum4 = rand.nextInt(9) + 1;
		int nRandomNum5 = rand.nextInt(9) + 1;
		int nRandomNum6 = rand.nextInt(9) + 1;
		int nRandomNum7 = rand.nextInt(9) + 1;
		String sRandomPhoneNumber = Integer.toString(nRandomNum1) +Integer.toString(nRandomNum2) +Integer.toString(nRandomNum3)
				+"-" +Integer.toString(nRandomNum4) +Integer.toString(nRandomNum5) +Integer.toString(nRandomNum6) 
				+Integer.toString(nRandomNum7);
		////////////////////////////////////////////////////
		// Canadian phone numbers                         //
		///////////////////////////////////////////////////
		if (countryCanada) {
			// yukon, nwt, and nunavut
			if (sCity.equals("Whitehorse") || sCity.equals("Dawson") || sCity.equals("Yellowknife") || sCity.equals("Hay River") || sCity.equals("Iqaluit") || sCity.equals("Arviat") || sCity.equals("Rankin Inlet")) {
				sPhone = "867";
			}
			// bc
			if (sCity.equals("Vancouver") || sCity.equals("Surrey") || sCity.equals("Burnaby") || sCity.equals("Richmond") || sCity.equals("Abbotsford") || sCity.equals("Coquitlam") || sCity.equals("Langley") || sCity.equals("Delta") || sCity.equals("North Vancouver") || sCity.equals("Chilliwack") || sCity.equals("Maple Ridge") || sCity.equals("New Westminster") || sCity.equals("Port Coquitlam")) {
				sPhone = "604";
			}
			if (sCity.equals("Kelowna") || sCity.equals("Saanich") || sCity.equals("Kamloops") || sCity.equals("Nanaimo") || sCity.equals("Victoria") || sCity.equals("Prince George")) {
				sPhone = "250";
			}
			// alberta
			if (sCity.equals("Calgary") || sCity.equals("Edmonton") || sCity.equals("Red Deer") || sCity.equals("Lethbridge") || sCity.equals("St. Albert") || sCity.equals("Medicine Hat") || sCity.equals("Grande Prairie")) {
				sPhone = "587";
			}
			// saskatchewan
			if (sCity.equals("Saskatoon") || sCity.equals("Regina") || sCity.equals("Prince Albert") || sCity.equals("Moose Jaw") || sCity.equals("Yorkton")) {
				sPhone = "306";				
			}
			// manitoba
			if (sCity.equals("Winnipeg") || sCity.equals("Brandon") || sCity.equals("Steinbach") || sCity.equals("Portage la Prairie") || sCity.equals("Thompson") || sCity.equals("Winkler") || sCity.equals("Selkirk") || sCity.equals("Dauphn") || sCity.equals("Morden")) {
				sPhone = "204";				
			}
			// ontario
			if (sCity.equals("Toronto")) {
				sPhone = "416";				
			}
			if (sCity.equals("Ottawa") || sCity.equals("Kingston") || sCity.equals("Belleville")) {
				sPhone = "613";				
			}
			if (sCity.equals("Brampton") || sCity.equals("Hamilton") || sCity.equals("Markham") ||  sCity.equals("Vaughan") ||  sCity.equals("Richmond Hill") || sCity.equals("Oakville") || sCity.equals("Burlington") || sCity.equals("Oshawa") || sCity.equals("St. Catharines") || sCity.equals("Whitby") || sCity.equals("Ajax") || sCity.equals("Pickering") || sCity.equals("Milton") || sCity.equals("Niagra Falls") || sCity.equals("Newmarket") || sCity.equals("Halton Hills")) {
				sPhone = "289";				
			}
			if (sCity.equals("London") || sCity.equals("Kitchener") || sCity.equals("Windsor") || sCity.equals("Cambridge") || sCity.equals("Guelph") || sCity.equals("Chatham-Kent") || sCity.equals("Waterloo") || sCity.equals("Brantford") || sCity.equals("Sarnia") || sCity.equals("Caledon")) {
				sPhone = "519";				
			}
			if (sCity.equals("Greater Sudbury") || sCity.equals("Barrie") || sCity.equals("Sault Ste. Marie") || sCity.equals("North Bay")) {
				sPhone = "705";
			}
			if (sCity.equals("Thunder Bay")) {
				sPhone = "807";
			}
			if (sCity.equals("Clarington") || sCity.equals("Aurora") || sCity.equals("Welland") || sCity.equals("Mississauga")) {
				sPhone = "289";
			}
			// quebec
			if (sCity.equals("Montreal") || sCity.equals("Dollard-des-Ormeaux")) {
				sPhone = "514";
			}
			if (sCity.equals("Quebec City") || sCity.equals("Saguenay") || sCity.equals("Lévis")) {
				sPhone = "418";
			}
			if (sCity.equals("Laval") || sCity.equals("Longueuil") || sCity.equals("Terrebonne") || sCity.equals("Repentigny") || sCity.equals("Brossard") || sCity.equals("Saint-Jérôme") || sCity.equals("Granby") || sCity.equals("Blainville")) {
				sPhone = "450";
			}
			if (sCity.equals("Gatineau") || sCity.equals("Sherbrooke") || sCity.equals("Trois-Rivières") || sCity.equals("Drummondville") || sCity.equals("Shawinigan")) {
				sPhone = "819";
			}
			// nb
			if (sCity.equals("Saint John") || sCity.equals("Moncton") || sCity.equals("Fredericton") || sCity.equals("Dieppe") || sCity.equals("Miramichi") || sCity.equals("Edmundston") || sCity.equals("Bathurst")) {
				sPhone = "506";
			}
			// pei and ns
			if (sCity.equals("Charlottetown") || sCity.equals("Summerside") || sCity.equals("Stratford") || sCity.equals("Cornwall") || sCity.equals("Halifax") || sCity.equals("Cape Breton")) {
				sPhone = "902";
			}
			// newfoundland
			if (sCity.equals("St. John's") || sCity.equals("Mount Pearl") || sCity.equals("Corner Brook")) {
				sPhone = "709";
			}
		}
		////////////////////////////////////////////////////
		// American phone numbers                         //
		///////////////////////////////////////////////////
		else {
			// alabama
			if (sCity.equals("Birmingham")) {
				sPhone = "205";
			}
			if (sCity.equals("Montgomery")) {
				sPhone = "334";
			}
			if (sCity.equals("Mobile")) {
				sPhone = "251";
			}
			// hawaii
			if (sCity.equals("Honolulu")) {
				sPhone = "808";
			}
			// massachusetts
			if (sCity.equals("Boston")) {
				sPhone = "617";
			}
			if (sCity.equals("Worcester")) {
				sPhone = "508";
			}
			if (sCity.equals("Springfield")) {
				sPhone = "413";
			}
			// new mexico
			if (sCity.equals("Albuquerque") || sCity.equals("Rio Rancho")) {
				sPhone = "505";
			}
			if (sCity.equals("Las Cruces")) {
				sPhone = "575";
			}
			// south dakota
			if (sCity.equals("Sioux Falls") || sCity.equals("Rapid City") || sCity.equals("Aberdeen")) {
				sPhone = "605";
			}
			// alaska
			if (sCity.equals("Anchorage") || sCity.equals("Fairbanks") || sCity.equals("Juneau")) {
				sPhone = "907";
			}
			// idaho
			if (sCity.equals("Boise") || sCity.equals("Nampa") || sCity.equals("Meridian")) {
				sPhone = "208";
			}
			// michigan
			if (sCity.equals("Detroid")) {
				sPhone = "313";
			}
			if (sCity.equals("Grand Rapids")) {
				sPhone = "616";
			}
			if (sCity.equals("Warren")) {
				sPhone = "586";
			}
			// ny
			if (sCity.equals("New York")) {
				sPhone = "212";
			}
			if (sCity.equals("Buffalo")) {
				sPhone = "716";
			}
			if (sCity.equals("Rochester")) {
				sPhone = "585";
			}
			// tennessee
			if (sCity.equals("Memphis")) {
				sPhone = "901";
			}
			if (sCity.equals("Nashville")) {
				sPhone = "615";
			}
			if (sCity.equals("Knoxville")) {
				sPhone = "865";
			}
			// arizona
			if (sCity.equals("Phoenix") || sCity.equals("Mesa")) {
				sPhone = "480";
			}
			if (sCity.equals("Tucson")) {
				sPhone = "520";
			}
			// illinois
			if (sCity.equals("Chicago")) {
				sPhone = "312";
			}
			if (sCity.equals("Aurora")) {
				sPhone = "630";
			}
			if (sCity.equals("Rockford")) {
				sPhone = "815";
			}
			// minnesota
			if (sCity.equals("Minneapolis")) {
				sPhone = "612";
			}
			if (sCity.equals("Saint Paul")) {
				sPhone = "651";
			}
			if (sCity.equals("Bloomington")) {
				sPhone = "952";
			}
			// north carolina
			if (sCity.equals("Charlotte")) {
				sPhone = "704";
			}
			if (sCity.equals("Raleigh")) {
				sPhone = "919";
			}
			if (sCity.equals("Greensboro")) {
				sPhone = "336";
			}
			// texas
			if (sCity.equals("Houston")) {
				sPhone = "281";
			}
			if (sCity.equals("San Antonio")) {
				sPhone = "210";
			}
			if (sCity.equals("Dallas")) {
				sPhone = "214";
			}
			// arkansas
			if (sCity.equals("Little Rock")) {
				sPhone = "501";
			}
			if (sCity.equals("Fort Smith") || sCity.equals("Fayetteville")) {
				sPhone = "479";
			}
			// indiana
			if (sCity.equals("Indianapolis")) {
				sPhone = "317";
			}
			if (sCity.equals("Fort Wayne")) {
				sPhone = "260";
			}
			if (sCity.equals("Evansville")) {
				sPhone = "812";
			}
			// mississippi
			if (sCity.equals("Jackson")) {
				sPhone = "601";
			}
			if (sCity.equals("Gulfport")) {
				sPhone = "228";
			}
			if (sCity.equals("Southaven")) {
				sPhone = "662";
			}
			// north dakota
			if (sCity.equals("Fargo") || sCity.equals("Bismarck") || sCity.equals("Grand Forks")) {
				sPhone = "701";
			}
			// utah
			if (sCity.equals("Salt Lake City") || sCity.equals("West Valley City") || sCity.equals("Provo")) {
				sPhone = "385";
			}
			// california
			if (sCity.equals("Los Angeles")) {
				sPhone = "213";
			}
			if (sCity.equals("San Diego")) {
				sPhone = "619";
			}
			if (sCity.equals("San Jose")) {
				sPhone = "408";
			}
			// iowa
			if (sCity.equals("Des Moines")) {
				sPhone = "515";
			}
			if (sCity.equals("Cedar Rapids")) {
				sPhone = "319";
			}
			if (sCity.equals("Davenport")) {
				sPhone = "563";
			}
			// missouri
			if (sCity.equals("Kansas City") || sCity.equals("Independence")) {
				sPhone = "816";
			}
			if (sCity.equals("St. Louis")) {
				sPhone = "314";
			}
			// ohio
			if (sCity.equals("Columbus")) {
				sPhone = "614";
			}
			if (sCity.equals("Cleveland")) {
				sPhone = "216";
			}
			if (sCity.equals("Cincinnati")) {
				sPhone = "513";
			}
			// vermont
			if (sCity.equals("Burlington") || sCity.equals("Rutland") || sCity.equals("Barre")) {
				sPhone = "802";
			}
			// colorado
			if (sCity.equals("Denver")) {
				sPhone = "303";
			}
			if (sCity.equals("Colorado Springs")) {
				sPhone = "719";
			}
			if (sCity.equals("Fort Collins")) {
				sPhone = "970";
			}
			// kansas
			if (sCity.equals("Wichita")) {
				sPhone = "316";
			}
			if (sCity.equals("Overland Park") || sCity.equals("Olathe")) {
				sPhone = "913";
			}
			// montana
			if (sCity.equals("Billings") || sCity.equals("Missoula") || sCity.equals("Great Falls")) {
				sPhone = "406";
			}
			// oklahoma
			if (sCity.equals("Oklahoma City") || sCity.equals("Norman")) {
				sPhone = "405";
			}
			if (sCity.equals("Tulsa")) {
				sPhone = "539";
			}
			// virginia
			if (sCity.equals("Virginia Beach") || sCity.equals("Norfolk") || sCity.equals("Chesapeake")) {
				sPhone = "757";
			}
			// connecticut
			if (sCity.equals("Bridgeport") || sCity.equals("New Haven")) {
				sPhone = "203";
			}
			if (sCity.equals("Hartford")) {
				sPhone = "860";
			}
			// kentucky
			if (sCity.equals("Louisville")) {
				sPhone = "503";
			}
			if (sCity.equals("Lexington")) {
				sPhone = "859";
			}
			if (sCity.equals("Bowling Green")) {
				sPhone = "419";
			}
			//nebraska
			if (sCity.equals("Omaha") || sCity.equals("Lincoln") || sCity.equals("Bellevue")) {
				sPhone = "402";
			}
			// oregon
			if (sCity.equals("Portland") || sCity.equals("Salem")) {
				sPhone = "503";
			}
			if (sCity.equals("Eugene")) {
				sPhone = "458";
			}
			// washington
			if (sCity.equals("Seattle")) {
				sPhone = "206";
			}
			if (sCity.equals("Spokane")) {
				sPhone = "509";
			}
			if (sCity.equals("Tacoma")) {
				sPhone = "253";
			}
			// delaware
			if (sCity.equals("Wilmington") || sCity.equals("Dover") || sCity.equals("Middleton")) {
				sPhone = "302";
			}
			// louisiana
			if (sCity.equals("New Orleans")) {
				sPhone = "504";
			}
			if (sCity.equals("Baton Rouge")) {
				sPhone = "225";
			}
			if (sCity.equals("Shreveport")) {
				sPhone = "318";
			}
			// nevada
			if (sCity.equals("Las Vegas") || sCity.equals("Henderson")) {
				sPhone = "702";
			}
			if (sCity.equals("Reno")) {
				sPhone = "775";
			}
			// pennsylvania
			if (sCity.equals("Philadelphia")) {
				sPhone = "215";
			}
			if (sCity.equals("Pittsburgh")) {
				sPhone = "412";
			}
			if (sCity.equals("Allentown")) {
				sPhone = "610";
			}
			// west virginia
			if (sCity.equals("Charleston") || sCity.equals("Huntington") || sCity.equals("Parkersburg")) {
				sPhone = "304";
			}
			// florida
			if (sCity.equals("Jacksonville")) {
				sPhone = "904";
			}
			if (sCity.equals("Miami")) {
				sPhone = "305";
			}
			if (sCity.equals("Tampa")) {
				sPhone = "813";
			}
			// maine
			if (sCity.equals("Lewiston") || sCity.equals("Bangor") || sCity.equals("South Portland")) {
				sPhone = "207";
			}
			// new hampshire
			if (sCity.equals("Manchester") || sCity.equals("Nashua") || sCity.equals("Condord")) {
				sPhone = "603";
			}
			// rhode island
			if (sCity.equals("Providence") || sCity.equals("Warwick") || sCity.equals("Cranston")) {
				sPhone = "401";
			}
			// wisconsin
			if (sCity.equals("Milwaukee")) {
				sPhone = "414";
			}
			if (sCity.equals("Madison")) {
				sPhone = "608";
			}
			if (sCity.equals("Green Bay")) {
				sPhone = "902";
			}
			// georgia
			if (sCity.equals("Atlanda")) {
				sPhone = "404";
			}
			if (sCity.equals("Augusta")) {
				sPhone = "706";
			}
			if (sCity.equals("Macon")) {
				sPhone = "478";
			}
			// maryland
			if (sCity.equals("Baltimore")) {
				sPhone = "410";
			}
			if (sCity.equals("Frederick") || sCity.equals("Rockville")) {
				sPhone = "301";
			}
			// new jersey
			if (sCity.equals("Newark")) {
				sPhone = "862";
			}
			if (sCity.equals("Jersey City") || sCity.equals("Paterson")) {
				sPhone = "201";
			}
			// south carolina
			if (sCity.equals("Columbia")) {
				sPhone = "803";
			}
			if (sCity.equals("Charleston") || sCity.equals("Mount Pleasant")) {
				sPhone = "843";
			}
			// wyoming
			if (sCity.equals("Cheyenne") || sCity.equals("Casper") || sCity.equals("Laramie")) {
				sPhone = "307";
			}
		}
		return sPhone +"-" +sRandomPhoneNumber;
	} // end method generatePhone
	
	/**
	 * String method to generate height in inches and cm.
	 * @return the height as a String.
	 */
	public String generateHeight() {
		String sFeet = "";
		String sInches = "";
		////////////////////////////////////////////////////
		// female height                                  //
		///////////////////////////////////////////////////
		if (!genderMale) {
			if (Integer.parseInt(sAge) < 18) {
				sFeet = Integer.toString(rand.nextInt(6 - 4) + 4);
				if (sFeet.equals("4")) {
					sInches = Integer.toString(rand.nextInt(12 - 10) + 10);
				}
				else {
					sInches = Integer.toString(rand.nextInt(12));
				}
			}
			if (Integer.parseInt(sAge) >= 18 && Integer.parseInt(sAge) < 65) {
				sFeet = Integer.toString(rand.nextInt(7 - 4) + 4);
				if (sFeet.equals("4")) {
					sInches = Integer.toString(rand.nextInt(12 - 10) + 10);
				}
				else if (sFeet.equals("5")) {
					sInches = Integer.toString(rand.nextInt(12));
				}
				else {
					sInches = Integer.toString(rand.nextInt(4));
				}
			}
			if (Integer.parseInt(sAge) >= 65) {
				sFeet = Integer.toString(rand.nextInt(6 - 4) + 4);
				if (sFeet.equals("4")) {
					sInches = Integer.toString(rand.nextInt(12-10) + 10);
				}
				else {
					sInches = Integer.toString(rand.nextInt(12));
				}
			}
		}
		////////////////////////////////////////////////////
		// male height                                    //
		///////////////////////////////////////////////////
		else {
			if (Integer.parseInt(sAge) < 18) {
				sFeet = Integer.toString(rand.nextInt(6 - 4) + 4);
				if (sFeet.equals("4")) {
					sInches = Integer.toString(rand.nextInt(12 - 10) + 10);
				}
				else {
					sInches = Integer.toString(rand.nextInt(12));
				}
			}
			if (Integer.parseInt(sAge) >= 18 && Integer.parseInt(sAge) < 65) {
				sFeet = Integer.toString(rand.nextInt(7 - 5) + 5);
				if (sFeet.equals("5")) {
					sInches = Integer.toString(rand.nextInt(12 - 8) + 8);
				}
				else {
					sInches = Integer.toString(rand.nextInt(6));
				}
			}
			if (Integer.parseInt(sAge) >= 65) {
				sFeet = Integer.toString(rand.nextInt(7 - 5) + 5);
				if (sFeet.equals("5")) {
					sInches = Integer.toString(rand.nextInt(12));
				}
				else {
					sInches = Integer.toString(rand.nextInt(3));
				}
			}
		}
		// calculate the total feet to make it easier to convert height to cm
		int nTotalFeet = Integer.parseInt(sFeet) * 12 + Integer.parseInt(sInches);
		return sFeet +"'" +sInches +"'' (" +(int) (nTotalFeet * 2.54) +" cm)";
	} // end method generateHeight
	
	/**
	 * String method to generate weight in lb and kg.
	 * @return the weight as a String.
	 */
	public String generateWeight() {
		DecimalFormat df = new DecimalFormat("#.##");
		String sWeight = "";
		////////////////////////////////////////////////////
		// female weight                                  //
		///////////////////////////////////////////////////
		if (!genderMale) {
			if (Integer.parseInt(sAge) < 18) {
				sWeight = Integer.toString(rand.nextInt(110 - 70) + 70);
			}
			if (Integer.parseInt(sAge) >= 18 && Integer.parseInt(sAge) < 65) {
				sWeight = Integer.toString(rand.nextInt(210 - 160) + 160);
			}
			if (Integer.parseInt(sAge) >= 65) {
				sWeight = Integer.toString(rand.nextInt(170 - 140) + 140);
			}
		}
		////////////////////////////////////////////////////
		// male height                                    //
		///////////////////////////////////////////////////
		else {
			if (Integer.parseInt(sAge) < 18) {
				sWeight = Integer.toString(rand.nextInt(130 - 70) + 70);
			}
			if (Integer.parseInt(sAge) >= 18 && Integer.parseInt(sAge) < 65) {
				sWeight = Integer.toString(rand.nextInt(250 - 160) + 160);
			}
			if (Integer.parseInt(sAge) >= 65) {
				sWeight = Integer.toString(rand.nextInt(200 - 160) + 160);
			}	
		}
		// include kg in String, which is lb/2.2046
		return sWeight + " lb (" +df.format((Integer.parseInt(sWeight)/2.2046)) +" kg)";
	} // end method generateWeight
	
	/**
	 * String method to generate martial status.
	 * @return the martial status as a String.
	 */
	public String generateMartialStatus() {
		String[] martialStatusArr = { "Married", "Divorced", "Widowed", "Single" };
		String sMartialStatus = "";
		// ppl under 19 are not married in this program
		if (Integer.parseInt(sAge) < 19) {
			sMartialStatus = "Single";
		}
		else {
			sMartialStatus = martialStatusArr[rand.nextInt(martialStatusArr.length)];
		}
		// single ppl are included as never married, setting the boolean to be
		// used to determine maiden name
		if (sMartialStatus.equals("Single")) {
			neverMarried = true;
		}
		else {
			neverMarried = false;
		}
		return sMartialStatus;
	} // end method generateMartialStatus
	
	/**
	 * String method to generate a blood type.
	 * @return the blood type as a String.
	 */
	public String generateBloodType() {
		String[] bloodTypeArr = { "A", "B", "AB", "O" };
		String sBloodType = bloodTypeArr[rand.nextInt(bloodTypeArr.length)];
		return sBloodType;
	} // end method generateBloodType
	
	/**
	 * String method to generate a person's eye color.
	 * @return the eye color as a String.
	 */
	public String generateEyeColor() {
		String[] eyeColorArr = { "Brown", "Hazel", "Amber", "Blue", "Green" };
		String sEyeColor = eyeColorArr[rand.nextInt(eyeColorArr.length)];
		return sEyeColor;
	} // end method generateEyeColor
	
	/**
	 * String method to generate a person's hair color.
	 * @return the person's hair color as a String.
	 */
	public String generateHairColor() {
		String[] hairColorArr = { "Black", "Brown", "Gray", "White", "Blonde", "Red" };
		String sHairColor = hairColorArr[rand.nextInt(hairColorArr.length)];
		// do not give ppl under 35 gray or white hair
		if (Integer.parseInt(sAge) < 35) {
			do {
				sHairColor = hairColorArr[rand.nextInt(hairColorArr.length)];
			} while (sHairColor.equals("Gray") || sHairColor.equals("White"));
		}
		return sHairColor;
	} // end method generateHairColor
	
	/**
	 * String method to generate a favorite color.
	 * @return the color as a String.
	 */
	public String generateFavoriteColor() {
		String colorsArr[] = { "Blue", "Black", "Green", "Pink", "Red", "Orange", "Yellow",
							"Cyan", "White", "Grey" };
		String sColor = colorsArr[rand.nextInt(colorsArr.length)];
		return sColor;
	} // end method generateFavoriteColor
	
	/**
	 * String method to generate a maiden name.
	 * @return the maiden name as a String.
	 */
	public String generateMaidenName() {
		String sMaidenName = "";
		// if male, then do not generate a maiden name
		if (genderMale) {
			sMaidenName = "N/A";
		}
		else {
			// if the person never married, the maiden name would be the
			// same as the current last name, otherwise generate a random
			// maiden name
			if (neverMarried) {
				sMaidenName = sLastName;
			}
			else {
				sMaidenName = lastNamesArr.get(rand.nextInt(lastNamesArr.size()));
			}
		}
		return sMaidenName;
	} // end method generateMaidenName
	
	/**
	 * String method to generate a mother's maiden name.
	 * @return the mother's maiden name as a String.
	 */
	public String generateMothersMaidenName() {
		String sMothersMaidenName = lastNamesArr.get(rand.nextInt(lastNamesArr.size()));
		return sMothersMaidenName;
	} // end method generateMothersMaidenName
	
	/**
	 * String method to generate a favorite food.
	 * @return the person's favorite food as a String.
	 */
	public String generateFavoriteFood() {
		String[] foodArr = { "Steak", "Carrot", "Potato", "Apple", "Orange", "Rice", "Salmon",
				"Beans", "Hamburger", "Shrimp", "Pancakes", "Turkey", "Chicken", "Grapes", 
				"Blueberries", "Strawberries" };
		String sFavoriteFood = foodArr[rand.nextInt(foodArr.length)];
		return sFavoriteFood;
	} // end method generateFavoriteFood
	
	/**
	 * String method to generate how many children the person has/had.
	 * @return the number of children born to the person as a String.
	 */
	public String generateNumberOfChildren() {
		String sNumberOfChildren = "";
		// ppl under 20 do not have children in this program
		if (Integer.parseInt(sAge) < 20) {
			sNumberOfChildren = "0";
		}
		// random number of kids between 0 and 4
		else {
			sNumberOfChildren = Integer.toString(rand.nextInt(5));
		}
		return sNumberOfChildren;
	} // end method generateNumberOfChildren
	
	/**
	 * String method to generate a random car.
	 * @return the car owned by the person as a String.
	 */
	public String generateCar() {
		String sCar = "";
		String[] yearsArr = { "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008",
				"2009", "2010" };
		String[] carArr = { "Chevy Malibu", "Ford F-150", "Mazda 3 Sport", "Hyundai Sonata", "Toyota Camry",
				"Mazda 6", "Hyundai Elantra", "Kia Rio", "Nissan Altima", "Honda Accord", "Honda Civic",
				"Ford Fusion", "Chevy Silverado", "GMC Sierra", "Dodge Grand Caravan" };
		// ppl under 20 do not own a car in this program
		if (Integer.parseInt(sAge) < 20) {
			sCar = "N/A";
		}
		else {
			sCar = yearsArr[rand.nextInt(yearsArr.length)] +" " +carArr[rand.nextInt(carArr.length)];
		}
		return sCar;
	} // end method generateNumberOfChildren
} // end class RandomPersonGenerator