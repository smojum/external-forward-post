//Do not directly modify this file. File Generated on 2018-10-10T11:36:38.702.
package com.dhp.codesetloadservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
interface OptumCptBaseV2Repository extends JpaRepository<OptumCptBaseV2, Long> {
   Collection<OptumCptBaseV2> findByFile_OptumCodesetFileSk(String sk);
}
