package com.shramko;

import com.shramko.component.Repository;
import com.shramko.dto.Extension;
import com.shramko.service.Reader;
import com.shramko.service.Runner;
import com.shramko.service.impl.XmlReader;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class App {
    public static void main(String[] args) {
        Repository repository = Repository.getRepository();
        Reader reader = new XmlReader(repository);
        Runner runner = new Runner(reader);
        try {
            runner.run(args);
        } catch (Exception exc) {
            log.info(exc.getMessage());
        }
    }
}
