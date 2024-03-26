package com.example.layeredstructuring.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer useraccount_id;

    @Column @NotNull
    private String fullname;

    @Column(unique = true) @NotNull
    private String email;

    @Column @NotNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
      name = "users_roles",
      joinColumns = {@JoinColumn(name = "fk_useraccount_id", referencedColumnName = "useraccount_id")},
      inverseJoinColumns = {@JoinColumn(name = "fk_role_id", referencedColumnName = "role_id")}
    )
    private List<UserRole> roles = new ArrayList<>();
}

