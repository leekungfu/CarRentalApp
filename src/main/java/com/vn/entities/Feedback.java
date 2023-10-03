package com.vn.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vn.dto.BookingDto;
import com.vn.dto.FeedbackDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double rating;
    private String content;
    private LocalDateTime dateTime;
    @OneToOne
    @JoinColumn(name = "booking_id")
    @JsonIgnore
    private Booking booking;

    public FeedbackDto toDto() {
        FeedbackDto dto = new FeedbackDto();
        dto.setRating(this.getRating());
        dto.setContent(this.getContent());
        dto.setDateTime(this.getDateTime());

        if (booking != null) {
            BookingDto bookingDto = this.getBooking().toDto();
            dto.setBooking(bookingDto);
        }
        return dto;
    }
}
