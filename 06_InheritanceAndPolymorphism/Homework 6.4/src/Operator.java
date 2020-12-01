public class Operator extends Function {

    public Operator() {

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
    public void getMonthSalary(Function operator, int index)
    {
        int salary = Company.operators.get(index);
        System.out.println(salary);
    }

}
