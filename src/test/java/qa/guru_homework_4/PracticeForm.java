package qa.guru_homework_4;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class PracticeForm {

    @BeforeAll
    static void configure(){
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillForms(){
        open("/automation-practice-form");
        $("#firstName").setValue("Gor");
        $("#lastName").setValue("Yeritsyan");
        $("#userEmail").setValue("g1o2i3n4h5g6c7@gmail.ru");
        $("[for=gender-radio-1]").click();
        $("#userNumber").setValue("89006548700");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption("March");
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOption("2002");
        $("[aria-label=\"Choose Friday, March 29th, 2002\"]").shouldHave(text("29")).click();
        $("#subjectsInput").setValue("English").pressEnter();
        $("[for=hobbies-checkbox-2]").click();
        $("#uploadPicture").uploadFromClasspath("engl.png");
        $("#currentAddress").setValue("Lenina 15");
        $("#state").scrollTo();
        $("#state").click();
        $(byText("Haryana")).click();
        $("#city").click();
        $(byText("Karnal")).click();
        $("#submit").scrollTo();
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        sleep(10000);
        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".modal-body").shouldHave(
                text("Gor Yeritsyan"),
                text("g1o2i3n4h5g6c7@gmail.ru"),
                text("Male"),
                text("8900654870"),
                text("29 March,2002"),
                text ("English"),
                text ("Reading"),
                text("engl.png"),
                text("Lenina 15"),
                text("Haryana Karnal"));
        $("#closeLargeModal").click();
    }
}
