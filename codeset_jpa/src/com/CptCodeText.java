package com;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "CPT_CODE_TEXT", schema = "DS_CODE", catalog = "")
@IdClass(CptCodeTextPK.class)
public class CptCodeText {
    private String cptCd;
    private String textType;
    private String text;
    private long processFileSk;
    private Timestamp insertDttm;
    private String insertLogonId;
    private Timestamp updateDttm;
    private String updateLogonId;
    private CptCode cptCodeByCptCd;
    private ProcessFile processFileByProcessFileSk;

    @Id
    @Column(name = "CPT_CD")
    public String getCptCd() {
        return cptCd;
    }

    public void setCptCd(String cptCd) {
        this.cptCd = cptCd;
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
        CptCodeText that = (CptCodeText) o;
        return processFileSk == that.processFileSk &&
                Objects.equals(cptCd, that.cptCd) &&
                Objects.equals(textType, that.textType) &&
                Objects.equals(text, that.text) &&
                Objects.equals(insertDttm, that.insertDttm) &&
                Objects.equals(insertLogonId, that.insertLogonId) &&
                Objects.equals(updateDttm, that.updateDttm) &&
                Objects.equals(updateLogonId, that.updateLogonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cptCd, textType, text, processFileSk, insertDttm, insertLogonId, updateDttm, updateLogonId);
    }

    @ManyToOne
    @JoinColumn(name = "CPT_CD", referencedColumnName = "CPT_CD", nullable = false)
    public CptCode getCptCodeByCptCd() {
        return cptCodeByCptCd;
    }

    public void setCptCodeByCptCd(CptCode cptCodeByCptCd) {
        this.cptCodeByCptCd = cptCodeByCptCd;
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
