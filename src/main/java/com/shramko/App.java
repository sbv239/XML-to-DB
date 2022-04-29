package com.shramko;

import com.shramko.component.impl.JdbcRepository;
import com.shramko.component.Repository;
import com.shramko.service.Reader;
import com.shramko.service.Runner;
import com.shramko.service.impl.XmlReader;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static void main(String[] args) {
        Repository repository = JdbcRepository.getRepository();
        Reader reader = new XmlReader(repository);
        Runner runner = new Runner(reader);
        try {
            runner.run(args);
        } catch (Exception exc) {
            log.info(exc.getMessage());
        }
    }
}
