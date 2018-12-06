package com;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "CPT_CODE", schema = "DS_CODE", catalog = "")
public class CptCodeEntity {
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

    @Id
    @Column(name = "CPT_CD", nullable = false, length = 50)
    public String getCptCd() {
        return cptCd;
    }

    public void setCptCd(String cptCd) {
        this.cptCd = cptCd;
    }

    @Basic
    @Column(name = "EFF_DT", nullable = false)
    public Time getEffDt() {
        return effDt;
    }

    public void setEffDt(Time effDt) {
        this.effDt = effDt;
    }

    @Basic
    @Column(name = "LAST_EFF_DT", nullable = false)
    public Time getLastEffDt() {
        return lastEffDt;
    }

    public void setLastEffDt(Time lastEffDt) {
        this.lastEffDt = lastEffDt;
    }

    @Basic
    @Column(name = "CHANGE_EFF_DT", nullable = true)
    public Time getChangeEffDt() {
        return changeEffDt;
    }

    public void setChangeEffDt(Time changeEffDt) {
        this.changeEffDt = changeEffDt;
    }

    @Basic
    @Column(name = "RELEASE_DT", nullable = false)
    public Time getReleaseDt() {
        return releaseDt;
    }

    public void setReleaseDt(Time releaseDt) {
        this.releaseDt = releaseDt;
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
        CptCodeEntity that = (CptCodeEntity) o;
        return processFileSk == that.processFileSk &&
                Objects.equals(cptCd, that.cptCd) &&
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
        return Objects.hash(cptCd, effDt, lastEffDt, changeEffDt, releaseDt, processFileSk, insertDttm, insertLogonId, updateDttm, updateLogonId);
    }
}
