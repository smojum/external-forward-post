package com;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CptCodeTextEntityPK implements Serializable {
    private String cptCd;
    private String textType;

    @Column(name = "CPT_CD", nullable = false, length = 50)
    @Id
    public String getCptCd() {
        return cptCd;
    }

    public void setCptCd(String cptCd) {
        this.cptCd = cptCd;
    }

    @Column(name = "TEXT_TYPE", nullable = false, length = 20)
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
        CptCodeTextEntityPK that = (CptCodeTextEntityPK) o;
        return Objects.equals(cptCd, that.cptCd) &&
                Objects.equals(textType, that.textType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cptCd, textType);
    }
}
