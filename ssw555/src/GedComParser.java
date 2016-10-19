import java.util.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class GedComParser {
	public static final String absPath = new File("").getAbsolutePath() + "/resources/";
	
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.print("Provide GEDCOM file name: ");
		File file = new File(absPath + input.nextLine());
		input.close();
		
		//Set<String> eligibleTags = getEligibleTags();
		input = new Scanner(file);
		
		Map<String, Person> personMap = new HashMap<>();
		Map<String, Family> familyMap = new HashMap<>();
		Person p = null;
		Family f = null;
		NuptialInfo ni = null;
		String nextDate = null;
		while (input.hasNextLine()) {
            String line = input.nextLine();
            
            String[] lineArr = line.split(" ");
            String level = lineArr[0];
            
            String tagName = lineArr[1];
            if(level.equals("0") && tagName.startsWith("@")) {
            	tagName = lineArr[2];
            	String id = lineArr[1];
            	
            	if(tagName.equals("INDI")) {
            		p = new Person();
            		p.setId(id);
            		personMap.put(id, p);
            	}
            	
            	if(tagName.equals("FAM")) {
            		f = new Family();
            		f.setId(id);
            		familyMap.put(id, f);
            	}
            }
          //-------------------I got an exception because of SUBM tag so add this
        	if(tagName.equals("SUBM")) {
        		input.nextLine();
        	}
        	//---------------------------------------------------------
    	/*	tagName = eligibleTags.contains(tagName) ? tagName : "Invalid tag";
            System.out.println("tag:\t" + tagName);
            System.out.println();
            
          
            if(tagName.equals("Invalid Tag"))
            	continue;*/
            
            
            if(tagName.equals("HUSB")) {
            	Person husband = personMap.get(lineArr[2]);
            	f.setHusband(husband);
            }
            if(tagName.equals("WIFE")) {
            	Person wife = personMap.get(lineArr[2]);
            	f.setWife(wife);
            }
            if(tagName.equals("CHIL")) {
            	Person child = personMap.get(lineArr[2]);
            	f.addChildren(child);
            }
            
            if(tagName.equals("NAME")) {
            	if(lineArr[3].charAt(0)=='/'){
            	p.setfName(lineArr[2]);
            	p.setlName(lineArr[3]);
            	}else
            	{p.setfName(lineArr[2]+" "+lineArr[3]);
            	 p.setlName(lineArr[4]);	
            	}
            }
            if(tagName.equals("SEX")) {
            	p.setSex(lineArr[2]);
            }
            if(tagName.equals("FAMC")) {
          	p.setChildOfFamilyId(lineArr[2]);
              }
            if(tagName.equals("FAMS")) {
            	ni=new NuptialInfo();
            	p.addNuptials(ni);
            	ni.setSpouseOfFamilyId(lineArr[2]);
            
            }
            //-----------------------------------------------------------
            if(nextDate != null) {
            	SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
            	Date dt = sdf.parse(lineArr[2] + " " + lineArr[3] + " " + lineArr[4]);
            	if(nextDate.equals("BIRT")) {
            		p.setBirthDate(dt);
            	}
            	if(nextDate.equals("DEAT")) {
            		p.setDeathDate(dt);
            	}
            	if(nextDate.equals("MARR")) {
            		//ni = new NuptialInfo();
            		ni.setMarriageDate(dt);
            		//p.addNuptials(ni);
            	}
            	if(nextDate.equals("DIV")) {
            		ni.setDivorceDate(dt);
            	}
            }
            if(tagName.equals("BIRT") || tagName.equals("DEAT") || tagName.equals("MARR") || tagName.equals("DIV")) {
            	nextDate = tagName;
            } else {
            	nextDate = null;
            }
        }
		input.close();
		//System.out.println("End of parsing");
		
		//----------------Individuals in the File-------------------// 
		System.out.println("\n---------list of Individuals------------------------");
		for(Map.Entry<String, Person> entry : personMap.entrySet()) {
			
			System.out.println(entry.getValue().getId()+": "+entry.getValue().getlName()+entry.getValue().getfName());
			
		}
		//----------------Families in the File-------------------// 
		System.out.println("\n-----------List of Families----------------------");
		for(Map.Entry<String, Family> entry : familyMap.entrySet()) {
			
			System.out.println(entry.getValue());
			
		}
		
		//------------------------user stories test ----------------------------//
		US30ListLivingMarried(familyMap);
		US28OrderSiblingsByAge(familyMap);
		 US31ListLivingSingle(personMap);
		US33ListOrphans(familyMap);
	}
	
	/*private static Set<String> getEligibleTags() throws Exception {
		Set<String> tags = new HashSet<>();
		Scanner i = new Scanner(new File(absPath + "tags.txt"));
		while(i.hasNextLine()) {
			tags.add(i.nextLine());
		}
		i.close();
		return tags;
	}*/
	// US29 Order siblings by age, Owner: Shahad
	public static void US28OrderSiblingsByAge(Map<String, Family> f) {
		System.out.println("\n--------------US28-order siblings by age-------------");
		List<Person> children = new ArrayList<>();
		for (String key : f.keySet()) {
			children=f.get(key).getChildren();
			Collections.sort(children, 
                    (o1,o2) -> o1.getBirthDate().compareTo(o2.getBirthDate()));
	 
			System.out.println("\n children of family :"+ f.get(key).getId());
			 if(children.size()>0){
			for(int i=0; i<children.size(); ++i){
			System.out.printf(children.get(i).getlName()+children.get(i).getfName() );
			 System.out.printf("%1$s %2$tB %2$td, %2$tY %n"," date of birth: ",children.get(i).getBirthDate()); 
			}
			}
			
		}
		
	}
	

	
	// US30 list living married, Owner: Shahad

		public static void US30ListLivingMarried(Map<String, Family> f) {
			List<String> list = new ArrayList<String>();
				   String married ;
			System.out.println("\n--------------US30-List of Living Married-------------");
			
			for (String key : f.keySet()) {
				if(f.get(key).getHusband().getDeathDate()==null)
				{ married=f.get(key).getHusband().getId()+":  "+f.get(key).getHusband().getfName()+f.get(key).getHusband().getlName() ;
					if (!(list.contains(married))){
					 list.add(married);
					System.out.println(married);}
				}
				if(f.get(key).getWife().getDeathDate()==null)
				{ married=f.get(key).getWife().getId()+":  "+f.get(key).getWife().getfName()+f.get(key).getWife().getlName();
				if (!(list.contains(married))){
				    list.add(married);
					System.out.println(married);}
				}
				
			}
			 
		}
   // US31 list living Single, Owner: Shahad

		public static void US31ListLivingSingle(Map<String, Person> p) {
		 
				   LocalDate today = LocalDate.now();
				   //LocalDate birthday = LocalDate.of(1960, Month.JANUARY, 1);
 
			System.out.println("\n--------------US31-List of Living single-------------");
			
			for (String key : p.keySet()) {
				if(p.get(key).getBirthDate()!=null)
				{				 LocalDate birthday = LocalDate.of(p.get(key).getBirthDate().getYear()+1900,p.get(key).getBirthDate().getMonth()+1,p.get(key).getBirthDate().getDate() );
				Period age = Period.between(birthday,today);
				if(p.get(key).getNuptials().isEmpty() && age.getYears() >= 30 && p.get(key).getDeathDate()== null )
				{  
		
					System.out.println(p.get(key).getlName()+p.get(key).getfName());}
				}
			}
		}
	// US33 List orphans, Owner: Shahad

				public static void US33ListOrphans (Map<String, Family> f) {
					 LocalDate today = LocalDate.now();
					
					System.out.println("\n--------------US33-List orphans-------------");
					
					for (String key : f.keySet()) {
						if(f.get(key).getHusband().getDeathDate()!=null && f.get(key).getWife().getDeathDate()!=null)
						{  f.get(key).getChildren();
						 for(int i=0;i<f.get(key).getChildren().size();i++)
						 {
							 LocalDate birthday = LocalDate.of(f.get(key).getChildren().get(i).getBirthDate().getYear()+1900,f.get(key).getChildren().get(i).getBirthDate().getMonth()+1,f.get(key).getChildren().get(i).getBirthDate().getDate() );
								Period age = Period.between(birthday,today);
							 if(age.getYears()<18){
								 System.out.println(f.get(key).getChildren().get(i).getlName()+f.get(key).getChildren().get(i).getfName());
							 }
						 }
						}
						
					}
					 
				}
			 
		} 
