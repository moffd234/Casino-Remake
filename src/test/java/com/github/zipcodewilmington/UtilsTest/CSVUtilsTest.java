package com.github.zipcodewilmington.UtilsTest;

import com.github.zipcodewilmington.utils.CSVUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class CSVUtilsTest {

    @Test
    public void testFileCreation() throws IOException {
        String csvPath = "./csvTest.csv";
        if(new File(csvPath).exists()){
            Assert.fail("Delete csvTest file");
        }

        // Write data to csv
        FileWriter writer = new FileWriter(csvPath);

        ArrayList<String> data = new ArrayList<>();
        data.add("A");
        data.add("B");
        data.add("C");

        CSVUtils.writeLine(writer, data);
        writer.close();

        // Check that the data was written in csv format
        List<String> lines = Files.readAllLines(Paths.get(csvPath));

        String[] parts = lines.get(0).split(",");
        Assert.assertArrayEquals(new String[]{"A", "B", "C"}, parts);
    }
}
