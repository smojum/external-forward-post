package com.dhp.codesetloadservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OptumCodesetFileIdentity implements Serializable {
    private String zipFileName;
    private String fileName;
    private LocalDateTime lastModifiedTs;
}
