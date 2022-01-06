// -----------------------------------------------------
// Assignment 3
// Written by: Reuven Ostrofsky - 40188881
// -----------------------------------------------------
package LinkedLists;
import java.util.Scanner;
/**
 * 
 * @author reuvenostrofsky
 *	Appointment class contains all attributes and methods for appointments on a doctor clinic's schedule 
 */
public class Appointment implements Bookable {
	/**
	 * Appointment Id of type string
	 */
    String appointmentID;
    /**
     * Appointment Doctor
     */
    String DoctorName;
    /**
     * Appointment start time
     */
    double startTime;
    /**
     * Appointment end time
     */
    double endTime;

/**
 * Parametrized constructor
 * @param appointmentID - Appointment Id of type string
 * @param DoctorName - Appointment Doctor of type string
 * @param startTime - Appointment start time of type double
 * @param endTime - Appointment end time
 */
public Appointment(String appointmentID, String DoctorName, double startTime,double endTime) {
    this.appointmentID = appointmentID;
    this.DoctorName = DoctorName;
    this.startTime = startTime;
    this.endTime = endTime;
}
/**
 * 
 * copy constructor
 * @param a - Appointment object
 * @param value - Different appointment Id that the Appointment being copied
 */
public Appointment(Appointment a,String value) {
   this.DoctorName = a.DoctorName;
   this.startTime = a.startTime;
   this.endTime = a.endTime;
   this.appointmentID = value;
}

/**
 * Getter method of Appointment start time
 * @return - start time double value
 */
public double getStartTime() {
	return this.startTime;
}
/**
 * Setter method of Appointment start time
 * @param startTime - start time double value
 */
public void setStartTime(double startTime) {
	this.startTime= startTime;
}
/**
 * Getter method of appointment ID
 * @return - String value of appointment id
 */
 public String getAppointmentID() {
 	return this.appointmentID;
 }
 /**
  * Setter method of appointment id
  * @param appointmentID - Appointment id string value
  */
 public void setAppointmentID(String appointmentID) {
 	this.appointmentID= appointmentID;
 }
/**
 * Getter method of doctor's name
 * @return - String value of doctor's name
 */
    public String getDoctorName() {
	return this.DoctorName;
}
    /**
     * Setter method of doctor's name 
     * @param DoctorName- doctor name string value 
     */
public void setDoctorName(String DoctorName) {
	this.DoctorName = DoctorName;
}
   /**
    * Getter method of appointment end time 
    * @return - double value of end time
    */
public double getEndTime() {
	return this.endTime;
}
/**
 * Setter method of end time
 * @param endTime - end time double value
 */
public void setEndTime(double endTime) {
	this.endTime = endTime;
}

/**
 * clone method giving a deep copy 
 */
public Appointment clone() {
    Scanner input= new Scanner(System.in);
    System.out.println("Enter Appointment ID:");
    String AppointmentID = input.nextLine();
    input.close();
    return new Appointment(this,AppointmentID);
}
	
/**
 * Checks whether two appointment are on the same time, overlapping, or different times
 */
    public String isOnSameTime(Appointment a) {
        if (this.startTime == a.startTime) {
            return "Same Time";
        }
        else if ((a.startTime > this.startTime && a.startTime < this.endTime) ||(this.startTime > a.startTime && this.startTime< a.endTime)) {
            return "Some Overlap";
        }else
        return "Different Time";
    }
    //This = 10-11
    //a = 1030 - 1130
    /**
     * returns toString() of all appointment attributes
     */
    public String toString() {
        return "Appointment ID: " + appointmentID + "\nDoctor's name: " + DoctorName + "\nStart Time: " + startTime + "\nEnd Time: " + endTime;
    }
    /**
     * Checks if two appointments are equals (other than appointment id)
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (getClass() != o.getClass() || o == null) {
            return false;
        }
        Appointment a = (Appointment) o;
        return this.DoctorName.equals(a.DoctorName) && this.startTime == a.startTime && this.endTime == a.endTime;   
    }
    
    
}
