//Do not directly modify this file. File Generated on 2018-10-10T11:36:38.694.
package com.dhp.codeset.codesetrepoloadservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//import org.hibernate.envers.Audited;
//import org.hibernate.envers.NotAudited;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OPTUM_CPT_BASE_V2", schema = "IN_CODE")
public class OptumCptBaseV2 extends MappedAuditBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "OptumCptBaseV2Seq")
    @SequenceGenerator(name="OptumCptBaseV2Seq",sequenceName="OPTUM_CPT_BASE_V2_SEQ", allocationSize = 1)
    @Column(name = "OPTUM_CPT_BASE_V2_SK")
    //@NotAudited
    private Long optumCptBaseV2Sk;
    @Column(name = "CODE")
    private String code;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "SHORT_DESCRIPTION")
    private String shortDescription;
    @Column(name = "LONG_DESCRIPTION")
    private String longDescription;
    @Column(name = "FULL_DESCRIPTION")
    private String fullDescription;
    @Column(name = "CODE_EFFECTIVE_DATE")
    private String codeEffectiveDate;
    @Column(name = "CHANGE_EFFECTIVE_DATE")
    private String changeEffectiveDate;
    @Column(name = "TERMINATION_DATE")
    private String terminationDate;
    @Column(name = "RELEASE_DATE")
    private String releaseDate;
    @Column(name = "FILE_DATE")
    private String fileDate;
    @Column(name = "REINSTATED_DELETE_GAP_BEGIN")
    private String reinstatedDeleteGapBegin;
    @Column(name = "REINSTATED_DELETE_GAP_END")
    private String reinstatedDeleteGapEnd;
    @Column(name = "REPLACEMENT_CODE_CROSSWALK")
    private String replacementCodeCrosswalk;
    @Column(name = "NF_TOTAL_RVU")
    private String nfTotalRvu;
    @Column(name = "FAC_TOTAL_RVU")
    private String facTotalRvu;
    @Column(name = "RESEQUENCED")
    private String resequenced;
    @Column(name = "SEQUENTIAL_ORDER")
    private String sequentialOrder;
}
