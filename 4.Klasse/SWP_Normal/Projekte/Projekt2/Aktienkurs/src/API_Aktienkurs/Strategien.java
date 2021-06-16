package API_Aktienkurs;

import java.time.LocalDate;

public class Strategien extends Aktien{
	
	int kauf=0;
	int anzahl = 0;
	boolean wert;
	LocalDate eit;
	
	public Strategien(String s, int t, String h, String d, String u, String p) {
		super(s, t, h, d, u, p);
	}
	
	public void strategie_200er(double kapital, int id, int max) {
		counter=0;
		anzahl = 0;
		wert=false;
		
		int groesse=id;
		while (id<=max) {
			strategie_200er_(id);
			if(id==groesse && kauf==0) {
				anzahl=0;
				kauf=0;
				id=strategie_200er_DB(zeitpunkt, type, anzahl, kauf, kapital, id);
			}else {
				if (kaufwert > durchschnitt && kauf == 0 && id > groesse && id < max) {
					anzahl = (int) (kapital / kaufwert);
					kapital = kapital - (anzahl * kaufwert);
					kauf = 1;
					id=strategie_200er_DB(zeitpunkt, type, anzahl, kauf, kapital, id);
				} else {
					if (kaufwert < durchschnitt && kauf == 1 && id > groesse && id < max) {
						kapital = kapital + (anzahl * kaufwert);
						anzahl = 0;
						kauf = 0;
						id=strategie_200er_DB(zeitpunkt, type, anzahl, kauf, kapital, id);
					}else {
						if(id==max) {
							kapital = kapital + (anzahl * kaufwert);
							anzahl=0;
							kauf=0;
							id=strategie_200er_DB(zeitpunkt, type, anzahl, kauf, kapital, id);
						}
					}
				}
			}
			id++;
		}
	}
	
	public void strategie_200er_3(double kapital, int id, int max) {
		counter=0;
		anzahl = 0;
		wert=false;

		int groesse=id;
		while (id<=max) {
			strategie_200er_3_(id);
			if(id==groesse && kauf==0) {
				anzahl=0;
				kauf=0;
				id=strategie_200er_3_DB(zeitpunkt, type, anzahl, kauf, kapital, id);
			}else {
				if (kaufwert > (durchschnitt * 1.03) && kauf == 0 && id > groesse && id < max) {
					anzahl = (int) (kapital / kaufwert);
					kapital = kapital - (anzahl * kaufwert);
					kauf = 1;
					id=strategie_200er_3_DB(zeitpunkt, type, anzahl, kauf, kapital, id);
				} else {
					if (kaufwert < (durchschnitt * 0.97) && kauf == 1 && id > groesse && id < max) {
						kapital = kapital + (anzahl * kaufwert);
						anzahl = 0;
						kauf = 0;
						id=strategie_200er_3_DB(zeitpunkt, type, anzahl, kauf, kapital, id);
					}else {
						if(id==max) {
							kapital = kapital + (anzahl * kaufwert);
							anzahl=0;
							kauf=0;
							id=strategie_200er_3_DB(zeitpunkt, type, anzahl, kauf, kapital, id);
						}
					} 
				}
			}
			id++;
		}
	}
	
	public void strategie_buyandhold(double kapital, int id, int max) {
		counter=0;
		anzahl = 0;
		wert=false;
		
		int groesse=id;
		while (id<=max) {
			strategie_buyandhold_(id);
			if(id==groesse && kauf==0) {
				kauf=0;
				id=strategie_buyandhold_DB(zeitpunkt, type, anzahl, kauf, kapital, id);
				id++;
			}else {
				if (id > groesse && id < max && kauf == 0) {
					anzahl = (int) (kapital / kaufwert);
					kapital = kapital - (anzahl * kaufwert);
					kauf = 1;
					id=strategie_buyandhold_DB(zeitpunkt, type, anzahl, kauf, kapital, id);
					id=max;
				} else {
					if (id == max) {
						kapital = kapital + (anzahl * kaufwert);
						anzahl = 0;
						kauf = 0;
						id=strategie_buyandhold_DB(zeitpunkt, type, anzahl, kauf, kapital, id);
						id++;
					}
				} 
			}
		}
	}

	public void strategie_200er_rohwerte(double kapital, int id, int max) {
		counter=0;
		anzahl=0;
		wert=false;
		split=0;
		
		int groesse=id;
		while (id<=max) {
			strategie_200er_rohwerte_(id);
			if(split>1 && id > groesse && id < max) {
				anzahl = (int)(anzahl*split);
				id=strategie_200er_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
			}else {
				if(id==groesse && kauf==0) {
					anzahl=0;
					kauf=0;
					id=strategie_200er_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
				}else {
					if (kaufwert > durchschnitt && kauf == 0 && id > groesse && id < max) {
						anzahl = (int) (kapital / kaufwert);
						kapital = kapital - (anzahl * kaufwert);
						kauf = 1;
						id=strategie_200er_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
					} else {
						if (kaufwert < durchschnitt && kauf == 1 && id > groesse && id < max) {
							kapital = kapital + (anzahl * kaufwert);
							anzahl = 0;
							kauf = 0;
							id=strategie_200er_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
						}else {
							if(id==max) {
								kapital = kapital + (anzahl * kaufwert);
								anzahl=0;
								kauf=0;
								id=strategie_200er_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
							}
						}
					}
				}
			}
			id++;
		}
	}
	
	public void strategie_200er_3_rohwerte(double kapital, int id, int max) {
		counter=0;
		anzahl=0;
		wert=false;
		split=0;
		
		int groesse=id;
		while (id<=max) {
			strategie_200er_3_rohwerte_(id);
			if(split>1 && id > groesse && id < max) {
				anzahl = (int)(anzahl*split);
				id=strategie_200er_3_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
			}else {
				if(id==groesse && kauf==0) {
					anzahl=0;
					kauf=0;
					id=strategie_200er_3_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
				}else {
					if (kaufwert > (durchschnitt * 1.03) && kauf == 0 && id > groesse && id < max) {
						anzahl = (int) (kapital / kaufwert);
						kapital = kapital - (anzahl * kaufwert);
						kauf = 1;
						id=strategie_200er_3_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
					} else {
						if (kaufwert < (durchschnitt * 0.97) && kauf == 1 && id > groesse && id < max) {
							kapital = kapital + (anzahl * kaufwert);
							anzahl = 0;
							kauf = 0;
							id=strategie_200er_3_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
						}else {
							if(id==max) {
								kapital = kapital + (anzahl * kaufwert);
								anzahl=0;
								kauf=0;
									id=strategie_200er_3_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
							}
						}
					} 
				}
			}
			id++;
		}
	}
	
	public void strategie_buyandhold_rohwerte(double kapital, int id, int max) {
		counter=0;
		anzahl=0;
		wert=false;
		split=0;
		
		int groesse=id;
		while (id<=max) {
			strategie_buyandhold_rohwerte_(id);
			if(split>1 && id > groesse && id < max) {
				anzahl = (int)(anzahl*split);
				id=strategie_buyandhold_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
			}else {
				if(id==groesse && kauf==0) {
					kauf=0;
					id=strategie_buyandhold_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
				}else {
					if (id==groesse+1 && kauf == 0) {
						anzahl = (int) (kapital / kaufwert);
						kapital = kapital - (anzahl * kaufwert);
						kauf = 1;
						id=strategie_buyandhold_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
					} else {
						if (id == max) {
							kapital = kapital + (anzahl * kaufwert);
							anzahl = 0;
							kauf = 0;
							id=strategie_buyandhold_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
						}
					}
				}
			}
			id++;
		}
	}
}
