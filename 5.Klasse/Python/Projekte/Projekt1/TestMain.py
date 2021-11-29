import mysql.connector
import SchereSteinPapier as SSP
import Datenbank as db


def kontrolle(ein):
    if(ein=="j"):
        return False
    if(ein=="n"):
        return False
    if(ein!="j" and ein!="n"):
        print("Falsche Eingabe: NUR j und n erlaubt!")
        return True
    
def erneut():
    r=True
    while(r):
        print("Erneut Spielen? (j, n)")
        ein=input()
        r=kontrolle(ein)
        if(ein=="n"):
            return False
        if(ein=="j"):
            return True

def menu(connection):
    r=True
    while(r):
        print("Spieldaten anzeigen? (j, n)")
        ein=input()
        r=kontrolle(ein)
    if(ein=="j"):
        db.select(connection)


def Main():
    connection=db.connectionOpen()
    psieg=0
    csieg=0
    zaehler=0
    zaehlerSC=0
    zaehlerST=0
    zaehlerP=0
    zaehlerE=0
    zaehlerSP=0
    loop=True
    while(loop):
        print("Willkommen bei Schere-Stein-Papier-Echse-Spock")
        menu(connection)
        ein1=SSP.willkommen()
        loop=kontrolle(ein1)
    if(ein1=="j"):
        l=True
        while(l):
            a = SSP.start(zaehlerSC, zaehlerST, zaehlerP, zaehlerE, zaehlerSP, psieg, csieg, zaehler)
            zaehlerSC=a[0]
            zaehlerST=a[1]
            zaehlerP=a[2]
            zaehlerE=a[3]
            zaehlerSP=a[4]
            psieg=a[5]
            csieg=a[6]
            zaehler=a[7]
            l=erneut()
    if(ein1=="n"):
        print("Spiel wird beendet!")
    db.insert(connection, zaehlerSC, zaehlerST, zaehlerP, zaehlerE, zaehlerSP, psieg, csieg, zaehler)
    menu(connection)
    db.connectionClose(connection)
    print("Spiel beendet!")

if __name__ == "__main__":
    Main()