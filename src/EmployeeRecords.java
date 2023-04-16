import java.util.*;
import java.util.regex.*;


class Validation
{
    // Validate Name String():
    public static boolean ValidateNameString( String Name )
    {
        Pattern p;
        Matcher m;

        p    = Pattern.compile("^[A-Za-z\\s]+$");  ///  "^[A-Za-z ]\\w{5,20}$" : Name_length = 5 to 20
        m    = p.matcher(Name);
        if ( m.matches() )
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    // Validate MobileNo.(10-digits):
    public static boolean ValidateMobileNumber( String MobileNo )
    {   Pattern p;
        Matcher m;

        p = Pattern.compile("^\\d{10}$");
        m = p.matcher(MobileNo);

        if (m.matches())
        {
            return true;
        }

        else
        {
            return false;
        }

    }
}

class Employee extends Validation
{
    // 1. Data Members :
    private int ID;
    private String Name;
    private String Dept;
    private String Manager;
    private String MobileNo;
    private double Salary;

    // scanner object.
    static Scanner sc = new Scanner(System.in);

    // list used :
    // key:DEPARTMENT-NAME , value: MANAGER-NAME
    static LinkedHashMap<String, String> dept_list = new LinkedHashMap<String, String>();

    // Make 'SET _OF_ ID' : To check no one enter duplicate ID'S.
    static Set<Integer> ID_Set = new HashSet<Integer>();


    // 2. Setter(...) :
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setDept(String Dept) {
        this.Dept = Dept;
    }

    public void setManager(String Manager) {
        this.Manager = Manager;
    }

    public void setMobileNo(String MobileNo) {
        this.MobileNo = MobileNo;
    }

    public void setSalary(double Salary) {
        this.Salary = Salary;
    }

    // 3. Getter() :

    public int getID() {
        return this.ID;
    }

    public String getName() {
        return this.Name;
    }

    public String getDept() {
        return this.Dept;
    }

    public String getManager() {
        return this.Manager;
    }

    public String getMobileNo() {
        return this.MobileNo;
    }

    public double getSalary() {
        return this.Salary;
    }

    // toString()
/*   @Override
    public String toString() {
        return Name +" "+ ID +" "+Dept+" "+Manager+" "+MobileNo+" "+Salary;
        //return this.getName() + "," + this.getDept() + "," + this.getManager() + "," + this.getMobileNo();
    }
*/

    //********DISPLAY EACH EMPLOYEE*******
    public static void PrintEachEmployeeDetail ( Employee Value   )
    {
        System.out.print("ID         : ");
        System.out.println( Value.getID() );
        System.out.print("Name       : ");
        System.out.println( Value.getName() );
        System.out.print("Deparment  : ");
        System.out.println( Value.getDept() );
        System.out.print("Manager    : ");
        System.out.println( Value.getManager() );
        System.out.print("MobileNo.  : ");
        System.out.println( Value.getMobileNo() );
        System.out.print("Salary     : ");
        System.out.println( Value.getSalary() + " RS.");

        System.out.println();
    }



    // [1.] -> ADD - EMPLOYEE
    public void AddEmployee(  )
    {
        /***ID***/

        int ID;
        do {
            System.out.print("Id: "); // e.setID ( ID = sc.nextInt() );
            ID = sc.nextInt();

            // if, ID already exist in ID_Set{}:
            if ( ID_Set.contains(ID) )
            {
                System.out.println("NOT-VALID. ID IS DUPLICATE.");
                System.out.println("ENTER UNIQUE-ID.\n");
            }

            // else, Id is UNIQUE -> add ID in ID_Set{}:
            else
            {
                ID_Set.add(ID);
                this.setID(ID);  //-> [ SET-ID ]
                break;
            }

        } while (ID_Set.contains(ID));

        sc.nextLine();



        /* --NAME-- */

        String Name;
        do {
            System.out.print("Name  : ");

            Name = sc.nextLine();

            if ( ValidateNameString(Name)  )  // VALID-NAME
            {
                this.setName( Name ); // -> [SET NAME]
                break;
            }

            else   // INVALID-NAME
            {    System.out.println("Invalid Name.\n");
                System.out.println("Enter Valid Name  : ");
            }

        } while ( ValidateNameString(Name) != true);


        /* ---MOBILE NUMBER--- */
        String MobileNo;
        do {

            System.out.print("MobileNo.  : ");
            MobileNo = sc.nextLine();

            if ( ValidateMobileNumber(MobileNo) )
            {
                this.setMobileNo(MobileNo); // -> [SET MOBILE-NUMBER]
                break;
            }

            else
            {
                System.out.println("Not a valid Number.\n");
                System.out.println("Enter Valid MobileNo.  : ");
            }

        } while ( ValidateMobileNumber(MobileNo)!= true);


        /* ---SALARY--- */
        System.out.print("Salary: ");
        this.setSalary( sc.nextDouble() );  // -> [ SET-SALARY ]


    }

    //[2.]  DISPLAY - RECORDS
    public static void DisplayRecords( LinkedHashMap<String, ArrayList<Employee>> EmployeesList  )
    {
        if ( ID_Set.isEmpty() ) {
            System.out.println("Can't DISPLAY, Because No Records has been created yet.\n");
        }

        else
        {
            // System.out.println( EmployeesList );
            for (Map.Entry<String, ArrayList<Employee>> mapElement : EmployeesList.entrySet())
            {

                String key = mapElement.getKey(); // key -> dept_name

                // Finding the value
                ArrayList<Employee> value = mapElement.getValue(); // value -> list<>
                // O/P:
                System.out.println("Dept: " + key);

                for (Employee Value : value)
                {
                    // **** PRINT EACH EMPLOYEE RECORDS *****//
                    PrintEachEmployeeDetail( Value );
                }
            } //for
        } //else

    }//fun()

    // [3.] DISPLAY-RECORDS (DEPT-WISE)
    public static void DisplayRecordsDepartmentWise( LinkedHashMap<String, ArrayList<Employee>> EmployeesList  )
    {


        if (  ID_Set.isEmpty()  ) {
            System.out.println("Can't DISPLAY, Because No Records has been created yet.\n");
        }

        else
        {

            // dept wise list:
            String Dept;
            System.out.println("Enter Department Name: ");
            Dept = sc.nextLine();

            // Department 'FOUND'.
            if ( EmployeesList.containsKey(Dept)   )   //   dept_list.containsKey(Dept)
            {
                ArrayList<Employee> value = EmployeesList.get(Dept); // extract list of particular-dept.
                for (Employee Value : value)
                {
                    // **** PRINT EACH EMPLOYEE RECORDS *****//
                    PrintEachEmployeeDetail( Value );
                }
            }


            else
            {
                System.out.println("DEPARTMENT NOT FOUND.\n");
            }



        }//else
    }//fun()

    // [4.]  DELETE RECORD [BY_ID]
    public static void DeleteRecordByID( LinkedHashMap<String, ArrayList<Employee>> EmployeesList   )
    {

        if (   ID_Set.isEmpty()  ) {
            System.out.println("Can't DELETE, Because No Records has been created yet.\n");
        }

        else
        {
            boolean flag = false;

            //Scanner sc= new Scanner(System.in);

            int ID;
            System.out.println("Enter Id : ");
            ID = sc.nextInt();

            for (Map.Entry<String, ArrayList<Employee>> mapElement : EmployeesList.entrySet())
            {

                //String key = mapElement.getKey(); // key -> dept_name(String)

                // Finding the value
                ArrayList<Employee> list = mapElement.getValue(); // value -> list-1<> -
                for (Employee Value : list)
                {
                    if (Value.getID() == ID)
                    {
                        flag = true;
                        System.out.println("ID MATCHED.");
                        // remove this record from list.
                        list.remove(Value);
                        System.out.println("RECORD DELETED SUCCESSFULLY!");

                        // * DELETE ID FROM 'ID_Set{...}' also.
                        ID_Set.remove(ID);
                        break;
                    }
                }

            }

            if (flag == false)
                System.out.println("ID Not Found. \n");
            //sc.close();
        }// else

    }//fun()


    //[5.] -> SORT SALARY (DEPT_WISE)
    public static void SortSalaryDepartmentWise ( LinkedHashMap<String, ArrayList<Employee>> EmployeesList  )
    {
        // IF, LIST IS EMPTY - NO RECORD FOUND:
        if( ID_Set.isEmpty()  )
            System.out.println("NO RECORD IS FOUND!");

            // EmployeesList < Dept , ArrayList<Employee> > :
        else
        {
            //Scanner sc= new Scanner(System.in);

            System.out.println("Enter DEPARTMENT-NAME to sort the Salary: ");
            String Dept_Name;
            Dept_Name = sc.nextLine();

            if (dept_list.containsKey(Dept_Name))
            {
                ArrayList<Employee> list_value = EmployeesList.get(Dept_Name); // extract list of particular-dept.


                Collections.sort( list_value, new SalaryComparator().reversed() );
                System.out.println("List is SUCCESSFULLY SORTED Salary-WISE!");
            }


            else // if( !dept_list.containsKey(Dept) )
            {
                System.out.println("DEPARTMENT NOT FOUND.\n");
            }

            //sc.close();
        }
    }//fun()

    //[6.] ->Search Record By NAME
    public static void DisplayRecordByName (  LinkedHashMap<String, ArrayList<Employee>> EmployeesList )
    {
        /* (i/p: vivek)
         * MENDIX ->  (aman)E1   (sakshi)E2  (vishal)E4 : LIST-1
         * IOT    -> (vivek)E3                          : LIST-2
         */

        // IF, LIST IS EMPTY - NO RECORD FOUND:
        if( ID_Set.isEmpty()  )
            System.out.println("NO RECORD IS CREATED YET!");

            // EmployeesList < Dept , ArrayList<Employee> > :
        else
        {  // Scanner sc = new Scanner( System.in );

            String Emp_Name;
            System.out.println("Enter Name to Search a RECORD: ");
            Emp_Name     = sc.nextLine();
            boolean flag = false;

            for (Map.Entry<String, ArrayList<Employee>> mapElement : EmployeesList.entrySet())
            {
                // Finding the value
                ArrayList<Employee> list_value = mapElement.getValue();

                // if , ID FOUND IN LIST . -> PRINT-RECORD
                for(Employee Value : list_value )
                {
                    if(  Value.getName().equalsIgnoreCase(Emp_Name) || Value.getName().toLowerCase().contains(Emp_Name.toLowerCase())   )  // list->object->name
                    {
                        flag = true;
                        // **** PRINT EACH EMPLOYEE RECORDS *****//
                        PrintEachEmployeeDetail( Value  );

                    }
                }
            }

            if( flag == false )
            {
                System.out.println("NO RECORD HAS BEEN FOUND!\n");
            }
            //sc.close();
        }
    }


    //[7.] ->UPDATE DEPARTMENT( BY_ID )

    public static void UpdateDepartmentByID ( LinkedHashMap<String, ArrayList<Employee>> EmployeesList  )
    {
        if( ID_Set.isEmpty()  )
            System.out.println("NO RECORD IS CREATED YET!");

            // EmployeesList < Dept , ArrayList<Employee> > :
        else
        {
            boolean flag=false;

            System.out.println("Input ID : ");
            int ID;
            ID = sc.nextInt();

            sc.nextLine();

            for ( Map.Entry<String, ArrayList<Employee>> mapElement : EmployeesList.entrySet() )
            {

                // Finding the value
                ArrayList<Employee> list = mapElement.getValue(); // value -> list-1< >
                for (Employee Value : list)
                {
                    if (Value.getID() == ID)
                    {
                        flag = true;
                        System.out.println("ID MATCHED.");

                        // ASK NEW_DEPARTENT NAME:
                        System.out.println("Enter New DEPARTMENT NAME : ");
                        String New_Dept = sc.nextLine();

                        // FIRST CHECK NEW-DEPARTMENT-NAME IN EXISTING dept_list:
                        if( !dept_list.containsKey(New_Dept)  )
                        {
                            System.out.println("This DEPARTMENT is not Created YET!");
                            return;
                        }

                        // 1. SET NEW_DEPARTENT:
                        Value.setDept( New_Dept );

                        // 2. SET NEW_MANAGER:
                        Value.setManager( dept_list.get(New_Dept)   );

                        //* (ID,NAME,MOBILE :- SAME ) & (DEPARTMENT-NAME, MANAGER-NAME :- UPDATE )

                        // 3. move object into New_Dept_List:
                        EmployeesList.get(New_Dept).add(Value);  // EmployeesList.get(New_Dept): list<>

                        // 4. remove this record from Old_List.
                        list.remove(Value);

                        System.out.println("DEPARTMENT UPDATED SUCCESSFULLY!");

                        return;
                    }
                }

                if (flag == false)
                    System.out.println("ID Not Found. \n");
            }



        }//else

    }//fun()

    //[8.]-> TOP-5 EMPLOYEES SALARY
    public static void TopEmployeesSalary ( LinkedHashMap<String, ArrayList<Employee>> EmployeesList )
    {
        if( ID_Set.isEmpty()  )
            System.out.println("NO RECORD IS CREATED YET!");



            // EmployeesList < Dept , ArrayList<Employee> > :
        else
        {

            ArrayList<Employee> AllEmployeesList = new ArrayList<Employee>();

            System.out.println("Input N (Top-N EmployeeSalary) : ");
            int n;
            n = sc.nextInt();
            sc.nextLine();

            // if, n > ID_Set
            if ( n > ID_Set.size() )
            {
                System.out.println("There are not sufficient Employee.");
                return;
            }

            // (1) SORT - LIST :
            for ( Map.Entry<String, ArrayList<Employee>> mapElement : EmployeesList.entrySet() )
            {
                // 1. add 'list1<>,list2<>' --- into 'AllEmployeesList<>' :-
                AllEmployeesList.addAll ( mapElement.getValue()  ) ;

                // 2. SORT - AllEmployeesList<> :-
                Collections.sort( AllEmployeesList , new SalaryComparator().reversed() );
                System.out.println("List is SUCCESSFULLY SORTED Salary-WISE!");
            }

            // (2) DISPLAY LIST :  n=5
            for(Employee Value : AllEmployeesList  )
            {
                // **** PRINT EACH EMPLOYEE RECORDS *****//
                PrintEachEmployeeDetail( Value  );  // 5. 4. 3. 2. 1.
                n--;
                if( n==0 )
                    return;
            }

        }//else
    }
}





class SalaryComparator implements Comparator<Employee>
{
    public int compare(Employee e1, Employee e2)
    {
        if( e1.getSalary() == e2.getSalary() )
            return 0;
        else if(  e1.getSalary() > e2.getSalary()  )
            return 1;
        else
            return -1;
    }
}


public class EmployeeRecords extends Employee //EmployeesList
{
    public static void main(String[] args)
    {
        //Scanner sc = new Scanner(System.in);

        // CHOICE
        int ch;

        // ( key: DEPT , value: LIST<Employee> )
        LinkedHashMap<String, ArrayList<Employee>> EmployeesList = new LinkedHashMap<String, ArrayList<Employee>>();


        ArrayList<Employee> list1 = new ArrayList<Employee>();

        do
        {
            System.out.println();
            System.out.println("1.Add Employees");
            System.out.println("2.Display Employees List");
            System.out.println("3.Display Employees List - Department-wise");
            System.out.println("4.Delete Record using ID.");
            System.out.println("5.Sort Salary Dept wise.");
            System.out.println("6.Search Record By: NAME");
            System.out.println("7.Update Department of a Employee");
            System.out.println("8.Top-N Employees Salary");
            System.out.println("9.Exit");

            // choice 1 to 5
            System.out.println("Enter your choice from '1 to 7':");
            ch = sc.nextInt();
            sc.nextLine(); // Escape CharSequence after int to read string/char.


            /*
             * MENDIX -> (1)E1 (2)E2 (4)E4 : LIST-1
             * IOT    -> (3)E3             : LIST-2
             */

            switch (ch)
            {
                case 1:
                    // Add elements to LinkedHashMap
                    System.out.println("Deparment Name: ");
                    String Dept = sc.nextLine();

                    Employee e = new Employee();

                    if ( !dept_list.containsKey(Dept) ) // new-dept
                    {
                        EmployeesList.put(Dept, new ArrayList<Employee>());
                        list1 = new ArrayList<Employee>();

                        String manager;
                        System.out.print("Manager    : ");
                        manager = sc.nextLine();

                        //Employee e= new Employee();

                        e.setManager(manager);
                        e.setDept(Dept);
                        // store dept in dept_list():
                        dept_list.put(Dept, manager);

                        e.AddEmployee( );   //  e.AddEmployee();

                        /*****ADD IN DEPT-LIST*******/
                        list1.add(e);
                        EmployeesList.put(Dept, list1);

                    }
                    else    //old-dept
                    {
                        String manager;
                        manager = dept_list.get(Dept); // get(key)=value : dept->value(manager)
                        e.setManager (manager);
                        e.setDept    (Dept);

                        e.AddEmployee( );


                        /*****ADD IN DEPT-LIST*******/
                        EmployeesList.get(Dept).add(e);

                    }
                    break;

                case 2:
                    DisplayRecords( EmployeesList );
                    break;

                case 3:
                    DisplayRecordsDepartmentWise( EmployeesList  );
                    break;
                case 4:
                    DeleteRecordByID( EmployeesList );
                    break;
                case 5:
                    SortSalaryDepartmentWise( EmployeesList );
                    break;
                case 6:
                    DisplayRecordByName( EmployeesList );
                    break;
                case 7:
                    UpdateDepartmentByID( EmployeesList );
                    break;
                case 8: TopEmployeesSalary( EmployeesList );
                    break;
                case 9:
                    break;

            }//switch

        }while ( ch != 9 );

        sc.close();

    }
}



