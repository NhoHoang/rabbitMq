package com.example.userservice.entity;


import com.example.userservice.converter.MemberTypeEnumConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "member_type")
    @Convert(converter = MemberTypeEnumConverter.class)
    private MemberType memberType;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + email + '\'' +
                ", memberType=" + memberType+
                '}';
    }
}
