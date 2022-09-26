package ru.kata.spring.boot_security.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

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

    @Column//(unique = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleEntity)) return false;
        RoleEntity that = (RoleEntity) o;
        return role.equals(that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role);
    }
}
