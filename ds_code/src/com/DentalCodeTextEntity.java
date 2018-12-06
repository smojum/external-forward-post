package com;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "DENTAL_CODE_TEXT", schema = "DS_CODE", catalog = "")
@IdClass(DentalCodeTextEntityPK.class)
public class DentalCodeTextEntity {
    private String dentalCd;
    private String textType;
    private String text;
    private long processFileSk;
    private Timestamp insertDttm;
    private String insertLogonId;
    private Timestamp updateDttm;
    private String updateLogonId;

    @Id
    @Column(name = "DENTAL_CD", nullable = false, length = 50)
    public String getDentalCd() {
        return dentalCd;
    }

    public void setDentalCd(String dentalCd) {
        this.dentalCd = dentalCd;
    }

    @Id
    @Column(name = "TEXT_TYPE", nullable = false, length = 20)
    public String getTextType() {
        return textType;
    }

    public void setTextType(String textType) {
        this.textType = textType;
    }

    @Basic
    @Column(name = "TEXT", nullable = false, length = 4000)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "PROCESS_FILE_SK", nullable = false, precision = 0)
    public long getProcessFileSk() {
        return processFileSk;
    }

    public void setProcessFileSk(long processFileSk) {
        this.processFileSk = processFileSk;
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
        DentalCodeTextEntity that = (DentalCodeTextEntity) o;
        return processFileSk == that.processFileSk &&
                Objects.equals(dentalCd, that.dentalCd) &&
                Objects.equals(textType, that.textType) &&
                Objects.equals(text, that.text) &&
                Objects.equals(insertDttm, that.insertDttm) &&
                Objects.equals(insertLogonId, that.insertLogonId) &&
                Objects.equals(updateDttm, that.updateDttm) &&
                Objects.equals(updateLogonId, that.updateLogonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dentalCd, textType, text, processFileSk, insertDttm, insertLogonId, updateDttm, updateLogonId);
    }
}
