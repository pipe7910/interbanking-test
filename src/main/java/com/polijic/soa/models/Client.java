package com.polijic.soa.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "surnames")
    private String surNames;

    @Column(name = "businessname")
    private String businessName;

    @Column(name = "businessid")
    private String businessId;

    @Column(name = "email")
    private String email;

    @Column(name = "addres")
    private String addres;

    @Column(name = "phone")
    private String phone;
}
