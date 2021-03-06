package com;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DentalCodeTextEntityPK implements Serializable {
    private String dentalCd;
    private String textType;

    @Column(name = "DENTAL_CD", nullable = false, length = 50)
    @Id
    public String getDentalCd() {
        return dentalCd;
    }

    public void setDentalCd(String dentalCd) {
        this.dentalCd = dentalCd;
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
        DentalCodeTextEntityPK that = (DentalCodeTextEntityPK) o;
        return Objects.equals(dentalCd, that.dentalCd) &&
                Objects.equals(textType, that.textType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dentalCd, textType);
    }
}
