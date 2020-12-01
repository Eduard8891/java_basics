public class Main {


    public static void main(String[] args) {


        Company company = new Company();
        Function manager = new Manager();
        Function operator = new Operator();
        Function topManager = new TopManager();


        company.hireAll(manager);  //Нанять всех менеджеров
        company.hireAll(operator);  //Нанять всех операторов
        company.hireAll(topManager);  //Нанять всех топменеджеров
        company.hire(manager);  //Нанять менеджера (1)
        company.fire(manager, 80);  //Уволить менеджера с индексом 80
        company.minMaxSalary();  //Мин./макс. зарплаты в компании
        company.fireFiftyPercentOfAll(); //Уволить половину сотрудников
        System.out.println(Company.allSalary.size()); //Общее количество сотрудников



        company.getIncome();  //Доход компании

        company.salary(operator, 5); //Реализация getMonthSalary, з/п оператора с индексом 5




    }
}