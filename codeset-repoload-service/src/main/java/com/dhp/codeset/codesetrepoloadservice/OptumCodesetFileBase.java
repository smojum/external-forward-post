package com.dhp.codesetloadservice;

import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
@Audited
public class OptumCodesetFileBase implements Serializable {

    @NotAudited
    @Column(nullable = false, unique = true)
    private String optumCodesetFileSk;

    @PrePersist
    private void assignUIID(){
        this.setOptumCodesetFileSk(UUID.randomUUID().toString());
    }

    @NotAudited
    @LastModifiedDate
    private LocalDateTime updateDttm;
    @LastModifiedBy
    private String updateLogonId;
}
