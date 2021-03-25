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

Das Programm ermittelt nach Angabe der Firmenkürzel in einer extrenen Textdatei die Closewerte (Tagesendwerte) von den angegebenen Aktienkursen über eine im Programm 
angegebene Zeitspanne (z.B. die letzten 1000 Einträge).
Nach der Ermittlung der "rohen" Closewerte werden diese mittels Splitcorrection korrigiert und die korrigierten Werte werden in einer MySQL-Datenbank abgespeichert. 
Aus dieser Datenbank werden anschließend alle Werte ausgelesen und in einem Liniendiagramm mittels JAVA-FX in der angegebenen Zeitspanne dargestellt.

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
Anschließend werden aus der API alle Close- sowie Splitwerte in der angegebenen Menge ausgelesen und in einer Datenbank abgespeichert. 
Es wurde in diesem Projekt eine MySQL-Datenbank verwendet. Die Datenbank wurde zur Gänze im Projekt erstellt. 
Damit das Programm allerdings funktioniert und Werte in die Datenbank geschrieben werden können, müssen 
**localhost, Datenbankname, Benutzer und Passwort**
geändert werden.
Daraufhin werden diese Werte wieder aus der Datenbank ausgelesen und mittels Splitcorrection (wenn der Splitwert größer als 1 ist, müssen alle nachherigen Werte durch diesen Wert dividiert werden)
werden die Aktiensplits korrigiert. Die korrigierten Werte werden dann wieder in einer Datenbanktabelle abgespeichert. Aus diesen korrigierten Werten wird danach der 200er-Durchschnitt 
berechnet (die letzten 200 Werte addieren und durch 200 dividieren, für alle Werte) und ebenfalls in einer Datenbank abgespeichert.
Zu guter Letzt werden die korrigierten Werte sowie die 200er-Werte ausgelesen und durch Java-FX mittels Liniendiagramm dargestellt.
Dargestellt werden also der 200er Durchschnitt mittels schwarzer Linie und die Closewerte, wobei der Hintergrund der Closewerte variable anhand des 200er-Durchschnitts gesteuert wird.

    * Wenn der Closewert größer als der 200er-Wert ist, dann ist der Hintergrund grün gefärbt (Aktie bringt Gewinn)
    * Wenn der Closewert kleiner als der 200er-Wert ist, dann ist der Hintergrund rot gefärbt (Aktie bringt Verlust)

Ganz am Ende werden die erzeugen Charts als .PNG auf dem PC abgespeichert.   

### Screenshot
***
[JavaFX-Ausgabe]{https://github.com/SimonHauser12/Github/blob/master/4.Klasse/SWP_Normal/Projekte/Projekt2/Aktienkurs/TSLA_2021-03-25.png?raw=true}