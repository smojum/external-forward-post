package com;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "OPTUM_REVINFO", schema = "SMOJUM", catalog = "")
public class OptumRevinfoEntity {
    private int rev;
    private long revtstmp;

    @Id
    @Column(name = "REV", nullable = false, precision = 0)
    public int getRev() {
        return rev;
    }

    public void setRev(int rev) {
        this.rev = rev;
    }

    @Basic
    @Column(name = "REVTSTMP", nullable = false, precision = 0)
    public long getRevtstmp() {
        return revtstmp;
    }

    public void setRevtstmp(long revtstmp) {
        this.revtstmp = revtstmp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptumRevinfoEntity that = (OptumRevinfoEntity) o;
        return rev == that.rev &&
                revtstmp == that.revtstmp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rev, revtstmp);
    }
}
