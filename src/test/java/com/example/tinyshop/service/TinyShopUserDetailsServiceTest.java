package com.example.tinyshop.service;

import com.example.tinyshop.model.entity.Role;
import com.example.tinyshop.model.entity.User;
import com.example.tinyshop.model.enums.GenderCategory;
import com.example.tinyshop.model.enums.RolesEnum;
import com.example.tinyshop.model.user.TinyShopUserDetails;
import com.example.tinyshop.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TinyShopUserDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepo;

    private TinyShopUserDetailsService toTest;

    @BeforeEach
    void setUp() {
        toTest = new TinyShopUserDetailsService(mockUserRepo);
    }

    @Test
    void testLoadUserByUsername_UserExist() {
        User testUser = new User();
        testUser.setEmail("ventsislavbachkov@gmail.com");
        testUser.setPassword("chelsea97");
        testUser.setGender(GenderCategory.MALE);
        testUser.setFullName("Ventsislav Bachkov");
        testUser.setBirthDate(LocalDate.of(1997, 12, 18));
        Set<Role> roles = new HashSet<>();
        Role admin = new Role();

        admin.setName(RolesEnum.ADMIN);
        roles.add(admin);
        testUser.setRoles(roles);

        when(mockUserRepo.findByEmail(testUser.getEmail())).
                thenReturn(Optional.of(testUser));
        TinyShopUserDetails userDetails = (TinyShopUserDetails) toTest.loadUserByUsername(testUser.getEmail());
        Assertions.assertEquals(testUser.getEmail(), userDetails.getUsername());
        Assertions.assertEquals(testUser.getFullName(), userDetails.getFullName());
        Assertions.assertEquals(testUser.getPassword(), userDetails.getPassword());

        var authorities = userDetails.getAuthorities();

        Assertions.assertEquals(1, authorities.size());

        var authoritiesIter = authorities.iterator();

        Assertions.assertEquals("ROLE_" + RolesEnum.ADMIN.name(),
                authoritiesIter.next().getAuthority());

    }

    @Test
    void testLoadUserByUsername_UserDoesNotExist() {

        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername("missing@abv.bg"));
    }
}
