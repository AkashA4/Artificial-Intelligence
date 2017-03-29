package missionairescannibales;

import java.util.*;

public class MissionairesCannibales {

    public static class State {

        int missionaires;
        int cannibales;
        boolean direction;

        public State(int m, int c, boolean d) {
            this.missionaires = m;
            this.cannibales = c;
            this.direction = d;
        }
        
        public ArrayList<State> next() {
            ArrayList<State> etats = new ArrayList<>();
            if (direction == true) {
                for (int dm = 0; dm <= missionaires; dm++) {
                    for (int dc = 0; dc <= cannibales; dc++) {
                        if ((missionaires - dm == 0) || (3 - missionaires + dm == 0)) {
                            if (dc + dm > 0 && dc + dm <= 2) {
                                State etat = new State(missionaires - dm, cannibales - dc, !direction);
                                etats.add(etat);
                            }
                        }
                        else{
                            if (dc + dm >= 1 && dc + dm <= 2 && missionaires - dm >= cannibales - dc && 3 - (missionaires - dm) >= 3 - (cannibales - dc)) {
                                State etat = new State(missionaires - dm, cannibales - dc, !direction);
                                etats.add(etat);
                            }
                       }
                    }
                }
            } else {
                for (int dm = 0; dm <= 3 - missionaires; dm++) {
                    for (int dc = 0; dc <= 3 - cannibales; dc++) {
                        if (3 - missionaires - dm == 0) {
                            if (dc + dm > 0 && dc + dm <= 2 && missionaires + dm >= cannibales + dc) {
                                State etat = new State(missionaires + dm, cannibales + dc, !direction);
                                etats.add(etat);
                            }
                        }
                        else if(missionaires==0 && dc>=1 && dm==0){                            
                            State etat=new State(missionaires,cannibales+dc,!direction);
                            etats.add(etat);
                        }
                        else{
                            if (dc + dm > 0 && dc + dm <= 2 && missionaires + dm >= cannibales + dc && 3 - missionaires - dm >= 3 - cannibales - dc) {
                                State etat = new State(missionaires + dm, cannibales + dc, !direction);
                                etats.add(etat);
                            } 
                        }
                    }
                }
            }
            return etats;
        }

        public static State SimpleReflexAgent(ArrayList<State> e) {
            State f = new State(0, 0, false);
            Random rand = new Random();
            if (e.size()==0) {
                return f;
            }
            int n = rand.nextInt(e.size());
            State aleatoire = e.get(n);
            if (aleatoire.missionaires==0 && aleatoire.cannibales==0 && aleatoire.direction==false) {
                System.out.println(aleatoire.missionaires + " " + aleatoire.cannibales + " " + aleatoire.direction);
                System.out.println("Batal men biladi");
            }
            else{
                System.out.println(aleatoire.missionaires + " " + aleatoire.cannibales + " " + aleatoire.direction);
                SimpleReflexAgent(aleatoire.next());
            }
            return f;
        }

        public static void main(String[] args) {
            State e1 = new State(3, 3, true);
            ArrayList<State> etats = e1.next();
            State f=new State(0,0,false);
            SimpleReflexAgent(etats);
        }
    }
}
