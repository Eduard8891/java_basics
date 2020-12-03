public class TopManager extends Function{
    private int salaryT;

    public TopManager()
    {

    }

    public void hire(Function topManager)
    {
        if(Company.income >= 10000000) {
            int salary = (int) (100000 + 100000 * Math.random());
            salary = salary + (salary/2);
            Company.topManagers.add(salary);
        }
        else {
            int salary = (int) (100000 + 100000 * Math.random());
            Company.topManagers.add(salary);
        }
    }

    public  void hireAll (Function torManager)
    {
        for (int i = 1; i <= 10; i++) {
            hire(torManager);
        }
    }

    public void  fire (Function topManager, int index)
    {
        int salary = Company.topManagers.remove(index);
        System.out.println(salary);
    }



    @Override
    public int getMonthSalary(Function topManager, int index)
    {
       salaryT = Company.topManagers.get(index);
       return salaryT;
    }

    public int salary()
    {
        return salaryT;
    }

}
