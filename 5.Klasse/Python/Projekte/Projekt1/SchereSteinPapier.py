import random

def willkommen():
    print("Willkommen bei Schere-Stein-Papier-Echse-Spock")
    print("Möchtest du ein Spiel starten (j, n)")
    ein1=input()
    return ein1

def waehlen(p):
    while(True):
        print("Bitte wählen (SC=Schere, ST=Stein, P=Papier, E=Echse, SP=Spock)")
        p1 = input()
        if(p1 in p):
            return p1
        else:
            print("Wrong value")

def vergleichen(p, c, wert, ergebnis):
    print("Spieler:" + p)
    print("Computer:" + c)
    a = wert[p] - wert[c]
    return ergebnis[a % 5]


def start(zaehlerSC, zaehlerST, zaehlerP, zaehlerE, zaehlerSP, psieg, csieg, zaehler):
    print("Spiel beginnt")
    l = ["SC", "ST", "P", "E", "SP"]
    e = ["Unentschieden", "Spieler hat gewonnen", "Spieler hat gewonnen", "Computer hat gewonnen", "Computer hat gewonnen"]
    wert = {"SC": 4, "ST": 0, "P": 2, "E": 3, "SP": 1}
    p = waehlen(l)
    c = random.choice(l)
    zaehlerSC, zaehlerST, zaehlerP, zaehlerE, zaehlerSP = anzahlElemente(zaehlerSC, zaehlerST, zaehlerP, zaehlerE, zaehlerSP, p, c)
    ergebnis = vergleichen(p, c, wert, e)
    print(ergebnis + "\n")
    if(ergebnis == "Spieler hat gewonnen"):
        psieg = psieg + 1
    elif(ergebnis == "Computer hat gewonnen"):
        csieg = csieg + 1
    zaehler = zaehler + 1
    print("Anzahl der Spiele: " + str(zaehler))
    print("Anzahl Siege Spieler: " + str(psieg))
    print("Anzahl Siege Computer " + str(csieg))
    l=[zaehlerSC, zaehlerST, zaehlerP, zaehlerE, zaehlerSP, psieg, csieg, zaehler]
    return l

def anzahlElemente(zaehlerSC, zaehlerST, zaehlerP, zaehlerE, zaehlerSP, p, c):
    if(p=="SC"): zaehlerSC=zaehlerSC+1
    if(c=="SC"): zaehlerSC=zaehlerSC+1
    if(p=="ST"): zaehlerST=zaehlerST+1
    if(c=="ST"): zaehlerST=zaehlerST+1
    if(p=="P"): zaehlerP=zaehlerP+1
    if(c=="P"): zaehlerP=zaehlerP+1
    if(p=="E"): zaehlerE=zaehlerE+1
    if(c=="E"): zaehlerE=zaehlerE+1
    if(p=="SP"): zaehlerSP=zaehlerSP+1
    if(c=="SP"): zaehlerSP=zaehlerSP+1
    return zaehlerSC, zaehlerST, zaehlerP, zaehlerE, zaehlerSP