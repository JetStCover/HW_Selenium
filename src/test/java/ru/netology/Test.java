package ru.netology;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Test {

    @org.junit.jupiter.api.Test
    void serviceWorkTest() {

        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Сергей сак");
        form.$("[data-test-id=phone] input").setValue("+79991112233");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__content").click();
        $("[data-test-id=order-success]").shouldHave(text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @org.junit.jupiter.api.Test
    void incorrectName() {

        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Sergey Sak");
        form.$("[data-test-id=phone] input").setValue("+79991112233");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__content").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @org.junit.jupiter.api.Test
    void incorrectPhone() {

        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Сергей Сак");
        form.$("[data-test-id=phone] input").setValue("+7999111");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__content").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @org.junit.jupiter.api.Test
    void notInfoName() {

        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$(".button__content").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @org.junit.jupiter.api.Test
    void formWithoutCheckbox() {

        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Сергей Сак");
        form.$("[data-test-id=phone] input").setValue("+79991112233");
        form.$(".button__content").click();
        $("[data-test-id=agreement].input_invalid").should(visible);
    }

    @org.junit.jupiter.api.Test
    void withoutName() {

        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=phone] input").setValue("+79991112233");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__content").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @org.junit.jupiter.api.Test
    void withoutPhone() {

        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Сергей Сак");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__content").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }
}