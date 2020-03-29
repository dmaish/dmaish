import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

@SuppressWarnings("unchecked")
public class Main {
    public static  void main(String[]args){
        ArrayList<String> allEmployees = new ArrayList<String>();
        boolean stillCollectingInput = true;
        Hashtable<String, Hashtable> weeklyHashtable = new Hashtable<String, Hashtable>();

        String [] weekDays = { "monday", "tuesday", "wednesday", "thursday", "friday" };

        while (stillCollectingInput) {
            System.out.println("please enter name of EMPLOYEES enter 'QUIT' to complete.");
            Scanner scannerObj = new Scanner(System.in);
            String employee = scannerObj.nextLine();

            if (employee.equalsIgnoreCase("quit")) {
                System.out.println(allEmployees);
                break;
            }
            allEmployees.add(employee);
        }

        System.out.println("all employees are: " + allEmployees);


        for ( int counter=0; counter < weekDays.length; counter++ ) {
            String day = (String) Array.get(weekDays, counter);
            weeklyHashtable.put(day, lateIndividuals(allEmployees, day));
            System.out.println(lateIndividuals(allEmployees, day));
        }

        System.out.println("WEEKLY LATENESS REPORT" + weeklyHashtable);
    }



    static Hashtable<String, Object> lateIndividuals ( ArrayList<String> allEmployees, String day){
        ArrayList<String> lateEmployees = new ArrayList<String>();
        boolean stillCollectingInput = true;
        int currentBeneficiaryIndex = 0;
        int nextBeneficiaryIndex = currentBeneficiaryIndex + 1;

        Hashtable<String, Object> detailsHashtable = new Hashtable<String, Object>();

        // collecting late employees
        while (stillCollectingInput) {
            System.out.println("Enter name of a LATE EMPLOYEES on " + day + ".Enter 'QUIT' to complete "+day+"'s report.");
            Scanner scannerObj = new Scanner(System.in);
            String lateEmployee = scannerObj.nextLine();

            if (lateEmployee.equalsIgnoreCase("quit")) {
                break;
            }
            lateEmployees.add(lateEmployee);
        }

        // compute who is getting coffee
        if (lateEmployees.size() > 1) {
            ArrayList<String> employeesGettingCoffee = (ArrayList <String>) allEmployees.clone();
            employeesGettingCoffee.removeAll(lateEmployees);
            detailsHashtable.put("buyer", lateEmployees);
            detailsHashtable.put("beneficiary", employeesGettingCoffee);

        }else {
            String employeeGettingCoffee = allEmployees.get(currentBeneficiaryIndex);
            detailsHashtable.put("buyer", lateEmployees);
            detailsHashtable.put("beneficiary", employeeGettingCoffee);
        }

        //compute next beneficiary
        for (int counter = nextBeneficiaryIndex; counter < allEmployees.size(); counter++) {
            if(lateEmployees.contains(allEmployees.get(nextBeneficiaryIndex))) {
                nextBeneficiaryIndex++;
            }
        }

        System.out.println("next beneficiary is:  " + allEmployees.get(nextBeneficiaryIndex));

        return detailsHashtable;
    }
}
