@Accumulators @RxClaims @Navitus @WhiteRabbit
Feature: Identify and display Rx claims received from Navitus that did not load to MTV

Benefits Administration would like the ability to identify Rx claims received from Navitus on a particular date that failed to load to MTV due to some issue. 
The ability to identify these "missed" claims in a more timely manner will allow for proactively refreshing the missed claims to move forward the next time the daily process is run.
This will ensure member accumulators are maintained accurately in MTV, and the member is not overpaying limits on deductible or OOP.
Initially the process of refreshing the missed claims identified by this process will be manual, but the ultimate goal is to automate one or more attempts to reprocess the missed claim.  Any that continue
to fail to reprocess might drop to an error report for review. 

These are the autosys jobs that comprise the process that loads inbound Rx claims from Navitus into MTV
- BOX_ADW_D_DLY_RX_PREMIER
- BOX_ADI_D_IF0620_BHP
- BOX_MTV_D_BATCHRXCLAIMS

Background: 
Given the daily inbound Rx claims process from Navitus is run end-to-end
And the user has an active session with the Rx Claim Reconciliation tool

Scenario: User search by Rx claim ID
When the user enters a valid Rx claim ID
Then the search results show records matching that Rx claim ID
And records for other Rx claims are not displayed

Scenario: User search by file date
When the user chooses a valid file date
Then the search results show Rx claims for that file date
And rcords for other file dates are not displayed

Scenario: User search by date range
When the user enters a valid date range
Then the search results show Rx claims received within that date range
And records outside the date range are not displayed

Scenario: User search by Member ID
When the user enters a valid Member ID
Then the search results show Rx claims for that Member ID
And records for other Member IDs are not displayed

Scenario: Member information displayed to user
When Rx claim is not loaded to MTV
#And that claim does not meet any of the criteria for exclusion
Then member information will be displayed to the user
And will contain <member_info>
|member_info|
|Carrier ID|
|Member ID|
|Contract ID|
|Group ID|
|PHA Benefit ID|
|Member Name|
|Alternate ID|

# need to determine if fields are named consistently throughout the process.  do we need multiple versions of this scenario for different steps of the process?
Scenario: Rx claim information displayed to user
When Rx claim is not loaded to MTV
#And that claim does not meet any of the criteria for exclusion
Then Rx claim information will be displayed to the user
And will contain <rx_claim_info>
|rx_claim_info|
|Claim Auth Number|
|Service Date|
|Approved Member Paid|
|Approved OOP|
|Copay|
|Deductible|
|Coinsurance|
|DAW Penalty|
|Grace Period Segment|
|Out of Network Indicator|

Scenario: Drug information displayed to user
When Rx claim is not loaded to MTV
#And that claim does not meet any of the criteria for exclusion
Then drug information will be displayed to the user
And will contain <drug_info>
|drug_info|
|Drug Product Name|
# need to confirm with Nichole which drug name she wants to see


#report where claim got stuck
Scenario: Rx claim failed to load to the Rx Claim pre-load table
Given an Rx claim that does not meet any valid exclusion criteria
And the Rx claim is in the Claims Accumulator ODS
When the Rx claim fails to load to the Rx Claim Pre-load table
Then that claim is identifed by the Rx claim reconciliation process
And the location where the claim stopped processing is reported

Scenario: Rx claim failed to load to the BHP table
Given an Rx claim that does not meet any valid exclusion criteria
And the Rx claim is in the Rx Claim Pre-load table
When the Rx claim fails to load to the BHP table
Then that claim is identifed by the Rx claim reconciliation process
And the location where the claim stopped processing is reported

Scenario: Rx claim failed to load to MTV from BHP
Given an Rx claim that does not meet any valid exclusion criteria
And the Rx claim is in the BHP table
When the Rx claim fails to load to MTV
Then that claim is identifed by the Rx claim reconciliation process
And the location where the claim stopped processing is reported
