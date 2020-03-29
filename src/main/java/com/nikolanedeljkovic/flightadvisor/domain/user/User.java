package com.nikolanedeljkovic.flightadvisor.domain.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nikolanedeljkovic.flightadvisor.domain.city.Comment;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
public class User {
	
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String username;

    @Column
    private String password;
    
    @JsonIgnore
    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(
       name="user_role",
       joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
       inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Roles> roles;
    
    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;
    

}
