import random

def willkommen():
    print("Willkommen bei Schere-Stein-Papier-Echse-Spock")
    print("Möchtest du ein Spiel starten (j, n)")
    ein1=input()
    return ein1

def start():
    print("Spiel beginnt")
    loop=True
    while(loop):
        print("Bitte wählen (SC=Schere, ST=Stein, P=Papier, E=Echse, SP=Spock)")
        ein=input()
        einC=random.randint(0, 4)
        loop=eingabekontrolle(ein, einC)

def spiel(einC, einP):
    if(einC==0 and einP==2):
        print("Computer: Schere / Spieler: Papier")
        print("Computer hat gewonnen")
    if(einC==2 and einP==0):
        print("Computer: Papier / Spieler: Schere")
        print("Spieler hat gewonnen")
    if(einC==2 and einP==1):
        print("Computer: Papier / Spieler: Stein")
        print("Computer hat gewonnen")
    if(einC==1 and einP==2):
        print("Computer: Stein / Spieler: Papier")
        print("Spieler hat gewonnen")
    if(einC==1 and einP==3):
        print("Computer: Stein / Spieler: Echse")
        print("Computer hat gewonnen")
    if(einC==3 and einP==1):
        print("Computer: Echse / Spieler: Stein")
        print("Spieler hat gewonnen")
    if(einC==3 and einP==4):
        print("Computer: Echse / Spieler: Spock")
        print("Computer hat gewonnen")
    if(einC==4 and einP==3):
        print("Computer: Spock / Spieler: Echse")
        print("Spieler hat gewonnen")
    if(einC==4 and einP==0):
        print("Computer: Spock / Spieler: Schere")
        print("Computer hat gewonnen")
    if(einC==0 and einP==4):
        print("Computer: Schere / Spieler: Spock")
        print("Spieler hat gewonnen")
    if(einC==0 and einP==3):
        print("Computer: Schere / Spieler: Echse")
        print("Computer hat gewonnen")
    if(einC==3 and einP==0):
        print("Computer: Echse / Spieler: Schere")
        print("Spieler hat gewonnen")
    if(einC==3 and einP==2):
        print("Computer: Echse / Spieler: Papier")
        print("Computer hat gewonnen")
    if(einC==2 and einP==3):
        print("Computer: Papier / Spieler: Echse")
        print("Spieler hat gewonnen")
    if(einC==2 and einP==4):
        print("Computer: Papier / Spieler: Spock")
        print("Computer hat gewonnen")
    if(einC==4 and einP==2):
        print("Computer: Spock / Spieler: Papier")
        print("Spieler hat gewonnen")
    if(einC==4 and einP==1):
        print("Computer: Spock / Spieler: Stein")
        print("Computer hat gewonnen")
    if(einC==1 and einP==4):
        print("Computer: Stein / Spieler: Spock")
        print("Spieler hat gewonnen")
    if(einC==1 and einP==0):
        print("Computer: Stein / Spieler: Schere")
        print("Computer hat gewonnen")
    if(einC==0 and einP==1):
        print("Computer: Schere / Spieler: Stein")
        print("Spieler hat gewonnen")
    if(einC==0 and einP==0):
        print("Unentschieden(Schere)")
        print("kein Sieger")
    if(einC==1 and einP==1):
        print("Unentschieden(Stein)")
        print("kein Sieger")
    if(einC==2 and einP==2):
        print("Unentschieden(Papier)")
        print("kein Sieger")
    if(einC==3 and einP==3):
        print("Unentschieden(Echse)")
        print("kein Sieger")
    if(einC==4 and einP==4):
        print("Unentschieden(Spock)")
        print("kein Sieger")
    

def eingabekontrolle(eing, einC):
    if(eing!="SC" and eing!="ST" and eing!="P" and eing!="E" and eing!="SP"):
        print("Falsche Eingabe: NUR SC, ST, P, E und SP erlaubt!")
        return True
    if(eing=="SC"):
        einP=Werte.SC
        print(einP)
        print(einC)
        spiel(einC, einP)
        return False
    if(eing=="ST"):
        einP=Werte.ST
        print(einP)
        print(einC)
        spiel(einC, einP)
        return False
    if(eing=="P"):
        einP=Werte.P
        print(einP)
        print(einC)
        spiel(einC, einP)
        return False
    if(eing=="E"):
        einP=Werte.E
        print(einP)
        print(einC)
        spiel(einC, einP)
        return False
    if(eing=="SP"):
        einP=Werte.SP
        print(einP)
        print(einC)
        spiel(einC, einP)
        return False
    
class Werte:
    SC, ST, P, E, SP = range(5)

