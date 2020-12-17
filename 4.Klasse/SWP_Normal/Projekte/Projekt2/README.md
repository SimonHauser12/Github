# Aktienkurse
***
### Inhaltsangabe
1. [Beschreibung des Projekts](#beschreibungdesprojekts)
2. [Installationen](#installationen)
3. [Ausführung](#ausführung)
4. [Screenshot](#screenshot)

### Beschreibung des Projekts
***
Progammiersprache: Java

Das Programm ermittelt nach der Eingabe des Firmenkürzels die jeweiligen Tagesendwerte und den 200er Durchschnitt der Firmenaktien für die letzten 280 Tage. 
Die zuvor ermittelten Werte sowie die Zeitspanne werden anschließend in eine SQL-Datenbank geschrieben, welche wiederum vom selben Programm ausgelesen werden kann.
Zu guter Letzt wird durch JavaFX ein Liniendiagramm generiert, welches die zuvor ermitteltetn Werte im angegebenen Zeitraum veranschaulicht.
Dabei wird anhand des 200er Durchschnittes ermittelt, ob die Aktie einen Gewinn oder einen Verlust verzeichnet. (Grün=Gewinn , Rot=Verlust) 

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
Bevor das Programm ausgeführt werden kann, muss das Firmenkürzel angegeben werden. Anschließend muss man angeben, ob eine neue Datenbank erstellt werden soll.

Ist dies der Fall, wird eine Verbindung zum SQL-Server hergestellt und eine neue Datenbank, inklusive der benötigten Tabellen, erstellt. Danach werden die Aktienwerte 
und der 200er Durchschnitt ermittelt und sofort in die Datenbank übertragen, jeweils in eine eigene Tabelle.

Wird die Anfrage einer neuen Datenbank verneint, wird ebenfalls eine Verbindung zum SQL-Server hergestellt, allerdings ohne eine neue Datenbank zu erzeugen. Die anschließende 
Ermittlung sowie Übertragung der Werte ist identisch. Es wurde in diesem Projekt eine MySQL-Datenbank verwendet. Die Datenbank wurde zur Gänze im Projekt erstellt. 
Damit das Programm allerdings funktioniert, müssen 

**localhost, Datenbankname, Benutzer und Passwort**

geändert werden.
Im Anschluss werden, wie zuvor erwähnt, alle ermittelten Werte im angegebenen Zeiraum durch JavaFX in einem Liniendiagramm veranschlaulicht. Dabei veranschlaulicht die rot
hinterlegte Fläche einen Aktienverlust und die grün hinterlegte Fläche einen Aktiengewinn. 

Dies wird anhand des 200er Durchschnitts berechnet:

Ist der Tagesendwert der Aktie größer als der 200er Durchschnitt   -->  Aktiengewinn

Ist der Tagesendwert der Aktie kleiner als der 200er Durchschnitt  -->  Aktienverlust

Am Ende werden die Werte aus der Datenbank gelesen und in der Console angezeigt.

### Screenshot
***
JavaFX-Ausgabe:
{https://github.com/SimonHauser12/Github/tree/master/4.Klasse/SWP_Normal/Projekte/Projekt2/Aktienkurs/JavaFX_Rubner.PNG?raw=true}
