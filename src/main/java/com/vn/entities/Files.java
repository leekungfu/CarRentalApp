package com.vn.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "files")
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String type;
    private String url;
    @Lob
    private byte[] data;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "car_id")
    private Car car;

    public Files(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Files(String name, String type, byte[] data) {
            this.name = name;
            this.type = type;
            this.data = data;

    }
}
