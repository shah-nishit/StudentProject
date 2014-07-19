package com.student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class OperationUtils 
{
	static StudentUtils objStudentUtils = new StudentUtils();
	static ValidationUtils objValidationUtils = new ValidationUtils();
	static final String filePath = System.getProperty("user.dir") + "\\src\\files\\";
	//static final String fileName = "StudentData.txt";
	static final String fileName = "StudentData.dat";
	String firstName = "";
	
	public void mainMenuView()
	{
		System.out.println("***********************************************************\n" + 
				"Please select any one option from the Main Menu: \n" + 
				"***********************************************************\n" +
				"\t1. Add New/Update/View Student Record(s)\n" + 
				"\t2. Sort Student Record(s)\n" + 
				"\t3. Search for Student Record(s)\n" + 
				"\t0. Exit\n");
		System.out.print("Enter your choice: ");
	}
		
	public void addUpdateViewSubMenuView()
	{
		System.out.println("\t1. Add Student Record\n" + 
				   "\t2. Update Student Record\n" +
				   "\t3. View Student Record(s)\n" +
				   "\t0. Exit\n" + 
				   "\t9. For Main Menu\n");
		System.out.print("Enter your choice: ");
	}
	
	public void updateSubMenuView()
	{
		System.out.println("\n***********************************************************\n" + 
				"Which information will you like to modify?\n"+
				"Please select any one option from the following Sub Menu:\n" + 
				"***********************************************************\n"+
				"\t1. Update First Name\n" + 
				"\t2. Update Last Name\n" +
				"\t3. Update Major Course\n" +
				"\t4. Update Phone Number\n" +
				"\t5. Update GPA\n" +
				"\t6. Update Date of Birth\n" +
				"\t0. Exit\n" + 
				"\t9. For Main Menu\n");
		System.out.print("Enter your choice: ");
	}
	
	public void sortSubMenuView()
	{
		System.out.println("\t1. Sort By First Name\n" + 
				   "\t2. Sort By Last Name\n" + 
				   "\t3. Sort By Major Course\n" +
				   "\t0. Exit\n" + 
				   "\t9. For Main Menu\n");
		System.out.print("Enter your choice: ");
	}
	
	public void searchSubMenuView()
	{
		System.out.println("\t1. Search By Student ID\n" + 
				   "\t2. Search By Last Name\n" + 
				   "\t3. Search By Major Course\n" +
				   "\t0. Exit\n" + 
				   "\t9. For Main Menu\n");
		System.out.print("Enter your choice: ");
	}
	
	//This function will add student records to the file.
	public void addRecord()
	{
		Scanner info = new Scanner(System.in);
		try 
		{	
			System.out.print("Enter Student ID (C0xxxxxx): ");
			String studentID = info.next();
			objStudentUtils.setStudentID(studentID);
			
			System.out.print("Enter Student First Name (Only Alphabets): ");
			firstName = info.next();
			objStudentUtils.setFirstName(firstName);
			
			System.out.print("Enter Student Last Name (Only Alphabets): ");
			String lastName = info.next();
			objStudentUtils.setLastName(lastName);
			
			System.out.print("Enter Major Course (Only Alphabets): ");
			String majorCourse = info.next();
			objStudentUtils.setMajorCourse(majorCourse.toUpperCase());
			
			System.out.print("Enter Student Phone Number (xxx-xxx-xxxx): ");
			String phoneNumber = info.next();
			objStudentUtils.setPhoneNumber(phoneNumber);
			
			System.out.print("Enter Student GPA (0.0-4.0): ");
			String GPA = info.next();
			objStudentUtils.setGPA(GPA);
			
			System.out.print("Enter Student Date of Birth (DD/MM/YYYY): ");
			String dateOfBirth = info.next();
			objStudentUtils.setDateOfBirth(dateOfBirth);
			
			if (objValidationUtils.validateData(objStudentUtils.getStudentID(), objStudentUtils.getFirstName(), objStudentUtils.getLastName(), objStudentUtils.getMajorCourse(), objStudentUtils.getPhoneNumber(), objStudentUtils.getGPA(), objStudentUtils.getDateOfBirth())
					&& objValidationUtils.isThisDateValid(objStudentUtils.getDateOfBirth()))
			{
				objStudentUtils.setPhoneNumber(objValidationUtils.phoneFormat(objStudentUtils.getPhoneNumber()));
				objStudentUtils.setFirstName(objValidationUtils.capitalFirstLetter(objStudentUtils.getFirstName()));
				objStudentUtils.setLastName(objValidationUtils.capitalFirstLetter(objStudentUtils.getLastName()));
				if (writeToFile())
				{
					System.out.println("\n***********************************************************\n" + 
					"Recorded added successfully for "  + objStudentUtils.getFirstName() + " " + objStudentUtils.getLastName() + "...!!!\n" +  
					"***********************************************************\n");
					ChoiceUtils.mainMenu();
					
				}
				else
				{
					System.out.println("\n***********************************************************" + 
					"Recorded was not successfully added for "  + objStudentUtils.getFirstName() + " " + objStudentUtils.getLastName() + "...!!!\n" + 
					"***********************************************************\n");
					ChoiceUtils.mainMenu();
				}
			}
			else
			{
				ChoiceUtils.mainMenu();
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception from addRecord: " + e.getMessage());
		}
		finally
		{
			info.close();
		}
	}
	
	//This function will update the existing student records to the file.
	public void updateRecord(String studentID, String oldValue, String newValue)
	{
		try
		{
			FileReader fileReader = new FileReader(filePath + fileName);
			BufferedReader bufferReader = new BufferedReader(fileReader);
			String line;
	        StringBuffer input = new StringBuffer();
	        boolean isReplaced = false;
	        while ((line = bufferReader.readLine()) != null)
	        {
	        	//System.out.println(input); // check that it's inputed right
		        //System.out.println(line.matches(".*" + studentID + ",.*" + oldValue + ".*"));
	        	
		        if (line.matches(".*" + studentID + ",.*" + oldValue + ".*"))
		        {
		        	String formattedValue = objValidationUtils.phoneFormat(newValue);
		        	if (!oldValue.equals(formattedValue))
		        	{
		        		line = line.replace(oldValue, formattedValue);
		        		isReplaced = true;
		        	}
		        }
		        input.append(line + "\n");
	        }
			if (isReplaced)
			{
			// write the new String with the replaced line OVER the same file
				FileOutputStream fos = new FileOutputStream(filePath + fileName);
				fos.write(input.toString().getBytes());
				System.out.println("\n*****************************************" + 
						"\nRecord updated for Student ID: " + studentID + 
						"\n*****************************************");
				searchByStudentID(studentID, false, true);
				fos.close();
			}
			else
			{
				System.out.println("\n*****************************************" + 
						"\nNo Record updated for Student ID: " + studentID + 
						"\n*****************************************");
			}
			bufferReader.close();
			fileReader.close();
			
			ChoiceUtils.mainMenu();
	    } 
		catch (Exception e)
		{
			System.out.println("Exception from updateRecord: " + e.getMessage());
		}
	}
	
	//This function will view all students records present in the file.
	public void viewRecords()
	{
		try
		{
			ArrayList<StudentUtils> studentList = getAllStudentData(true);
			if(studentList.size() != 0)
			{
				System.out.println("\n*******************************" + 
				"\nTotal Record(s) found: " + studentList.size() + 
				"\n*******************************");
				for (int i = 0; i < studentList.size(); i++)
				{
					objStudentUtils = studentList.get(i);
					System.out.println("*******************************\n" + (i + 1) + ") Record for Student: " + studentList.get(i).getStudentID() + 
							"\n********************************" + 
							objStudentUtils.toString());
				}
			}
			ChoiceUtils.mainMenu();
		} 
		catch (Exception e)
		{
			System.out.println("Exception from updateRecord: " + e.getMessage());
		}
	}
	
	//This function will exit user from the application.
	public void exitMenu()
	{
		System.out.println("\n***********************************************************\n" + 
				"Exiting, Thank you for using this application...!!!\n" + 
				"***********************************************************");
		System.exit(0);
	}

	// Appending student data to the file.
	public boolean writeToFile()
	{
		try
		{
			FileWriter fileWriter = new FileWriter(filePath + fileName, true);
			PrintWriter outputFile = new PrintWriter(fileWriter);
			
			outputFile.println(objStudentUtils.getStudentID() + ", " + objStudentUtils.getFirstName() + ", " + objStudentUtils.getLastName() + 
					", " + objStudentUtils.getMajorCourse() + ", " + objStudentUtils.getPhoneNumber() +
					", " + objStudentUtils.getGPA() + ", " + objStudentUtils.getDateOfBirth());
			
			outputFile.close();
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception from writeToFile" + e.getMessage());
			return false;
		}
	}
	
	public ArrayList<StudentUtils> getAllStudentData(boolean displayNoRecordFound)
	{
		try
		{
			File fileRead = new File(filePath + fileName);
		    Scanner lineScanner = new Scanner(fileRead);
		    //ArrayList<String> studentData = new ArrayList<String>();    
		    ArrayList<StudentUtils> studentsArrayList = new ArrayList<StudentUtils>();
			 // Read lines from the file until no more are left.
			 while (lineScanner.hasNext())
			 {
				 // Read the next name.
				 String studentInfo = lineScanner.nextLine();
			
				 // Display the last name read.
				 String[] studentData = studentInfo.split(",");
				 
				 studentsArrayList.add(new StudentUtils(studentData[0].trim(), studentData[1].trim(), studentData[2].trim(), studentData[3].trim(), studentData[4].trim(), studentData[5].trim(), studentData[6].trim())); 
			 }
			 
			 // Close the file.
			 lineScanner.close();
			 if (studentsArrayList.size() != 0)
			 {
				 return studentsArrayList;
			 }
			 else if (displayNoRecordFound)
			 {
				 System.out.println("*****************************\nSorry, No Record Found...!!!" +   
							"\n*****************************");
				 ChoiceUtils.mainMenu();
				 return null;
			 }
		}
		catch(Exception e)
		{
			System.out.println("Exception from readFromFile: " + e.getMessage());
			return null;
		}
		return null;
	}

	public void readFromFile()
	{
		try
		{
			FileInputStream fileInputStream = new FileInputStream(filePath + fileName);
		    ObjectInputStream objInputFile = new ObjectInputStream(fileInputStream);
		     
			StudentUtils[] studentList = (StudentUtils[])objInputFile.readObject();
		
			for(int i = 0; i < studentList.length; i++)
			{
				System.out.println(studentList[i]);
			}
			objInputFile.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception from readFromFile: " + e);
		}
		
	}
	public void sortByLastName()
	{
		try
		{
			StudentUtils temp;
			ArrayList<StudentUtils> studentList = getAllStudentData(true);
			if(studentList != null)
			{
				for(int i = 0; i < studentList.size() - 1; i++)
				{
					for(int j = i + 1; j < studentList.size(); j++)
					{
						if(studentList.get(i).getLastName().compareTo(studentList.get(j).getLastName()) > 0)
						{
							temp = studentList.get(i);
							studentList.set(i, studentList.get(j));
							studentList.set(j, temp);
						}
					}
				}
			
				for (int i = 0; i < studentList.size(); i++)
				{
					objStudentUtils = studentList.get(i);
					System.out.println("*******************************\n" + (i + 1) + ") Record for Student: " + studentList.get(i).getStudentID() + 
							"\n********************************" + 
							objStudentUtils.toString());
				}
			}
			else
			{
				System.out.println("*****************************\nSorry, No Record Found...!!!\n" +  
						"\n*****************************");
			}
			ChoiceUtils.mainMenu();
		}
		catch (Exception e)
		{
			System.out.println("Exception from sortByLastName: " + e.getMessage());
		}
		
	}
	
	public void sortByFirstName()
	{
		try
		{
			StudentUtils temp;
			ArrayList<StudentUtils> studentList = getAllStudentData(true);
			if(studentList != null)
			{
				for(int i = 0; i < studentList.size() - 1; i++)
				{
					for(int j = i + 1; j < studentList.size(); j++)
					{
						if(studentList.get(i).getFirstName().compareTo(studentList.get(j).getFirstName()) > 0)
						{
							temp = studentList.get(i);
							studentList.set(i, studentList.get(j));
							studentList.set(j, temp);
						}
					}
				}
			
				for (int i = 0; i < studentList.size(); i++)
				{
					objStudentUtils = studentList.get(i);
					System.out.println("*******************************\n" + (i + 1) + ") Record for Student: " + studentList.get(i).getStudentID() + 
							"\n********************************" + 
							objStudentUtils.toString());
				}
			}
			else
			{
				System.out.println("*****************************\nSorry, No Record Found...!!!\n" +  
						"\n*****************************");
			}
			ChoiceUtils.mainMenu();
		}
		catch (Exception e)
		{
			System.out.println("Exception from sortByLastName: " + e.getMessage());
		}	
	}
	
	public void sortByMajorCourse()
	{
		try
		{
			StudentUtils temp;
			ArrayList<StudentUtils> studentList = getAllStudentData(true);
			if(studentList != null)
			{
				for(int i = 0; i < studentList.size() - 1; i++)
				{
					for(int j = i + 1; j < studentList.size(); j++)
					{
						if(studentList.get(i).getMajorCourse().compareTo(studentList.get(j).getMajorCourse()) > 0)
						{
							temp = studentList.get(i);
							studentList.set(i, studentList.get(j));
							studentList.set(j, temp);
						}
					}
				}
			
				for (int i = 0; i < studentList.size(); i++)
				{
					objStudentUtils = studentList.get(i);
					System.out.println("*******************************\n" + (i+1) + ") Record for Student: " + studentList.get(i).getStudentID() + 
							"\n********************************" + 
							objStudentUtils.toString());
				}
			}
			else
			{
				System.out.println("*****************************\nSorry, No Record Found...!!!\n" +  
						"\n*****************************");
			}
			ChoiceUtils.mainMenu();
		}
		catch (Exception e)
		{
			System.out.println("Exception from sortByLastName: " + e.getMessage());
		}		
	}
	
	public boolean searchByStudentID(String studentID, boolean isCalledFromSearch, boolean displayNoRecordFound)
	{
		boolean found = false;
		try
		{
			ArrayList<StudentUtils> studentList = getAllStudentData(displayNoRecordFound);
			ArrayList<StudentUtils> searchList = new ArrayList<StudentUtils>();
			if(studentList != null)
			{
				for(int i = 0; i < studentList.size(); i++)
				{
					if(studentList.get(i).getStudentID().equals(studentID))
					{
						found = true;
						searchList.add(studentList.get(i));
					}
				}
				if(found)
				{
					for(int i = 0; i < searchList.size(); i++)
					{
						System.out.println("Total Record(s) Found: " + searchList.size());
						objStudentUtils = searchList.get(i);
						System.out.println("*****************************\n"+ (i + 1) + ") Record Found for: " + studentID +  
								"\n*****************************" + 
								objStudentUtils.toString());
					}
				}
				else if (displayNoRecordFound)
				{
					System.out.println("*****************************\nSorry, No Record Found for: " + studentID +  
							"\n*****************************");
				}
			}
			if (isCalledFromSearch)
			{
				ChoiceUtils.mainMenu();
				return found;
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception from searchByStudentID: " + e.getMessage());
		}
		return found;
	}
	
	public void searchByLastName(String lastName)
	{
		try
		{
			boolean found = false;
			
			ArrayList<StudentUtils> studentList = getAllStudentData(true);
			ArrayList<StudentUtils> searchList = new ArrayList<StudentUtils>();
			if(studentList != null)
			{
				for(int i = 0; i < studentList.size(); i++)
				{
					if(studentList.get(i).getLastName().equals(lastName))
					{
						found = true;
						searchList.add(studentList.get(i));
					}
				}
				if(found)
				{
					for(int i = 0; i < searchList.size(); i++)
					{
						System.out.println("Total Record(s) Found: " + searchList.size());
						objStudentUtils = searchList.get(i);
						System.out.println("*****************************\n"+ (i + 1) + ") Record Found for: " + lastName +  
								"\n*****************************" + 
								objStudentUtils.toString());
					}
				}
				else
				{
					System.out.println("*****************************\nSorry, No Record Found for: " + lastName +  
							"\n*****************************");
				}
			}
			ChoiceUtils.mainMenu();
		}
		catch (Exception e)
		{
			System.out.println("Exception from searchByStudentID: " + e.getMessage());
		}
	}
	
	public void searchByMajorCourse(String majorCourse)
	{
		try
		{
			boolean found = false;
			
			ArrayList<StudentUtils> studentList = getAllStudentData(true);
			ArrayList<StudentUtils> searchList = new ArrayList<StudentUtils>();
			if(studentList != null)
			{
				for(int i = 0; i < studentList.size(); i++)
				{
					if(studentList.get(i).getMajorCourse().equals(majorCourse))
					{
						found = true;
						searchList.add(studentList.get(i));
					}
				}
				if(found)
				{
					for(int i = 0; i < searchList.size(); i++)
					{
						System.out.println("Total Record(s) Found: " + searchList.size());
						objStudentUtils = searchList.get(i);
						System.out.println("*****************************\n"+ (i + 1) + ") Record Found for: " + majorCourse +  
								"\n*****************************" + 
								objStudentUtils.toString());
					}
				}
				else
				{
					System.out.println("*****************************\nSorry, No Record Found for: " + majorCourse +  
							"\n*****************************");
				}
			}
			ChoiceUtils.mainMenu();
		}
		catch (Exception e)
		{
			System.out.println("Exception from searchByStudentID: " + e.getMessage());
		}
	}
}