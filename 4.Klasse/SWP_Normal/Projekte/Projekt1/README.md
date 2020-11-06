# Arbeits- und Feiertage
***
### Inhaltsangabe
1. [Beschreibung des Projekts](#beschreibungdesprojekts)
2. [Installationen](#installationen)
3. [Ausführung](#ausführung)
4. [Screenshot](#screenshot)

### Beschreibung des Projekts
***
Prgammiersprache: Java
Das Programm ermittelt nach der Eingabe von Start- und Endjahr, an welchem Wochentag (Montag-Freitag) die meisten freien Tage in der angegebenen Zeitspanne fallen (Österreich). 
Jene freien Tage, welche allerdings durch Schulferien verursacht werden, werden nicht gewertet.
Nachdem die jeweiligen Werte ermittelt wurden, wird durch JavaFX ein Säulendiagramm generiert, welches die zuvor ermitteltetn Werte im angegebenen Zeitraum veranschaulicht.
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
    
Außerdem müssen auch die folgenden Ferien-API's im selber Ordner wie das Projekt abgespeichert werden,
da ansonsten der Code nicht funktioniert:
* 2017.json
* 2018.json 
* 2019.json
* 2020.json
* 2021.json
* 2022.json

### Ausführung
***
Bevor man das Programm ausführen kann, müssen Start- und Endjahr angegeben werden, wobei Startjahr und Endjahr nicht kleiner als 0 sein dürfen 
und das Startjahr nicht größer als als das Endjahr sein darf. Anschließend werden aus der Feiertage-api alle Feiertage der angegebenen Zeitspanne ausgelesen
und in der Arraylist "feiertage" gespeichert. Daraufhin werden aus der Ferien-api ebenfalls alle freien Tage ausgelesen und in einer anderen Arraylist "schulferien" gespeichert.
Anschließend werden die einzelnen Wochentage aussummiert und wenn ein Tag sowohl in "feiertage" als auch in "schulferien" vorkommt, dann wird dieser entfernt. Die ermittelten Werte
werden dann in der Console angezeigt. Im Anschluss werden, wie zuvor erwähnt, alle ermittelten Werte durch JavaFX in einem Säulendiagramm veranschlaulicht. 
Dann wird eine Verbindung zur Datenbank hergestellt.  Es wurde in diesem Projekt eine MySQL-Datenbank verwendet. Die Datenbank wurde zur Gänze im Projekt erstellt. 
Damit das Programm allerdings funktioniert, müssen 
**localhost, Datenbankname, Benutzer und Passwort**
geändert werden. Die Werte werden dann, nach einer Bestätigung durch den Benutzer, in die Datenbank geschrieben. Danach werden die Werte, natürlich nur nach einer Bestätigung 
des Benutzers, wieder ausgelesen und erneut in der Console angezeigt.

### Screenshot
***
![JavaFX-Ausgabe]{https://github.com/SimonHauser12/Github/blob/master/4.Klasse/SWP_Normal/Projekte/Projekt1/Arbeits-und%20Feiertage/JavaFX_Projekt_Rubner.jpg?raw=true}