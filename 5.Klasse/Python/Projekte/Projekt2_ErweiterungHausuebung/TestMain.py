from random import randint
import time
import VerketteteListe as vl
import ArrayList as al

def Main():
    aliste=al.ArrayListe()
    liste=vl.VerketteListe()
    for i in range(10000):
        zufallszahl=randint(1,99)
        aliste.insert(zufallszahl)
        liste.insert(zufallszahl)

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
    print("---------------------------------------------------------------")
    print("Aufwandsklassen:")
    print("Funktion                        |     Liste     |     ArrayList")
    print("insert/insertAfter/insertBefore |       1       |        1     ")
    print("deleteAfter/deleteBefore        |       1       |        1     ")
    print("select/selectReverce            |       n       |        n     ")
    print("findElement                     |       n       |        n     ")
    print("sortAsc/sortDesc                |       n2      |        n2     ")


    #Fertig:
    #liste.select()
    #liste.selectReverse()
    #print("Anzahl der List-Elemente: "+ vl.ElementListe.counterListe())
    #----------------------------
    #Anhand der Objekte:
    #liste.insertObj(52, 10, "before")
    #liste.insertObj(85, 12, "after")
    #liste.deleteObj(85, "after")
    #liste.deleteObj(52, "before")
    #liste.findObjPlace(85)
    #-----------------------------
    #Anhand der Stellen
    #liste.insertPlace(5, 10.2, "before")
    #liste.insertPlace(5, 1.2, "after")
    #liste.deletePlace(5, "before")
    #liste.deletePlace(5, "after")
    #-----------------------------
    #liste.sortDesc()
    #liste.sortAsc()
        
if __name__ == "__main__":
    Main()