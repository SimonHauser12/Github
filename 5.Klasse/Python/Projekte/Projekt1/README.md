# Aktienkurs - API
***
### Inhaltsangabe
1. [Beschreibung des Projekts](#beschreibungdesprojekts)
2. [Installationen](#installationen)
3. [Ausführung](#ausführung)
4. [Screenshot](#screenshot)

### Beschreibung des Projekts
***
Progammiersprache: Python

Dieses Programm basiert auf dem Spiel Scher-Stein-Papier-Echse-Spock, bekannt aus der Serie "Big Bang Theorie". 
Man kann also gegen den Computer spielen. Die Wahl des Computers wird durch das Random-Modul generiert. 
Die jeweiligen Spielstände werden sowohl in einer Datenbank als auch auf einem API-Server gespeichert. 
Diese Spielstände (Anzahl der Siege), Anzahl der gewählten Elemente und die Spielanzahl können ausgelesen werden 
Am Ende kann man sich sogar ein Kreisdiagramm über die häufigst gewählten Elemente anzeigen lassen. 

### Installationen
***
Damit das Projekt ausgeführt werden kann, müssen folgende folgende Python-Module installiert werden (Konsole):
1. MySQL
    * python -m pip install mysql-connector-python
2. Image
    * python -m pip install --update Pillow
3. API
    * python -m pip install requests

### Ausführung
***
Bevor das Programm ausgeführt werden kann, muss der API-Server gestartet werden, da ansonsten das Programm fehlerhaft duchgeführt wird
Außerdem müssen folgende MySQL-Daten im Datenbankteil des Programms geändert werden:

* **localhost, Datenbankname, Benutzer und Passwort**

Nachdem diese Schritte durchgeführt wurden, kann das Programm gestartet werden. Anschließend wird sofort eine Datenbankverbindung hergestellt und man
kann sich die aktuellen Datenbankeinträge (Anzahl Siege, Anzahl Spiele, Anzahl gewählter Elemente) anzeigen lassen. Dann beginnt das Spiel.
Der Spieler muss ein Elemten auswählen, anschließend wählt der PC und das Ergebnis wird kurz darauf dargestellt. Man kann anschließend das Spiel beenden oder eine 
erneute Runde spielen, je nach belieben des Spieler. Wenn man sich gegen eine neue Runde entschiedet, hat man wieder die Möglichkeit, sich die aktualisierten Datenbankeinträge
anzeigen zu lassen. 

Danach erfolgt das Ansprechen des API-Servers und die aktuellen Datenbankeinträge (Anzahl gewählter Elemente) werden dem Server übermittelt und dort abgespeichert.
Aufgrund dieser Werte erzeugt der Server anschließend ein Kreisdiagramm, welches dann auf dem PC abgespeichert wird. Dieses PNG-File kann dann am Ende ganz nach belieben
angezeigt werden oder nicht. 

### Screenshot
***
[Kreisdiagramm (Häufigkeit gewählter Elemente)](https://github.com/SimonHauser12/Github/tree/master/5.Klasse/Python/Projekte/Projekt1/SchereSteinPapier/chart.png?raw=true)