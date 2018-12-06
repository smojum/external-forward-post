package com;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CptCodeTextPK implements Serializable {
    private String cptCd;
    private String textType;

    @Column(name = "CPT_CD")
    @Id
    public String getCptCd() {
        return cptCd;
    }

    public void setCptCd(String cptCd) {
        this.cptCd = cptCd;
    }

    @Column(name = "TEXT_TYPE")
    @Id
    public String getTextType() {
        return textType;
    }

    public void setTextType(String textType) {
        this.textType = textType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CptCodeTextPK that = (CptCodeTextPK) o;
        return Objects.equals(cptCd, that.cptCd) &&
                Objects.equals(textType, that.textType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cptCd, textType);
    }
}
