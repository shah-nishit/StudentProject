package com.student;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils 
{
	StudentUtils objStudentUtils = new StudentUtils();
	OperationUtils objOperationUtils = new OperationUtils();
	
	public boolean validateChoice(int value)
	{
		return true;
	}
	
	public boolean isNumeric(int value)
	{
		return (Integer.toString(value)).matches("\\d");
	}

	public boolean validateData(String studentID, String firstName, String lastName, String majorCourse, String phoneNumber, String GPA, String dateOfBirth)
	{
		if(objOperationUtils.searchByStudentID(studentID, false, false))
		{
			System.out.println("Student Record already exsists for: " + studentID);
			return false;
		}
		else 
		{
			if (!studentID.matches("^C0(\\d{6})$"))
			{
				System.out.println("Please enter a valid Student ID (C0xxxxxx).");
				return false;
			}
			else if (!firstName.matches("[a-zA-Z]+"))
			{
				System.out.println("There shouldn't be any digit in the First Name.");
				return false;
			}
			else if (!lastName.matches("[a-zA-Z]+"))
			{
				System.out.println("There shouldn't be any digit in the Last Name.");
				return false;
			}
			else if (!majorCourse.matches("[A-Z]+"))
			{
				System.out.println("There shouldn't be any digit in the Major Course.");
				return false;
			}
			else if(phoneNumber.length() != 10 && phoneNumber.length() != 12)
			{
				System.out.println("Please enter proper phone number (xxx-xxx-xxxx).");
				return false;
			}
			else if(Double.parseDouble(GPA) < 0.0 || Double.parseDouble(GPA) > 4.0)
			{
				System.out.println("GPA should be in the range of 0.0 to 4.0");
				return false;
			}
			return true;
		}
	}

	public String phoneFormat(String phoneNumber) 
	{
		if ((!phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}")) && phoneNumber.length() == 10)
		{
			String formatPhoneNumber = phoneNumber.substring(0,3) + "-" + phoneNumber.substring(3,6) + "-" + phoneNumber.substring(6,10);
			return formatPhoneNumber;
		}
		return phoneNumber;
	}
	
	public boolean isThisDateValid(final String date)
	 {
		final String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(DATE_PATTERN);
		matcher = pattern.matcher(date);
		if(matcher.matches())
		{
			matcher.reset();
			if(matcher.find())
			{
				String day = matcher.group(1);
				String month = matcher.group(2);
				int year = Integer.parseInt(matcher.group(3));
				if (day.equals("31") && (month.equals("4") || month.equals("6") || month.equals("9") ||
                 month.equals("11") || month.equals("04") || month.equals("06") ||
                 month.equals("09")))
				{
					System.out.println("Please enter valid Date of Birth in proper format (DD/MM/YYYY).");
					return false; // only 1,3,5,7,8,10,12 has 31 days
				}
				else if (month.equals("2") || month.equals("02"))
				{
					//leap year
					if(year % 4==0)
					{
						if(day.equals("30") || day.equals("31"))
						{
							System.out.println("Please enter valid Date of Birth in proper format (DD/MM/YYYY).");
							return false;
						}
						else
						{
							return true;
						}
					}
					else
					{
						if(day.equals("29")||day.equals("30")||day.equals("31"))
						{
							System.out.println("Please enter valid Date of Birth in proper format (DD/MM/YYYY).");
							return false;
						}
						else
						{
							return true;
						}
					}
				}
				else
				{				 
					return true;				 
				}
			}
			else
			{
				System.out.println("Please enter valid Date of Birth in proper format (DD/MM/YYYY).");
				return false;
			}		  
		}
		else
		{
			System.out.println("Please enter valid Date of Birth in proper format (DD/MM/YYYY).");
			return false;
		}
	 }
	
	public String capitalFirstLetter(String value)
	{
		return value.substring(0, 1).toUpperCase() + value.substring(1);
	}
}

