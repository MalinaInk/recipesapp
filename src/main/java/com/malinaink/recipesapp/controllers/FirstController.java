package com.malinaink.recipesapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping("/")
    public String helloWorld() {
        return "Приложение зпущено";
    }

    @GetMapping("/info")
    public String getProjectInfo() {
        return "Имя ученика: Малиничева Наталья;<br>" +
                "Название проекта: Приложение для сайта рецептов;<br>" +
                "Дата создания: 15.12.2022;<br>" +
                "Описание : Приложение для работы с сайтом рецептов;<br>" +
                "Язык: java;<br>" +
                "Фреймворк: Spring;<br>" +
                "Сборщик: MAVEN<br>";
    }


}
