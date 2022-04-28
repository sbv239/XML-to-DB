# XML to DB :)

---
DB: PostgreSQL

- DB-name: Test
- DB-user: "postgres"
- DB-password: "postgres"
---

Compile: _`mvn clean compile assembly:single`_

Execution: `java -jar xml-parser.jar persons.xml`

---

#### Description

- Parser reads only the first file in the args and only if it is a xml
- If there is any null field in <person> than no DB insert will be provided
- Duplicates checked by UID

