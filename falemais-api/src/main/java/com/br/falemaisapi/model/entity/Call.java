package com.br.falemaisapi.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Call {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private Integer dddOrigin;

    @Getter
    @Setter
    private Integer dddDestiny;

    @Getter
    @Setter
    private Integer duration;

    @Getter
    @Setter
    @ManyToOne
    private Plan plan;
}
