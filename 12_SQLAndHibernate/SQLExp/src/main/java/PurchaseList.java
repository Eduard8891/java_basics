import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Getter
@Setter
@IdClass(PurchaseList.PurchaseListId.class)
@Table(name  = "purchaselist")
public class PurchaseList {

    @Id
    @Column(name = "student_name", insertable = false, updatable = false)
    private String studentName;
    @Id
    @Column(name = "course_name", insertable = false, updatable = false)
    private String CourseName;
    private Integer price;
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @EqualsAndHashCode
    @ToString
    @Embeddable
    public static class PurchaseListId implements Serializable {
        @Column(name = "student_name", insertable = false, updatable = false)
        private String studentName;
        @Column(name = "course_name", insertable = false, updatable = false)
        private String CourseName;
    }
}
