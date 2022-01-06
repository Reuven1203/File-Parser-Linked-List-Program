package LinkedLists;

import LinkedLists.Schedule.AppointmentNode;

public class Driver {
    public static void main(String[] args) {
        Schedule s = new Schedule();
        Appointment a =new Appointment("M1","Dr. name",13.00,13.30);
        Appointment b = a.clone();
        Schedule.AppointmentNode d = s.new AppointmentNode(a,null);
        Schedule.AppointmentNode copyNode = d.clone();
        copyNode.getAppointment().setDoctorName("Reuven");
        System.out.println(d.getAppointment());
        System.out.println(copyNode.getAppointment());
        
    }
    
}
