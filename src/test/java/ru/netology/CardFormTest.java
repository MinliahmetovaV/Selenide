package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


public class CardFormTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    @Test
    void shouldDeliveryCartTest() {
        String planningDate = generateDate(4);
        Configuration.holdBrowserOpen=true;
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Омск").pressEscape();
        $("[placeholder='Дата встречи']").doubleClick().pressEscape().sendKeys(planningDate);
        $(byName("name")).setValue("Сергеева Марина");
        $(byName("phone")).setValue("+79043270856");
        $("[data-test-id='agreement']").click();
        $(".button__text").click();
        $(withText("Встреча успешно")).should(visible,Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(visible);

    }

}