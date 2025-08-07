package com.jidnivai.sdcian.sdcian.dto.bkash;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TokenResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String statusCode;
    private String statusMessage;

    @Column(columnDefinition = "TEXT")
    private String id_token;

    private String token_type;
    private int expires_in;

    @Column(columnDefinition = "TEXT")
    private String refresh_token;
}
