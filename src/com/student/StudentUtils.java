package com.student;

public class StudentUtils
{
	private String studentID;
	private String firstName;
	private String lastName;
	private String majorCourse;
	private String phoneNumber;
	private String GPA;
	private String dateOfBirth;

	public StudentUtils()
	{
		
	}
	
	public StudentUtils(String studentID, String firstName, String lastName, String majorCourse, String phoneNumber, String GPA, String dateOfBirth)
	{
		this.studentID = studentID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.majorCourse = majorCourse;
		this.phoneNumber = phoneNumber;
		this.GPA = GPA;
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getStudentID()
	{
		return studentID;
	}

	public void setStudentID(String studentID)
	{
		this.studentID = studentID;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getMajorCourse()
	{
		return majorCourse;
	}

	public void setMajorCourse(String majorCourse)
	{
		this.majorCourse = majorCourse;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public String getGPA()
	{
		return GPA;
	}

	public void setGPA(String GPA) {
		this.GPA = GPA;
	}

	public String getDateOfBirth()
	{
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}
	
	@Override
	public String toString()
	{
		String result;
		
		result =  "\nStudent ID\t=====>\t" + getStudentID();
		result += "\nFirst Name\t=====>\t" + getFirstName();
		result += "\nLast Name\t=====>\t" + getLastName();
		result += "\nMajor Course\t=====>\t" + getMajorCourse();
		result += "\nPhone No.\t=====>\t" + getPhoneNumber();
		result += "\nGPA\t\t=====>\t" + getGPA();
		result += "\nDate of Birth\t=====>\t" + getDateOfBirth() + "\n";
		return result;
	}
}
