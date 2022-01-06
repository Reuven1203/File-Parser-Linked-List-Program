// -----------------------------------------------------
// Assignment 3
// Written by: Reuven Ostrofsky - 40188881
// -----------------------------------------------------
package LinkedLists;
/**
 * 
 * @author reuvenostrofsky
 * Bookable interface implements all classes that are schedule bookable
 */
public interface Bookable {
	/**
	 * Checks if appointment is on the same time as the inputted appointment
	 * @param a - Appointment Object
	 * @return - String displaying outcome
	 */
    String isOnSameTime(Appointment a);
}
