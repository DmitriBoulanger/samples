package de.dbo.samples.jpa.jpa0.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "STUDENTS")
public class Student implements Serializable {
    private static final long serialVersionUID = -6146935825517747043L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STUDENT_ID")
    private Long              studentId;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID", nullable = false)
    private StudentGroup      group;

    @Column(name = "LAST_NAME", length = 35)
    private String            lastname;

    @Column(name = "FIRST_NAME", nullable = false, length = 35)
    private String            firstname;

    @Column(name = "BIRTH_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date              birthdate;

    public StudentGroup getGroup() {
        return group;
    }

    public void setGroup(final StudentGroup group) {
        this.group = group;
    }

    public Long getSudentId() {
        return studentId;
    }

    public void setSudentId(final Long studentId) {
        this.studentId = studentId;
    }

    public String getmLastname() {
        return lastname;
    }

    public void setmLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(final Date birthdate) {
        this.birthdate = birthdate;
    }

    public final String toString() {
        return "Student: ID=" + studentId + " FirstName=" + firstname + " Group=" + group.getGroupName();
    }

}
