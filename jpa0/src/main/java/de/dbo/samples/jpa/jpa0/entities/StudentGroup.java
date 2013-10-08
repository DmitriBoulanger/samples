package de.dbo.samples.jpa.jpa0.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "STUDENT_GROUPS")
public class StudentGroup implements Serializable {
    private static final long serialVersionUID = 6682592613968564789L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GROUP_ID")
    private Long              groupId;

    @Column(name = "GROUP_NAME", length = 35)
    private String            groupName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Student>     students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(final List<Student> students) {
        this.students = students;
    }

    public Long getmId() {
        return groupId;
    }

    public void setGroupId(final Long mId) {
        this.groupId = mId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(final String groupName) {
        this.groupName = groupName;
    }

    public final String toString() {
        return "GROUP: ID=" + groupId + " Name=" + groupName;
    }

}
