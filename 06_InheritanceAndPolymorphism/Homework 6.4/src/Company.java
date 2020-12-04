import java.util.*;

public class Company
{

    private int salary;

    protected static int income = (int)(80*(115000+25000*Math.random()));


    public static ArrayList <Employee> allSalary = new ArrayList<>();


    public List<Employee> getLowestSalaryStaff(int count){


        count = count > allSalary.size() ? allSalary.size() : count;

        allSalary.sort(Comparator.comparing(Employee::getMonthSalary));

        return new ArrayList<Employee>(allSalary.subList(0, count)){
            @Override
            public String toString() {
                String ret = "Список " + this.size() + " самых низких зарплат\n";
                for (int i = 0; i < this.size(); ret += i + 1 + ". " + this.get(i).getMonthSalary() + "\n", i++) ;
                return ret;
            }
        };
    }

    public List<Employee> getTopSalaryStaff(int count){

        count = Math.min(count, allSalary.size());


        allSalary.sort(Comparator.comparing((Employee employee) -> employee.getMonthSalary()).reversed());

        return new ArrayList<Employee>(allSalary.subList(0, count)){
            @Override
            public String toString() {
                String ret = "Список " + this.size() + " самых высоких зарплат\n";
                for (int i = 0; i < this.size(); ret += i + 1 + ". " + this.get(i).getMonthSalary() + "\n", i++) ;
                return ret;
            }
        };
    }


    public void hire (Function function)
    {
        function.hire(function);
    }

    public void fireFiftyPercentOfAll ()
    {
        for (int o = 1; o <= allSalary.size(); o++)
        {
            if ((o % 2) == 0)
            {
                allSalary.remove(allSalary.size() - allSalary.size()/2);
            }
        }
    }



    public void hireAll (Function function)
    {
        function.hireAll(function);
    }

    public void fire (Function function, int index)
    {
        function.fire(function, index);
    }


    public void getIncome ()
    {
        System.out.println("Доход компании: " + income + " руб.");
    }


    public void getMonthSalary ()
    {
    }

}
