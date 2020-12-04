public class TopManager extends Function{


    public TopManager(int salary)
    {
        super(salary);

    }

    public void hire(Function topManager)
    {

            Company.topManagers.add(new TopManager(getMonthSalary()));

    }

    public  void hireAll (Function torManager)
    {
        for (int i = 1; i <= 10; i++) {
            hire(torManager);
        }
    }

    public void  fire (Function topManager, int index)
    {
        Company.topManagers.remove(index);
    }



    @Override
    public int getMonthSalary()
    {
        int salary;
        if(Company.income >= 10000000) {
            salary = (int) (100000 + 100000 * Math.random());
            salary = salary + (salary/2);
        }
        else {
            salary = (int) (100000 + 100000 * Math.random());
        }
       return salary;
    }



}
