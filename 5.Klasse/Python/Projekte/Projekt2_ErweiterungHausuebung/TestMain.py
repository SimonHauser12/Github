from random import randint
import time
import VerketteteListe as vl
import ArrayList as al

def Main():
    wert=False
    wahl=""
    while(wert==False):
        print("Bitte Zahl wählen (0-1000): ")
        wahl=input()
        if(int(wahl)>1 or int(wahl)<1000):
            wert=True
    wert=False
    print("Listen werden mit Elemente von 0 bis "+str(wahl)+" befüllt")
    aliste=al.ArrayListe()
    liste=vl.VerketteListe()
    for i in range(10000):
        zufallszahl=randint(0,int(wahl))
        aliste.insert(zufallszahl)
        liste.insert(zufallszahl)
    while(wert==False):
        print("Listen wurden befüllt, Sortierung starten? (y, n)")
        wahl=input()
        if(wahl=="y" or wahl=="n"):
            wert=True
    wert=False
    if(wahl=="n"):
        print("Programm beendet")
    elif(wahl=="y"):
        startzeita=time.time()
        aliste.sortAsc()
        endzeita=time.time()

        startzeitl=time.time()
        liste.sortAsc()
        endzeitl=time.time()

        print("ArrayList:")
        print("Anzahl der List-Elemente: "+ str(aliste.length()))
        print("Sortierdauer: "+ str(endzeita-startzeita))
        print("---------------------------------------------------------------")
        print("Verkettete Liste:")
        print("Anzahl der List-Elemente: "+ vl.ElementListe.counterListe())
        print("Sortierdauer: "+ str(endzeitl-startzeitl))


    #Fertig:
    #liste.printErgebnis()
    #liste.printErgebnisReverse()
    #aliste.printErgebnis()
    #aliste.printErgebnisReverse()
    #----------------------------
    #Anhand der Objekte:
    #liste.insertObj(52, 10, "before")
    #liste.insertObj(85, 12, "after")
    #liste.deleteObj(85, "after")
    #liste.deleteObj(52, "before")
    #liste.findbyObj(85)
    #aliste.insertObj(52, 10, "before")
    #aliste.insertObj(85, 12, "after")
    #aliste.deleteObj(85, "after")
    #aliste.deleteObj(52, "before")
    #aliste.findbyObj(85)
    #-----------------------------
    #Anhand der Stellen
    #liste.insertPlace(5, 10.2, "before")
    #liste.insertPlace(5, 1.2, "after")
    #liste.deletePlace(5, "before")
    #liste.deletePlace(5, "after")
    #liste.findbyPlace(5)
    #aliste.insertPlace(5, 10.2, "before")
    #aliste.insertPlace(5, 1.2, "after")
    #aliste.deletePlace(5, "before")
    #aliste.deletePlace(5, "after")
    #aliste.findbyPlace(5)
    #-----------------------------
    #liste.sortDesc()
    #liste.sortAsc()
    #aliste.sortDesc()
    #aliste.sortAsc()
        
if __name__ == "__main__":
    Main()