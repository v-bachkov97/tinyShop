package com.example.tinyshop.service;

import com.example.tinyshop.model.dtos.AddressDTO;
import com.example.tinyshop.model.entity.Address;
import com.example.tinyshop.model.entity.User;
import com.example.tinyshop.repository.AddressRepository;
import com.example.tinyshop.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public AddressService(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public void saveAddress(String username, AddressDTO addressDTO) {

        Optional<User> user = userRepository.findByEmail(username);
        Address address = new Address();
        address.setCityOrProvince(addressDTO.getCityOrProvince());
        address.setName(addressDTO.getName());
        address.setNumber(addressDTO.getNumber());
        address.setPostalCode(addressDTO.getPostalCode());

        user.get().setAddress(address);

        userRepository.save(user.get());
        addressRepository.save(address);

    }

    public boolean isRegistered(UserDetails userDetails) {
        Optional<User> user = userRepository.findByEmail(userDetails.getUsername());
        Address address = user.get().getAddress();

        if (address==null){
            return false;
        }
        return true;
    }
}
