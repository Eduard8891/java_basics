public class Main {


    public static void main(String[] args) {


        Company company = new Company();
        Function manager = new Manager(5000);
        Function operator = new Operator(5000);
        Function topManager = new TopManager(50000);

        company.hireAll(manager);  //Нанять всех менеджеров
        company.hireAll(operator);  //Нанять всех операторов
        company.hireAll(topManager);  //Нанять всех топменеджеров
        company.hire(manager);  //Нанять менеджера (1)
        company.fire(manager, 80);  //Уволить менеджера с индексом 80

        company.fireFiftyPercentOfAll(); //Уволить половину сотрудников
        System.out.println(Company.allSalary.size()); //Количество сотрудников
        company.getIncome();  //Доход компании
        System.out.println(company.getLowestSalaryStaff(20));
        System.out.println(company.getTopSalaryStaff(5));
    }
}
