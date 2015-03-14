# Monitor

## Moduły
* monitor-app (war)
* installer

### Budowanie projektu (w katalogu głównym monitor-back/):

`mvn clean install` (maven)

### Instalacja (monitor-back/installer):

`ant install` (ant)

* `install.properties` - plik konfiguracyjny, można w nim ustawić: 

`app_dir` - katalog , gdzie ma zostać wypakowany serwer aplikacyjny wraz z aplikacją (domyślnie: "/monitor-back")

`app_java_home` - środowisko jdk dla aplikacji (wymagana wersja jdk: **1.8**, domyślnie: "C:/Program Files/Java/jdk1.8.0_25")

`app_tomcat_port` - port servera na którym nasłuchuje server (domyślnie: "18080")

* po instalacji w katalogu `app_dir` pojawi się dodatkowy folder `/tomcat` z serverem aplikacyjnym oraz aplikacją, dodatkowo pojawią się też dwa skrypty:

`start.bat` - uruchamia serwer (`tomcat/bin/startup.bat`)

`debug.bat` - uruchamia serwer w trybie debug (`tomcat/bin/catalina.bat jpda start`) standardowo debuger nasłuchuje na porcie **8000**

