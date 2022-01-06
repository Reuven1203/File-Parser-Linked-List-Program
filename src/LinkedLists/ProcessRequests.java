// -----------------------------------------------------
// Assignment 3
// Written by: Reuven Ostrofsky - 40188881
// -----------------------------------------------------
package LinkedLists;
import java.util.ArrayList;
import java.util.Scanner;
import LinkedLists.Schedule.AppointmentNode;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 

import java.io.File;
/**
 * 
 * @author reuvenostrofsky
 *ProcessRequest class processes all requested appointment and tests if doctors are available on the request time and tests the schedule class's methods
 */

public class ProcessRequests {
    public static void main(String[] args) {
       Schedule s1 = new Schedule();
       
       Scanner sc1  =null;
       Scanner sc2 = null;
       File schedule = new File("src/Given Files/Schedule.txt");
       File requests = new File ("src/Given Files/Requests.txt");
       try{
        sc1 = new Scanner(new FileInputStream(schedule));
        sc2 = new Scanner(new FileInputStream(requests));
       }catch(FileNotFoundException e){
           System.out.println(e.getMessage());
           System.exit(0);

       }

       ArrayList<String> Doctors = new ArrayList<String>();
       String doctor = null;

       while (!(doctor = sc1.nextLine()).equals("")){
           Doctors.add(doctor);
       }
       String appID = sc1.next();
       String doctorName = sc1.nextLine();
       sc1.next();
       double sTime = sc1.nextDouble();
       sc1.next();
       double eTime = sc1.nextDouble();
       s1.addToStart(new Appointment(appID,doctorName,sTime,eTime));
       sc1.nextLine();
       int index = 1;

       while(sc1.hasNext()){
        appID = sc1.next();
        doctorName = sc1.nextLine().trim();
        sc1.next();
        sTime = sc1.nextDouble();
        sc1.next();
        eTime = sc1.nextDouble();
        sc1.nextLine();
        s1.insertAtIndex(new Appointment(appID,doctorName,sTime,eTime), index);
        index++;
        
       }
       AppointmentNode start = s1.getHead();
       AppointmentNode sNext;
       int n = 1;
       int s=1;
       while(start != null) {
             sNext = start.getNext();
             n=1;
           while(sNext != null){
            if(start.getAppointment().equals(sNext.getAppointment())){
                s1.deleteFromIndex(s+n);
            }

            n++;
            sNext = sNext.getNext();
            
           }
           start = start.getNext();
           s++;
           
       }
       ArrayList<Appointment> appRequests = new ArrayList<Appointment>();
      
       while(sc2.hasNext()){
           appID = sc2.next();
           sTime = sc2.nextDouble();
           eTime = sc2.nextDouble();
           appRequests.add(new Appointment(appID,null,sTime,eTime));
       }
       
        //System.out.println(appRequests);
        System.out.println(s1.toString());
        String currentDoc;
        index = 0;
        int count = 0;
        int docCount = 0;
        int reqIndex = 0;
        String message= null;
        Schedule.AppointmentNode currentNode = s1.new AppointmentNode();
        currentNode = s1.getHead();
        Appointment currentAppointment = currentNode.getAppointment();
        Appointment currentRequest = appRequests.get(0);
        //Privacy leak while making AppointmentNode inner class public
        while(reqIndex < appRequests.size()){
            docCount = 0;
            index = 0;
            currentRequest = appRequests.get(reqIndex);
            while(index < Doctors.size()) {
                count = 0;
                currentDoc = Doctors.get(index).substring(Doctors.get(index).indexOf(" ")+1,Doctors.get(index).length());
                currentNode = s1.getHead();
                currentAppointment = currentNode.getAppointment();
                while(currentNode !=null){
                if(currentDoc.equals(currentAppointment.getDoctorName().strip())){

                if (currentAppointment.isOnSameTime(currentRequest).equals("Different Time") && !currentAppointment.isOnSameTime(currentRequest).equals("Some Overlap")){
                    message = "Patient can book appointment " + currentRequest.getAppointmentID() + " from " + currentRequest.getStartTime() + "0 to " + currentRequest.getEndTime() + "0 with Dr. " + currentDoc + " as other doctors are not available at this time.";
                    //currentRequest.setDoctorName(currentDoc);
                    //s1.insertAtIndex(currentRequest,count+1);
                    docCount++;
                }else if(currentAppointment.isOnSameTime(currentRequest).equals("Same Time") ||currentAppointment.isOnSameTime(currentRequest).equals("Some Overlap")){
                    message = "";
                    currentNode = null;
                    currentAppointment = null;
                    index++;
                    
                }
               

            }   if(currentNode != null){
                    currentNode = currentNode.getNext();
                    if(currentNode != null){
                         currentAppointment = currentNode.getAppointment();
                    }else
                    index++;
                   
            }
            
               
                
                
            }
            
            count++;
            }
            if(docCount == 1)
            System.out.println(message);
            else if (docCount > 1  && !message.equals(""))
            System.out.println("Patient can book appointment " + currentRequest.getAppointmentID() + " from " + currentRequest.getStartTime() + "0 to " + currentRequest.getEndTime() + "0 as nothing is scheduled during that time for mutliple doctors.");
            else 
            System.out.println("Patient can't book appointment " + currentRequest.getAppointmentID() + " from " + currentRequest.getStartTime() + "0 to " + currentRequest.getEndTime() + "0 as no doctor is available at this time.");
            reqIndex++;


            
            

           
        }
            boolean Continue = true;
            String ID;
            String ans;
            Scanner input = new Scanner(System.in);
            while (Continue){
                System.out.println("Which appointment would you like to find: ");
                ID = input.nextLine();
                s1.find(ID.toUpperCase());
                System.out.println("Would you like to find another ID?(yes or no)");
                ans = input.nextLine();
                if(!ans.equalsIgnoreCase("yes")){
                    Continue = false;
                }
            }

            System.out.println("\nTesting clone() method\n");
            Schedule s2 = new Schedule(s1);
            System.out.println(s2);

            System.out.println("\nTesting equals() method\n");
            System.out.println(s1.equals(s2));


            //testing addtoStart method
            Appointment ap1 = new Appointment("L4","Dr. Hazan",13.00,13.30);
          
            s2.addToStart(ap1);
            System.out.println("\nTesting addToStart() method \n");
            System.out.println(s2.toString() + "\n"); 

            //Testing deleteFromIndex method
            System.out.println("\nTesting deleteFromIndex() method\n");
            s2.deleteFromIndex(4);
            System.out.println(s2.toString()); 

            System.out.println("\nTesting insertAtIndex() method\n");
            Appointment ap2 = new Appointment("D5","Dr. Cohen",12.30,13.00);
            s2.insertAtIndex(ap2,3);
            System.out.println(s2);

            System.out.println("\nTesting replaceatIndex() method\n");
            Appointment ap3 = new Appointment("L7","Dr. Andrews",13.00,13.30);
            s2.replaceAtIndex(ap3, 4);
            System.out.println(s2);


            System.out.println("Testing if scehdule contains M7 appointment");
            System.out.println(s2.contains("M7"));
            
            System.out.println(s1);


            



                
            
            
            

            









            sc1.close();
            sc2.close();
            input.close();


       





    }
}