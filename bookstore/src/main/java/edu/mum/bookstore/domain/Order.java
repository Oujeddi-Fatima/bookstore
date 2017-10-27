package edu.mum.bookstore.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = "ORDERS")
@Table(name = "ORDERS")
public class Order implements Serializable {

	public Order() {
	}

	private static final long serialVersionUID = 1L;

	public Order(User user, List<OrderItem> orderItems) {
		this.user = user;
		this.orderItems = orderItems;
		setAddress(user.getAddress());
		setPhone(user.getPhone());
		setZipcode(user.getZipcode());
		setTime(new Date());
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
	private List<OrderItem> orderItems;

	private String orderNo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	@NotEmpty(message="Please insert address")
	private String address;
	@Size(min = 2, max=6, message="Please Insert a Valid ZipCode")
	private String zipcode;
	private String phone;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;

	public void addOrderItem(OrderItem orderItem) {
		if (orderItems == null) {
			orderItems = new ArrayList<>();
		}
		orderItems.add(orderItem);

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Order updateShippingDataOrder(Order order) {
		this.address = order.getAddress();
		this.zipcode = order.getZipcode();
		this.phone = order.getPhone();
		return this;
	}

}
