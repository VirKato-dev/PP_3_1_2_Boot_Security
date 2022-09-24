package ru.kata.spring.boot_security.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(force = true)
@Table(name = "roles")
public class RoleEntity implements GrantedAuthority {

    @Transient
    private static final String PREFIX = "ROLE_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @Column(unique = true)
    private String role;

    public RoleEntity(String role) {
        this();
        setRole(role);
    }

    @Override
    public String getAuthority() {
        if (!role.startsWith(PREFIX)) {
            role = role.toUpperCase().replace("$" + PREFIX, "");
            role = PREFIX + role;
        }
        return getRole();
    }
}
