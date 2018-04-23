# SocialPortal 1.0.0v
Prosty portal społeczniościowy

## Funkcjonalności

* Rejestracja
* Logowanie
* Udostępnianie postów
* Komentowanie postów
* Dodawanie do znajomych
* Zmiana języka
* Dodawanie zdjęć
* Zmiana zdjęcia profilowego
* Usuwanie swoich postów
* Usuwanie dowolnych postów przez administratora
* Wyszukiwanie po imieniu, nazwisku lub e-mailu

## Licencja 
Projekt udostępniany na licencji Beerware
```
/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <phk@FreeBSD.ORG> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Poul-Henning Kamp
 * ----------------------------------------------------------------------------
 */
 ```

## Baza danych
Jako baza danych został użyty MySql 
* [socialPortalDB.sql](socialPortalDB.sql) - plik SQL
* [sqlDiagram.pdf](sqlDiagram.pdf) - diagram tabel

## Budowane z:
* Maven 3.5
* Intellij IDEA 2017
* apache-tomcat-8.5.29

## Instalacja 
```
mvn clean install
```

### Uruchomienie
```
spring-boot:run
```

### ScrenShoots

![Login](/ScreenShots/login.png?raw=true "Login View")
![Home](/ScreenShots/home.png?raw=true "Home View")
![Profile](/ScreenShots/profile.png?raw=true "Profile View")
![Search](/ScreenShots/search.png?raw=true "Search View")
