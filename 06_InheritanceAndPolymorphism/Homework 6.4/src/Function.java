public abstract class Function implements Employee {


    public Function(int salary)
    {

        getMonthSalary();
    }

    public abstract void hire (Function function);

    public abstract void hireAll (Function function);

    public abstract void fire (Function function, int index);

    @Override
    public abstract int getMonthSalary();


}
