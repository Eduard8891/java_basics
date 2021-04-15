import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Getter
@Setter
@Table(name  = "purchaselist")
public class PurchaseList {
    @Column(name = "student_name")
    String studentName;
    @Column(name = "course_name")
    String courseName;
    private Integer price;
    @Id
    @Column(name = "subscription_date")
    private Date subscriptionDate;
}
