package qa.guru_homework_4;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistationPageForm.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class PracticeForm {

    private SelenideElement
            firstName = $("#firstName"),
            lastName = $("#lastName"),
            email = $("#userEmail"),
            gender = $("[for=gender-radio-1]"),
            number = $("#userNumber"),
            subjectName = $("#subjectsInput"),
            adress = $("#currentAddress"),
            hobby = $("[for=hobbies-checkbox-2]"),
            uploadPicture = $("#uploadPicture"),
            state = $("#state"),
            city = $("#city");


    @BeforeAll
    static void configure(){
        Configuration.baseUrl = "https://demoqa.com";
    }

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();


    @Test
    void fillForms(){
                registrationFormPage.openPage()
                    .setFirstName("Got")
                    .setLastName("Ner")
                    .setEmail("gor@ya.ru")
                    .setGender("Female")
                    .setNumber("8912218376")
                    .setBirthDate("29", "March", "2008")
                    .setSubjects("History")
                    .setHobby("Sports")
                    .setHobby("Reading")
                    .setHobby("Music")
                    .uploadFile("src/test/resources/file.txt")
                    .setCurrentAddress("Moscow")
                    .setState("NCR")
                    .setCity("Delhi")
                    .pressSubmit();

            registrationFormPage.checkResultsTableVisible()
                    .checkResults("Student Name", "Got Ner")
                    .checkResults("Student Email", "gor@ya.ru")
                    .checkResults("Gender", "Female")
                    .checkResults("Mobile", "8912218376")
                    .checkResults("Date of Birth", "29 March,2008")
                    .checkResults("Subjects", "History")
                    .checkResults("Hobbies", "Sports, Reading, Music")
                    .checkResults("Picture", "file.txt")
                    .checkResults("Address", "Moscow")
                    .checkResults("State and City", "NCR Delhi");
        }
}
