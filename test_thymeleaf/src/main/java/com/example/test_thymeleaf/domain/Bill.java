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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Entity
@Table(name="bills")
public class Bill implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	private String description;
	
	@NotEmpty
	private String observation;
	
	@Temporal(TemporalType.DATE)
	@Column(name="creation_date")
	private Date creationDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Client client;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="bill_id")
	private List<BillItem>items;
	
	
	
	public Bill() {
		this.items = new ArrayList<BillItem>();
		creationDate=new Date();
	}

	public Double getTotal()
	{
		Double t = new Double(0);
		for(BillItem item : items)
		{
			t+= item.getSubTotal();
		}
		return t;
	}
	public void addItem(BillItem item)
	{
		items.add(item);
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public List<BillItem> getItems() {
		return items;
	}
	public void setItems(List<BillItem> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "Bill [id=" + id + ", description=" + description + ", observation=" + observation + ", creationDate="
				+ creationDate + ", client=" + client + ", items=" + items + "]";
	}
	
	

}
