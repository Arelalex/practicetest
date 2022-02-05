package tests;

import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormTest {
String name = "Alex";
String lastName = "Моск";
String email = "alex@bk.ru";
String gender = "Female";
String num = "8123456789";

    @Test
    void practiceFormTestPositive() {
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").val(name);
        $("#lastName").val(lastName); //UTF-8
        $(" #userEmail").val(email);
        $x("//label[text()='" + gender + "']").click();
        $("#userNumber").val(num);
        $("#dateOfBirthInput").click();
        $x("//select[contains(@class, 'month')]").click();
        $x("//option[text()='August']").click();
        $x("//option[text()='1989']").click();
        $x("//div[text()='27']").click();
        $("#subjectsInput").val("m");
        $x("//*[text()='Maths']").click();
        $("#subjectsInput").val("m");
        $x("//*[text()='Computer Science']").scrollTo().click();
        $x("//label[text()='Reading']").click();
        $x("//label[text()='Music']").click();
        $("#uploadPicture").uploadFile(new File("./src/test/resources/photo/Smadj.jpg"));
        $("#currentAddress").val("Москва, Красная площадь, 1/2");
        $x("//div[text()='Select State']").click();
        $$x("//div[contains(@id, 'react')]").filterBy(text("Uttar Pradesh")).first().click(); //работа с коллекцией элементов
        $x("//div[text()='Select City']").click();
        $$x("//div[contains(@id, 'react')]").filterBy(text("Agra")).first().click();
        $("#submit").click();
        //проверка элементов на форме
        $x("//div[text()='Thanks for submitting the form']").shouldBe(visible);
        $x("//td[text()='" + name + " " + lastName + "']/../td[text()='Student Name']").shouldBe(visible);
        $x("//td[text()='" + email + "']/../td[text()='Student Email']").shouldBe(visible);
        $x("//td[text()='" + gender + "']/../td[text()='Gender']").shouldBe(visible);
        $x("//td[text()='" + num + "']/../td[text()='Mobile']").shouldBe(visible);
        $x("//td[text()='27 August,1989']/../td[text()='Date of Birth']").shouldBe(visible);
        $x("//td[text()='Maths, Computer Science']/../td[text()='Subjects']").shouldBe(visible);
        $x("//td[text()='Reading, Music']/../td[text()='Hobbies']").shouldBe(visible);
        $x("//td[text()='Smadj.jpg']/../td[text()='Picture']").shouldBe(visible);
        $x("//td[text()='Москва, Красная площадь, 1/2']/../td[text()='Address']").shouldBe(visible);
        $x("//td[text()='Uttar Pradesh Agra']/../td[text()='State and City']").shouldBe(visible);
    }

    @Test
    void practiceFormTestNegative() {
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").val(name);
        $("#lastName").val(lastName); //UTF-8
        $(" #userEmail").val(email);
        $x("//label[text()='" + gender + "']").click();
        $("#userNumber").val(num);
        $("#dateOfBirthInput").click();
        $x("//select[contains(@class, 'month')]").click();
        $x("//option[text()='August']").click();
        $x("//option[text()='1989']").click();
        $x("//div[text()='27']").click();
        $("#subjectsInput").val("m");
        $x("//*[text()='Maths']").click();
        $("#subjectsInput").val("m");
        $x("//*[text()='Computer Science']").scrollTo().click();
        $x("//label[text()='Reading']").click();
        $x("//label[text()='Music']").click();
        $("#uploadPicture").uploadFile(new File("./src/test/resources/photo/Smadj.jpg"));
        $("#currentAddress").val("Москва, Красная площадь, 1/2");
        $x("//div[text()='Select State']").click();
        $$x("//div[contains(@id, 'react')]").filterBy(text("Uttar Pradesh")).first().click(); //работа с коллекцией элементов
        $x("//div[text()='Select City']").click();
        $$x("//div[contains(@id, 'react')]").filterBy(text("Agra")).first().click();
        $(" #userEmail").clear(); //очищаем поле email, так как это обязательный атрибут, создание формы не должно быть возможным
        $("#submit").scrollTo().click();
        //проверка отображения формы
        $x("//div[text()='Thanks for submitting the form']").shouldBe(not(visible)); //уведомление о том, что форма создана успешно, что не удовлетворяет ожидаемому результату. Тест падает.
    }
}
