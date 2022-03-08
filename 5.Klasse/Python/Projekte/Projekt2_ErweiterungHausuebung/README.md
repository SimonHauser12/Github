# Aktienkurs - API
***
### Inhaltsangabe
1. [Beschreibung des Projekts](#beschreibungdesprojekts)
2. [Installationen](#installationen)
3. [Ausführung](#ausführung)
4. [Screenshot](#screenshot)

### Beschreibung des Projekts
***
Progammiersprache: Java

Das Programm ermittelt nach Angabe der Firmenkürzel in einer extrenen Textdatei die Closewerte (Tagesendwerte) von den angegebenen Aktien. Der Zeitraum, in welchem 
die Werte ermittelt werden sollten, wird in der Console eingegeben. Je nach Belieben kann auch eine Splitcorrection durchgeführt werden. Alle Daten werden dann
in einer MySQL-Datenbank abgespeichert. Aus dieser Datenbank werden anschließend alle Werte ausgelesen und ein Aktienkauf mit einem beliebigen Kapital simuliert.
Am Ende kann man sich durch JAVA-FX entweder eine AreaChart, welcher die Entwicklung der Aktien darstellt, oder einen LineChart, der den Verlauf des eingesetzten Kapitals aufzeigt, erstellen lassen.

### Installationen
***
Damit das Projekt ausführbar ist, müssen folgende jar-Dateien eingebunden werden:
1. API
    * [java-json.jar](https://jar-download.com/artifacts/org.json) neuste Version
    * [commons-io-2.7.jar](http://commons.apache.org/proper/commons-io/) Version 2.7
2. JavaFX
    * [javafx.base.jar](https://gluonhq.com/products/javafx/) Version 11.0.2
3. MySQL
    * [mysql-connector-java-8.0.jar](https://dev.mysql.com/downloads/windows/installer/8.0.html) neuste Version

### Ausführung
***
Bevor das Programm ausgeführt werden kann, muss in der externen Textdatei mindestens ein Firmenkürzel angegeben werden, damit dessen Aktienkurs ermittelt werden kann.
Außerdem müssen in den ersten vier Zeilen der Textdatei die folgenden Daten der Datenbank angegeben werden:

* **localhost, Datenbankname, Benutzer und Passwort**

Anschließend werden, wenn man die Datenbank aktualisieren will, aus der API alle Closewerte, wenn benötigt mit Splitcorrection, im angegebenen Zeitraum ausgelesen und in einer Datenbank abgespeichert. 
Es wurde in diesem Projekt eine MySQL-Datenbank verwendet. Die Datenbank wurde zur Gänze im Projekt erstellt. 

Aus den in der Datenbank liegenden Werten wird nun jeweils der 200er-Durchschnitt berechnet (die letzten 200 Werte addieren und durch 200 dividieren, für alle Werte) und ebenfalls in einer Datenbank abgespeichert.
Anschließend wird ein Aktienkauf mit drei unterschiedlichen Strategien simuliert. Die Werte werden wieder in der Datenbank abgespeichert.
Zu guter Letzt wird der Verlauf des eingesetzten Kapitals im angegebenen Zeitraum mittels Java-FX in einem Liniendiagramm dargestellt.
Jenachdem welche Strategie gewählt wurde, wird entweder jeder Graph einzelnt oder alle drei Graphen zusammen dargestellt.

Ganz am Ende werden die erzeugen Charts als .PNG auf dem PC abgespeichert.   

### Screenshot
***
[JavaFX-Ausgabe (alle Strategien)](https://github.com/SimonHauser12/Github/blob/master/4.Klasse/SWP_Normal/Projekte/Projekt2/Aktienkurs/Aktien_2021-06-16_(4).png?raw=true)