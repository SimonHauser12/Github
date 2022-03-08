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
        return len(self.list)

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
