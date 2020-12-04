public class Manager extends Function
{


    public Manager(int salary)
    {
        super(salary);
    }


    public void hire(Function manager)
    {

        Manager man = new Manager(getMonthSalary());
            Company.managers.add(man);
    }

    public  void hireAll (Function manager)
    {
        for (int i = 1; i <= 80; i++) {
            hire(manager);
        }
    }

    public void  fire (Function manager, int index)
    {
        Company.managers.remove(index);
    }


    @Override
    public int getMonthSalary()
    {
        int salary = (int) (25000 + 10000 * Math.random());
        return salary;
    }



}
