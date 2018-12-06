package com;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class IcdCodePK implements Serializable {
    private String icdCd;
    private String icdCdType;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IcdCodePK icdCodePK = (IcdCodePK) o;
        return Objects.equals(icdCd, icdCodePK.icdCd) &&
                Objects.equals(icdCdType, icdCodePK.icdCdType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(icdCd, icdCdType);
    }
}
