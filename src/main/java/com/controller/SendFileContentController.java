package com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

@RestController
public class SendFileContentController {


    // 获取所有用户信息的接口
    @GetMapping("/users")
    public ResponseEntity<String> readFileContent(String fileName) {
        File file = new File(fileName);
        String str = "";
        try (FileInputStream fis = new FileInputStream(new File(fileName));
             FileChannel channel = fis.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());
            channel.read(buffer);
            buffer.flip();
            byte[] bytes = buffer.array();
            return  ResponseEntity.ok(Arrays.toString(bytes));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
