package com.dhp.codesetloadservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OptumCodesetConfig {

    @Id
    private Long OptumCodesetConfigSk;
    private String zipFileMask;
    private String fileNameMask;
    private String fileType;
    private String delimiter;
    private String targetTable;
    private String description;
    private Boolean isInScope;
}
