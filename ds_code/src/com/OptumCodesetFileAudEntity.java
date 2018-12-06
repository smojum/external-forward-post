package com;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "OPTUM_CODESET_FILE_AUD", schema = "SMOJUM", catalog = "")
@IdClass(OptumCodesetFileAudEntityPK.class)
public class OptumCodesetFileAudEntity {
    private String fileName;
    private Timestamp lastModifiedTs;
    private String zipFileName;
    private int rev;
    private Byte revtype;
    private String updateLogonId;
    private String fileType;
    private String processStatus;

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

    @Id
    @Column(name = "REV", nullable = false, precision = 0)
    public int getRev() {
        return rev;
    }

    public void setRev(int rev) {
        this.rev = rev;
    }

    @Basic
    @Column(name = "REVTYPE", nullable = true, precision = 0)
    public Byte getRevtype() {
        return revtype;
    }

    public void setRevtype(Byte revtype) {
        this.revtype = revtype;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptumCodesetFileAudEntity that = (OptumCodesetFileAudEntity) o;
        return rev == that.rev &&
                Objects.equals(fileName, that.fileName) &&
                Objects.equals(lastModifiedTs, that.lastModifiedTs) &&
                Objects.equals(zipFileName, that.zipFileName) &&
                Objects.equals(revtype, that.revtype) &&
                Objects.equals(updateLogonId, that.updateLogonId) &&
                Objects.equals(fileType, that.fileType) &&
                Objects.equals(processStatus, that.processStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, lastModifiedTs, zipFileName, rev, revtype, updateLogonId, fileType, processStatus);
    }
}
