package com.service.impl;

import com.service.FileSerializeService;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class FileSerializeServiceImpl implements FileSerializeService {
    @Override
    public String getFileContent(String filePath) {
        File file = new File(filePath);
        String str = "";
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             FileChannel channel = fis.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());
            channel.read(buffer);
            buffer.flip();
            byte[] bytes = buffer.array();
            return  Arrays.toString(bytes);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
