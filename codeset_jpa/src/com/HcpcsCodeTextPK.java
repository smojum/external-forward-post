package com;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class HcpcsCodeTextPK implements Serializable {
    private String hcpcsCd;
    private String textType;

    @Column(name = "HCPCS_CD")
    @Id
    public String getHcpcsCd() {
        return hcpcsCd;
    }

    public void setHcpcsCd(String hcpcsCd) {
        this.hcpcsCd = hcpcsCd;
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
        HcpcsCodeTextPK that = (HcpcsCodeTextPK) o;
        return Objects.equals(hcpcsCd, that.hcpcsCd) &&
                Objects.equals(textType, that.textType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hcpcsCd, textType);
    }
}
