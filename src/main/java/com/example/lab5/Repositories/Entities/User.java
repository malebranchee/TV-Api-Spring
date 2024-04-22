package com.example.lab5.Repositories.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "login")
    @Size(min=2, message = "Не меньше 2 знаков")
    @NotBlank(message = "Поле не должно содержать символы пробелы!")
    private String username;

    @Setter
    @Column(name = "password")
    @Size(min=2, message = "Не меньше 2 знаков")
    @NotBlank(message = "Пароль не может содержать пробелы!")
    private String password;

    @Setter
    @Getter
    @Column(name = "passwordConfirm")
    @Transient
    private String passwordConfirm;

    @Setter
    @Getter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_x_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public void addRole(Role role) {
        if (isNull(roles))
            roles = new HashSet<Role>();
        roles.add(role);
    }

    public void deleteRole(Role role) {
        roles.remove(role);
    }

    public void deleteAllRoles() {
        roles.clear();
    }

    public User() {
    }

    @Override
    public String getUsername() {
        return username;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }
}
