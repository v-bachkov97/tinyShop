package com.example.tinyshop.model.user;

import com.example.tinyshop.model.entity.Cart;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class TinyShopUserDetails implements UserDetails {


    private final String password;
    private final String username;
    private final String fullName;
    private final Cart cart;
    private final long id;
    private final Collection<GrantedAuthority> authorities;

    public TinyShopUserDetails(String password,
                               String username,
                               String fullName,
                               Cart cart,
                               long id,
                               Collection<GrantedAuthority> authorities) {

        this.password = password;
        this.username = username;
        this.fullName = fullName;
        this.cart = cart;
        this.id = id;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public long getId() {
        return id;
    }

    public Cart getCart() {
        return this.cart;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
