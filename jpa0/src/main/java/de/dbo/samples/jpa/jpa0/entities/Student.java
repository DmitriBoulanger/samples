package de.dbo.samples.jpa.jpa0.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mId;

    @Column(name = "LAST_NAME", length = 35)
    private String mLastName;

    @Column(name = "FIRST_NAME", nullable = false, length = 35)
    private String mFirstName;

    @Column(name = "BIRTH_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date mBirthDate;

	public Long getmId() {
		return mId;
	}

	public void setmId(Long mId) {
		this.mId = mId;
	}

	public String getmLastName() {
		return mLastName;
	}

	public void setmLastName(String mLastName) {
		this.mLastName = mLastName;
	}

	public String getmFirstName() {
		return mFirstName;
	}

	public void setmFirstName(String mFirstName) {
		this.mFirstName = mFirstName;
	}

	public Date getmBirthDate() {
		return mBirthDate;
	}

	public void setmBirthDate(Date mBirthDate) {
		this.mBirthDate = mBirthDate;
	}

	
    public final String toString() {
    	return "Student: ID="+mId+ " FirstName="+mFirstName;
    }
    
}

