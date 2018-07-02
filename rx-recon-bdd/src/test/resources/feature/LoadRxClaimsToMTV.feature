@Accumulators @RxClaims @Navitus @WhiteRabbit
Feature: Process daily inbound Rx claims from Navitus
  
  
  This feature describes scenarios covering existing logic that performs the following:
  - receives the daily transaction Rx claims files from Navitus (ETF and non-ETF)
  - loads Rx claims from inbound files to Rx Claims Premier Daily (minus duplicates?)
  - loads Rx claims from Rx Claims Premier Daily to Pre-Staging
  - loads Rx claims from Pre-Staging to Stage
  - loads Rx claims from Stage to the Accumulator ODS
  - applies filter conditions before loading Rx claims from the Accumulator ODS to the Rx Claim Pre-load table
  - From Rx claim Pre-load table, claims are prepared for loading into BHP file
  - applies additional filter conditions before loading Rx claims from the Rx Claim Pre-load table to the BHP file
  - loads claims from BHP file to MTV
  
  These are the autosys jobs that comprise this process
  - BOX_ADW_D_DLY_RX_PREMIER
  - BOX_ADI_D_IF0620_BHP
  - BOX_MTV_D_BATCHRXCLAIMS

  Background: 
    Given The daily non-ETF Rx claims transactional file is received from Navitus
    And the daily ETF Rx claims transactional file is received from Navitus
    And the daily inbound Rx claims process is run end-to-end

  Scenario: ETF and non-ETF Rx claims are received
    When the daily files contain claims for ETF and non-ETF members
    Then the Rx claims contained in the daily non-ETF file are loaded into the Rx Claims Premier Daily table
    And the Rx claims contained in the daily ETF file are loaded into the Rx Claims Premier Daily table

  #Scenario: Duplicate RX claim numbers are identified
  #while there is logic to exclude duplicate claims it appears it would not work properly due to cache property on lookup table
  #Is there something else preventing duplicate claims?
  @IF0279a
  Scenario: Rx claims load from Rx Claims Premier Daily table to pre-staging table
    When claims are found in the Rx Claims Premier Daily table
    Then the same claims are also found in the pre-staging table

  @IF0279a
  Scenario: Rx claims load from pre-staging table to Stage
    When claims are found in the pre-staging table
    Then the same claims are also found in the Stage table

  @IF0279a
  Scenario: Rx claims load from Stage to Accumlator ODS
    When claims are found in the Stage table
    Then the same claims are also found in the Claim Accumulator ODS

  @IF0620
  Scenario: Non-ETF Rx claims load from Accumulator ODS to Rx Claim Pre-load table
    #INT.RX_CLAIM_PRE_LOAD
    When Rx claims are loaded from the Accumulator ODS to the Rx Claim Pre-load table
    Then claims where member grace period = 0 are found in the Rx Claim Pre-load table
    And claims where member grace period = 1 are found in the Rx Claim Pre-load table
    But claims where member grace period > 1 are excluded

  @IF0620
  Scenario: Non-ETF Rx claims load from Accumulator ODS to Rx Claim Pre-load table
    #INT.RX_CLAIM_PRE_LOAD
    When Rx claims are loaded from the Accumulator ODS to the Rx Claim Pre-load table
    Then claims where member grace period > 1 are excluded

  Scenario: ETF member Rx claims load from Accumulator ODS to Rx Claim Pre-load table
    When Rx claims are loaded from the Accumulator ODS to the Rx Claim Pre-load table
    And Carrier ID is 'NVTETF'
    Then the ETF Member ID is crosswalked to MTV Alternate ID
    And Rx claims are loaded to the Claim Pre-load table

  Scenario: Source type is MEDD
    When source type is MEDD
    Then Rx claims are excluded from the Claim Pre-load table

  Scenario: Source type is DMR and certain carrier IDs
    When Source type is DMR
    And Carrier ID is in the exclusion list ('DHPDHS1','DHPMKD1','DHPETFW','DHPSDJ1','DHPETF1','DHP5154','DHPMKC1','DHPDHSW','DHPEGWP','DHPMKB1','DHP5155','DHP5170')
    Then Rx claims are excluded from the Claim Pre-load table

  #Join with additional values from EDW
  Scenario: Member ID not found in EDW
    When Member ID on Rx claim is not found in EDW
    Then member Rx claims are not loaded to the BHP file (and table)

  Scenario: Member has verifiable pharmacy coverage in EDW
    When member has active pharmacy coverage on the Rx fill date
    And the Rx claim load flag in BIDS is true
    Then member Rx claims are loaded to BHP file

  Scenario Outline: Member pharmacy coverage not found in EDW
    When <member> does not have active pharmacy coverage on the <Rx fill date>
    Then member Rx claims are not loaded to the BHP file

    Examples: 
      #example 1: member never had Rx coverage
      #example 2: Rx fill date before coverage effective date
      #example 3: Rx fill date after coverage term date
      | member | Rx fill date |

  Scenario: Rx Claim Load flag in BIDS is false
    When member has active pharmacy coverage on the Rx fill date
    And the Rx claim load flag in BIDS is false
    Then member Rx claims are not loaded to the BHP file

  Scenario: Rx Claim in BHP file - need to validate expected result
    When an Rx Claim file is in the BHP file
    Then the Rx claim is loaded to MTV

  Scenario: Paid and reversal on a claim in same inbound file
    When an Rx claim with Paid status is on the file
    And a Reversal of the same Rx claim is also on the file
    Then the claim is not loaded to MTV

  Scenario: Reversal received on a paid claim in MTV
    When a Reversal is received on the file
    And the paid claim is in MTV
    Then the claim will be backed out of MTV
    And the member paid amount subtracted from the appropriate accumulator - need to verify this

  Scenario: Reversal received for claim not in MTV
    When a Reversal is received on the file
    But the claim in not found in MTV
    Then the reversed claim is not loaded to MTV
    And is there an error logged somewhere?
