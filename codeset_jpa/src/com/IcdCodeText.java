package com;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "ICD_CODE_TEXT", schema = "DS_CODE", catalog = "")
@IdClass(IcdCodeTextPK.class)
public class IcdCodeText {
    private String icdCd;
    private String icdCdType;
    private String textType;
    private String text;
    private long processFileSk;
    private Timestamp insertDttm;
    private String insertLogonId;
    private Timestamp updateDttm;
    private String updateLogonId;
    private IcdCode icdCode;
    private ProcessFile processFileByProcessFileSk;

    @Id
    @Column(name = "ICD_CD")
    public String getIcdCd() {
        return icdCd;
    }

    public void setIcdCd(String icdCd) {
        this.icdCd = icdCd;
    }

    @Id
    @Column(name = "ICD_CD_TYPE")
    public String getIcdCdType() {
        return icdCdType;
    }

    public void setIcdCdType(String icdCdType) {
        this.icdCdType = icdCdType;
    }

    @Id
    @Column(name = "TEXT_TYPE")
    public String getTextType() {
        return textType;
    }

    public void setTextType(String textType) {
        this.textType = textType;
    }

    @Basic
    @Column(name = "TEXT")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "PROCESS_FILE_SK")
    public long getProcessFileSk() {
        return processFileSk;
    }

    public void setProcessFileSk(long processFileSk) {
        this.processFileSk = processFileSk;
    }

    @Basic
    @Column(name = "INSERT_DTTM")
    public Timestamp getInsertDttm() {
        return insertDttm;
    }

    public void setInsertDttm(Timestamp insertDttm) {
        this.insertDttm = insertDttm;
    }

    @Basic
    @Column(name = "INSERT_LOGON_ID")
    public String getInsertLogonId() {
        return insertLogonId;
    }

    public void setInsertLogonId(String insertLogonId) {
        this.insertLogonId = insertLogonId;
    }

    @Basic
    @Column(name = "UPDATE_DTTM")
    public Timestamp getUpdateDttm() {
        return updateDttm;
    }

    public void setUpdateDttm(Timestamp updateDttm) {
        this.updateDttm = updateDttm;
    }

    @Basic
    @Column(name = "UPDATE_LOGON_ID")
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
        IcdCodeText that = (IcdCodeText) o;
        return processFileSk == that.processFileSk &&
                Objects.equals(icdCd, that.icdCd) &&
                Objects.equals(icdCdType, that.icdCdType) &&
                Objects.equals(textType, that.textType) &&
                Objects.equals(text, that.text) &&
                Objects.equals(insertDttm, that.insertDttm) &&
                Objects.equals(insertLogonId, that.insertLogonId) &&
                Objects.equals(updateDttm, that.updateDttm) &&
                Objects.equals(updateLogonId, that.updateLogonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(icdCd, icdCdType, textType, text, processFileSk, insertDttm, insertLogonId, updateDttm, updateLogonId);
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "ICD_CD", referencedColumnName = "ICD_CD", nullable = false), @JoinColumn(name = "ICD_CD_TYPE", referencedColumnName = "ICD_CD_TYPE", nullable = false)})
    public IcdCode getIcdCode() {
        return icdCode;
    }

    public void setIcdCode(IcdCode icdCode) {
        this.icdCode = icdCode;
    }

    @ManyToOne
    @JoinColumn(name = "PROCESS_FILE_SK", referencedColumnName = "PROCESS_FILE_SK", nullable = false)
    public ProcessFile getProcessFileByProcessFileSk() {
        return processFileByProcessFileSk;
    }

    public void setProcessFileByProcessFileSk(ProcessFile processFileByProcessFileSk) {
        this.processFileByProcessFileSk = processFileByProcessFileSk;
    }
}
