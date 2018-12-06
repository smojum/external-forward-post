package com.dhp.codeset.codesetrepositoryservice;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@MappedSuperclass
@Data
@EnableJpaAuditing

public class AuditBase {
    @CreatedDate
    private Timestamp insertDttm;
    private String insertLogonId;
    @LastModifiedDate
    private Timestamp updateDttm;
    private String updateLogonId;
}
