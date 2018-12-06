package com;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "OPTUM_CODESET_CONFIG", schema = "SMOJUM", catalog = "")
public class OptumCodesetConfig {
    private long optumCodesetConfigSk;
    private String delimiter;
    private String description;
    private String fileNameMask;
    private String fileType;
    private Boolean isInScope;
    private String targetTable;
    private String zipFileMask;
    private Collection<OptumCodesetFile> optumCodesetFilesByOptumCodesetConfigSk;

    @Id
    @Column(name = "OPTUM_CODESET_CONFIG_SK")
    public long getOptumCodesetConfigSk() {
        return optumCodesetConfigSk;
    }

    public void setOptumCodesetConfigSk(long optumCodesetConfigSk) {
        this.optumCodesetConfigSk = optumCodesetConfigSk;
    }

    @Basic
    @Column(name = "DELIMITER")
    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "FILE_NAME_MASK")
    public String getFileNameMask() {
        return fileNameMask;
    }

    public void setFileNameMask(String fileNameMask) {
        this.fileNameMask = fileNameMask;
    }

    @Basic
    @Column(name = "FILE_TYPE")
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Basic
    @Column(name = "IS_IN_SCOPE")
    public Boolean getInScope() {
        return isInScope;
    }

    public void setInScope(Boolean inScope) {
        isInScope = inScope;
    }

    @Basic
    @Column(name = "TARGET_TABLE")
    public String getTargetTable() {
        return targetTable;
    }

    public void setTargetTable(String targetTable) {
        this.targetTable = targetTable;
    }

    @Basic
    @Column(name = "ZIP_FILE_MASK")
    public String getZipFileMask() {
        return zipFileMask;
    }

    public void setZipFileMask(String zipFileMask) {
        this.zipFileMask = zipFileMask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptumCodesetConfig that = (OptumCodesetConfig) o;
        return optumCodesetConfigSk == that.optumCodesetConfigSk &&
                Objects.equals(delimiter, that.delimiter) &&
                Objects.equals(description, that.description) &&
                Objects.equals(fileNameMask, that.fileNameMask) &&
                Objects.equals(fileType, that.fileType) &&
                Objects.equals(isInScope, that.isInScope) &&
                Objects.equals(targetTable, that.targetTable) &&
                Objects.equals(zipFileMask, that.zipFileMask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optumCodesetConfigSk, delimiter, description, fileNameMask, fileType, isInScope, targetTable, zipFileMask);
    }

    @OneToMany(mappedBy = "optumCodesetConfigByOptumCodesetConfigSk")
    public Collection<OptumCodesetFile> getOptumCodesetFilesByOptumCodesetConfigSk() {
        return optumCodesetFilesByOptumCodesetConfigSk;
    }

    public void setOptumCodesetFilesByOptumCodesetConfigSk(Collection<OptumCodesetFile> optumCodesetFilesByOptumCodesetConfigSk) {
        this.optumCodesetFilesByOptumCodesetConfigSk = optumCodesetFilesByOptumCodesetConfigSk;
    }
}
