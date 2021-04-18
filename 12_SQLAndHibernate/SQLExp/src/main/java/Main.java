import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;


public class Main {
    static SessionFactory sessionFactory;
    static ArrayList<String> purchaseList = new ArrayList<>();
    static ArrayList<String> studentsList = new ArrayList<>();
    static ArrayList<String> coursesList = new ArrayList<>();

    public static void main(String[] args) {
        sessionFactoryMethod();


        getStudentsAndCourses();
        createTable();


students();



        sessionFactory.close();
    }

    public static void createTable() {
        Session s = sessionFactory.getCurrentSession();
        s.beginTransaction();
//        s.createNativeQuery("TRUNCATE TABLE LinkedPurchaseList;").executeUpdate();
        for (String line1: purchaseList) {
            String[] linePurchaseList = line1.split(",");
            for (String line2: studentsList) {
                String[] lineStudents = line2.split(",");
                if (lineStudents[1].equals(linePurchaseList[0])) {
                    for (String line3: coursesList) {
                        String[] lineCourses = line3.split(",");
                        if (linePurchaseList[1].equals(lineCourses[1])) {
                            int idStudent = Integer.parseInt(lineStudents[0]);
                            int idCourse = Integer.parseInt(lineCourses[0]);
                            LinkedPurchaseList.LinkedPurchaseListId key =
                                    new LinkedPurchaseList
                                            .LinkedPurchaseListId
                                            (s.get(Student.class, idStudent), s.get(Course.class, idCourse));
                            LinkedPurchaseList obj = new LinkedPurchaseList(key, idStudent, idCourse);
                            s.save(obj);
                        }
                    }
                }
            }
        }
        s.getTransaction().commit();
    }

    public static void teachers() {
        Session session = sessionFactory.openSession();
        Teacher teacher = session.get(Teacher.class, 1);
        System.out.println(teacher.getName());
    }

    public static void students() {
        Session session = sessionFactory.openSession();
        Student student = session.get(Student.class, 5);
        for(Subscription subscription: student.getSubscriptions()) System.out.println(subscription.getCourse().getName()+" - "+student.getName());
    }

    public static void courses() {
        Session session = sessionFactory.openSession();
        Course course = session.get(Course.class, 1);
        System.out.println(course.getName());
        for(Student student: course.getStudents()) System.out.println(course.getName()+" - "+student.getName());
    }

    public static void subscriptions() {
        Session session = sessionFactory.openSession();
        Subscription.SubscriptionId key =
                new Subscription.SubscriptionId(session.get(Student.class, 47),
                        session.get(Course.class, 1));
        Subscription subscription = session.get(Subscription.class, key);
        System.out.println(subscription.getStudent().getName()+" - "+subscription.getCourse().getName());
        session.close();
    }

    public static void getStudentsAndCourses() {
        Session s = sessionFactory.getCurrentSession();
        s.beginTransaction();
        List<PurchaseList> list = s.createQuery("FROM PurchaseList").getResultList();
        for (PurchaseList p: list) {
            purchaseList.add(p.getStudentName()+","+p.getCourseName());
        }

        List<Student> list2 = s.createQuery("FROM Student").getResultList();
        for (Student stud: list2) {
            if (!studentsList.contains(String.valueOf(stud.getId())+","+stud.getName()))
                studentsList.add(String.valueOf(stud.getId())+","+stud.getName());
        }

        List<Course> list3 = s.createQuery("FROM Course").getResultList();
        for (Course cour: list3) {
            if (!coursesList.contains(String.valueOf(cour.getId()) + "," + cour.getName())) {
                coursesList.add(String.valueOf(cour.getId()) + "," + cour.getName());
            }
        }
        s.close();
    }

    public static void sessionFactoryMethod() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }
}
