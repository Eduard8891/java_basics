public class Manager extends Function
{

    public Manager() {
    }

    public void hire(Function manager)
    {
            int salary = (int) (25000 + 10000 * Math.random());
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
    public void getMonthSalary(Function manager, int index)
    {
        int salary = Company.managers.get(index);
        System.out.println(salary);
    }

}
