import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name  = "linked_purchaselist")
public class LinkedPurchaseList {
    @EmbeddedId
    private LinkedPurchaseListId key;
    @Column(name = "student_id", insertable = false, updatable = false)
    private int studentId;
    @Column(name = "course_id", insertable = false, updatable = false)
    private int courseId;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode
    @ToString
    public static class LinkedPurchaseListId implements Serializable {
        @ManyToOne
        private Student student;
        @ManyToOne
        private Course course;
    }
}
