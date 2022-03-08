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
        self.endEl=None

    def getLastElement(self):               #sucht das letzte Objekt der Liste
        le=self.startEl
        while(le.nextEl!=None):
            le=le.nextEl
        return le

    def getElement(self, obj):              #sucht ein bestimmtes Objekt
        le=self.startEl
        while(le.obj!=obj and le.nextEl!=None):
            le=le.nextEl
        return le

    def findElementPlace(self, obj):        #sucht die Stelle des jeweiligen Objekts
        le=self.startEl
        counter=0
        while(le.obj!=obj and le.nextEl!=None):
            le=le.nextEl
            counter+=1
        c=[le, counter]
        return c

    def findElement(self, pl):              #sucht das Objekt an einer definerten Stelle
        le=self.startEl
        for i in range(pl):
            le=le.nextEl
        return le
    
    def insertAfter(self, le, newObj):      #Tauschoperation beim Einfügen eines neuen Objekts hinter einem vorhandenen Objekt
        if(le.nextEl!=None):
            nle=le.nextEl

            newEl=ElementListe(newObj)
            newEl.prevEl=le
            le.nextEl=newEl
            newEl.nextEl=nle
            nle.prevEl=newEl  
        elif(le.nextEl==None):
            nle=None

            newEl=ElementListe(newObj)
            newEl.prevEl=le
            le.nextEl=newEl
            newEl.nextEl=nle
            self.endEl=newEl

    def insertBefore(self, le, newObj):     #Tauschoperation beim Einfügen eines neuen Objekts vor einem vorhandenen Objekt
        if(le.prevEl!=None):
            ple=le.prevEl

            newEl=ElementListe(newObj)
            le.prevEl=newEl
            newEl.nextEl=le
            ple.nextEl=newEl
            newEl.prevEl=ple  
        elif(le.prevEl==None):
            ple=None

            newEl=ElementListe(newObj)
            le.prevEl=newEl
            newEl.nextEl=le
            newEl.prevEl=ple  
            self.startEl=newEl    

    def deleteAfter(self, le):              #Tauschoperation beim Löschen eines Objekts hinter einem vorhandenen Objekt
        dle=le.nextEl
        if(dle.nextEl!=None):
            ndle=dle.nextEl

            le.nextEl=ndle
            ndle.prevEl=le
        elif(dle.nextEl==None):
            ndle=None

            le.nextEl=ndle
            self.endEl=le

        ElementListe.counter-=1
    
    def deleteBefore(self, le):             #Tauschoperation beim Löschen eines neuen Objekts vor einem vorhandenen Objekt
        dle=le.prevEl
        if(dle.prevEl!=None):
            pdle=dle.prevEl

            le.prevEl=pdle
            pdle.nextEl=le
        elif(dle.prevEl==None):
            pdle=None

            le.prevEl=pdle
            self.startEl=le

        ElementListe.counter-=1

    def insert(self, obj):                 #einfügen eines neuen Objekts an letzter Stelle der Liste
        if(self.startEl==None):
            self.startEl=ElementListe(obj)
        elif(self.startEl!=None):
            newEl=ElementListe(obj)
            lastEl=self.getLastElement()
            newEl.prevEl=lastEl
            lastEl.nextEl=newEl
            self.endEl=newEl  

    def insertObj(self, obj, newObj, art):  #einfügen eines neuen Objekts vor oder nach einem vorhandenen Objekt
        le=self.getElement(obj)
        if(le.obj==obj):
            if(art=="after"):
                self.insertAfter(le, newObj)
            elif(art=="before"):
                self.insertBefore(le, newObj)
            elif(art!="before" and art!="after"):
                print("Art ist unbekannt!")
        elif(le.obj!=obj):
            print("Element "+str(obj)+" ist nicht in der Liste enthalten")
            print(str(newObj)+" konnte nicht hinzugefügt werden")   

    def deleteObj(self, obj, art):          #löschen eines Objekts vor oder nach einem vorhandenen Objekt
        le=self.getElement(obj)
        if(le.obj==obj):
            if(le.nextEl!=None):
                if(art=="after"):
                    self.deleteAfter(le)
                elif(art=="before"):
                     self.deleteBefore(le)
                elif(art!="before" and art!="after"):
                    print("Art ist unbekannt!")
            elif(le.nextEl==None):
                print("Es ist kein Element nach "+str(obj)+" vorhanden, kein Delete durchführbar")        
        elif(le.obj!=obj):
            print("Element "+str(obj)+" ist nicht in der Liste enthalten")
            print("Kein Delete konnte durchgeführt werden")       

    def findObjPlace(self, obj):                    #suchen der Stelle eines bestimmten Objekts
        c=self.findElementPlace(obj)
        le=c[0]
        s=c[1]
        if(le.obj==obj):
            if(le.prevEl!=None and le.nextEl!=None):
                print("Das Element "+str(obj)+" befindet sich an der "+str(s)+". Stelle")
                print("Vorheriges Element: "+str(le.prevEl.obj))
                print("Nachfolgendes Element: "+str(le.nextEl.obj))
            elif(le.prevEl==None):
                print("Das Element "+str(obj)+" befindet sich an der "+str(s)+". Stelle")
                print("Vorheriges Element nicht vorhanden (erstes Element)")
                print("Nachfolgendes Element: "+str(le.nextEl.obj))
            elif(le.nextEl==None):
                print("Das Element "+str(obj)+" befindet sich an der "+str(s)+". Stelle")
                print("Vorheriges Element: "+str(le.prevEl.obj))
                print("Nachfolgendes Element nicht vorhanden (letztes Element)")      
        elif(le.obj!=obj):
            print("Element "+str(obj)+" ist nicht in der Liste enthalten")  

    def insertPlace(self, pl, newObj, art):         #einfügen eines neuen Objekts vor oder nach einer bestimmten Stelle
        if(pl>=ElementListe.counter or pl<0):
            print("Es wurde eine falsche Zahl gewählt")
            print("Bitte zwischen 0-"+str(ElementListe.counter-1)+" wählen")
        elif(pl<ElementListe.counter):
            le=self.findElement(pl)
            if(art=="after"):
                self.insertAfter(le, newObj)
            elif(art=="before"):
                self.insertBefore(le, newObj)
            elif(art!="before" and art!="after"):
                print("Art ist unbekannt!")

    def deletePlace(self, pl, art):                 #löschen eines Objekts vor oder nach einer bestimmten Stelle
        if(pl>=ElementListe.counter or pl<0):
            print("Es wurde eine falsche Zahl gewählt")
            print("Bitte zwischen 0-"+str(ElementListe.counter-1)+" wählen")
        elif(pl<ElementListe.counter):
            le=self.findElement(pl)
            if(le.nextEl!=None):
                if(art=="after"):
                    self.deleteAfter(le)
                elif(art=="before"):
                    self.deleteBefore(le)
                elif(art!="before" and art!="after"):
                    print("Art ist unbekannt!")
            elif(le.nextEl==None):
                print("Es ist kein Element nach der Stelle "+str(pl)+" vorhanden, kein Delete durchführbar")   

    def findObj(self, pl):                          #finden des Objekts an einer bestimmten Stelle
        if(pl>=ElementListe.counter or pl<0):
            print("Es wurde eine falsche Zahl gewählt")
            print("Bitte zwischen 0-"+str(ElementListe.counter-1)+" wählen")
        elif(pl<ElementListe.counter):
            le=self.findElement(pl)
            if(le.prevEl!=None and le.nextEl!=None):
                print("An der Stelle "+str(pl)+". befindet sich das Element "+str(le.obj))
                print("Vorheriges Element: "+str(le.prevEl.obj))
                print("Nachfolgendes Element: "+str(le.nextEl.obj))
            elif(le.prevEl==None):
                print("An der Stelle "+str(pl)+". befindet sich das Element "+str(le.obj))
                print("Vorheriges Element nicht vorhanden (erstes Element)")
                print("Nachfolgendes Element: "+str(le.nextEl.obj))
            elif(le.nextEl==None):
                print("An der Stelle "+str(pl)+". befindet sich das Element "+str(le.obj))
                print("Vorheriges Element: "+str(le.prevEl.obj))
                print("Nachfolgendes Element nicht vorhanden (letztes Element)")

    def select(self):                           #ausgabe der verketteten Liste
        print("Normal")
        le=self.startEl
        while(le!=None):
            print("| " + str(le.obj) + " |")
            le=le.nextEl
    
    def selectReverse(self):                    #ausgabe der ungedrehten verketteten Liste
        print("Rückwerts")
        rle=self.endEl
        while(rle!=None):
            print("| " + str(rle.obj) + " |")
            rle=rle.prevEl

    """def sortAsc(self):                          #Aufsteigend Sortieren
        j = -1
        key = self.findElement(0)
        for i in range(1, int(ElementListe.counterListe())):
            key = self.findElement(i).__str__()
            j = i-1
            while(j >= 0 and self.findElement(j).__str__() > key):
                l=self.findElement(j).__str__()
                self.deletePlace(j, "after")
                self.insertPlace(j, l, "after")
                j -= 1
            if(j>=0):
                self.deletePlace(j, "after")
                self.insertPlace(j, key, "after")
            elif(j<0):
                self.deletePlace(1, "before")
                self.insertPlace(1, key, "before")
                
    def sortDesc(self):                                 #Absteigend Sortieren
        j = -1
        key = self.findElement(0)
        for i in range(1, int(ElementListe.counterListe())):
            key = self.findElement(i).__str__()
            j = i-1
            while(j >= 0 and self.findElement(j).__str__() < key):
                l=self.findElement(j).__str__()
                self.deletePlace(j, "after")
                self.insertPlace(j, l, "after")
                j -= 1
            if(j>=0):
                self.deletePlace(j, "after")
                self.insertPlace(j, key, "after")
            elif(j<0):
                self.deletePlace(1, "before")
                self.insertPlace(1, key, "before")"""
    
    def sortAsc(self):                          #Aufsteigend Sortieren
        if(self.startEl==None):
            return
        else:
            key=self.startEl
            while (key.nextEl!=None):
                i=key.nextEl
                while (i!=None):
                    if(key.obj>i.obj):
                        j=key.obj
                        key.obj=i.obj
                        i.obj=j
                    i=i.nextEl
                key=key.nextEl

    def sortDesc(self):                          #Absteigend Sortieren
        if(self.startEl==None):
            return
        else:
            key=self.startEl
            while (key.nextEl!=None):
                i=key.nextEl
                while (i!=None):
                    if(key.obj<i.obj):
                        j=key.obj
                        key.obj=i.obj
                        i.obj=j
                    i=i.nextEl
                key=key.nextEl
