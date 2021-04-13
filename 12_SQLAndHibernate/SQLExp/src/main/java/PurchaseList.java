import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name  = "purchaselist")
public class PurchaseList {

    @EmbeddedId
    private PurchaseListId key;
    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_name", insertable = false, updatable = false)
    private Student student;
    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_name", insertable = false, updatable = false)
    private Course course;
    @Getter
    @Setter
    private int price;
    @Getter
    @Setter
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @EqualsAndHashCode
    @ToString
    @Embeddable
    public static class PurchaseListId implements Serializable {
        static final long serialVersionUID = 1L;
        @Getter
        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        Student student;
        @Getter
        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        Course course;
        public PurchaseListId() {}
        public PurchaseListId(Student student, Course course) {
            this.student = student;
            this.course = course;
        }
    }
}
