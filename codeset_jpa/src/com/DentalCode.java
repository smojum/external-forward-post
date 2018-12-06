package com;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "DENTAL_CODE", schema = "DS_CODE", catalog = "")
public class DentalCode {
    private String dentalCd;
    private Time effDt;
    private Time lastEffDt;
    private Time changeEffDt;
    private Time releaseDt;
    private long processFileSk;
    private Timestamp insertDttm;
    private String insertLogonId;
    private Timestamp updateDttm;
    private String updateLogonId;
    private Collection<DentalCodeText> dentalCodeTextsByDentalCd;

    @Id
    @Column(name = "DENTAL_CD")
    public String getDentalCd() {
        return dentalCd;
    }

    public void setDentalCd(String dentalCd) {
        this.dentalCd = dentalCd;
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
        DentalCode that = (DentalCode) o;
        return processFileSk == that.processFileSk &&
                Objects.equals(dentalCd, that.dentalCd) &&
                Objects.equals(effDt, that.effDt) &&
                Objects.equals(lastEffDt, that.lastEffDt) &&
                Objects.equals(changeEffDt, that.changeEffDt) &&
                Objects.equals(releaseDt, that.releaseDt) &&
                Objects.equals(insertDttm, that.insertDttm) &&
                Objects.equals(insertLogonId, that.insertLogonId) &&
                Objects.equals(updateDttm, that.updateDttm) &&
                Objects.equals(updateLogonId, that.updateLogonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dentalCd, effDt, lastEffDt, changeEffDt, releaseDt, processFileSk, insertDttm, insertLogonId, updateDttm, updateLogonId);
    }

    @OneToMany(mappedBy = "dentalCodeByDentalCd")
    public Collection<DentalCodeText> getDentalCodeTextsByDentalCd() {
        return dentalCodeTextsByDentalCd;
    }

    public void setDentalCodeTextsByDentalCd(Collection<DentalCodeText> dentalCodeTextsByDentalCd) {
        this.dentalCodeTextsByDentalCd = dentalCodeTextsByDentalCd;
    }
}
