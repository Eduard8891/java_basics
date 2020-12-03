import java.util.*;

public class Company
{

    private int salary;

    protected static int income = (int)(80*(115000+25000*Math.random()));

    public static ArrayList<Integer> managers = new ArrayList<>();
    public static ArrayList<Integer> operators = new ArrayList<>();
    public static ArrayList<Integer> topManagers = new ArrayList<>();

    public static ArrayList <Employee> allSalary = new ArrayList<>();

    public List<Employee> getLowestSalaryStaff(int count){

        count = count > allSalary.size() ? allSalary.size() : count;

        allSalary.sort(Comparator.comparing(Employee::salary));

        return new ArrayList<Employee>(allSalary.subList(0, count)){
            @Override
            public String toString() {
                String ret = "Список " + this.size() + " самых низких зарплат\n";
                for (int i = 0; i < this.size(); ret += i + 1 + ". " + this.get(i) + "\n", i++) ;
                return ret;
            }
        };
    }

    public List<Employee> getTopSalaryStaff(int count){

        count = count > allSalary.size() ? allSalary.size() : count;

        allSalary.sort((man1, man2) -> man2.salary().compareTo(man1.salary()));

        return new ArrayList<Employee>(allSalary.subList(0, count)){
            @Override
            public String toString() {
                String ret = "Список " + this.size() + " самых высоких зарплат\n";
                for (int i = 0; i < this.size(); ret += i + 1 + ". " + this.get(i) + "\n", i++) ;
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
        for (int o = 1; o <= operators.size(); o++)
        {
            if ((o % 2) == 0)
            {
                 operators.remove(operators.size() - operators.size()/2);
            }
        }
        for (int o = 1; o <= managers.size(); o++)
        {
            if ((o % 2) == 0)
            {
                managers.remove(managers.size() - managers.size()/2);
            }
        }
        for (int o = 1; o <= topManagers.size(); o++)
        {
            if ((o % 2) == 0)
            {
                topManagers.remove(topManagers.size() - topManagers.size()/2);
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


    public void getMonthSalary (Function function, int index)
    {
        function.getMonthSalary(function, index);
    }

}
