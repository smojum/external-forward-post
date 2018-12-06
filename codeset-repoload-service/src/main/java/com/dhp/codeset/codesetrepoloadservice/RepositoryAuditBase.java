package com.dhp.codeset.codesetrepoloadservice;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
//@Audited
public class MappedAuditBase implements Serializable {

    //@NotAudited
    @LastModifiedDate
    private LocalDateTime createDttm;
    @LastModifiedBy
    private String createLogonId;

    //@NotAudited
    @LastModifiedDate
    private LocalDateTime updateDttm;
    @LastModifiedBy
    private String updateLogonId;

    //@NotAudited
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE})
    @JoinColumn(name = "OPTUM_CODESET_FILE_SK", referencedColumnName = "optumCodesetFileSk")
    private OptumCodesetFile file;

}
