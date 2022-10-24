package pages;

import io.qameta.allure.Step;
import pages.DateOfBirthday;
import pages.ResultModalComponent;


import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistationPageForm {



    public static class RegistrationFormPage {

        private DateOfBirthday calendarComponent = new DateOfBirthday();
        private ResultModalComponent resultsModalComponent = new ResultModalComponent();

        private final static String TITLE_TEXT = "Student Registration Form";

        @Step("Открыть страницу")
        public RegistrationFormPage openPage() {
            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text(TITLE_TEXT));
            executeJavaScript("$('footer').remove()");
            executeJavaScript("$('#fixedban').remove()");

            return this;
        }

        @Step("Задать имя")
        public RegistrationFormPage setFirstName(String value) {
            $("#firstName").setValue(value);

            return this;
        }

        @Step("Задать фамилию")
        public RegistrationFormPage setLastName(String value) {
            $("#lastName").setValue(value);

            return this;
        }

        @Step("Задать почтовый адрес")
        public RegistrationFormPage setEmail(String value) {
            $("#userEmail").setValue(value);

            return this;
        }

        @Step("Задать пол")
        public RegistrationFormPage setGender(String value) {
            $("#genterWrapper").$(byText(value)).click();

            return this;
        }

        @Step("Задать номер")
        public RegistrationFormPage setNumber(String value) {
            $("#userNumber").setValue(value);

            return this;
        }

        @Step("Задать дату рождения")
        public RegistrationFormPage setBirthDate(String day, String month, String year) {
            $("#dateOfBirthInput").click();
            calendarComponent.setDate(day, month, year);

            return this;
        }

        public RegistrationFormPage setSubjects(String value) {
            $("#subjectsInput").setValue(value).pressEnter();

            return this;
        }

        @Step("Задать хобби")
        public RegistrationFormPage setHobby(String value) {
            $("#hobbiesWrapper").$(byText(value)).click();

            return this;
        }

        @Step("Загрузить файл")
        public RegistrationFormPage uploadFile(String value) {
            $("#uploadPicture").uploadFile(new File(value));

            return this;
        }

        @Step("Задать адресс")
        public RegistrationFormPage setCurrentAddress(String value) {
            $("#currentAddress").setValue(value);

            return this;
        }

        public RegistrationFormPage setState(String value) {
            $("#react-select-3-input").setValue(value).pressEnter();

            return this;
        }


        public RegistrationFormPage setCity(String value) {
            $("#react-select-4-input").setValue(value).pressEnter();

            return this;
        }

        @Step("Нажать подтвердить")
        public RegistrationFormPage pressSubmit() {
            $("#submit").scrollTo().pressEnter();

            return this;
        }

        @Step("Проверить отображение таблицы")
        public RegistrationFormPage checkResultsTableVisible() {
            resultsModalComponent.checkVisible();

            return this;
        }

        @Step("Проверить корректность заполнения  ")
        public RegistrationFormPage checkResults(String key, String value) {
            resultsModalComponent.checkResult(key, value);

            return this;
        }
    }
}
