package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageUpdateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String status,
		final String legalStuff, final String budget, final String furtherInfo, final String company, final String statement, final String pFurtherInformation,
		final String name, final String surname) {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "List my patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, code);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.fillInputBoxIn("status", status);
		super.clickOnSubmit("Update status");

		super.clickOnMenu("Inventor", "List my patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("furtherInfo", furtherInfo);
		super.checkInputBoxHasValue("patron.company", company);
		super.checkInputBoxHasValue("patron.statement", statement);
		super.checkInputBoxHasValue("patron.furtherInfo", pFurtherInformation);
		super.checkInputBoxHasValue("patron.identity.name", name);
		super.checkInputBoxHasValue("patron.identity.surname", surname);

		super.signOut();
	}

	
}
