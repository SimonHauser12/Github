class Firma:
    def __init__(self, firmenname):
        self.firmenname=firmenname

    def firma(self):
        return f"Firma: {self.firmenname}"

class Abteilungen(Firma):
    counterA=0
    abteilungElektronik=0
    abteilungFinanzen=0
    abteilungMaschinenbau=0
    abteilungPersonal=0
    def __init__(self, firmenname, abteilungsname):
        super().__init__(firmenname)
        self.abteilungsname=abteilungsname
        type(self).counterA+=1

    def anzahlAbteilungen():
        return f"{Abteilungen.counterA}"

class Personen(Abteilungen):
    counterMale=0
    counterFemale=0
    def __init__(self, firmenname, abteilungsname, vorname, nachname, age, sex):
        super().__init__(firmenname, abteilungsname)
        self.vorname=vorname
        self.nachname=nachname
        self.age=age 
        self.sex=sex

class Mitarbeiter(Personen):
    counterM=0
    def __init__(self, firmenname, abteilungsname, vorname, nachname, age, sex):
        super().__init__(firmenname, abteilungsname, vorname, nachname, age, sex)
        type(self).counterM+=1
        self.abteilung(abteilungsname)
        self.sex_(sex)
    
    def abteilung(self, abteilungsname):
        if(abteilungsname=="Elektronik"):
            type(self).abteilungElektronik+=1
        if(abteilungsname=="Maschinenbau"):
            type(self).abteilungMaschinenbau+=1
        if(abteilungsname=="Personal"):
            type(self).abteilungPersonal+=1
        if(abteilungsname=="Finanzen"):
            type(self).abteilungFinanzen+=1

    def sex_(self, sex):
        if(sex=="male"):
            type(self).counterMale+=1
        elif(sex=="female"):
            type(self).counterFemale+=1

    def anzahlMitarbeiter():
        return f"{Mitarbeiter.counterM}"

class Gruppenleiter(Personen):
    counterG=0
    def __init__(self, firmenname, abteilungsname, vorname, nachname, age, sex):
        super().__init__(firmenname, abteilungsname, vorname, nachname, age, sex)
        type(self).counterG+=1
        self.abteilung(abteilungsname)
        self.sex_(sex)

    def abteilung(self, abteilungsname):
        if(abteilungsname=="Elektronik"):
            type(self).abteilungElektronik+=1
        if(abteilungsname=="Maschinenbau"):
            type(self).abteilungMaschinenbau+=1
        if(abteilungsname=="Personal"):
            type(self).abteilungPersonal+=1
        if(abteilungsname=="Finanzen"):
            type(self).abteilungFinanzen+=1

    def sex_(self, sex):
        if(sex=="male"):
            type(self).counterMale+=1
        elif(sex=="female"):
            type(self).counterFemale+=1

    def anzahlGruppenleiter():
        return f"{Gruppenleiter.counterG}"

def maleProzent():
    max=Gruppenleiter.counterMale+Mitarbeiter.counterMale+Gruppenleiter.counterFemale+Mitarbeiter.counterFemale
    male=Gruppenleiter.counterMale+Mitarbeiter.counterMale
    return f"{round((male/max)*100, 2)}%"

def femaleProzent():
    max=Gruppenleiter.counterMale+Mitarbeiter.counterMale+Gruppenleiter.counterFemale+Mitarbeiter.counterFemale
    female=Gruppenleiter.counterFemale+Mitarbeiter.counterFemale
    return f"{round((female/max)*100, 2)}%"

def hoechsteMitarbeiterstaerke():
    e=Gruppenleiter.abteilungElektronik+Mitarbeiter.abteilungElektronik
    f=Gruppenleiter.abteilungFinanzen+Mitarbeiter.abteilungFinanzen
    p=Gruppenleiter.abteilungPersonal+Mitarbeiter.abteilungPersonal
    m=Gruppenleiter.abteilungMaschinenbau+Mitarbeiter.abteilungMaschinenbau
    l=[e, f, p, m]
    max(l)
    ergebnis=""
    if(e==max(l)): ergebnis+=" | Elektronik | "
    if(f==max(l)): ergebnis+=" | Finanzen | "
    if(p==max(l)): ergebnis+=" | Personal | "
    if(m==max(l)): ergebnis+=" | Maschinenbau | "
    
    return ergebnis

def Main():
    thoeni=Firma("Thöni")

    Elektronik=Abteilungen("Thöni", "Elektronik")
    Maschinenbau=Abteilungen("Thöni", "Maschinenbau")
    Personal=Abteilungen("Thöni", "Personal")
    Finanzen=Abteilungen("Thöni", "Finanzen")

    max=Mitarbeiter("Thöni", "Elektronik", "Max", "Klein", 25, "male")
    tanja=Mitarbeiter("Thöni", "Maschinenbau", "Tanja", "Haus", 52, "female")
    manfred=Mitarbeiter("Thöni", "Finanzen", "Manfred", "Weiß", 24, "male")
    tim=Mitarbeiter("Thöni", "Personal", "Tim", "Gabel", 25, "male")
    martin=Mitarbeiter("Thöni", "Elektronik", "martin", "Fuchs", 28, "male")
    tamira=Mitarbeiter("Thöni", "Maschinenbau", "Tamira", "Heiß", 21, "female")
    bruno=Mitarbeiter("Thöni", "Finanzen", "Bruno", "Grau", 44, "male")
    john=Mitarbeiter("Thöni", "Personal", "John", "Cena", 35, "male")
    maria=Mitarbeiter("Thöni", "Finanzen", "Maria", "Hausen", 52, "female")
    gerhard=Mitarbeiter("Thöni", "Maschinenbau", "Gerhard", "Weißer", 24, "male")
    arno=Gruppenleiter("Thöni", "Elektronik", "Arno", "Bucher", 36, "male")
    franzi=Gruppenleiter("Thöni", "Maschinenbau", "Franzi", "Schmid", 39, "female")
    guenther=Gruppenleiter("Thöni", "Personal", "Günther", "Dach", 36, "male")
    emma=Gruppenleiter("Thöni", "Finanzen", "Emma", "Plan", 39, "female")

    print("\n"+thoeni.firma())
    print("Anzahl Mitarbeiter: "+ Mitarbeiter.anzahlMitarbeiter())
    print("Anzahl Gruppenleiter: "+ Gruppenleiter.anzahlGruppenleiter())
    print("Anzahl Abteilungen: "+ Abteilungen.anzahlAbteilungen())
    print("größte Mitarbeiterstärke: "+ hoechsteMitarbeiterstaerke())
    print("Prozentueller Anteil Männer: "+ maleProzent())
    print("Prozentueller Anteil Frauen: "+ femaleProzent()+"\n")
    
if __name__ == "__main__":
    Main()
