package com.example.maxnumber.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.PriorityQueue;

@Service
public class MaxNumberService {
    public int findNthMaxNumber(File file, int n) throws IOException {
        if (n <= 0) {
            throw new IllegalArgumentException("N must be greater than 0");
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(n);

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        int value = (int) cell.getNumericCellValue();
                        if (minHeap.size() < n) {
                            minHeap.offer(value);
                        } else if (value > minHeap.peek()) {
                            minHeap.poll();
                            minHeap.offer(value);
                        }
                    }
                }
            }
        }

        if (minHeap.size() < n) {
            throw new IllegalArgumentException("Not enough numbers in the file");
        }

        return minHeap.peek();
    }
}
