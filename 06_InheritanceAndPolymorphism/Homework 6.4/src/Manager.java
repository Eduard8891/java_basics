public class Manager extends Function
{
    private int salaryM;
    private int salary;

    public Manager()
    {
    }

    public void hire(Function manager)
    {
             salary = (int) (25000 + 10000 * Math.random());
            Company.managers.add(salary);
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
    public int getMonthSalary(Function manager, int index)
    {
        int salaryM = Company.managers.get(index);
        return salaryM;
    }

    public int salary()
    {
        return salaryM;
    }

}
