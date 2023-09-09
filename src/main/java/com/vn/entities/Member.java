package com.vn.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vn.enums.Role;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "member")
public class Member implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "NVARCHAR(50)")
    private String fullName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDay;
    private String nationalID;
    @Column(unique = true)
    @NotEmpty(message = "Please enter your email")
    private String email;
    @NotEmpty(message = "Please enter your password")
    private String password;
    @NotNull
    private String phone;
    @Column(columnDefinition = "NVARCHAR(50)")
    private String province;
    @Column(columnDefinition = "NVARCHAR(50)")
    private String district;
    @Column(columnDefinition = "NVARCHAR(50)")
    private String ward;
    private String street;
    private String drivingLicense;
    private Double wallet;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(length = 30)
    private String resetPasswordToken;
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
//	@Fetch(FetchMode.JOIN)
    @JsonIgnore
    private List<Booking> bookings;
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MemberTransaction> memberTransactions;
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
//	@Fetch(FetchMode.JOIN)
    @JsonIgnore
    @JsonManagedReference
    private List<Car> cars;
}
