public abstract class Function implements Employee {


    public Function() {

    }

    public abstract void hire (Function function);

    public abstract void hireAll (Function function);

    public abstract  void  fire (Function function, int index);

    @Override
    public abstract void getMonthSalary(Function function, int index);
}
