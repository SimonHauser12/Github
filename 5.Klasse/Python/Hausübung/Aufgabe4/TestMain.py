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
    
    def setElement(self, el):
        self.obj=el

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

    def insert(self, obj):
        newEl=ElementListe(obj)
        lastEl=self.getLastElement()
        lastEl.nextEl=newEl

    def select(self):
        le=self.startEl
        while(le!=None):
            print("| " + str(le.__str__()) + " |")
            le=le.getnextElement()

    def delete(self, obj):
        counter=0
        here=False
        le=self.startEl
        while(le!=None):
            if(le.__str__()==obj):
                le.setElement(le.getnextElement())
                here=True
                ElementListe.counter-=1
                print("Element gelöscht!")
            elif(le.getnextElement().__str__()==obj):
                nle=le.getnextElement()
                le.setnextElement(nle.getnextElement())
                ElementListe.counter-=1
                here=True
                print("Element gelöscht!")
            le=le.getnextElement()  
        if(here==False):
            print("Element nicht vorhanden!")    

def Main():
    liste=VerketteListe()
    for i in range(11):
        zufallszahl=randint(10,99)
        liste.insert(zufallszahl)
    liste.select()
    print("Anzahl der List-Elemente: "+ ElementListe.counterListe())
    liste.delete(12)
    liste.select()
    print("Anzahl der List-Elemente: "+ ElementListe.counterListe())
        

if __name__ == "__main__":
    Main()