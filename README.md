# Monitor

## Moduły
* monitor-app (war)
* installer

### Budowanie projektu (w katalogu głównym monitor-back/):

`mvn clean install` (maven)

### Instalacja (monitor-back/installer):

`ant install` (ant)

ant version >= 1.8.2

* `install.properties` - plik konfiguracyjny, można w nim ustawić: 

`app_dir` - katalog , gdzie ma zostać wypakowany serwer aplikacyjny wraz z aplikacją (domyślnie: "/monitor-back")

`app_java_home` - środowisko jdk dla aplikacji (wymagana wersja jdk: **1.8**, domyślnie: "C:/Program Files/Java/jdk1.8.0_25")

`app_tomcat_port` - port servera na którym nasłuchuje server (domyślnie: "18080")

* po instalacji w katalogu `app_dir` pojawi się dodatkowy folder `/tomcat` z serverem aplikacyjnym oraz aplikacją, dodatkowo pojawią się też dwa skrypty:

`start.bat` - uruchamia serwer (`tomcat/bin/startup.bat`)

`debug.bat` - uruchamia serwer w trybie debug (`tomcat/bin/catalina.bat jpda start`) standardowo debuger nasłuchuje na porcie **8000**

###Po uruchomieniu serwera należy sprawdzić czy aplikacja odpowiada prawidłowo:
`http://localhost:18080/monitor-back/rest/hello`

###Adresy webservisów
http://localhost:18080/monitor-back/hosts/2/measurements/CPU/3

http://localhost:18080/monitor-back/hosts/2/measurements/CPU

http://localhost:18080/monitor-back/hosts/2/measurements/MEMORY

http://localhost:18080/monitor-back/hosts/2/measurements/NETWORK

http://localhost:18080/monitor-back/hosts/2/measurements

http://localhost:18080/monitor-back/hosts/2

http://localhost:18080/monitor-back/hosts/2



