package com.vn.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Base64;

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
    private String base64Data;
    @ManyToOne
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

    public Files(String name, String type, byte[] data, Car car) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.car = car;
    }
    public String getBase64Data() {
        if (data != null) {
            return Base64.getEncoder().encodeToString(data);
        }
        return null;
    }

    public void setBase64Data(String base64Data) {
        if (base64Data != null) {
            this.data = Base64.getDecoder().decode(base64Data);
        }
    }
}
