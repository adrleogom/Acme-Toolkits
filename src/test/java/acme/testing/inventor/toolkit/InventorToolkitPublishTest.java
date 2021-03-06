package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitPublishTest extends TestHarness{
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/publish-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String reference) {
		super.signIn("inventor1", "inventor1");
		
		
		super.clickOnMenu("Inventor", "List my toolkits");
		super.checkListingExists();
		
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, reference);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnSubmit("Publish toolkit");
		super.checkNotErrorsExist();

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/publish-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String reference) {
		super.signIn("inventor1", "inventor1");
		
		
		super.clickOnMenu("Inventor", "List my toolkits");
		super.checkListingExists();
		
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, reference);
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnSubmit("Publish toolkit");
		super.checkAlertExists(false);

		super.signOut();
	}
	

}
