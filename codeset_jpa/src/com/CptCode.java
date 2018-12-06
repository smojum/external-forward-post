package com;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "CPT_CODE", schema = "DS_CODE", catalog = "")
public class CptCode {
    private String cptCd;
    private Time effDt;
    private Time lastEffDt;
    private Time changeEffDt;
    private Time releaseDt;
    private long processFileSk;
    private Timestamp insertDttm;
    private String insertLogonId;
    private Timestamp updateDttm;
    private String updateLogonId;
    private Collection<CptCodeText> cptCodeTextsByCptCd;

    @Id
    @Column(name = "CPT_CD")
    public String getCptCd() {
        return cptCd;
    }

    public void setCptCd(String cptCd) {
        this.cptCd = cptCd;
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
        CptCode cptCode = (CptCode) o;
        return processFileSk == cptCode.processFileSk &&
                Objects.equals(cptCd, cptCode.cptCd) &&
                Objects.equals(effDt, cptCode.effDt) &&
                Objects.equals(lastEffDt, cptCode.lastEffDt) &&
                Objects.equals(changeEffDt, cptCode.changeEffDt) &&
                Objects.equals(releaseDt, cptCode.releaseDt) &&
                Objects.equals(insertDttm, cptCode.insertDttm) &&
                Objects.equals(insertLogonId, cptCode.insertLogonId) &&
                Objects.equals(updateDttm, cptCode.updateDttm) &&
                Objects.equals(updateLogonId, cptCode.updateLogonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cptCd, effDt, lastEffDt, changeEffDt, releaseDt, processFileSk, insertDttm, insertLogonId, updateDttm, updateLogonId);
    }

    @OneToMany(mappedBy = "cptCodeByCptCd")
    public Collection<CptCodeText> getCptCodeTextsByCptCd() {
        return cptCodeTextsByCptCd;
    }

    public void setCptCodeTextsByCptCd(Collection<CptCodeText> cptCodeTextsByCptCd) {
        this.cptCodeTextsByCptCd = cptCodeTextsByCptCd;
    }
}
