package com.clients.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Table(name = "Users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client extends RepresentationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "{name.not.blank}")
    private String name;
    @NotBlank(message = "{birth.not.blank}")
    private  String birth;
    @NotBlank(message = "{gender.not.blank}")
    private String gender;
    @Email(message = "{email.not.valid}")
    private String email;
    @NotBlank(message = "{contact.not.blank}")
    private String contact;
    @NotBlank(message = "{address.not.blank}")
    private String address;
    @NotBlank(message = "{cpf.not.blank}")
    private String cpf;



}
