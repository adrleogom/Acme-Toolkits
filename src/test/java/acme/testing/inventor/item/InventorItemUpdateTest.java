package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorItemUpdateTest  extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String itemType, final String name,final String code, final String technology, 
		final String description, final String retailPrice,final String furtherInfo) {
		super.signIn("inventor3", "inventor3");
		
		super.clickOnMenu("Inventor", "List my components and tools");
		super.checkListingExists();
		super.sortListing(1, "asc");
		

		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.fillInputBoxIn("itemType", itemType);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("furtherInfo", furtherInfo);
		super.clickOnSubmit("Update");

		super.checkListingExists();
		super.sortListing(1, "asc");
	

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("itemType", itemType);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("furtherInfo", furtherInfo);

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String itemType, final String name,final String code, final String technology, 
		final String description, final String retailPrice,final String furtherInfo) {
		super.signIn("inventor3", "inventor3");
		
		super.clickOnMenu("Inventor", "List my components and tools");
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();

	
		
		super.fillInputBoxIn("itemType", itemType);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("furtherInfo", furtherInfo);
		super.clickOnSubmit("Update");

		super.checkErrorsExist();

		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/inventor/item/update");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/inventor/item/update");
		super.checkPanicExists();
		super.signOut();

		super.signIn("patron1", "patron1");
		super.navigate("/inventor/item/update");
		super.checkPanicExists();
		super.signOut();
	}
}

