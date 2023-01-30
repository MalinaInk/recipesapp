package com.malinaink.recipesapp.service.impl;

import com.malinaink.recipesapp.exception.ReadFileException;
import com.malinaink.recipesapp.service.FilesService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceImpl implements FilesService {

    @Override
    public boolean saveToFile(String json, String path) {
        try {
            cleanToFile(path);
            Files.writeString(Path.of(path), json);
            return true;
        } catch (IOException e) {
            throw new ReadFileException("Невозможно сохранить данные в файл. По данному пути файл не найден.");
        }
    }

    @Override
    public String readFromFile(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch(IOException e) {
            throw new ReadFileException("Невозможно считать данные из файла. По данному пути файл не найден");
        }
    }

    @Override
    public boolean cleanToFile(String path) {
        Path path1 = Path.of(path);
        try {
            Files.deleteIfExists(path1);
            Files.createFile(path1);
            return true;
        } catch (IOException e) {
            throw new ReadFileException("Невозможно обновить данные файла. По данному пути файл не найден");
        }
    }

    @Override
    public File getDataFile(String path) {
        return new File(path);
    }


}
