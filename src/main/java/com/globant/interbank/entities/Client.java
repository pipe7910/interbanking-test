package com.globant.interbank.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


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
