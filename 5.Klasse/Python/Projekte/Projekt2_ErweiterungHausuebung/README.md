# Projekt - Verkettete Liste
***
### Inhaltsangabe
1. [Beschreibung des Projekts](#beschreibungdesprojekts)
2. [Ausführung](#ausführung)
2. [Ablauf des Programms](#ablaufdesprogramms)
2. [Ergebnisse des Programms](#ergebnissedesprogramms)
2. [Aufwandsklassen](#aufwandsklassen)
3. [Screenshot](#screenshot)

### Beschreibung des Projekts
***
Progammiersprache: Python

Das Projekt befasst sich mit der Funktionsweiße der doppelt verkettenten Liste sowie der Arrayliste. Die Implementierung verschiedener Methoden zum Einfügen, Löschen, Finden und Sortieren von Listenelementen der jeweiligen Listen wurde ebenfalls realisiert.
Das Programm stellt in weiterer Folge einen Vergleich zwischen doppelt-verketteter Liste und Arrayliste dar. 

### Ausführung
***
Die Entwicklung des Programms erfolgte mit Visual Studio Code. Die Ausführung des Projekt ist allerdings in jeder Entwicklungsumgebung, welche die Erstellung von Python-Projekte unterstützt, durchführbar.
Bei der Realisierung des Projekt erfolgt mit der Python-Version 3.7.

### Ablauf des Programms
***
Beim Starten des Programms muss der Benutzer die Spannweite der Zufallszahlen festlegen. Danach werden Zufallszahlen von 0 bis (vorher eingegebener Wert) erstellt und jeweils sowohl der doppelt verketteten Liste als auch der Arrayliste hinzugefügt. In weiterer Folge wird abgefragt, ob die jeweiligen Listenelemente sortiert werden sollen oder nicht. 
Wenn ja, werden die Listen sortiert und es wird am Ende eine Auflistung (Vergleich) der jeweiligen Sortierdauer ausgegeben. 
PS: Alle definierten Methoden sind funktionsfähig und einsatzbereit, werden aber nicht in der Main-Funktion aufgerufen

### Ergebnisse des Programms
***
Mittels for-Schleife werden 10000 Elemente erzeugt und jeweils sowohl der doppelt verketteten Liste sowie der Arrayliste hinzugefügt. Anschließend erfolgt eine Sortierung beider Liste. 
Als Sortieralgorithmus wurde Insertionssort gewählt. Die Sortierung erfolgt je nach belieben des Benutzers aufsteigend (vom kleistens zum größste) oder absteigend (vom größten zum kleisten).
Die Dauer der Sortierung der jeweiligen Liste wird gemessen und im Anschluss aufschlussreich ausgegeben. Man erhält einen Effizienzvergleich zwischen doppelt verkettete Liste und Arrayliste.
Ergebnisse:
	- Doppelt verkettete Liste benötigt zwischen 7 und 10 Sekunden für die Sortierung
	- Arrayliste benötigt zwischen 5 und 8 Sekunden für die Sortierung
Man erkennt, dass die Arrayliste minimal scheller ist als die doppelt verkettete Liste

### Aufwandklassen
*** 
| Methode | doppelt verkettete Liste | Arrayliste |
| --- | --- | --- |
| insertAfter()/insertBefore() | n | n + 1 |
| deleteAfter()/deleteBefore() | n | n |
| ausgabe()/ausgabeReverse() | n | n |
| findByIndex()/findByObject() | n | n |
| sortAsc()/sortDesc()| n^2 | n^2 |