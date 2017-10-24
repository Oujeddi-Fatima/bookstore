package edu.mum.bookstore.service;

import java.util.List;

import edu.mum.bookstore.domain.Order;

public interface OrderService {

	public Order makeOrder(Order order);

	public List<Order> listOrders();

	public long count();
}
