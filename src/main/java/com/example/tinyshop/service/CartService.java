package com.example.tinyshop.service;

import com.example.tinyshop.exception.ProductNotFoundException;
import com.example.tinyshop.model.entity.*;
import com.example.tinyshop.model.view.ProductCartView;
import com.example.tinyshop.repository.CartRepository;
import com.example.tinyshop.repository.OrderRepository;
import com.example.tinyshop.repository.ProductRepository;
import com.example.tinyshop.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public CartService(ProductRepository productRepository, UserRepository userRepository, ModelMapper modelMapper, CartRepository cartRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;

        this.modelMapper = modelMapper;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }


    public BigDecimal findTotalPrice(List<ProductCartView> customerProducts) {
        BigDecimal sum = new BigDecimal(0);
        for (ProductCartView customerProduct : customerProducts) {
            sum = sum.add(customerProduct.getPrice());
        }

        return sum;
    }

    public List<ProductCartView> getProducts(String username) {
        Optional<User> customer = this.userRepository.findByEmail(username);
        List<Product> cart = customer.get().getCart().getProducts();


        return cart.stream().map(product -> modelMapper.map(product, ProductCartView.class)).toList();
    }

    public void addProduct(long id, String email) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(()->
                new ProductNotFoundException("Product with id " + id + "not found."));
        Optional<User> user = userRepository.findByEmail(email);
        user.get().getCart().getProducts().add(product);
        Cart currentCart = user.get().getCart();
        cartRepository.save(currentCart);
    }

    public void removeItem(long id, String username) throws ProductNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        List<Product> userProducts = user.get().getCart().getProducts();
        Cart cart = user.get().getCart();
        Product productToRemove = productRepository.findById(id).orElseThrow(()->
                new ProductNotFoundException("Product with id " + id + "not found."));
        userProducts.remove(productToRemove);
        cartRepository.save(cart);
    }

    public boolean makeOrder(String username) {
        Optional<User> user = userRepository.findByEmail(username);
        Address address = user.get().getAddress();

        if (address == null) {
            return false;
        }
        Order order = new Order();
        order.setBuyer(user.get());
        order.setProducts(new ArrayList<>());
        for (Product product : user.get().getCart().getProducts()) {
            order.getProducts().add(product);
        }
        orderRepository.save(order);


        return true;
    }

    public LinkedHashMap<String, String> getOrderDetails(String username) {
        LinkedHashMap<String, String> details = new LinkedHashMap<>();
        Optional<User> user = userRepository.findByEmail(username);
        details.put("name", user.get().getFullName());
        details.put("street", String.format("%s %d", user.get().getAddress().getName(), user.get().getAddress().getNumber()));
        details.put("city", String.format("%d %s", user.get().getAddress().getPostalCode(), user.get().getAddress().getCityOrProvince()));
        details.put("totalPrice", this.findTotalPrice(this.getProducts(username)).add(BigDecimal.valueOf(8.50)).toString());
        return details;
    }

    public void clearCart(UserDetails userDetails) {
        Optional<User> user = userRepository.findByEmail(userDetails.getUsername());
        user.get().getCart().getProducts().clear();
        userRepository.save(user.get());
    }
}
