package com.example.test_thymeleaf.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Clients")
public class Client implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	@Column(name="last_name")
	private String lastName;
	
	@NotEmpty
	@Email
	private String email;
	
	@Column(name="creation_date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull
	private Date creationDate;
	
	@OneToMany(mappedBy="client",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Bill>bills;
	
	
	private String image;
	
	public Client ()
	{
		creationDate = new Date();
	}
	@PrePersist
	public void prePersist()
	{
		creationDate = new Date();
		bills = new ArrayList<Bill>();
	}
	public void addBill(Bill bill)
	{
		bills.add(bill);
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<Bill> getBills() {
		return bills;
	}
	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}
	@Override
	public String toString() {
		return name+" "+lastName;
	}

}
