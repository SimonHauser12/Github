from random import randint

class ElementListe:
    counter=0
    nextEl=0
    obj=0
    def __init__(self, obj):
        self.obj=obj
        self.nextEl=None
        type(self).counter+=1
    
    def __str__(self):
        return self.obj
    
    def setnextElement(self, nextEl):
        self.nextEl=nextEl
    
    def getnextElement(self):
        return self.nextEl
    
    def counterListe():
        return f"{ElementListe.counter}"

class VerketteListe:
    start=randint(10,99)
    startEl=ElementListe(start)

    def getLastElement(self):
        le=self.startEl
        while(le.getnextElement()!=None):
            le=le.getnextElement()
        return le

    def addNewEl(self, obj):
        newEl=ElementListe(obj)
        lastEl=self.getLastElement()
        lastEl.nextEl=newEl

    def select(self):
        le=self.startEl
        while(le!=None):
            print("| " + str(le.__str__()) + " |")
            le=le.getnextElement()

def Main():
    liste=VerketteListe()
    for i in range(11):
        zufallszahl=randint(10,100)
        #print(zufallszahl)
        liste.addNewEl(zufallszahl)
    liste.select()
    print("Anzahl der List-Elemente: "+ ElementListe.counterListe())
        

if __name__ == "__main__":
    Main()