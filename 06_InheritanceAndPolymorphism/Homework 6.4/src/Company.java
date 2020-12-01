import java.util.*;

public class Company
{

    protected static int income = (int)(80*(115000+25000*Math.random()));

    public static ArrayList<Integer> managers = new ArrayList<Integer>();
    public static ArrayList<Integer> operators = new ArrayList<Integer>();
    public static ArrayList<Integer> topManagers = new ArrayList<Integer>();
    public static ArrayList <Integer> allSalary = new ArrayList<>();





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

    public void minMaxSalary ()
    {
        allSalary.addAll(operators);
        allSalary.addAll(topManagers);
        allSalary.addAll(managers);
        Collections.sort(allSalary);

        int countMin = 0;
        int countMax = 0;


        System.out.println("30 самых маленьких зарплат:");

        for (int go: allSalary)
        {
            if (countMin == 30) {
                break;
            }
            System.out.println("\t"+go);
            countMin++;

        }
        System.out.println("15 самых больших зарплат:");

        for (int go: allSalary)
        {
            if (countMax > allSalary.size()-16) {

                System.out.println("\t"+go);

                if(countMax == allSalary.size())
                {
                    break;
                }
            }
            countMax++;
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


    public void salary (Function function, int index)
    {
        function.getMonthSalary(function, index);
    }

}
