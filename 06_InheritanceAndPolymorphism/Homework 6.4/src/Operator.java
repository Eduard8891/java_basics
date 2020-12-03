public class Operator extends Function {
    private int salaryO;

    public Operator()
    {
    }

    public void hire(Function operator)
    {
        int salary = (int) (15000 + 10000 * Math.random());
        Company.operators.add(salary);
    }

    public  void hireAll (Function operator)
    {
        for (int i = 1; i <= 180; i++) {
            hire(operator);
        }
    }

    public void  fire (Function operator, int index)
    {
        Company.operators.remove(index);
    }


    @Override
    public int getMonthSalary(Function function, int index) {
        salaryO = Company.managers.get(index);
        return salaryO;
    }


    public int salary()
    {
        return salaryO;
    }
}
