import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name  = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int duration;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private CourseType type;
    private  String description;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Teacher teacher;
    @Column(name = "students_count")
    private Integer studentsCount;
    private int price;
    @Column(name = "price_per_hour")
    private float pricePerHour;
    @OneToMany
    @JoinColumn(name = "course_id")
    private List <Subscription> subscriptions;
    @OneToMany
    @JoinColumn(name = "course_name")
    private List <PurchaseList> purchaseLists;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "subscriptions", joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private List<Student> students;
}
