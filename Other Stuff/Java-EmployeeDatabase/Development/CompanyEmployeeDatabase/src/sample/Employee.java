/**
 * This class represents an employee within an organisation
 *
 * @author Wayne Thomson
 * @version 05/04/2022, v1
 */
package sample;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.List;
public class Employee implements Comparable<Employee>
{
    private String nationalInsuranceNumber; // Assumes this to be unique
    private int payPA; 
    private final String[] roleAndDepartment = new String[2];
    private String lineManager;
    private static int totalEmployees; // Keep track of total employees added, includes removed.
    private int employeeNumber;
    private String employeeName;

    public Employee(int employeeNumber, String nationalInsuranceNumber, int payPA, String role, String department, String lineManager, String employeeName)
    {
        this.nationalInsuranceNumber = nationalInsuranceNumber;
        this.payPA = payPA;
        roleAndDepartment[0] = role;
        roleAndDepartment[1] = department;
        this.lineManager = lineManager;
        this.employeeNumber = employeeNumber; // Sets this instances number and increments the static.
        this.employeeName = employeeName;
    }

    /*
     *
     */
    public String getEmployeeName(){
        return employeeName;
    }

    /*
     *
     */
    public void setEmployeeName(String employeeName){
        this.employeeName = employeeName;
    }

    /*
     * Setter Method for National Insurance Number
     * 
     * Not advisable to change.
     * 
     * @param nationalInsuranceNumber The new NI for this employee
     */
    public void setNationalInsuranceNumber(String nationalInsuranceNumber)
    {
        Scanner s = new Scanner(System.in); // Create new scanner object
        System.out.println("Warning, altering the NI for this employee will massively alter them."
                            +"\nPlease make sure that you are certain you want to change this, if you do, enter 'yes': ");
        String choice = s.nextLine().toLowerCase().trim(); // User choice, formatted to be lowercase with no spaces.
        if(choice.equals("yes")){
            this.nationalInsuranceNumber = nationalInsuranceNumber;
            System.out.println("The NI has been changed to: " + nationalInsuranceNumber);
            System.out.println("The hashCode and equality of this employee have now changed");
        
        } else {
            System.out.println("Change canceled.");
        }
    }

    /*
     * Getter Method for National Insurance Number
     * @return the NI for this employee
     */
    public String getNationalInsuranceNumber()
    {
        return nationalInsuranceNumber;
    }

    /*
     * Setter Method for payPA
     * @param payPa The new wage PA for this employee
     */
    public void setPayPA(int payPA)
    {
        this.payPA = payPA;
        System.out.println("The wage of this employee has been changed to: " + payPA);
    }

    /*
     * Getter method for payPA
     * @return The payPA for this employee
     */
    public int getPayPA()
    {
        return payPA;
    }

    /*
     * Getter method for employeeNumber
     * 
     * @return This employees number
     */
    public int getEmployeeNumber()
    {
        return employeeNumber;
    }
    
    
    /*
     * Setter method for employeeNumber
     * 
     * As this is a static number that increments with each new employee added to the database, added confirmation.
     * If the new number is higher than the current highest employee then the static value is changed to reflect this.
     * 
     * Ideally, this would not be changed.
     * 
     * @param newNumber The new employeeNumber for this employee
     * 
     */
    public void setEmployeeNumber(int newNumber)
    {
        if(newNumber >= totalEmployees) {
            Scanner s = new Scanner(System.in); // Create new scanner object
            System.out.println("Warning, this number is higher than the current highest set employee"
                                +"\nSetting it to this will result in a likely duplicate number being used.");
            System.out.println("If you are sure, type 'yes', otherwise number will not be changed."
                                + "\n if 'yes' is selected the next employee will be numbered: " + (newNumber + 1));
            String choice = s.nextLine();
            if(choice.toLowerCase().trim().equals("yes"))
            {
                this.employeeNumber = newNumber;
                System.out.println("Number has been updated. Next employee will be number: " + (newNumber + 1));
                totalEmployees = newNumber + 1;
            } else {
                System.out.println("Update canceled. Current highest employee number is: " + (totalEmployees - 1));
            }
            
        }
    }
    
    /*
     * Setter Method for roleAndDepartment
     * Sets index 0 & 1 of roleAndDepartment to new values
     * 
     * I did consider making these separate, but decided it was better to have it combined.
     * If one or more values are null then the corresponding value doesn't change.
     * 
     * @param role The new role of the employee
     * @param department The new department of the employee
     */
    public void setRoleAndDepartment(String role, String department)
    {
        roleAndDepartment[0] = role == null ? roleAndDepartment[0] : role;
        roleAndDepartment[1] = department == null ? roleAndDepartment[1] : department;
    }

    /*
     * Getter method for RoleAndDepartment
     * 
     * @return roleAndDepartment A list consisting of role and department for this employee
     */
    public String[] getRoleAndDepartment()
    {
        return roleAndDepartment;
    }

    /*
     * Setter Method for lineManager
     * 
     * @param lineManager New lineManager for this employee
     */
    public void setLineManager(String lineManager)
    {
        this.lineManager = lineManager;
        System.out.println("The line manager of this employee is now: " + lineManager);
    }

    /*
     * Getter Method for lineManager
     * 
     * @return The lineManager of this employee
     */

    public String getLineManager()
    {
        return lineManager;
    }

    /*
     * Overrides the toString method to return a custom string for Employees
     * @return A string with this objects details
     */
    @Override
    public String toString()
    {

        return employeeName;
    }

    
    /*
     * Converts the unique NI of the employee to an integer to form a hashCode
     * 
     * To convert the NI to a hash I break it down into a list of chars and then convert them to their unicode value.
     * After that I concatenate them into a string and parse that to an int. I think this will always result in a unique
     * hash int so long as the NI is unique.
     * 
     * @return The hash for this employee
     * 
     */
    @Override 
    public int hashCode()
    {
        StringBuilder newHashString = new StringBuilder(); // Temp hashCode while concatenating.
        
        // Use stream and collector to convert to a valueOf List. Uses functional style.
        List<Integer> elements = Arrays.stream(nationalInsuranceNumber.split("")).map(Integer::valueOf)
    .collect(Collectors.toList());
        // Concatenate hash code using each char value in NI
        for(int num : elements) {
            newHashString.append(num);
        }
        return Integer.parseInt(newHashString.toString()); // Convert to int and return
    }


    /*
     * Overrides compareTo to implement own sort order to the following:
     * 
     * Sort by lineManager name, if same lineManager sort by lowest employeeNumber
     * 
     * @param obj the employee to check sort order against
     */
    @Override
    public int compareTo(Employee obj)
    {
        if(equals(obj)) {
            return 0;
        }
        else if(obj.getLineManager().equals(getLineManager()))
        {   // lineManager is the same, so sort by vin
            return getEmployeeNumber() - obj.getEmployeeNumber(); // Checks who is higher.
        } else {
            return getLineManager().compareTo(obj.getLineManager()); // Uses Strings compareTo method to sort alphabetically *** SPELLCHECK
        }
    }
}
