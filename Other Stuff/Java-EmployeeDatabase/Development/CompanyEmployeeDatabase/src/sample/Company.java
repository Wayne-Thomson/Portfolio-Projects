                                                                                                    
/**
 * This class represents the company holding employees
 *
 * @author Wayne Thomson
 * @version 05/04/2022, v1
 */
package sample;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*; // Figure out actually import location
import java.io.*;
import java.nio.file.Paths;
public class Company
{
    private final TreeSet<Employee> employeesInCompany; // A set to hold employees

    /*
     * Constructor init a TreeSet
     * 
     * TreeSet is selected for two reasons.
     * 1.) It is sorted automatically based on the classes compareTo
     * 2.) It does not allow duplicates, as we would only want to employ someone once, this is ideal.
     * 
     */
    public Company()
    {
        employeesInCompany = new TreeSet<>();
    }

    /*
     *Getter method for Employees TreeSet
     *
     * @return TreeSet of employees in company
     */
    public TreeSet<Employee> getEmployeesInCompany() {
        return employeesInCompany;
    }

    /*public boolean setEmployeesInCompany(TreeSet<Employee> newSet) {
        employeesInCompany = newSet;
        return true;
    }*/

    /*
     * Clears all employees out of the set.
     */
    public void clear()
    {
        // MAYBE RESET STATIC EMPLOYEE COUNT TO ZERO OR ADD A METHOD TO RESET IT
        employeesInCompany.clear();
        System.out.println("Database cleared");
    }
    
    /*
     * Reorders the TreeSet after a variable used in sort order is changed
     * 
     * Creates a copy of the company, clears the original and finally adds old back in.
     * Not super efficient, but a simple solution.
     * 
     */
    public void reorderCompany(){
        // CREATE COPY
        // CLEAR
        // ADD IN FROM COPY
        
    }
    
    /*
     * Populates the database with several example employees
     * 
     * Future: MAYBE ADD RANDOM NAMES, ETC WITH PROMPT OF QUANTITY
     */
    public void populate()
    {
        // Please note, I don't know the rules for NI numbers and used mine as a reference. However, a Sting of letters/numbers
        // should work regardless of positioning.
        addMember(1, "AA 12 12 A", 22000, "Clerk", "Accounting", "Lucas Haynes", "Lavena Stevens");
        addMember(2,"AB 21 21 V", 29000, "Sales Associate", "Sales", " Jayden Whitney ", "Gaynor Clement");
        addMember(3, "HG 45 12 O", 21000, "Engineer", "Developments", "Cash Tatham", "Matt Allen");
        addMember(4,"OP 21 99 G", 28000, "Sales Associate", "Sales", " Jayden Whitney ", "Harris Victors");
        addMember(5,"II 87 22 R", 23000, "Sales Associate", "Sales", " Jayden Whitney ", "Kirk Morton");
        addMember(6, "UF 01 62 A", 21000, "Support Assistant", "Support", "Anthony Sexton", "Darrel Haywood");
        addMember(7,"HA 78 36 C", 27000, "Clerk", "Accounting", "Lucas Haynes", "Algernon Lowell");
        addMember(8, "BB 24 45 R", 21000, "Sales Associate", "Sales", "Jayden Whitney", "Gillian Ashton");
        addMember(9,"IE 12 59 K", 24000, "Clerk", "Accounting", "Lucas Haynes", "Carleton Adair");
        addMember(10,"LO 88 85 T", 11000, "Engineer", "Developments", "Anthony Sexton", "Daren Haley");
        addMember(11, "BC 45 67 H", 45000, "Payroll Manager", "Accounting", "Lucas Haynes", "Bethany Michaelson");
        addMember(12,"EJ 90 91 B", 23000, "Payroll Assistant", "Accounting", "Lucas Haynes", "Stace Webster");
        addMember(13, "KI 51 62 HO", 80000, "Accountant", "Accounting", "Lucas Haynes", "Teresa Savidge");
        addMember(14,"OO 21 33 C", 22000, "Trainee Accountant", "Accounting", "Lucas Haynes", "Tamzen Richard");
        addMember(15,"IL 87 22 L", 17000, "Support Assistant", "Support", "Anthony Sexton", "Jacinth Randell");
        addMember(16, "JJ 15 91 B", 30000, "Clerk", "Accounting", "Lucas Haynes", "Montgomery Traviss");
        addMember(17,"IB 12 41 V", 44000, "Office Manager", "Developments", "Cash Tatham", "Celandine Sherburne");
        addMember(18, "UJ 83 69 OP", 35000, "Sales Manager", "Sales", " Jayden Whitney ", "Mary Anne Bryan");
        addMember(19,"UJ 79 15 RE", 14000, "Sales Associate", "Sales", " Jayden Whitney ", "Owen Saylor");
        addMember(20,"CC 81 12 G", 29000, "Support Manager", "Support", "Anthony Sexton", "Delphia Ellisson");
    }

    /*
     * Prints whole database to a CSV with desired filename.
     * Saved to program root dir
     * 
     * @param filename The name of the output file
     */
    public int writeCSVFile(URI filename)
    {
        int amountSaved = 0;
        Path path = Paths.get(filename);
        // Tries to write with given name to root dir
        try(BufferedWriter writer = Files.newBufferedWriter(path))
        {
            
            for(Employee employee : employeesInCompany)
            {
                writer.write(employee.getEmployeeNumber() + "," + employee.getNationalInsuranceNumber() + "," + employee.getPayPA() + "," + employee.getRoleAndDepartment()[0] + ","
                            + employee.getRoleAndDepartment()[1] + "," + employee.getLineManager() + "," + employee.getEmployeeName());
                writer.write('\n');
                amountSaved++;
            }

        }
        // FURTHER DEVELOP
        catch(IOException e)
        {
            System.out.println(e);
        }
        return amountSaved;
    }

    /*
     * Reads the selected file and creates employees using the CSV file
     * 
     * @param filename The name of the file in the root dir
     */
    public int readCSVFile(URI filename)
    {
        int amountAdded = 0;
        // Tries to read the CSV file and creates a new employee for each line.
        try
        {
            Scanner employeeCSV = new Scanner(Paths.get(filename));
            
            while(employeeCSV.hasNextLine())
            {
                amountAdded++;
                String[] currentEmployee = employeeCSV.nextLine().split(","); // Splits the line and applies the data to new employee
                employeesInCompany.add(new Employee(Integer.parseInt(currentEmployee[0]),currentEmployee[1], Integer.parseInt(currentEmployee[2]), currentEmployee[3], currentEmployee[4], currentEmployee[5], currentEmployee[6]));
            }
        }
        // FURTHER DEVELOP
        catch(IOException e)
        {
            System.out.println(e);
        }

        return amountAdded;
    }
    
    /*
     * Manually add a new member to the database. If the database already contains said person then rejects the creation and informs
     * the user.
     * 
     * @param nationInsuranceNumber The NI of the new employee
     * @param payPA The pay of the new employee per year
     * @param role Role of new employee
     * @param department Department of new employee
     * @param lineManager LM of new employee
     * @param employeeName Employees name
     */
    public boolean addMember(int employeeNumber, String nationalInsuranceNumber, int payPA, String role, String department, String lineManager, String employeeName)
    {
        Employee newEmployee = new Employee(employeeNumber, nationalInsuranceNumber, payPA, role, department, lineManager, employeeName);
        if(employeesInCompany.contains(newEmployee))
        {
            return false;
        } else {
            employeesInCompany.add(newEmployee);
            return true;
        }
    }
    
    /*
     * Removes an employee from the database
     * 
     * @param employeeRemove The employee to remove
     * @return If the employee was removed
     */
    public boolean removeMember(int employeeRemove)
    {
        TreeSet<Employee> temp = new TreeSet<>(employeesInCompany);
        for(Employee employee : temp) {
            if(employeeRemove == employee.getEmployeeNumber()){
                employeesInCompany.remove(employee);
                return true;
            }
        }
        return false;
    }
    
    /*
     * Method updateMember Overload One
     * 
     * Updates a data point of an employee, uses employeeNumber to find employee
     * 
     * @param employeeNumber Employee to be changed
     * @param newLineManager The name of the new line manager for this employee
     * @return If the update was a success
     * 
     */
    public boolean updateMember(int employeeNumber, String newLineManager, String employeeName)
    {
        for(Employee employee : employeesInCompany)
        {
            if(newLineManager != null && employee.getEmployeeNumber() == employeeNumber){
                employee.setLineManager(newLineManager);
                return true;
            }
            if(employeeName != null && employee.getEmployeeNumber() == employeeNumber){
                employee.setEmployeeName(employeeName);
                return true;
            }
        }
        return false;
    }
    
    /*
     * Method updateMember Overload Two
     * 
     * Updates a data point of an employee, uses employeeNumber to find employee
     * If ether of the data point in the list are null then that value is ignored, handled by employee method.
     * 
     * @param employeeNumber Employee to be changed
     * @param newRoleAndDepartment A string list containing new role and/or department
     */
    public boolean updateMember(int employeeNumber, String[] newRoleAndDepartment)
    {
        for(Employee employee : employeesInCompany)
        {
            if(employee.getEmployeeNumber() == employeeNumber){
                employee.setRoleAndDepartment(newRoleAndDepartment[0], newRoleAndDepartment[1]);
                return true;
            }
        }
        return false;
    }
    
    /*
     * Method updateMember Overload Three
     * 
     * Updates a data point of an employee, uses employeeNumber to find employee
     * 
     * @param employeeNumber The employee to change
     * @param payPA The new value of the employees wage
     */
    public boolean updateMember(int employeeNumber, int payPA)
    {
        for(Employee employee : employeesInCompany)
        {
            if(employee.getEmployeeNumber() == employeeNumber){
                employee.setPayPA(payPA);
                return true;
            }
        }
        return false;
    }


    /*
     * Method updateMember Overload Four
     * 
     * Updates a data point of an employee, uses employeeNumber to find employee
     * Changes fundamental employee details, could have unexpected results.
     * 
     * @param employeeNumber The employee to change
     * @param newNI New NI number of employee
     * @param newEmployeeNumber The new employee number for the employee
     */
    public boolean updateMember(int employeeNumber, String newNI, Integer newEmployeeNumber)
    {
        for(Employee employee : employeesInCompany) {
            if (employee.getEmployeeNumber() == employeeNumber) {
                // Checks if the NI is being changed
                if (!(newNI == null) && !(employee.getNationalInsuranceNumber().equals(newNI))) {
                    employee.setNationalInsuranceNumber(newNI);
                    reorderCompany();
                    return true;
                }
                if (!(newEmployeeNumber == null) && !(employee.getEmployeeNumber() == newEmployeeNumber)) {
                    employee.setEmployeeNumber(newEmployeeNumber);
                    reorderCompany();
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * Method to filter out by selected criteria and return a the results in a set
     *
     * @param filterConditions The conditions to filter out
     * @return A set filtered down to meet criteria
     */
    public TreeSet<Employee> filterMembers(ArrayList<String> filterConditions) {
        TreeSet<Employee> filtered = new TreeSet<>();
        for(Employee employee : employeesInCompany) {
            if(filterConditions.get(0) != null && employee.getEmployeeName().equals(filterConditions.get(0))){
                filtered.add(employee);
                continue;
            }
            if(filterConditions.get(1) != null && employee.getEmployeeNumber() == Integer.parseInt(filterConditions.get(1))){
                filtered.add(employee);
                continue;
            }
            if(filterConditions.get(2) != null && employee.getLineManager().equals(filterConditions.get(2))){
                filtered.add(employee);
                continue;
            }
            if(filterConditions.get(3) != null && employee.getRoleAndDepartment()[0].equals(filterConditions.get(3))){
                filtered.add(employee);
                continue;
            }
            if(filterConditions.get(4) != null && employee.getRoleAndDepartment()[1].equals(filterConditions.get(4))){
                filtered.add(employee);
                continue;
            }
            // Checks pay is in provided range, a little clunky, but whole method is clunky :)
            if((filterConditions.get(5) != null && filterConditions.get(6) != null)
                    && (employee.getPayPA() >= Integer.parseInt(filterConditions.get(5))
                    && employee.getPayPA() <= Integer.parseInt(filterConditions.get(6)))){
                filtered.add(employee);
                continue;
            }
            if(filterConditions.get(7) != null && employee.getNationalInsuranceNumber().equals(filterConditions.get(7))){
                filtered.add(employee);
            }

        }
        return filtered;

    }

    /*
     * Find a single Employee
     *
     * @param employeeID Employee to find
     * @return The toString of the found employee
     */
    public ArrayList<String> findEmployee(int employeeID) {
        ArrayList<String> employeeDetails = new ArrayList<>();
        for(Employee employee : employeesInCompany){
            if(employee.getEmployeeNumber() == employeeID) {
                employeeDetails.add(employee.getEmployeeName());
                employeeDetails.add(employee.getRoleAndDepartment()[1]);
                employeeDetails.add(employee.getRoleAndDepartment()[0]);
                employeeDetails.add(Integer. toString(employee.getPayPA()));
                employeeDetails.add(employee.getLineManager());
                employeeDetails.add(Integer. toString(employee.getEmployeeNumber()));
                return employeeDetails;
            }
        }
        return employeeDetails;
    }
    
    /*
     * Prints formatted text detailing all employees in the company
     * Error Finding Method
     */
    public void printMembers()
    {
        for(Employee employee : employeesInCompany)
        {
           String outputS = String.format("The Employee: %s, The PayPA: %d, Their role and department: %s-%s, Their NI: %s"
                            , employee.getLineManager(),employee.getPayPA(), employee.getRoleAndDepartment()[0]
                            , employee.getRoleAndDepartment()[1], employee.getNationalInsuranceNumber());                
           System.out.println(outputS);
        }
        System.out.println();
    }
}
