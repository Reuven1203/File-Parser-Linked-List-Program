// -----------------------------------------------------
// Assignment 3
// Written by: Reuven Ostrofsky - 40188881
// -----------------------------------------------------
package LinkedLists;
import java.util.NoSuchElementException;
/**
 * 
 * @author reuvenostrofsky
 *Schedule class is a linked list of appointment nodes containing appointment information
 */
public class Schedule {
	/**
	 * First appointment node in linked list
	 */
    private AppointmentNode head;
    /**
     * Linked list size
     */
    private int size;
    
    /**
     * Parametrized 
     * @param head - First node of schedule
     * @param size - ususlly set to 1
     */
    public Schedule(AppointmentNode head, int size) {
        this.head = head;
        this.size = size;
    }
    /**
     * Default constructor
     */
    public Schedule(){
        head = null;
        size = 0;
    }
    /**
     * Copy constructor
     * @param s - Schedule object
     */
    public Schedule(Schedule s){
       this.head = s.head.clone();
       this.size = s.size;
       AppointmentNode copyPos = this.head;
       AppointmentNode pos = s.head;

       while(pos.next !=null){
        copyPos.next = pos.next.clone();
        pos = pos.next;
        copyPos = copyPos.next;


       }
    }
    /**
     * Getter method for schdule head
     * @return - head of linked list (AppointmentNode object)
     */
    public AppointmentNode getHead() {
        return head;
    }
    /**
     * Getter method for list size
     * @return - size of int value
     */
    public int getSize() {
        return size;
    }
    /**
     * Add appointment node to start (head)
     * @param a - Appointment object
     */
    public void addToStart(Appointment a) { 
        
        AppointmentNode node = new AppointmentNode(a,null);
        
        if(head == null){
            head = node;
            size++;
            return;
    }

        AppointmentNode temp = head;
        head = node;
        head.next = temp;
        size++;

    }
    /**
     * Clone method return deep copy
     */
    public Schedule clone() {

        return new Schedule(this);
    }

    /**
     * Insert appointment node at inputted index of list 
     * @param a - Appointment object
     * @param n - Index at which user chooses to insert appointment (int value)
     * @throws NoSuchElementException - Throws exception if input is invalid
     */
    public void insertAtIndex(Appointment a, int n) throws NoSuchElementException {
        if (n  <= 0 || n> (size)) {
            throw new NoSuchElementException();
        }
        AppointmentNode an  = new AppointmentNode(a,null);
        AppointmentNode currentPosition = head.next;
        AppointmentNode previousPosition = head; 
        for(int i = 1; i < n;i++) {
            previousPosition = currentPosition;
            currentPosition = currentPosition.next;
        }
        previousPosition.next = an;
        an.next = currentPosition;  
        size++;

    }
    /**
     * Deletes appointment node at inputed index
     * @param n - index at which user wants to delete
     * @throws NoSuchElementException
     */
    public void  deleteFromIndex(int n) throws NoSuchElementException{
        if (n>size || head == null || n<=0) {
            throw new NoSuchElementException();
        }
        if(n==1) {
            head = head.next;
            size--;
            return;
        }
        AppointmentNode currentPosition = head.next;
        AppointmentNode previousPosition = head;
        for(int i = 2; i<n;i++){
            previousPosition = currentPosition;
            currentPosition = currentPosition.next;
        }
        previousPosition.next = currentPosition.next;
        size--;

    }
    /**
     * Replaces appointment node with another one
     * @param a - Appointment object to replace
     * @param n - index to be replaces (int value0
     */
    public void replaceAtIndex(Appointment a, int n) {
        if(n <= 0 || n> size-1) {
            return;
        }
        deleteFromIndex(n);
        insertAtIndex(a,n-1);
    }
    /**
     * deletes head of schedule list
     */
    public void deleteFromStart() {
        if (size == 0) {
            return;
        }

        if (size == 1) {
            head = null;
            size--;
            return;
        } else {
            head = head.next;
            size--;
            return;
        }

    }
    /**
     * toString() method displaying the schedule linked list (from head to end)
     */
    public String toString(){
        String str = "LinkedList=> ";
        if (head != null) {
            AppointmentNode start = head;

       for (int i = 0; i < size; i++)

       {

           str += start.getAppointment().toString()+ "->";

           start = start.getNext();

       }

       
       }
      str += "null";

       return str;
   }
       
    
    /**
     * Find at which index thev inputed ID is at 
     * @param id - String type ID
     * @return - Posiiton at which th eappointment is found in the list
     */
    public AppointmentNode find(String id) {
        AppointmentNode currentPosition = head;
        for(int i = 0; i<size;i++){
            if(currentPosition.getAppointment().getAppointmentID().equals(id)){
                System.out.println("Appointment ID was found at position|:" + i);
                return currentPosition;
            }
            currentPosition = currentPosition.next;
        }
        System.out.println("Appointment ID: " + id + " was not found");
        return null;

    }
    /**
     * Checks if schedule lisy contains inputed ID
     * @param id - String ID
     * @return - Boolean (true) if ID is found... false if otherwise
     */
    public boolean contains(String id) {
        AppointmentNode currentPosition = head;
        for(int i = 0; i<size;i++){
            if(currentPosition.getAppointment().getAppointmentID().equals(id)){
                return true;
            }
            currentPosition = currentPosition.next;
        }
        return false;
    }
    /**
     * Checks if two linked list (Schedule object) are equal
     * @param s - Schedule Object to be testes
     * @return - Boolean (true if equal, false if otherwise)
     */
    public boolean equals(Schedule s) {
        if (size != s.size){
            return false;
        }
        if(this == s) {
            return true;
        }
        AppointmentNode currentPosition = head;
        AppointmentNode sPosition = s.head;
        for(int i =0; i < size;i++) {
            if(!(currentPosition.getAppointment().equals(sPosition.getAppointment()))){
                return false;
            }
            currentPosition = currentPosition.next;
            sPosition = sPosition.next;
        }
        return true;

    }


    /**
     * 
     * @author reuvenostrofsky
     * AppointmentNode class is the inner class of schedule, being the nodes of the schedule linked list
     */
    
    //Privacy leak due to the fact the inner class is public so they can be accessed outside the outer class. Also this class is mutable which defeats the purpose of private attributes
    public class AppointmentNode{
    	/**
    	 * Appoitment in node
    	 */
        private Appointment a;
        /**
         * Pointer to the node's next node
         */
        private AppointmentNode next;
        /**
         * Default constructor
         */
        public AppointmentNode() {
                a = null;
                next= null;
        }
        /**
         * paramatrized constructor
         * @param a - Appoitment in node (appointment object)
         * @param an - Pointer to the node's next node (Appointmentnode object)
         */
        public AppointmentNode(Appointment a, AppointmentNode an) {
            this.a = a;
            this.next = an;
        }
        /**
         * Copy construcotr
         * @param an - Appointmentnode object
         */
        public AppointmentNode(AppointmentNode a){
          this.a = new Appointment(a.getAppointment(),a.getAppointment().appointmentID);
          this.next = null;
        }
        /**
         * Getter method of AppointmentNode's appointment
         * @return - Appointment object
         */
        public Appointment getAppointment() {
            return this.a;
        }
        /**
         * Setter method of AppointmentNode's appointment
         * @param a - Appointment Object
         */
        public void setAppointment(Appointment a) {
            this.a = a;
        }
        /**
         * Getter method of the next Appointmentnode
         * @return
         */
        public AppointmentNode getNext() {
            return this.next;
        }
        /**
         * Setter method for the next appointmentnode
         * @param an - Appointmentnode object
         */
        public void setNext(AppointmentNode an) {
            this.next = an;
        }
        /**
         * Clone method returning deep copy
         */
        public AppointmentNode clone() {
            return new AppointmentNode(this);
        }

        
}
}
