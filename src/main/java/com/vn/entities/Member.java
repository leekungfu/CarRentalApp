package com.vn.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
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

	@Override
	public String toString() {
		return "Member{" +
				"city='" + city + '\'' +
				", district='" + district + '\'' +
				", ward='" + ward + '\'' +
				'}';
	}

	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<MemberTransaction> memberTransactions;

	@OneToMany(mappedBy = "member")
	private List<Car> cars;
}
