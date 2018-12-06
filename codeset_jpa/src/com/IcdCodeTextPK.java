package com;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class IcdCodeTextPK implements Serializable {
    private String icdCd;
    private String icdCdType;
    private String textType;

    @Column(name = "ICD_CD")
    @Id
    public String getIcdCd() {
        return icdCd;
    }

    public void setIcdCd(String icdCd) {
        this.icdCd = icdCd;
    }

    @Column(name = "ICD_CD_TYPE")
    @Id
    public String getIcdCdType() {
        return icdCdType;
    }

    public void setIcdCdType(String icdCdType) {
        this.icdCdType = icdCdType;
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
        IcdCodeTextPK that = (IcdCodeTextPK) o;
        return Objects.equals(icdCd, that.icdCd) &&
                Objects.equals(icdCdType, that.icdCdType) &&
                Objects.equals(textType, that.textType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(icdCd, icdCdType, textType);
    }
}
