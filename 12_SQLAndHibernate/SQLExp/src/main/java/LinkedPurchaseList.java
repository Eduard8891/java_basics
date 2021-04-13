import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class LinkedPurchaseList {

    private LinkedPurchaseListKey key;
    private int studentId;
    private int courseId;

    @EqualsAndHashCode
    @ToString
    @Embeddable
    public static class LinkedPurchaseListKey implements Serializable {
        @Getter
        @Setter
        @Column(name  = "student_name")
        private String studentName;

        @Getter
        @Setter
        @Column(name  = "course_name")
        private String courseName;
    }
}
