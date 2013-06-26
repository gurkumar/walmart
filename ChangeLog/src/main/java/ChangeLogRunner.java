package main.java;

import java.util.Date;
import java.util.List;

import main.java.input.Address;
import main.java.input.Employee;

public class ChangeLogRunner {
	
	public static void main (String[] args) throws Exception{
		
		Address address_e1 = new Address ("123 Street",null,"San Jose","CA","95131","US");
		
		Employee employee_e1 = Employee.createEmployeeWithAddressDateEmployed (1 , "John" , "Smith" , address_e1 , new Date() );
		
        Address address_e2 = new Address ("123 Street","","Fremont","CA","95131","US");
		
		Employee employee_e2 = Employee.createEmployeeWithAddressDateEmployed (1 , "Harry" , "Smith" , address_e2 , new Date() );
		
        List <ChangeLog> changeLogList = new GenerateChangeLog ().getChangeLog(employee_e2 , employee_e1 );
        
		for (ChangeLog changeLog:changeLogList) {
			
			System.out.println ("field updated " + changeLog.getField() + " with value: " + changeLog.getValue());
			
		}
        
		
	}

}
