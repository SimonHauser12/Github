class ArrayListe:

    def __init__(self):
        self.list=[]

    def insert(self, obj):                              # Element am Ende hinzufügen
        self.list.append(obj)

    def insertAfterObj(self, obj, newObj):              # Element nach einem bestimmten Objekt hinzufügen
        counter=0
        for i in range(0, len(self.liste)):
            if self.liste[i] == obj:
                self.list.insert(i+1, newObj)
                counter+=1
        if(counter==0):
            print("Element "+str(obj)+" nicht in Liste enthalten")
        
    def insertBeforeObj(self, obj, newObj):             #  Element vor einem bestimmten Objekt hinzufügen
        counter=0
        for i in range(0, len(self.liste)):
            if self.liste[i] == obj:
                self.list.insert(i-1, newObj)
                counter+=1
        if(counter==0):
            print("Element "+str(obj)+" nicht in Liste enthalten")
          
    def deleteAfterObj(self, obj):                      # Element nach einem bestimmten Objekt löschen
        counter=0
        for i in range(0, len(self.liste)):
            if self.liste[i] == obj:
                self.list.pop(i+1)
                counter+=1
        if(counter==0):
            print("Element "+str(obj)+" nicht in Liste enthalten")

    def deleteBeforeObj(self, obj):                      # Element vor einem bestimmten Objekt löschen
        counter=0
        for i in range(0, len(self.liste)):
            if self.liste[i] == obj:
                self.list.pop(i-1)
                counter+=1
        if(counter==0):
            print("Element "+str(obj)+" nicht in Liste enthalten")

    def insertAfterPlace(self, pl, newObj):             # Element nach einer bestimmten Stelle hinzufügen
        if(pl+1<len(self.list) and pl>=0):
            self.list.insert(pl+1, newObj)
        elif():
            print("Nur zwischen 0 und "+str(len(self.list)-2)+" wählen")
        
    def insertBeforePlace(self, pl, newObj):            # Element vor einer bestimmten Stelle hinzufügen
        if(pl<len(self.list) and pl-1>=0):
            self.list.insert(pl-1, newObj)
        elif():
            print("Nur zwischen 1 und "+str(len(self.list)-1)+" wählen")
          
    def deleteAfterPlace(self, pl):                         # Element nach einer bestimmten Stelle löschen
        if(pl+1<len(self.list) and pl>=0):
            self.list.pop(pl+1)
        elif():
            print("Nur zwischen 0 und "+str(len(self.list)-2)+" wählen")

    def deleteBeforePlace(self, pl):                        # Element vor einer bestimmten Stelle löschen
        if(pl<len(self.list) and pl-1>=0):
            self.list.pop(pl-1)
        elif():
            print("Nur zwischen 1 und "+str(len(self.list)-1)+" wählen")

    def findbyPlace(self, pl):                                 # Element an einer bestimmten Stelle finden
        print("An der Stelle "+str(pl)+" befindet sich das Element "+ str(self.list[pl]))

    def findbyObj(self, obj):                               # Stelle eines bestimmten Elements finden
        for i in range(0, len(self.liste)):
            if self.liste[i] == obj:
                print("Das Element "+str(obj)+" befindet sich an der Stelle "+ str(i))

    def printErgebnisse(self):                              # alle Elemente der Arrayliste ausgeben
        for i in self.list:
            print("|"+str(i)+"|")
    
    def printErgebnisseReverse(self):                       # alle Elemente der Arrayliste verkehrt herum ausgeben
        for i in reversed(self.list):
            print("|"+str(i)+"|")

    def length(self):                                       # Länge der Arrayliste ausgeben
        return len(self.list)

    def sortAsc(self):                                      # Aufsteigend sortieren
        j = -1
        key = self.list[0]
        for i in range(1, len(self.list)):
            key = self.list[i]
            j = i-1
            while(j >= 0 and self.list[j] > key):
                self.list[j+1] = self.list[j]
                j -= 1
            self.list[j+1] = key

    def sortDesc(self):                                     # Absteigend sortieren
        j = -1
        key = self.list[0]
        for i in range(1, len(self.list)):
            key = self.list[i]
            j = i-1
            while(j >= 0 and self.list[j] < key):
                self.list[j+1] = self.list[j]
                j -= 1
            self.list[j+1] = key
