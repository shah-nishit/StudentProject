package com.student;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ChoiceUtils 
{
	static OperationUtils objOperationUtils = new OperationUtils();
	static ValidationUtils objValidationUtils = new ValidationUtils();
	static Scanner input = new Scanner(System.in);
	static int choice = 0;
	static String searchString = "";
	public static void mainMenu()
	{
		try
		{
			objOperationUtils.mainMenuView();
			choice = input.nextInt();
			String newValue;
			String oldValue;
			if (choice > 0 && choice < 4)
			{
				System.out.println("\n***********************************************************\n" + 
						"Please select any one option from the following Sub Menu:\n" + 
						"***********************************************************");
				switch (choice) 
				{
					case 1: objOperationUtils.addUpdateViewSubMenuView();
						choice = input.nextInt();
						switch (choice)
						{
							case 1 : objOperationUtils.addRecord();
								break;
							case 2 : System.out.print("Please provide with the Student ID for updating record: ");
								searchString = input.next();
								if (objOperationUtils.searchByStudentID(searchString, false, true))
								{
									objOperationUtils.updateSubMenuView();
									choice = input.nextInt();
									switch (choice)
									{
										case 1: oldValue = OperationUtils.objStudentUtils.getFirstName();
											System.out.print("Enter New First Name (Only Alphabets): ");
											newValue = input.next();
											if (!newValue.matches("[a-zA-Z]+"))
											{
												System.out.println("There shouldn't be any digit in the First Name.");
																							}
											else
											{
												newValue = objValidationUtils.capitalFirstLetter(newValue);
												objOperationUtils.updateRecord(searchString, oldValue, newValue);
											}
											break;
										case 2: oldValue = OperationUtils.objStudentUtils.getLastName();
											System.out.print("Enter New Last Name (Only Alphabets): ");
											newValue = input.next();
											if (!newValue.matches("[a-zA-Z]+"))
											{
												System.out.println("There shouldn't be any digit in the Last Name.");
											}
											else
											{
												newValue = objValidationUtils.capitalFirstLetter(newValue);
												objOperationUtils.updateRecord(searchString, oldValue, newValue);
											}
											break;
										case 3: oldValue = OperationUtils.objStudentUtils.getMajorCourse();
											System.out.print("Enter New Major Course (Only Alphabets): ");
											newValue = input.next();
											if (!newValue.matches("[A-Z]+"))
											{
												System.out.println("There shouldn't be any digit in the Major Course.");
											}
											else
											{
												objOperationUtils.updateRecord(searchString, oldValue, newValue);
											}
											break;
										case 4: oldValue = OperationUtils.objStudentUtils.getPhoneNumber();
											System.out.print("Enter New Phone Number: ");
											newValue = input.next();
											if(newValue.length() != 10 && newValue.length() != 12)
											{
												System.out.println("Please enter proper phone number (xxx-xxx-xxxx).");
											}
											else
											{
												objOperationUtils.updateRecord(searchString, oldValue, newValue);
											}
											break;
										case 5: oldValue = OperationUtils.objStudentUtils.getGPA();
											System.out.print("Enter New GPA: ");
											newValue = input.next();
											if(Double.parseDouble(newValue) < 0.0 || Double.parseDouble(newValue) > 4.0)
											{
												System.out.println("GPA should be in the range of 0.0 to 4.0");
											}
											else
											{
												objOperationUtils.updateRecord(searchString, oldValue, newValue);
											}
											break;
										case 6: oldValue = OperationUtils.objStudentUtils.getDateOfBirth();
											System.out.print("Enter New Date of Birth: ");
											newValue = input.next();
											if (objValidationUtils.isThisDateValid(newValue))
											{
												objOperationUtils.updateRecord(searchString, oldValue, newValue);
											}
											break;
										case 0 : objOperationUtils.exitMenu();
											break;
										case 9 :System.out.println("\n"); 
											mainMenu();
											break;
										default:
											System.out.println("\n***********************************************************\n" + 
												"Please select proper option.\nEnter 1 to continue.\n" + 
												"***********************************************************");
											choice = input.nextInt();
											if (choice == 1)
												mainMenu();
											else
											{
												input.close();
												objOperationUtils.exitMenu();
											}		
									}
								}
								mainMenu();
								break;
							case 3 : objOperationUtils.viewRecords();
								//objOperationUtils.readFromFile();
								break;
							case 0 : objOperationUtils.exitMenu();
								break;
							case 9 :System.out.println("\n"); 
								mainMenu();
								break;
							default:
								System.out.println("\n***********************************************************\n" + 
										"Please select proper option.\nEnter 1 to continue.\n" + 
										"***********************************************************");
								choice = input.nextInt();
								if (choice == 1)
									mainMenu();
								else
								{
									input.close();
									objOperationUtils.exitMenu();
								}		
						}
					break;
					case 2: objOperationUtils.sortSubMenuView();
						choice = input.nextInt();
						switch (choice)
						{
							case 1:  objOperationUtils.sortByFirstName();
								break;
							case 2: objOperationUtils.sortByLastName();
								break;
							case 3: objOperationUtils.sortByMajorCourse();
								break;
							case 0: objOperationUtils.exitMenu();
								break;
							case 9: System.out.println("\n"); 
								mainMenu();
								break;
							default:
								System.out.println("\n***********************************************************\n" + 
										"Please select proper option.\nEnter 1 to continue.\n" + 
										"***********************************************************");
								choice = input.nextInt();
								if (choice == 1)
									mainMenu();
								else
								{
									input.close();
									objOperationUtils.exitMenu();
								}
						}
					case 3: objOperationUtils.searchSubMenuView();
					choice = input.nextInt();
					switch (choice)
					{
						case 1: System.out.print("Enter Student ID for Search: "); 
							searchString = input.next();
							objOperationUtils.searchByStudentID(searchString, true, true);
							break;
						case 2: System.out.print("Enter Last Name for Search: "); 
							searchString = input.next();
							objOperationUtils.searchByLastName(searchString);
							break;
						case 3: System.out.print("Enter Major Course for Search: "); 
							searchString = input.next();
							objOperationUtils.searchByMajorCourse(searchString);
							break;
						case 0: objOperationUtils.exitMenu();
							break;
						case 9: System.out.println("\n"); 
							mainMenu();
							break;
						default:
							System.out.println("\n***********************************************************\n" + 
									"Please select proper option.\nEnter 1 to continue.\n" + 
									"***********************************************************");
							choice = input.nextInt();
							if (choice == 1)
								mainMenu();
							else
							{
								input.close();
								objOperationUtils.exitMenu();
							}
					}
				}
			}
			else if (choice == 0)
			{
				input.close();
				objOperationUtils.exitMenu();
			}
			else
			{
				System.out.println("\n***********************************************************\n" + 
						"Please select proper option.\nEnter 1 to continue.\n" + 
						"***********************************************************");
				choice = input.nextInt();
				if (choice == 1)
					mainMenu();
				else
				{
					input.close();
					objOperationUtils.exitMenu();
				}
			}
		}
		catch (InputMismatchException ime)
		{
			System.out.println("Please provide proper number as input.");
		}
		catch(Exception e)
		{
			System.out.println("Exception from mainMenu: " + e.getMessage());
		}
	}
}
