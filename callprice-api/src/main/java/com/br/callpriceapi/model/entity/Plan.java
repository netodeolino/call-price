package com.br.callpriceapi.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Plan {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private Integer duration;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @ManyToOne
    private TaxRate taxRate;
}
