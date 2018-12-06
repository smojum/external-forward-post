package com;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "PROCESS_FILE", schema = "DS_CODE", catalog = "")
public class ProcessFileEntity {
    private long processFileSk;
    private String fileLoadInd;
    private Timestamp fileLoadDttm;
    private String fileType;
    private String fileProcessType;
    private String codesetFileSk;
    private String sourceSystemCd;
    private Timestamp insertDttm;
    private String insertLogonId;
    private Timestamp updateDttm;
    private String updateLogonId;

    @Id
    @Column(name = "PROCESS_FILE_SK", nullable = false, precision = 0)
    public long getProcessFileSk() {
        return processFileSk;
    }

    public void setProcessFileSk(long processFileSk) {
        this.processFileSk = processFileSk;
    }

    @Basic
    @Column(name = "FILE_LOAD_IND", nullable = false, length = 1)
    public String getFileLoadInd() {
        return fileLoadInd;
    }

    public void setFileLoadInd(String fileLoadInd) {
        this.fileLoadInd = fileLoadInd;
    }

    @Basic
    @Column(name = "FILE_LOAD_DTTM", nullable = true)
    public Timestamp getFileLoadDttm() {
        return fileLoadDttm;
    }

    public void setFileLoadDttm(Timestamp fileLoadDttm) {
        this.fileLoadDttm = fileLoadDttm;
    }

    @Basic
    @Column(name = "FILE_TYPE", nullable = false, length = 20)
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Basic
    @Column(name = "FILE_PROCESS_TYPE", nullable = true, length = 20)
    public String getFileProcessType() {
        return fileProcessType;
    }

    public void setFileProcessType(String fileProcessType) {
        this.fileProcessType = fileProcessType;
    }

    @Basic
    @Column(name = "CODESET_FILE_SK", nullable = true, length = 255)
    public String getCodesetFileSk() {
        return codesetFileSk;
    }

    public void setCodesetFileSk(String codesetFileSk) {
        this.codesetFileSk = codesetFileSk;
    }

    @Basic
    @Column(name = "SOURCE_SYSTEM_CD", nullable = false, length = 20)
    public String getSourceSystemCd() {
        return sourceSystemCd;
    }

    public void setSourceSystemCd(String sourceSystemCd) {
        this.sourceSystemCd = sourceSystemCd;
    }

    @Basic
    @Column(name = "INSERT_DTTM", nullable = false)
    public Timestamp getInsertDttm() {
        return insertDttm;
    }

    public void setInsertDttm(Timestamp insertDttm) {
        this.insertDttm = insertDttm;
    }

    @Basic
    @Column(name = "INSERT_LOGON_ID", nullable = false, length = 50)
    public String getInsertLogonId() {
        return insertLogonId;
    }

    public void setInsertLogonId(String insertLogonId) {
        this.insertLogonId = insertLogonId;
    }

    @Basic
    @Column(name = "UPDATE_DTTM", nullable = false)
    public Timestamp getUpdateDttm() {
        return updateDttm;
    }

    public void setUpdateDttm(Timestamp updateDttm) {
        this.updateDttm = updateDttm;
    }

    @Basic
    @Column(name = "UPDATE_LOGON_ID", nullable = false, length = 50)
    public String getUpdateLogonId() {
        return updateLogonId;
    }

    public void setUpdateLogonId(String updateLogonId) {
        this.updateLogonId = updateLogonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessFileEntity that = (ProcessFileEntity) o;
        return processFileSk == that.processFileSk &&
                Objects.equals(fileLoadInd, that.fileLoadInd) &&
                Objects.equals(fileLoadDttm, that.fileLoadDttm) &&
                Objects.equals(fileType, that.fileType) &&
                Objects.equals(fileProcessType, that.fileProcessType) &&
                Objects.equals(codesetFileSk, that.codesetFileSk) &&
                Objects.equals(sourceSystemCd, that.sourceSystemCd) &&
                Objects.equals(insertDttm, that.insertDttm) &&
                Objects.equals(insertLogonId, that.insertLogonId) &&
                Objects.equals(updateDttm, that.updateDttm) &&
                Objects.equals(updateLogonId, that.updateLogonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(processFileSk, fileLoadInd, fileLoadDttm, fileType, fileProcessType, codesetFileSk, sourceSystemCd, insertDttm, insertLogonId, updateDttm, updateLogonId);
    }
}
