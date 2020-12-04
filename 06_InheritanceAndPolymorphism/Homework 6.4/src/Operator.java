public class Operator extends Function {


    public Operator(int salary)
    {
        super(salary);
    }

    public void hire(Function operator)
    {
        Company.operators.add(new Operator(getMonthSalary()));
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
    public int getMonthSalary() {
        int salary = (int) (15000 + 10000 * Math.random());
        return salary;
    }


}
