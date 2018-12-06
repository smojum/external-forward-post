package com.dhp.codesetloadservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Audited
public class OptumCodesetFile extends OptumCodesetFileBase {

    @EmbeddedId
    private OptumCodesetFileIdentity optumCodesetFileIdentity;

    private String fileType;
    private String processStatus;

    @NotAudited
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OPTUM_CODESET_CONFIG_SK")
    private OptumCodesetConfig config;
}
