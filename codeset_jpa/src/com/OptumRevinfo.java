package com;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "OPTUM_REVINFO", schema = "SMOJUM", catalog = "")
public class OptumRevinfo {
    private int rev;
    private long revtstmp;
    private Collection<OptumCodesetFileAud> optumCodesetFileAudsByRev;

    @Id
    @Column(name = "REV")
    public int getRev() {
        return rev;
    }

    public void setRev(int rev) {
        this.rev = rev;
    }

    @Basic
    @Column(name = "REVTSTMP")
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
        OptumRevinfo that = (OptumRevinfo) o;
        return rev == that.rev &&
                revtstmp == that.revtstmp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rev, revtstmp);
    }

    @OneToMany(mappedBy = "optumRevinfoByRev")
    public Collection<OptumCodesetFileAud> getOptumCodesetFileAudsByRev() {
        return optumCodesetFileAudsByRev;
    }

    public void setOptumCodesetFileAudsByRev(Collection<OptumCodesetFileAud> optumCodesetFileAudsByRev) {
        this.optumCodesetFileAudsByRev = optumCodesetFileAudsByRev;
    }
}
