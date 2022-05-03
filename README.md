<a href="https://www.elementfleet.com/"><img src="http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/ORD-4979/img/elementlogo.gif" title="ElementFleet"></a>

# EQA_UI_ORDERING_V2
> Ordering Automation Test Suite Repo by ElementFleet

- [Installation](#installation)
- [Dependencies](#dependencies)
- [Execution](#execution)
- [Contributing](#contributing)
- [Team](#team)
- [Scripts](#scripts)

## Installation

> Applications to be installed.

### Prerequisite
- JDK `1.8 check at C:\Program Files\Java`.
- Latest version of Eclipse(much preferable)/STS.
  - Download <a href="https://www.eclipse.org/downloads/">Eclipse</a> if you have access or raise RITM request.
- TestNG
  - Select Help / Install New Software...
  - Enter the update site URL in "Work with:" field:
  - Update site for release: https://dl.bintray.com/testng-team/testng-eclipse-release/.
  - Make sure the check box next to URL is checked and click Next.
  - Eclipse will then guide you through the process.
- Gitbash (Not Mandatory)
  - Download <a href="https://git-scm.com/downloads">GIT Bash</a> if you have access or raise RITM request.

## Dependencies

> Dependencies used in the project.

- <a href="http://github.fleet.ad/Testing/rainbow">Rainbow packages</a> `v0.5.02-SNAPSHOT`
- <a href="http://rest-assured.io/">Rest Assured</a> `v4.1.2`
- <a href="http://rest-assured.io/">JSON Path</a> `v4.1.2`
- <a href="https://github.com/red6/pdfcompare">PDF Compare</a> `v1.1.40`
- <a href="http://www.pdfbox.org/">PDF Box</a> `v2.0.16`
- <a href="https://mvnrepository.com/artifact/org.postgresql/postgresql">Postgre SQL</a> `v42.1.4`
- <a href="https://mvnrepository.com/artifact/log4j/log4j">Log4j</a> `v1.2.17`
- <a href="https://mvnrepository.com/artifact/org.apache.poi/poi">Apache POI</a> `v3.9`
- <a href="https://mvnrepository.com/artifact/org.apache.poi/poi">Apache POI-OOXML</a> `v3.9`

## Execution

### Local

> Make sure you have followed the prerequisites.

- :black_nib: Clone the Ordering repo using `http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2.git`.

<img src="http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/ORD-4979/img/cloningGitRepo.gif" >

- Open Eclipse.
- Import the maven project.

<img src="http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/ORD-4979/img/importMavenProject.gif" >

- Clean the project `Right click on the project → Run As → Maven clean`.

<img src="http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/ORD-4979/img/mavenClean.gif" >

- Force update the project `Right click on the project → Maven → Update Project → Check Force Update option → Click Ok`.

<img src="http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/ORD-4979/img/mavenForceUpdate.gif" >

- Move to the driectory `src/test/resources` click on any of the section i.e. Regression or Sanity.
- Modify the test data excel according to your convenience in `TestData/OrderingTestData.xlsx`.
- Select the xml file that you want to run and `Right click on the xml file → Run As → TestNG Suite`.
- :boom: Test Case started.
- After execution PDF report will be generated with in `target/<TC Name>.pdf`.

> Make sure before your execution you clean your project.

### Jenkins

> Make sure you have <a href="http://vspljenkins01qa.fleet.ad:8080/job/Ordering/">QA Jenkins</a> access.

- Login to <a href="http://vspljenkins01qa.fleet.ad:8080/job/Ordering/">QA Jenkins</a>.
- Click on the job that you want to run.
- Click on Build now opion and enter the required details and click on `Build Now` button.
- After your build is completed click on the build number download the PDF for analysis.

## Contributing

Currently we are having master and feature branch:
 - `feature` branch will have all the latest code that on which all teams will be contributing.
 - `master` branch will act as our backup.

> Note: You can't push to `master` or `feature` branch direclty.

Steps to add your code to `feature` or `master` branch:
 - Create a new branch with name as your task name. Ex ORD-1234.
 
 <img src="http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/ORD-4979/img/gitNewBranch.gif" >
 
 - Push your branch to remote repository i.e GITHUB.
 - Raise a PR of your branch to feature branch.
 - If you have conflicts please resolve the conflicts and again push the changes to GITHUB. No need to raise a new PR your old PR will be present and will reflect your new changes.
 - Please assign it to a Automation team member as they only have the access to merge a PR.
 
> Note: After you assign a PR to automation team member please mail or ping him as currently we are not getting mails from GITHUB.

 - Create a Jenkins job for your automation test case if you need to run that test case from Jenkins.

### Things to remember before raising PR
  1. Your code is well indented.
  2. Your xml and java files are present in the correct directory i.e xml file in `src/test/resources/<functionality-section-folder>/<file-name>.xml` and its java file in `src/test/java/<functionality-section-package>/<file-name>.java`.
  3. Your code change does not have any impact on the existing code.
  4. After raising PR inform the person to whom you have assigned it to.

## Team

Automation Team Members:
  1. Shivam Pratap Singh
  2. Akshay Knadkonde
  3. Mounika K
  4. Shivam S
  5. Pratik Dhole
  6. Megha P
  7. Sidheshwar K

## Scripts

### Regression

|Script Name|XML Location|Java Location|
|--- |---|---|
|Reg_Create_Fleet_Spec|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Create_Fleet_Spec.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Create_Fleet_Spec.java'>JAVA<a>|
|Reg_Create_Upfit_Spec|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Create_Upfit_Spec.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Create_Upfit_Spec.java'>JAVA<a>|
|Reg_Factory_AutoUnit_FleetSpec_DefaultDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Factory_AutoUnit_FleetSpec_DefaultDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Factory_AutoUnit_FleetSpec_DefaultDealer.java'>JAVA<a>|
|Reg_Factory_FleetSpec_DefaultDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Factory_FleetSpec_DefaultDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Factory_FleetSpec_DefaultDealer.java'>JAVA<a>|
|Reg_Factory_Pool_FleetSpec_DefaultDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Factory_Pool_FleetSpec_DefaultDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Factory_Pool_FleetSpec_DefaultDealer.java'>JAVA<a>|
|Reg_Factory_BuildFromScratch_DefaultDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Factory_BuildFromScratch_DefaultDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Factory_BuildFromScratch_DefaultDealer.java'>JAVA<a>|
|Reg_Factory_FleetSpec_DiffDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Factory_FleetSpec_DiffDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Factory_FleetSpec_DiffDealer.java'>JAVA<a>|
|Reg_Factory_FleetSpec_RequestedDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Factory_FleetSpec_RequestedDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Factory_FleetSpec_RequestedDealer.java'>JAVA<a>|
|Reg_Factory_DlvAddress_FleetSpec_DefaultDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Factory_DlvAddress_FleetSpec_DefaultDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Factory_DlvAddress_FleetSpec_DefaultDealer.java'>JAVA<a>|
|Reg_Factory_UsedUnit_FleetSpec_DefaultDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Factory_UsedUnit_FleetSpec_DefaultDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Factory_UsedUnit_FleetSpec_DefaultDealer.java'>JAVA<a>|
|Reg_Factory_FleetSpec_AddDIO_DefaultDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Factory_FleetSpec_AddDIO_DefaultDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Factory_FleetSpec_AddDIO_DefaultDealer.java'>JAVA<a>|
|Reg_Factory_FleetSpec_AddAdhocDIO_DefaultDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Factory_FleetSpec_AddAdhocDIO_DefaultDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Factory_FleetSpec_AddAdhocDIO_DefaultDealer.java'>JAVA<a>|
|Reg_Factory_FleetSpec_AddAdhocUpfitSpec_DefaultDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Factory_FleetSpec_AddAdhocUpfitSpec_DefaultDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Factory_FleetSpec_AddAdhocUpfitSpec_DefaultDealer.java'>JAVA<a>|
|Reg_Factory_FleetSpec_AssociatedUpfitSpec_DefaultDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Factory_FleetSpec_AssociatedUpfitSpec_DefaultDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Factory_FleetSpec_AssociatedUpfitSpec_DefaultDealer.java'>JAVA<a>|
|Reg_Factory_FleetSpec_AdhocUpfitLineItem_DefaultDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Factory_FleetSpec_AdhocUpfitLineItem_DefaultDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Factory_FleetSpec_AdhocUpfitLineItem_DefaultDealer.java'>JAVA<a>|
|Reg_Stock_AutoUnit_BuildFromScratch_DiffDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Stock_AutoUnit_BuildFromScratch_DiffDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Stock_AutoUnit_BuildFromScratch_DiffDealer.java'>JAVA<a>|
|Reg_Stock_BuildFromScratch_DiffDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Stock_BuildFromScratch_DiffDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Stock_BuildFromScratch_DiffDealer.java'>JAVA<a>|
|Reg_Stock_ClientLocated_VIN_DiffDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Stock_ClientLocated_VIN_DiffDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Stock_ClientLocated_VIN_DiffDealer.java'>JAVA<a>|
|Reg_Stock_FleetSpec_DiffDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Stock_FleetSpec_DiffDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Stock_FleetSpec_DiffDealer.java'>JAVA<a>|
|Reg_Stock_FleetSpec_RequestedDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Stock_FleetSpec_RequestedDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Stock_FleetSpec_RequestedDealer.java'>JAVA<a>|
|Reg_Stock_DlvAddress_FleetSpec_DiffDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Stock_DlvAddress_FleetSpec_DiffDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Stock_DlvAddress_FleetSpec_DiffDealer.java'>JAVA<a>|
|Reg_Stock_UsedUnit_FleetSpec_DiffDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Stock_UsedUnit_FleetSpec_DiffDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Stock_UsedUnit_FleetSpec_DiffDealer.java'>JAVA<a>|
|Reg_Stock_FleetSpec_AddDIO_DiffDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Stock_FleetSpec_AddDIO_DiffDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Stock_FleetSpec_AddDIO_DiffDealer.java'>JAVA<a>|
|Reg_Stock_FleetSpec_AddAdhocDIO_DiffDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Stock_FleetSpec_AddAdhocDIO_DiffDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Stock_FleetSpec_AddAdhocDIO_DiffDealer.java'>JAVA<a>|
|Reg_Stock_FleetSpec_AddAdhocUpfitSpec_DiffDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Stock_FleetSpec_AddAdhocUpfitSpec_DiffDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Stock_FleetSpec_AddAdhocUpfitSpec_DiffDealer.java'>JAVA<a>|
|Reg_Stock_FleetSpec_AssociatedUpfitSpec_DiffDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Stock_FleetSpec_AssociatedUpfitSpec_DiffDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Stock_FleetSpec_AssociatedUpfitSpec_DiffDealer.java'>JAVA<a>|
|Reg_Stock_FleetSpec_AdhocUpfitLineItem_DiffDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Stock_FleetSpec_AdhocUpfitLineItem_DiffDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Stock_FleetSpec_AdhocUpfitLineItem_DiffDealer.java'>JAVA<a>|
|Reg_Factory_AutoUnit_BuildFromScratch_DefaultDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Factory_AutoUnit_BuildFromScratch_DefaultDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Factory_AutoUnit_BuildFromScratch_DefaultDealer.java'>JAVA<a>|
|Reg_Dealer_Pool_FleetSpec_DiffDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Dealer_Pool_FleetSpec_DiffDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Dealer_Pool_FleetSpec_DiffDealer.java'>JAVA<a>|
|Reg_Dealer_FleetSpec_DiffDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Dealer_FleetSpec_DiffDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Dealer_FleetSpec_DiffDealer.java'>JAVA<a>|
|Reg_Dealer_FleetSpec_RequestedDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Dealer_FleetSpec_RequestedDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Dealer_FleetSpec_RequestedDealer.java'>JAVA<a>|
|Reg_Dealer_UsedUnit_FleetSpec_DiffDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Dealer_UsedUnit_FleetSpec_DiffDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Dealer_UsedUnit_FleetSpec_DiffDealer.java'>JAVA<a>|
|Reg_Dealer_FleetSpec_AddDIO_DiffDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Dealer_FleetSpec_AddDIO_DiffDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Dealer_FleetSpec_AddDIO_DiffDealer.java'>JAVA<a>|
|Reg_Dealer_FleetSpec_AddAdhocUpfitSpec_DiffDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Dealer_FleetSpec_AddAdhocUpfitSpec_DiffDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Dealer_FleetSpec_AddAdhocUpfitSpec_DiffDealer.java'>JAVA<a>|
|Reg_Dealer_FleetSpec_AddAdhocUpfitLineItem_DiffDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Dealer_FleetSpec_AddAdhocUpfitLineItem_DiffDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Dealer_FleetSpec_AddAdhocUpfitLineItem_DiffDealer.java'>JAVA<a>|
|Reg_Dealer_FleetSpec_AssociatedUpfitSpec_DiffDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Dealer_FleetSpec_AssociatedUpfitSpec_DiffDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Dealer_FleetSpec_AssociatedUpfitSpec_DiffDealer.java'>JAVA<a>|
|Reg_Dealer_FleetSpec_DIO_UpfitSpec_DiffDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Dealer_FleetSpec_DIO_UpfitSpec_DiffDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Dealer_FleetSpec_DIO_UpfitSpec_DiffDealer.java'>JAVA<a>|
|Reg_Dealer_FleetSpec_AdhocDIO_AdhocUpfitLine_DiffDealer|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Dealer_FleetSpec_AdhocDIO_AdhocUpfitLine_DiffDealer.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Dealer_FleetSpec_AdhocDIO_AdhocUpfitLine_DiffDealer.java'>JAVA<a>|
|Reg_Stock_Change_Order_Type_Back_Office|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Stock_Change_Order_Type_Back_Office.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Stock_Change_Order_Type_Back_Office.java'>JAVA<a>|
|Reg_Edit_Driver_Info_Factory_Order_Back_Office|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Edit_Driver_Info_Factory_Order_Back_Office.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Edit_Driver_Info_Factory_Order_Back_Office.java'>JAVA<a>|
|Reg_FactoryOrder_With_PriceAndConfig_FrontOffice|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_FactoryOrder_With_PriceAndConfig_FrontOffice.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_FactoryOrder_With_PriceAndConfig_FrontOffice.java'>JAVA<a>|
|Reg_Dealer_Order_Change_Order_Status_Back_Office|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Dealer_Order_Change_Order_Status_Back_Office.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Dealer_Order_Change_Order_Status_Back_Office.java'>JAVA<a>|
|Reg_Stock_Order_Edit_General_Order_Info|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Stock_Order_Edit_General_Order_Info.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Stock_Order_Edit_General_Order_Info.java'>JAVA<a>|
|Reg_Factory_AutoUnit_FleetSpec_DefaultDealer_WorkInProcess|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Factory_AutoUnit_FleetSpec_DefaultDealer_WorkInProcess.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Factory_AutoUnit_FleetSpec_DefaultDealer_WorkInProcess.java'>JAVA<a>|
|Reg_Factory_Order_Edit_General_Order_Info|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Factory_Order_Edit_General_Order_Info.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Factory_Order_Edit_General_Order_Info.java'>JAVA<a>|
|Reg_Stock_AutoUnit_BuildFromScratch_DiffDealer_WorkInProcess|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Stock_AutoUnit_BuildFromScratch_DiffDealer_WorkInProcess.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Stock_AutoUnit_BuildFromScratch_DiffDealer_WorkInProcess.java'>JAVA<a>|
|Reg_Factory_Validate_Deny_CreditOrder|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Factory_Validate_Deny_CreditOrder.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Factory_Validate_Deny_CreditOrder.java'>JAVA<a>|
|Reg_Dealer_Validate_Deny_CreditOrder|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Dealer_Validate_Deny_CreditOrder.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Dealer_Validate_Deny_CreditOrder.java'>JAVA<a>|
|Reg_Stock_Validate_Deny_CreditOrder|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Regression/Reg_Stock_Validate_Deny_CreditOrder.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/regression/Reg_Stock_Validate_Deny_CreditOrder.java'>JAVA<a>|

### Sanity

|Script Name|XML Location|Java Location|
|--- |---|---|
|Sanity_Ordering_Links|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Sanity/Sanity_Ordering_Links.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/sanity/Sanity_Ordering_Links.java'>JAVA<a>|
|Sanity_Vehicle_Configuration_Links|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Sanity/Sanity_Vehicle_Configuration_Links.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/sanity/Sanity_Vehicle_Configuration_Links.java'>JAVA<a>|
|Sanity_Fleet_Prefrences_Links|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Sanity/Sanity_Fleet_Prefrences_Links.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/sanity/Sanity_Fleet_Prefrences_Links.java'>JAVA<a>|
|Sanity_Queues|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Sanity/Sanity_Queues.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/sanity/Sanity_Queues.java'>JAVA<a>|
|Sanity_Edit_Order|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Sanity/Sanity_Edit_Order.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/sanity/Sanity_Edit_Order.java'>JAVA<a>|
|Sanity_Order_Approval|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Sanity/Sanity_Order_Approval.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/sanity/Sanity_Order_Approval.java'>JAVA<a>|
|Sanity_Codes_Tables|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Sanity/Sanity_Codes_Tables.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/sanity/Sanity_Codes_Tables.java'>JAVA<a>|
|Sanity_Batch_Processing|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Sanity/Sanity_Batch_Processing.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/sanity/Sanity_Batch_Processing.java'>JAVA<a>|
|Sanity_Business_Maintained_Tables|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/resources/Sanity/Sanity_Business_Maintained_Tables.xml'>XML<a>|<a href='http://github.fleet.ad/Testing/EQA_UI_ORDERING_V2/blob/feature/src/test/java/com/element/fleet/ordering/sanity/Sanity_Business_Maintained_Tables.java'>JAVA<a>|
