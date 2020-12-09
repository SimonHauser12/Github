# Aktienkurse
***
### Inhaltsangabe
1. [Beschreibung des Projekts](#beschreibungdesprojekts)
2. [Installationen](#installationen)
3. [Ausführung](#ausführung)

### Beschreibung des Projekts
***
Progammiersprache: Java

Das Programm ermittelt nach der Eingabe der Firma und der Anzahl der Tage die jeweiligen Tagesendwerte und den 200er Durchschnitt der Firmenaktien. 
Nachdem die jeweiligen Werte ermittelt wurden, wird durch JavaFX ein Liniendiagramm generiert, welches die zuvor ermitteltetn Werte im angegebenen Zeitraum veranschaulicht.
Zu guter Letzt werden die ermittelten Werte sowie die Zeitspanne in eine SQL-Datenbank geschrieben, welche wiederum vom selben Programm nach belieben ausgelesen werden kann.

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
