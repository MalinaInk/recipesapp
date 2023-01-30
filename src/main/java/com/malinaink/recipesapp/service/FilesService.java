package com.malinaink.recipesapp.service;

import java.io.File;
import java.io.IOException;

public interface FilesService {
    boolean saveToFile(String json, String path);

    String readFromFile(String path);

    boolean cleanToFile(String path1);

    File getDataFile(String path);


}
