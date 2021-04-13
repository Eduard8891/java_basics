import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class Main {
    static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactoryMethod();


        purchaseList();


        sessionFactory.close();
    }


    public static void teachers() {
        Session session = sessionFactory.openSession();
        Teacher teacher = session.get(Teacher.class, 1);
        System.out.println(teacher.getName());
    }

    public static void students() {
        Session session = sessionFactory.openSession();
        Student student = session.get(Student.class, 1);
        System.out.println(student.getName());
        for(Course course: student.getCourses()) System.out.println(course.getName()+" - "+student.getName());
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

    public static void purchaseList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Student s = session.get(Student.class, 84);
        Course c = session.get(Course.class, 3);
        PurchaseList.PurchaseListId key = new PurchaseList.PurchaseListId(s,c);
        session.getTransaction().commit();
        PurchaseList purchaseList = session.get(PurchaseList.class, key);

        System.out.println(purchaseList.getStudent().getName()+" - "+purchaseList.getCourse().getName());
        session.close();
    }


    public static void sessionFactoryMethod() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }
}
