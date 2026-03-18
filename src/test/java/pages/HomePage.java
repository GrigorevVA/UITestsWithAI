package pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {

    public HomePage openPage() {
        open("/");
        return this;
    }

    public void shouldShowHome() {
        $("#title").shouldHave(text("Choose the model"));
    }

    public void shouldShowCarsCatalog() {
        $("body").shouldHave(text("S500 Cabriolet AMG"));
    }

    public HomePage openCarDetails() {
        $("#go_to_step_2").click();
      
        return this;
    }

    public void shouldShowCarDetails() {
        $("body").shouldHave(text("Engine"));
    }

    public HomePage selectNextCar() {
        $("#slider .next").click();
        return this;
    }

    public void shouldShowCar(String carName) {
        $("#slider").shouldHave(text(carName));
    }

    public HomePage chooseEngineAndTransmission() {
        $("#engine").selectOption(0);
        $("#transmission").selectOption(0);
        return this;
    }

    public HomePage goToPersonalDataStep() {
        $("#go_to_step_3").click();
        return this;
    }

    public HomePage fillPersonalData(String lastName,
                                     String firstName,
                                     String middleName,
                                     String age,
                                     String phoneNumber) {
        $("#form_last_name").setValue(lastName);
        $("#form_first_name").setValue(firstName);
        $("#form_middle_name").setValue(middleName);
        $("#form_age").setValue(age);
        $("#form_phone").setValue(phoneNumber);
        return this;
    }

    public HomePage goToSummaryStep() {
        $("#go_to_step_4").click();
        return this;
    }

    public void shouldShowSummaryWithUserData(String lastName, String firstName, String middleName, String age, String phoneNumber) {
        $("#step_4_name").shouldHave(text(lastName)).shouldHave(text(firstName)).shouldHave(text(middleName));
        $("#step_4_age").shouldHave(text(age));
        $("#step_4_phone").shouldHave(text(phoneNumber));
    }

    public HomePage finishBooking() {
        $("#finish").click();
        return this;
    }

    public void shouldShowBookingConfirmation() {
        $("body").shouldHave(text("Our managers will contact you within next 12 hours."));
    }
}

