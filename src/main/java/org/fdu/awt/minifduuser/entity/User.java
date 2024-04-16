package org.fdu.awt.minifduuser.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.fdu.awt.minifduuser.bo.user.RegisterReq;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
//@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false, name = "password")
    private String password;

    public static User fromRegisterReq(RegisterReq registerReq) {
        return User.builder()
                .name(registerReq.getName())
                .email(registerReq.getEmail())
                .password(registerReq.getPassword())
                .build();
    }

}
