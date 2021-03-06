import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name  = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String name;
    private int age;
    @Column(name = "registration_date")
    private Date registrationDate;
    @OneToMany
    @JoinColumn(name = "student_id")
    private List <Subscription> subscriptions;
    @OneToMany
    @JoinColumn(name = "student_name")
    private List <PurchaseList> purchaseLists;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "subscriptions", joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private List<Course> courses;

}
