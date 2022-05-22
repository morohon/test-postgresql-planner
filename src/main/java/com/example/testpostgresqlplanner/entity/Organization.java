package com.example.testpostgresqlplanner.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "organizations")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Organization {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "inn", unique = true, nullable = false)
    private String inn;

    @Column(name = "ogrn", nullable = false)
    private String ogrn;

    @Column(name = "org_type")
    @Enumerated(EnumType.STRING)
    private OrganizationType organizationType;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "short_name")
    private String shortname;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Organization that = (Organization) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
