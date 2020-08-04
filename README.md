# Oddam w dobre ręce

  Aplikacja dla tych, którzy chcą przekazać darowiznę dla wybranej fundacji w postaci zabawek, ubrań w różnym stanie,
   książek i innych.
   
<img src="https://user-images.githubusercontent.com/59224048/89333435-3d970500-d695-11ea-9ea8-55b078a773b2.png"/>
   
## Technologie

 * Spring Boot
 * Spring Security
 * Spring Data JPA
 * Hibernate
 * MySQL
 * JavaScript
 * JQuery
 * CSS
 * HTML
 
 ## Uruchamianie aplikacji
 
 #### Lokalnie:
 
 * Należy uzupełnić informację dotyczące połączenia z bazą danych<br>
   w katalogu src/main/resources:
   
   ```
   application-db.properties
   
   Uzupełnij nazwę użytkownika do swojej lokalnej bazy danych:
   spring.datasource.username=
   ```
   
   ```
   application-db.properties
   
   Uzupełnij hasło do swojej lokalnej bazy danych:
   spring.datasource.password=
   ```
   
   ```
   application-db.properties
   
   Jeżeli korzystasz z MySQL, upewnij się, że nie masz uruchomionej bazy danych na innym porcie
   (projekt ustawiony jest na domyślnym dla MySQL porcie: 3306)
   spring.datasource.url=jdbc:mysql://localhost:3306/charity_donation?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&useSSL=false
   ```
   
 * Uruchamianie: 
    * z użyciem maven: <strong>mvn package spring-boot:run</strong>
    * z użyciem java -jar: <strong>java -jar target/charity-0.0.1-SNAPSHOT.jar</strong>
    * "ręcznie" uruchom w swoim IDE plik CharityApplicaton.class z katalogu src/main/java/pl/coderslab/charity
 
 ## Spring security 
 
 Aby ułatwić prezentację projektu, w bazie danych charity_donation.sql, znajdującej się w katalogu src/main/resources/META-INF
  znajdują się już użytkownicy. Możesz skorzystać z ich kont aby przyjrzeć sie aplikacji. 
  
  ```
  Użytkownik Zofia Wasilonek z rolą ADMIN oraz USER:

  Email: admin@gmail.com
  Hasło: adminpass
  ```
  
  ```
  Użytkownik Jan Kowalski z rolą USER

  Email: jan@o2.pl
  Hasło: 12345678
  ```
 
 ## Kontakt
  
 zofiawasilonek@.gmail.com<br>
 <a href="https://www.linkedin.com/in/zofia-wasilonek/">Linkedin</a>

 