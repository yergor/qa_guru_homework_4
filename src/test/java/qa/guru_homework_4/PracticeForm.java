package qa.guru_homework_4;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.RegistationPageForm;
import utils.RandomUtils;


import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;



public class PracticeForm extends TestBase {

    RegistationPageForm.RegistrationFormPage registrationFormPage = new RegistationPageForm.RegistrationFormPage();

    Faker fakerRu = new Faker(new Locale("ru-RU"));
    Faker fakerEn = new Faker();

    String  valueFirstName,
            valueLastName,
            valueDay,
            valueMonth ,
            valueYear ,
            valueMail ,
            valueGender,
            valueNumber,
            valueHobby ,
            valueAdress;


    @BeforeEach
    void prepareTestData(){
        valueFirstName = fakerRu.name().firstName();
        valueLastName = fakerRu.name().lastName();
        valueDay = fakerRu.number().numberBetween(1, 30)+"";
        valueMonth = RandomUtils.getRandomMonth();
        valueYear = fakerRu.number().numberBetween(1900, 2022)+"";
        valueMail = fakerEn.internet().emailAddress();
        valueGender = fakerRu.demographic().sex();
        valueNumber = fakerEn.phoneNumber().subscriberNumber(10);
        valueHobby = RandomUtils.getRandomHobby();
        valueAdress = fakerRu.address().fullAddress();

    }

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





    @Test
    void fillForms(){
                registrationFormPage.openPage()
                    .setFirstName(valueFirstName)
                    .setLastName(valueLastName)
                    .setEmail(valueMail)
                    .setGender(valueGender)
                    .setNumber(valueNumber)
                    .setBirthDate(valueDay, valueMonth, valueYear)
                    .setHobby(valueHobby)
                    .uploadFile("src/test/resources/file.txt")
                    .setCurrentAddress(valueAdress)
                    .pressSubmit();

            registrationFormPage.checkResultsTableVisible()
                    .checkResults("Student Name", valueFirstName + " " +  valueLastName)
                    .checkResults("Student Email", valueMail)
                    .checkResults("Gender", valueGender)
                    .checkResults("Mobile", valueNumber)
                    .checkResults("Date of Birth", valueDay + " " + valueMonth + "," + valueYear )
                    .checkResults("Hobbies", valueHobby)
                    .checkResults("Picture", "file.txt");
        }
}
