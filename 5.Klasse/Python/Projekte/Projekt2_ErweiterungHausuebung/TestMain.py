from random import randint
import time

class ArrayListe:

    def __init__(self):
        self.list=[]

    def insert(self, obj):
        self.list.append(obj)

    def insertAfterPlace(self, pl, newObj):
        if(pl+1<len(self.list) and pl>=0):
            self.list.insert(pl+1, newObj)
        elif():
            print("Nur zwischen 0 und "+str(len(self.list)-2)+" wählen")
        
    def insertBeforePlace(self, pl, newObj):
        if(pl<len(self.list) and pl-1>=0):
            self.list.insert(pl-1, newObj)
        elif():
            print("Nur zwischen 1 und "+str(len(self.list)-1)+" wählen")
          
    def deleteAfterPlace(self, pl):
        if(pl+1<len(self.list) and pl>=0):
            self.list.pop(pl+1)
        elif():
            print("Nur zwischen 0 und "+str(len(self.list)-2)+" wählen")

    def deleteBeforePlace(self, pl):
        if(pl<len(self.list) and pl-1>=0):
            self.list.pop(pl-1)
        elif():
            print("Nur zwischen 1 und "+str(len(self.list)-1)+" wählen")

    def findObj(self, pl):
        print("An der Stelle "+str(pl)+"befindet sich das Element "+ str(self.list[pl]))

    def select(self):
        for i in self.list:
            print("|"+str(i)+"|")
    
    def selectReverse(self):
        for i in reversed(self.list):
            print("|"+str(i)+"|")

    def length(self):
        print ("Länge Arraylist: "+str(len(self.list)))

    def sortAsc(self):
        j = -1
        key = self.list[0]
        for i in range(1, len(self.list)):
            key = self.list[i]
            j = i-1
            while(j >= 0 and self.list[j] > key):
                self.list[j+1] = self.list[j]
                j -= 1
            self.list[j+1] = key

    def sortDesc(self):
        j = -1
        key = self.list[0]
        for i in range(1, len(self.list)):
            key = self.list[i]
            j = i-1
            while(j >= 0 and self.list[j] < key):
                self.list[j+1] = self.list[j]
                j -= 1
            self.list[j+1] = key

class ElementListe:
    counter=0
    def __init__(self, obj):
        self.obj=obj
        self.prevEl=None
        self.nextEl=None
        type(self).counter+=1
    
    def __str__(self):
        return self.obj
    
    def setElement(self, el):
        self.obj=el

    def setnextElement(self, nextEl):
        self.nextEl=nextEl
    
    def getnextElement(self):
        return self.nextEl

    def setprevElement(self, prevEl):
        self.prevEl=prevEl
    
    def getprevElement(self):
        return self.prevEl
    
    def counterListe():
        return f"{ElementListe.counter}"

class VerketteListe:

    def __init__(self):
        self.startEl=None

    def getLastElement(self):
        le=self.startEl
        while(le.getnextElement()!=None):
            le=le.getnextElement()
        return le

    def getElement(self, obj):
        le=self.startEl
        while(le.__str__()!=obj and le.getnextElement()!=None):
            le=le.getnextElement()
        return le

    def findElementPlace(self, obj):
        le=self.startEl
        counter=0
        while(le.__str__()!=obj and le.getnextElement()!=None):
            le=le.getnextElement()
            counter+=1
        c=[le, counter]
        return c

    def findElement(self, pl):
        le=self.startEl
        for i in range(pl):
            le=le.getnextElement()
        return le

    def insert(self, obj):
        if(self.startEl==None):
            self.startEl=ElementListe(obj)
        elif(self.startEl!=None):
            newEl=ElementListe(obj)
            lastEl=self.getLastElement()
            newEl.prevEl=lastEl
            lastEl.nextEl=newEl  

    def insertAfterObj(self, obj, newObj):
        le=self.getElement(obj)
        if(le.__str__()==obj):
            if(le.getnextElement()!=None):
                nle=le.getnextElement()

                newEl=ElementListe(newObj)
                newEl.prevEl=le
                le.nextEl=newEl
                newEl.nextEl=nle
                nle.prevEl=newEl  
            elif(le.getnextElement()==None):
                nle=None

                newEl=ElementListe(newObj)
                newEl.prevEl=le
                le.nextEl=newEl
                newEl.nextEl=nle          
        elif(le.__str__()!=obj):
            print("Element "+str(obj)+" ist nicht in der Liste enthalten")
            print(str(newObj)+" konnte nicht hinzugefügt werden") 

    def insertBeforeObj(self, obj, newObj):
        le=self.getElement(obj)
        if(le.__str__()==obj):
            if(le.getprevElement()!=None):
                ple=le.getprevElement()

                newEl=ElementListe(newObj)
                le.prevEl=newEl
                newEl.nextEl=le
                ple.nextEl=newEl
                newEl.prevEl=ple  
            elif(le.getprevElement()==None):
                ple=None

                newEl=ElementListe(newObj)
                le.prevEl=newEl
                newEl.nextEl=le
                newEl.prevEl=ple  
                self.startEl=newEl          
        elif(le.__str__()!=obj):
            print("Element "+str(obj)+" ist nicht in der Liste enthalten")
            print(str(newObj)+" konnte nicht hinzugefügt werden")  

    def deleteAfterObj(self, obj):
        le=self.getElement(obj)
        if(le.__str__()==obj):
            if(le.getnextElement()!=None):
                dle=le.getnextElement()
                if(dle.getnextElement()!=None):
                    ndle=dle.getnextElement()

                    le.nextEl=ndle
                    ndle.prevEl=le
                elif(dle.getnextElement()==None):
                    ndle=None

                    le.nextEl=ndle

                ElementListe.counter-=1
            elif(le.getnextElement()==None):
                print("Es ist kein Element nach "+str(obj)+" vorhanden, kein Delete durchführbar")        
        elif(le.__str__()!=obj):
            print("Element "+str(obj)+" ist nicht in der Liste enthalten")
            print("Kein Delete konnte durchgeführt werden")     
        
    def deleteBeforeObj(self, obj):
        le=self.getElement(obj)
        if(le.__str__()==obj):
            if(le.getprevElement()!=None):
                dle=le.getprevElement()
                if(dle.getprevElement()!=None):
                    pdle=dle.getprevElement()

                    le.prevEl=pdle
                    pdle.nextEl=le
                elif(dle.getprevElement()==None):
                    pdle=None

                    le.prevEl=pdle

                ElementListe.counter-=1
            elif(le.getprevElement()==None):
                print("Es ist kein Element vor "+str(obj)+" vorhanden, kein Delete durchführbar")        
        elif(le.__str__()!=obj):
            print("Element "+str(obj)+" ist nicht in der Liste enthalten")
            print("Kein Delete konnte durchgeführt werden")  

    def findObjPlace(self, obj):
        c=self.findElementPlace(obj)
        le=c[0]
        s=c[1]
        if(le.__str__()==obj):
            if(le.getprevElement()!=None and le.getnextElement()!=None):
                print("Das Element "+str(obj)+" befindet sich an der "+str(s)+". Stelle")
                print("Vorheriges Element: "+str(le.getprevElement().__str__()))
                print("Nachfolgendes Element: "+str(le.getnextElement().__str__()))
            elif(le.getprevElement()==None):
                print("Das Element "+str(obj)+" befindet sich an der "+str(s)+". Stelle")
                print("Vorheriges Element nicht vorhanden (erstes Element)")
                print("Nachfolgendes Element: "+str(le.getnextElement().__str__()))
            elif(le.getnextElement()==None):
                print("Das Element "+str(obj)+" befindet sich an der "+str(s)+". Stelle")
                print("Vorheriges Element: "+str(le.getprevElement().__str__()))
                print("Nachfolgendes Element nicht vorhanden (letztes Element)")      
        elif(le.__str__()!=obj):
            print("Element "+str(obj)+" ist nicht in der Liste enthalten")  

    def insertAfterPlace(self, pl, newObj):
        if(pl>=ElementListe.counter or pl<0):
            print("Es wurde eine falsche Zahl gewählt")
            print("Bitte zwischen 0-"+str(ElementListe.counter-1)+" wählen")
        elif(pl<ElementListe.counter):
            le=self.findElement(pl)
            if(le.getnextElement()!=None):
                nle=le.getnextElement()

                newEl=ElementListe(newObj)
                newEl.prevEl=le
                le.nextEl=newEl
                newEl.nextEl=nle
                nle.prevEl=newEl  
            elif(le.getnextElement()==None):
                nle=None

                newEl=ElementListe(newObj)
                newEl.prevEl=le
                le.nextEl=newEl
                newEl.nextEl=nle 

    def insertBeforePlace(self, pl, newObj):
        if(pl>=ElementListe.counter or pl<0):
            print("Es wurde eine falsche Zahl gewählt")
            print("Bitte zwischen 0-"+str(ElementListe.counter-1)+" wählen")
        elif(pl<ElementListe.counter):
            le=self.findElement(pl)
            if(le.getprevElement()!=None):
                ple=le.getprevElement()

                newEl=ElementListe(newObj)
                le.prevEl=newEl
                newEl.nextEl=le
                ple.nextEl=newEl
                newEl.prevEl=ple  
            elif(le.getprevElement()==None):
                ple=None

                newEl=ElementListe(newObj)
                le.prevEl=newEl
                newEl.nextEl=le
                newEl.prevEl=ple  
                self.startEl=newEl 

    def deleteAfterPlace(self, pl):
        if(pl>=ElementListe.counter or pl<0):
            print("Es wurde eine falsche Zahl gewählt")
            print("Bitte zwischen 0-"+str(ElementListe.counter-1)+" wählen")
        elif(pl<ElementListe.counter):
            le=self.findElement(pl)
            if(le.getnextElement()!=None):
                dle=le.getnextElement()
                if(dle.getnextElement()!=None):
                    ndle=dle.getnextElement()

                    le.nextEl=ndle
                    ndle.prevEl=le
                elif(dle.getnextElement()==None):
                    ndle=None

                    le.nextEl=ndle

                ElementListe.counter-=1
            elif(le.getnextElement()==None):
                print("Es ist kein Element nach der Stelle "+str(pl)+" vorhanden, kein Delete durchführbar")   
        
    def deleteBeforePlace(self, pl):
        if(pl>=ElementListe.counter or pl<0):
            print("Es wurde eine falsche Zahl gewählt")
            print("Bitte zwischen 0-"+str(ElementListe.counter-1)+" wählen")
        elif(pl<ElementListe.counter):
            le=self.findElement(pl)
            if(le.getprevElement()!=None):
                dle=le.getprevElement()
                if(dle.getprevElement()!=None):
                    pdle=dle.getprevElement()

                    le.prevEl=pdle
                    pdle.nextEl=le
                elif(dle.getprevElement()==None):
                    pdle=None

                    le.prevEl=pdle

                ElementListe.counter-=1
            elif(le.getprevElement()==None):
                print("Es ist kein Element vor der Stelle "+str(pl)+" vorhanden, kein Delete durchführbar")     

    def findObj(self, pl):
        if(pl>=ElementListe.counter or pl<0):
            print("Es wurde eine falsche Zahl gewählt")
            print("Bitte zwischen 0-"+str(ElementListe.counter-1)+" wählen")
        elif(pl<ElementListe.counter):
            le=self.findElement(pl)
            if(le.getprevElement()!=None and le.getnextElement()!=None):
                print("An der Stelle "+str(pl)+". befindet sich das Element "+str(le.__str__()))
                print("Vorheriges Element: "+str(le.getprevElement().__str__()))
                print("Nachfolgendes Element: "+str(le.getnextElement().__str__()))
            elif(le.getprevElement()==None):
                print("An der Stelle "+str(pl)+". befindet sich das Element "+str(le.__str__()))
                print("Vorheriges Element nicht vorhanden (erstes Element)")
                print("Nachfolgendes Element: "+str(le.getnextElement().__str__()))
            elif(le.getnextElement()==None):
                print("An der Stelle "+str(pl)+". befindet sich das Element "+str(le.__str__()))
                print("Vorheriges Element: "+str(le.getprevElement().__str__()))
                print("Nachfolgendes Element nicht vorhanden (letztes Element)")

    def select(self):
        print("Normal")
        le=self.startEl
        while(le!=None):
            print("| " + str(le.__str__()) + " |")
            le=le.getnextElement()
    
    def selectReverse(self):
        print("Rückwerts")
        le=self.startEl
        while(le.getnextElement()!=None):
            le=le.getnextElement()
        rle=le
        while(rle!=None):
            print("| " + str(rle.__str__()) + " |")
            rle=rle.getprevElement()

    def sortAsc(self):
        j = -1
        key = self.findElement(0)
        for i in range(1, int(ElementListe.counterListe())):
            key = self.findElement(i).__str__()
            j = i-1
            while(j >= 0 and self.findElement(j).__str__() > key):
                l=self.findElement(j).__str__()
                self.deleteAfterPlace(j)
                self.insertAfterPlace(j, l)
                j -= 1
            if(j>=0):
                self.deleteAfterPlace(j)
                self.insertAfterPlace(j, key)
            elif(j<0):
                self.deleteBeforePlace(1)
                self.insertBeforePlace(1, key)

    def sortDesc(self):
        j = -1
        key = self.findElement(0)
        for i in range(1, int(ElementListe.counterListe())):
            key = self.findElement(i).__str__()
            j = i-1
            while(j >= 0 and self.findElement(j).__str__() < key):
                l=self.findElement(j).__str__()
                self.deleteAfterPlace(j)
                self.insertAfterPlace(j, l)
                j -= 1
            if(j>=0):
                self.deleteAfterPlace(j)
                self.insertAfterPlace(j, key)
            elif(j<0):
                self.deleteBeforePlace(1)
                self.insertBeforePlace(1, key)
                
             
            

def Main():
    """startzeit=time.time()
    liste=VerketteListe()
    for i in range(1000):
        zufallszahl=randint(1,9)
        liste.insert(zufallszahl)
    #liste.select()
    endzeit=time.time()
    print("Anzahl der List-Elemente: "+ ElementListe.counterListe())
    print("Dauer: "+ str(endzeit-startzeit))

    #Fertig:
    #liste.select()
    #liste.selectReverse()
    #print("Anzahl der List-Elemente: "+ ElementListe.counterListe())

    #Anhand der Objekte:
    #liste.insertBeforeObj(52, 10)
    #liste.insertAfterObj(85, 12)
    #liste.deleteAfterObj(85)
    #liste.deleteBeforeObj(52)
    #liste.findObjPlace(85)
    #-----------------------------
    #Anhand der Stellen
    #liste.insertBeforePlace(5, 10)
    #liste.insertAfterPlace(5, l)
    #liste.deleteAfterPlace(5)
    #liste.deleteBeforePlace(12)
    #liste.findObj(13)
    #liste.sortDesc()
    #liste.sortAsc()
    start=time.time()
    liste.sortAsc()
    ende=time.time()
    print("Dauer Sortierung: "+ str(ende-start))
    #liste.select()
    
    #liste.delete(12)
    #print("Anzahl der List-Elemente: "+ ElementListe.counterListe())"""

    aliste=ArrayListe()
    for i in range(10):
        zufallszahl=randint(1,9)
        aliste.insert(zufallszahl)
    aliste.select()
    aliste.sortDesc()
    print("-----------------")
    aliste.select()
        

if __name__ == "__main__":
    Main()