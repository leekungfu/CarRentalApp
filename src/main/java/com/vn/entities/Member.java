package com.vn.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vn.dto.MemberDto;
import com.vn.enums.Role;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
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
    private String email;
    private String password;
    private String phone;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String province;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String district;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String ward;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String street;
    private String drivingLicense;
    private Double wallet;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(length = 30)
    private String resetPasswordToken;
    private String token;
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
//	@Fetch(FetchMode.JOIN)
    @JsonIgnore
    private List<Booking> bookings;
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MemberTransaction> memberTransactions;
    @OneToMany(mappedBy = "member")
    @LazyCollection(LazyCollectionOption.FALSE)
//	@Fetch(FetchMode.JOIN)
//    @JsonIgnore
    @JsonManagedReference
    private List<Car> cars;

    public MemberDto toDto() {
        MemberDto dto = new MemberDto();
        dto.setEmail(this.getEmail());
        dto.setFullName(this.getFullName());
        dto.setBirthDay(String.valueOf(this.getBirthDay()));
        dto.setPhone(this.getPhone());
        dto.setNationalID(this.getNationalID());
        dto.setProvince(this.getProvince());
        dto.setDistrict(this.getDistrict());
        dto.setWard(this.getWard());
        dto.setStreet(this.getStreet());
        dto.setRole(String.valueOf(this.getRole()));
        dto.setToken(this.getToken());
        dto.setWallet(this.getWallet());
        dto.setMemberTransactions(this.getMemberTransactions());
        return dto;
    }
}
