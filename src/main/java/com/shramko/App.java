package com.shramko;

import com.shramko.service.Runner;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class App {
    public static void main(String[] args) {
        Runner runner = new Runner();
        try {
            runner.run(args);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }
}
