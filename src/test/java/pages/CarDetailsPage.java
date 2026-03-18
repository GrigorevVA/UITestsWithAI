package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CarDetailsPage {

    public void shouldHaveTitle(String carName) {
        $(byText(carName)).shouldBe(visible);
    }
}

