class Firma:
    def __init__(self, firmenname, abteilung):
        self.firmenname=firmenname
        self.abteilung=abteilung

    def firma(self):
        return f"Firma: {self.firmenname}"

class Abteilungen:
    counterA=0
    def __init__(self, abteilungsname, gruppenleiter, mitarbeiter):
        self.abteilungsname=abteilungsname
        type(self).counterA+=1
        self.gruppenleiter=gruppenleiter
        self.mitarbeiter=mitarbeiter

    def anzahlAbteilungen():
        return f"{Abteilungen.counterA}"

class Personen:
    def __init__(self, vorname, nachname, age, sex):
        self.vorname=vorname
        self.nachname=nachname
        self.age=age 
        self.sex=sex

class Mitarbeiter(Personen):
    counterM=0
    def __init__(self, vorname, nachname, age, sex):
        super().__init__(vorname, nachname, age, sex)
        type(self).counterM+=1

    def anzahlMitarbeiter():
        return f"{Mitarbeiter.counterM}"

class Gruppenleiter(Personen):
    counterG=0
    def __init__(self, vorname, nachname, age, sex):
        super().__init__(vorname, nachname, age, sex)
        type(self).counterG+=1

    def anzahlGruppenleiter():
        return f"{Gruppenleiter.counterG}"


def hoechsteMitarbeiterstaerke(thoeni):
    counter = 0
    counterM = 0
    abt = None
    for a in range(len(thoeni.abteilung)):
        if thoeni.abteilung[a].gruppenleiter is not None:
                counter+=1
        for b in range(len(thoeni.abteilung[a].mitarbeiter)):
                counter+=1   
        if counterM < counter:
            counterM = counter
            abt = thoeni.abteilung[a]
        counter = 0
    return abt.abteilungsname

def male(thoeni):
    ges = 0
    counterMale = 0
    for a in range(len(thoeni.abteilung)):
        if thoeni.abteilung[a].gruppenleiter.sex=="male":
                counterMale+=1
        ges += 1
        for b in range(len(thoeni.abteilung[a].mitarbeiter)):
            if(thoeni.abteilung[a].mitarbeiter[b].sex=="male"):
                counterMale+=1  
            ges += 1 
    return round((counterMale/ges)*100, 2) 

def female(thoeni):
    ges = 0
    counterFemale = 0
    for a in range(len(thoeni.abteilung)):
        if thoeni.abteilung[a].gruppenleiter.sex=="female":
                counterFemale+=1
        ges += 1
        for b in range(len(thoeni.abteilung[a].mitarbeiter)):
            if(thoeni.abteilung[a].mitarbeiter[b].sex=="female"):
                counterFemale+=1  
            ges += 1 
    return round((counterFemale/ges)*100, 2)  

def Main():
    max=Mitarbeiter("Max", "Klein", 25, "male")
    klaus=Mitarbeiter("Klaus", "Laus", 26, "male")
    tanja=Mitarbeiter("Tanja", "Haus", 52, "female")
    manfred=Mitarbeiter("Manfred", "Weiß", 24, "male")
    tim=Mitarbeiter("Tim", "Gabel", 25, "male")
    martin=Mitarbeiter("martin", "Fuchs", 28, "male")
    tamira=Mitarbeiter("Tamira", "Heiß", 21, "female")
    bruno=Mitarbeiter("Bruno", "Grau", 44, "male")
    john=Mitarbeiter("John", "Cena", 35, "male")
    maria=Mitarbeiter("Maria", "Hausen", 52, "female")
    gerhard=Mitarbeiter("Gerhard", "Weißer", 24, "male")
    arno=Gruppenleiter("Arno", "Bucher", 36, "male")
    franzi=Gruppenleiter("Franzi", "Schmid", 39, "female")
    guenther=Gruppenleiter("Günther", "Dach", 36, "male")
    emma=Gruppenleiter("Emma", "Plan", 39, "female")

    Elektronik=Abteilungen("Elektronik", franzi, [max, martin])
    Maschinenbau=Abteilungen("Maschinenbau", guenther, [tanja, tamira, gerhard, klaus])
    Personal=Abteilungen("Personal", emma, [tim, john])
    Finanzen=Abteilungen("Finanzen", arno, [manfred, bruno, maria])

    thoeni=Firma("Thöni", [Elektronik, Maschinenbau, Personal, Finanzen])

    print("\n"+thoeni.firma())
    print("Anzahl Mitarbeiter: "+ Mitarbeiter.anzahlMitarbeiter())
    print("Anzahl Gruppenleiter: "+ Gruppenleiter.anzahlGruppenleiter())
    print("Anzahl Abteilungen: "+ Abteilungen.anzahlAbteilungen())
    print("größte Mitarbeiterstärke: "+ hoechsteMitarbeiterstaerke(thoeni))
    print("Prozentueller Anteil Männer: "+ str(male(thoeni)))
    print("Prozentueller Anteil Frauen: "+ str(female(thoeni))+"\n")
    
if __name__ == "__main__":
    Main()
