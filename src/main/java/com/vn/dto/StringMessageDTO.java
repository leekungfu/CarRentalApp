package com.vn.dto;

import com.vn.entities.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class StringMessageDTO implements Serializable {
    String message;
}
