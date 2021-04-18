import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@Entity
@Table(name  = "subscriptions")
public class Subscription {
    @EmbeddedId
    private SubscriptionId key;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;
    @Column(name  = "subscription_date")
    private Date subscriptionDate;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @EqualsAndHashCode
    @ToString
    @Embeddable
    public static class SubscriptionId implements Serializable {
        static final long serialVersionUID = 1L;
        @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "student_id", insertable = false, updatable = false)
        private Student student;
        @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "course_id", insertable = false, updatable = false)
        private Course course;
    }
}
