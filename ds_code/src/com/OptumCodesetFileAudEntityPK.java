package com;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class OptumCodesetFileAudEntityPK implements Serializable {
    private String fileName;
    private Timestamp lastModifiedTs;
    private String zipFileName;
    private int rev;

    @Column(name = "FILE_NAME", nullable = false, length = 255)
    @Id
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name = "LAST_MODIFIED_TS", nullable = false)
    @Id
    public Timestamp getLastModifiedTs() {
        return lastModifiedTs;
    }

    public void setLastModifiedTs(Timestamp lastModifiedTs) {
        this.lastModifiedTs = lastModifiedTs;
    }

    @Column(name = "ZIP_FILE_NAME", nullable = false, length = 255)
    @Id
    public String getZipFileName() {
        return zipFileName;
    }

    public void setZipFileName(String zipFileName) {
        this.zipFileName = zipFileName;
    }

    @Column(name = "REV", nullable = false, precision = 0)
    @Id
    public int getRev() {
        return rev;
    }

    public void setRev(int rev) {
        this.rev = rev;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptumCodesetFileAudEntityPK that = (OptumCodesetFileAudEntityPK) o;
        return rev == that.rev &&
                Objects.equals(fileName, that.fileName) &&
                Objects.equals(lastModifiedTs, that.lastModifiedTs) &&
                Objects.equals(zipFileName, that.zipFileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, lastModifiedTs, zipFileName, rev);
    }
}
