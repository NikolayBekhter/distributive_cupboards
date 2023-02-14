package ru.gentle.distributive.cupboard.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nickname", nullable = false)
    private String nickname;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "is_present", nullable = false)
    private boolean isPresent;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @CreationTimestamp
    @Column(name = "created_at"/*, nullable = false, updatable = false*/)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
