import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name  = "subscriptions")
public class Subscription {


    @EmbeddedId
    private SubscriptionId key;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

    @Getter
    @Setter
    @Column(name  = "subscription_date")
    private Date subscriptionDate;

    @EqualsAndHashCode
    @ToString
    @Embeddable
    public static class SubscriptionId implements Serializable {

        static final long serialVersionUID = 1L;
        @Getter
        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Student student;

        @Getter
        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Course course;

        public SubscriptionId() {}
        public SubscriptionId(Student student, Course course){
            this.student = student;
            this.course = course;
        }
    }
}
