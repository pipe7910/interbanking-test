package com.polijic.soa.dto.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private String id;

    private String fullName;

    private String surNames;

    private String businessName;

    private String businessId;

    private String email;

    private String address;

    private String phone;
}
