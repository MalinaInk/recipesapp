package com.malinaink.recipesapp.service;

public interface FilesService {
//    boolean saveToFile(String json);
//
//    String readFromFile();
//
//    boolean cleanToFile();

    boolean saveToFile(String json, String path);

    String readFromFile(String path);

    boolean cleanToFile(String path1);
}
