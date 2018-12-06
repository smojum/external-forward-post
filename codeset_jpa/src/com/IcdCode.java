package com;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ICD_CODE", schema = "DS_CODE", catalog = "")
@IdClass(IcdCodePK.class)
public class IcdCode {
    private String icdCd;
    private String icdCdType;
    private String icdCdWithoutDecimal;
    private Time effDt;
    private Time lastEffDt;
    private Time changeEffDt;
    private Time releaseDt;
    private String validityInd;
    private long processFileSk;
    private Timestamp insertDttm;
    private String insertLogonId;
    private Timestamp updateDttm;
    private String updateLogonId;
    private Collection<IcdCodeText> icdCodeTexts;

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

    @Basic
    @Column(name = "ICD_CD_WITHOUT_DECIMAL")
    public String getIcdCdWithoutDecimal() {
        return icdCdWithoutDecimal;
    }

    public void setIcdCdWithoutDecimal(String icdCdWithoutDecimal) {
        this.icdCdWithoutDecimal = icdCdWithoutDecimal;
    }

    @Basic
    @Column(name = "EFF_DT")
    public Time getEffDt() {
        return effDt;
    }

    public void setEffDt(Time effDt) {
        this.effDt = effDt;
    }

    @Basic
    @Column(name = "LAST_EFF_DT")
    public Time getLastEffDt() {
        return lastEffDt;
    }

    public void setLastEffDt(Time lastEffDt) {
        this.lastEffDt = lastEffDt;
    }

    @Basic
    @Column(name = "CHANGE_EFF_DT")
    public Time getChangeEffDt() {
        return changeEffDt;
    }

    public void setChangeEffDt(Time changeEffDt) {
        this.changeEffDt = changeEffDt;
    }

    @Basic
    @Column(name = "RELEASE_DT")
    public Time getReleaseDt() {
        return releaseDt;
    }

    public void setReleaseDt(Time releaseDt) {
        this.releaseDt = releaseDt;
    }

    @Basic
    @Column(name = "VALIDITY_IND")
    public String getValidityInd() {
        return validityInd;
    }

    public void setValidityInd(String validityInd) {
        this.validityInd = validityInd;
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
        IcdCode icdCode = (IcdCode) o;
        return processFileSk == icdCode.processFileSk &&
                Objects.equals(icdCd, icdCode.icdCd) &&
                Objects.equals(icdCdType, icdCode.icdCdType) &&
                Objects.equals(icdCdWithoutDecimal, icdCode.icdCdWithoutDecimal) &&
                Objects.equals(effDt, icdCode.effDt) &&
                Objects.equals(lastEffDt, icdCode.lastEffDt) &&
                Objects.equals(changeEffDt, icdCode.changeEffDt) &&
                Objects.equals(releaseDt, icdCode.releaseDt) &&
                Objects.equals(validityInd, icdCode.validityInd) &&
                Objects.equals(insertDttm, icdCode.insertDttm) &&
                Objects.equals(insertLogonId, icdCode.insertLogonId) &&
                Objects.equals(updateDttm, icdCode.updateDttm) &&
                Objects.equals(updateLogonId, icdCode.updateLogonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(icdCd, icdCdType, icdCdWithoutDecimal, effDt, lastEffDt, changeEffDt, releaseDt, validityInd, processFileSk, insertDttm, insertLogonId, updateDttm, updateLogonId);
    }

    @OneToMany(mappedBy = "icdCode")
    public Collection<IcdCodeText> getIcdCodeTexts() {
        return icdCodeTexts;
    }

    public void setIcdCodeTexts(Collection<IcdCodeText> icdCodeTexts) {
        this.icdCodeTexts = icdCodeTexts;
    }
}
