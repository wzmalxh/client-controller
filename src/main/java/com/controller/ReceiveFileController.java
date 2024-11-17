package com.controller;

import com.service.FileSerializeService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Arrays;

@RestController
public class ReceiveFileController {

    @Resource
    private static FileSerializeService fileSerializeService;

    private static final String newFile = "/Users/wd/Desktop/receiveFileController.txt";

    @GetMapping("/download")
    public void writeFileContent(String fileName) {
        String content = fileSerializeService.getFileContent(fileName);
        byte [] contentBytes = content.getBytes(Charset.forName("UTF-8"));
        try (FileOutputStream fos = new FileOutputStream(new File(newFile));
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            bos.write(contentBytes);
            bos.flush(); // 刷新缓冲区，确保数据都写入文件
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String content = getContent("/Users/wd/Desktop/didi.txt");
        byte [] contentBytes = content.getBytes("UTF-8");
        try (FileOutputStream fos = new FileOutputStream(new File(newFile));
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            bos.write(contentBytes);
            bos.flush(); // 刷新缓冲区，确保数据都写入文件
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getContent (String filePath) {
        File file = new File(filePath);
        String str = "";
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             FileChannel channel = fis.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());
            channel.read(buffer);
            buffer.flip();
            byte[] bytes = buffer.array();
            String result = new String(bytes, "UTF-8");
            return  result;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
