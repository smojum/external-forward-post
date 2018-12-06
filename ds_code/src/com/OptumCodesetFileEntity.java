package com;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "OPTUM_CODESET_FILE", schema = "SMOJUM", catalog = "")
@IdClass(OptumCodesetFileEntityPK.class)
public class OptumCodesetFileEntity {
    private String fileName;
    private Timestamp lastModifiedTs;
    private String zipFileName;
    private String optumCodesetFileSk;
    private Timestamp updateDttm;
    private String updateLogonId;
    private String fileType;
    private String processStatus;
    private Long optumCodesetConfigSk;

    @Id
    @Column(name = "FILE_NAME", nullable = false, length = 255)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Id
    @Column(name = "LAST_MODIFIED_TS", nullable = false)
    public Timestamp getLastModifiedTs() {
        return lastModifiedTs;
    }

    public void setLastModifiedTs(Timestamp lastModifiedTs) {
        this.lastModifiedTs = lastModifiedTs;
    }

    @Id
    @Column(name = "ZIP_FILE_NAME", nullable = false, length = 255)
    public String getZipFileName() {
        return zipFileName;
    }

    public void setZipFileName(String zipFileName) {
        this.zipFileName = zipFileName;
    }

    @Basic
    @Column(name = "OPTUM_CODESET_FILE_SK", nullable = false, length = 255)
    public String getOptumCodesetFileSk() {
        return optumCodesetFileSk;
    }

    public void setOptumCodesetFileSk(String optumCodesetFileSk) {
        this.optumCodesetFileSk = optumCodesetFileSk;
    }

    @Basic
    @Column(name = "UPDATE_DTTM", nullable = true)
    public Timestamp getUpdateDttm() {
        return updateDttm;
    }

    public void setUpdateDttm(Timestamp updateDttm) {
        this.updateDttm = updateDttm;
    }

    @Basic
    @Column(name = "UPDATE_LOGON_ID", nullable = true, length = 255)
    public String getUpdateLogonId() {
        return updateLogonId;
    }

    public void setUpdateLogonId(String updateLogonId) {
        this.updateLogonId = updateLogonId;
    }

    @Basic
    @Column(name = "FILE_TYPE", nullable = true, length = 255)
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Basic
    @Column(name = "PROCESS_STATUS", nullable = true, length = 255)
    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    @Basic
    @Column(name = "OPTUM_CODESET_CONFIG_SK", nullable = true, precision = 0)
    public Long getOptumCodesetConfigSk() {
        return optumCodesetConfigSk;
    }

    public void setOptumCodesetConfigSk(Long optumCodesetConfigSk) {
        this.optumCodesetConfigSk = optumCodesetConfigSk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptumCodesetFileEntity that = (OptumCodesetFileEntity) o;
        return Objects.equals(fileName, that.fileName) &&
                Objects.equals(lastModifiedTs, that.lastModifiedTs) &&
                Objects.equals(zipFileName, that.zipFileName) &&
                Objects.equals(optumCodesetFileSk, that.optumCodesetFileSk) &&
                Objects.equals(updateDttm, that.updateDttm) &&
                Objects.equals(updateLogonId, that.updateLogonId) &&
                Objects.equals(fileType, that.fileType) &&
                Objects.equals(processStatus, that.processStatus) &&
                Objects.equals(optumCodesetConfigSk, that.optumCodesetConfigSk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, lastModifiedTs, zipFileName, optumCodesetFileSk, updateDttm, updateLogonId, fileType, processStatus, optumCodesetConfigSk);
    }
}
