package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CarsCatalogPage {

    public void shouldBeOpened() {
        $(byText("S500 Cabriolet AMG")).shouldBe(visible);
    }

    public CarsCatalogPage filterByBrand(String brandName) {
        $(byText(brandName)).click();
        return this;
    }

    public CarDetailsPage openCarDetails(String carName) {
        $(byText(carName)).click();
        return new CarDetailsPage();
    }

    public void shouldShowBrand(String brandName) {
        $(byText(brandName)).shouldBe(visible);
    }
}

