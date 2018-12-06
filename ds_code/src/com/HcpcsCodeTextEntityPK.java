package com;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class HcpcsCodeTextEntityPK implements Serializable {
    private String hcpcsCd;
    private String textType;

    @Column(name = "HCPCS_CD", nullable = false, length = 50)
    @Id
    public String getHcpcsCd() {
        return hcpcsCd;
    }

    public void setHcpcsCd(String hcpcsCd) {
        this.hcpcsCd = hcpcsCd;
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
        HcpcsCodeTextEntityPK that = (HcpcsCodeTextEntityPK) o;
        return Objects.equals(hcpcsCd, that.hcpcsCd) &&
                Objects.equals(textType, that.textType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hcpcsCd, textType);
    }
}
