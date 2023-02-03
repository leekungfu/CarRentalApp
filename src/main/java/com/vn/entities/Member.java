package com.vn.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Member implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty(message = "Please enter your name")
	@Column(columnDefinition = "NVARCHAR(50)")
	private String fullName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDay;
	private String nationalID;

	@Column(unique = true)
	@NotEmpty(message = "Please enter your email")
	@Length(max = 50, message = "Email must not exceed 50 characters.")
	@Pattern(regexp = "^\\w*@\\w{5}\\.\\w{3}", message = "Please enter the correct email.")
	private String email;
	@NotEmpty(message = "Please enter your password")
	private String password;
	@NotNull
	@NotEmpty(message = "Please enter your phone number")
	@Size(min = 10, max= 11)
	@Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[\\s\\./0-9]*$")
	@Digits(fraction = 0, integer = 11)
	private String phone;
	@Column(columnDefinition = "NVARCHAR(50)")
	private String city;
	@Column(columnDefinition = "NVARCHAR(50)")
	private String district;
	@Column(columnDefinition = "NVARCHAR(50)")
	private String ward;
	private String street;
	private String drivingLicense;
	private Double wallet;
	private String role;
	@Column(length = 30)
	private String resetPasswordToken;

	@OneToMany(mappedBy = "member")
	private List<Booking> bookings;

	public Member(String fullName, LocalDate birthDay, String nationalID, String email, String phone, String city, String district, String ward, String street, String drivingLicense) {
		this.fullName = fullName;
		this.birthDay = birthDay;
		this.nationalID = nationalID;
		this.email = email;
		this.phone = phone;
		this.city = city;
		this.district = district;
		this.ward = ward;
		this.street = street;
		this.drivingLicense = drivingLicense;
	}



	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<MemberTransaction> memberTransactions;

	@OneToMany(mappedBy = "member")
	private List<Car> cars;
}
