package acme.testing.authenticated.announcements;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TemporalAwareTestHarness;

public class AuthenticatedAnnouncementListTest extends TemporalAwareTestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/announcement/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestAuthenticated(final int recordIndex, final String moment, final String title, 
		final String body, final String flag, final String furtherInfo, final int deltaDays) {
		String momentTest;
		
		momentTest = super.computeDeltaMoment(deltaDays);
		
		super.signIn("patron1", "patron1");
		
		super.clickOnMenu("Authenticated", "Announcements");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, momentTest);
		super.checkColumnHasValue(recordIndex, 1, flag);
		super.checkColumnHasValue(recordIndex, 2, title);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("moment", momentTest);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("flag", flag);
		super.checkInputBoxHasValue("body", body);
		super.checkInputBoxHasValue("flag", flag);
		super.checkInputBoxHasValue("furtherInfo", furtherInfo);
		
		super.signOut();
	}
	
	@Test
    @Order(30)
    public void hackingTest() {
        super.navigate("/authenticated/announcement/list");
        super.checkPanicExists();
    }

}
