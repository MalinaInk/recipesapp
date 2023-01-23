package com.malinaink.recipesapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Информация", description = "Дополнительные информационные оповещения, которые выводятся по запросу в браузер")
public class FirstController {

    @GetMapping("/")
    @Operation(summary = "Приветствие", description = "Информирует, что приложение готово к работе на порту 8080")
    public String helloWorld() {
        return "Приложение зaпущено";
    }

    @GetMapping("/info")
    @Operation(summary = "Информация", description = "Выводит на экран служебную информацию")
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
