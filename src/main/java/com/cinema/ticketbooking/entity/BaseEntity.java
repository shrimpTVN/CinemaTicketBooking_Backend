package com.cinema.ticketbooking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@MappedSuperclass
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Column(name="created_at", nullable =false, updatable=false)
    @CreatedDate
    @CreationTimestamp
    private Instant createdAt;

    @Column(name="created_by", nullable =false, updatable=false, length = 20)
    @CreatedBy
    private String createdBy;

    @Column(name="updated_at", insertable=false)
    @LastModifiedDate
    private Instant updateAt;

    @Column(name="updated_by", insertable=false, length=20)
    @LastModifiedBy
    private String updatedBy;
}
