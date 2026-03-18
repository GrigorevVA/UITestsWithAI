package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import pages.HomePage;

public class TestDriveUiTests extends TestBase {

    private final HomePage homePage = new HomePage();
    private final Faker faker = new Faker();

    @Test
    void userCanOpenHomepage() {
        homePage.openPage();

        homePage.shouldShowHome();
    }

    @Test
    void userCanOpenCarsCatalog() {
        homePage.openPage();

        homePage.shouldShowCarsCatalog();
    }

    @Test
    void userCanViewCarDetailsPage() {
        homePage.openPage();

        homePage.openCarDetails();

        homePage.shouldShowCarDetails();
    }

    @Test
    void userCanFilterCarsByBrand() {
        String expectedCar = "Cayenne 400";

        homePage.openPage();

        homePage.selectNextCar();

        homePage.shouldShowCar(expectedCar);
    }

    @Test
    void userCanCompleteFullTestDriveFlow() {
        String lastName = faker.name().lastName();
        String firstName = faker.name().firstName();
        String middleName = faker.name().lastName();
        String age = String.valueOf(faker.number().numberBetween(18, 80));
        String phone = faker.phoneNumber().cellPhone();

        homePage.openPage();

        homePage
                .selectNextCar()
                .openCarDetails()
                .chooseEngineAndTransmission()
                .goToPersonalDataStep()
                .fillPersonalData(lastName, firstName, middleName, age, phone)
                .goToSummaryStep();

        homePage.shouldShowSummaryWithUserData(lastName, firstName, middleName, age, phone);

        homePage.finishBooking();

        homePage.shouldShowBookingConfirmation();
    }

}

