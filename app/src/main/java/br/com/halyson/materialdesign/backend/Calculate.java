package br.com.halyson.materialdesign.backend;

/**
 * Created by Ivan on 14.6.2015..
 */

public class Calculate {
    private int player_domino_1;
    private int player_domino_2;
    private int player_domino_3;
    private int player_domino_4;
    private int player_hand_1;
    private int player_hand_2;

    private int dealer_domino_1;
    private int dealer_domino_2;
    private int dealer_domino_3;
    private int dealer_domino_4;
    private int dealer_hand_1;
    private int dealer_hand_2;

    private int[] values = new int[32];
    private int [] rang = new int [32];

    //konstruktor
    public Calculate () {
        values[0] = 3;
        values[1] = 6;
        values[2] = 12;
        values[3] = 12;
        values[4] = 2;
        values[5] = 2;
        values[6] = 8;
        values[7] = 8;
        values[8] = 4;
        values[9] = 4;
        values[10] = 10;
        values[11] = 10;
        values[12] = 6;
        values[13] = 6;
        values[14] = 4;
        values[15] = 4;
        values[16] = 11;
        values[17] = 11;
        values[18] = 10;
        values[19] = 10;
        values[20] = 7;
        values[21] = 7;
        values[22] = 6;
        values[23] = 6;
        values[24] = 9;
        values[25] = 9;
        values[26] = 8;
        values[27] = 8;
        values[28] = 7;
        values[29] = 7;
        values[30] = 5;
        values[31] = 5;
        rang[0] = 33;
        rang[1] = 33;
        rang[2] = 2;
        rang[3] = 2;
        rang[4] = 3;
        rang[5] = 3;
        rang[6] = 4;
        rang[7] = 4;
        rang[8] = 5;
        rang[9] = 5;
        rang[10] = 6;
        rang[11] = 6;
        rang[12] = 7;
        rang[13] = 7;
        rang[14] = 8;
        rang[15] = 8;
        rang[16] = 9;
        rang[17] = 9;
        rang[18] = 10;
        rang[19] = 10;
        rang[20] = 11;
        rang[21] = 11;
        rang[22] = 12;
        rang[23] = 12;
        rang[24] = 13;
        rang[25] = 13;
        rang[26] = 14;
        rang[27] = 14;
        rang[28] = 15;
        rang[29] = 15;
        rang[30] = 16;
        rang[31] = 16;

    }

    //primi korisnikove domine
    public void setPlayerHand(int dom1, int dom2, int dom3, int dom4) {
        this.player_domino_1 = dom1;
        this.player_domino_2 = dom2;
        this.player_domino_3 = dom3;
        this.player_domino_4 = dom4;
    }

    //primi dealerove domine
    public void setDealerHand(int dom1, int dom2, int dom3, int dom4) {
        this.dealer_domino_1 = dom1;
        this.dealer_domino_2 = dom2;
        this.dealer_domino_3 = dom3;
        this.dealer_domino_4 = dom4;
    }

    //vrati pobjednika
    public int[] getWinner () {
        calculatePlayerHand();
        HouseWay2 h = new HouseWay2();
        h.Calculate();
        int [] dealer_dominos= h.arrangeTiles(dealer_domino_1, dealer_domino_2, dealer_domino_3, dealer_domino_4);
        for (int i=0;i<dealer_dominos.length;i++) {
            System.out.println(dealer_dominos[i]);
        }

        dealer_domino_1=dealer_dominos[0];
        dealer_domino_2=dealer_dominos[1];
        dealer_domino_3=dealer_dominos[2];
        dealer_domino_4=dealer_dominos[3];
        calculateDealerHand();
        //............
        int [] winners = new int[3];
        int[] hand_player = new int[2];
        hand_player[0] = player_hand_1;
        hand_player[1] = player_hand_2;
        if (hand_player[1]<hand_player[0]) {
            int temp=hand_player[1];
            hand_player[1]=hand_player[0];
            hand_player[0]=temp;
        }
        int[] hand_dealer = new int[2];
        hand_dealer[0] = dealer_hand_1;
        hand_dealer[1] = dealer_hand_2;
        if (hand_dealer[1]<hand_dealer[0]) {
            int temp=hand_dealer[1];
            hand_dealer[1]=hand_dealer[0];
            hand_dealer[0]=temp;
        }
        if (hand_player[0] == 12 && hand_dealer[0] == 12) {
            if (isPair(player_domino_1,player_domino_2) < isPair(dealer_domino_1,dealer_domino_2)) {
                winners[0]=0;
            } else if (isPair(player_domino_1,player_domino_2) > isPair(dealer_domino_1,dealer_domino_2)) {
                winners[0]=1;
            }
        } else if (hand_player[0] == 12) {
            winners[0]=0;
        } else if (hand_dealer[0] == 12) {
            winners[0]=1;
        } else 	if (hand_player[0] == hand_dealer[0]) {
            winners[0]=tieBreaker(player_domino_1, player_domino_2, dealer_domino_1,dealer_domino_2);
        } else if (hand_player[0] > hand_dealer[0]) {
            winners[0]=0;
        } else if (hand_player[0] < hand_dealer[0]) {
            winners[0]=1;
        }

        if (hand_player[1] == 12 && hand_dealer[1] == 12) {
            if (isPair(player_domino_3,player_domino_4) < isPair(dealer_domino_3,dealer_domino_4)) {
                winners[1]=0;
            } else if (isPair(player_domino_3,player_domino_4) > isPair(dealer_domino_3,dealer_domino_4)) {
                winners[1]=1;
            }
        } else if (hand_player[1] == 12) {
            winners[1]=0;
        } else if (hand_dealer[1] == 12) {
            winners[1]=1;
        } else 	if (hand_player[1] == hand_dealer[1]) {
            winners[1]=tieBreaker(player_domino_3, player_domino_4, dealer_domino_3,dealer_domino_4);
        } else if (hand_player[1] > hand_dealer[1]) {
            winners[1]=0;
        } else if (hand_player[1] < hand_dealer[1]) {
            winners[1]=1;
        }

        if (winners[0]==0 && winners[1]==0) {
            winners[2]=0;
        } else if (winners[0]==1 && winners[1]==1) {
            winners[2]=1;
        } else
            winners[2]=2;

        return winners;
    }

    private int isPair(int dom1, int dom2) {
        if ((dom1 == 1 && dom2 == 2) || (dom1 == 2 && dom2 == 1)) {
            return 1;
        } else if ((dom1 == 3 && dom2 == 4)|| (dom1 == 4 && dom2 == 3)) {
            return 2;
        } else if ((dom1 == 5 && dom2 == 6) || (dom1 == 6 && dom2 == 5)) {
            return 3;
        } else if ((dom1 == 7 && dom2 == 8) || (dom1 == 8 && dom2 == 7)){
            return 4;
        } else if ((dom1 == 9 && dom2 == 10) || (dom1 == 10 && dom2 == 9)) {
            return 5;
        } else if ((dom1 == 11 && dom2 == 12) || (dom1 == 12 && dom2 == 11)) {
            return 6;
        } else if ((dom1 == 13 && dom2 == 14) || (dom1 == 14 && dom2 == 13)) {
            return 7;
        } else if ((dom1 == 15 && dom2 == 16) || (dom1 == 16 && dom2 == 15)){
            return 8;
        } else if ((dom1 == 17 && dom2 == 18) || (dom1 == 18 && dom2 == 17)) {
            return 9;
        } else if ((dom1 == 19 && dom2 == 20) || (dom1 == 20 && dom2 == 19)) {
            return 10;
        } else if ((dom1 == 21 && dom2 == 22) || (dom1 == 22 && dom2 == 21)){
            return 11;
        } else if ((dom1 == 23 && dom2 == 24) || (dom1 == 24 && dom2 == 23)) {
            return 12;
        } else if ((dom1 == 25 && dom2 == 26) || (dom1 == 26 && dom2 == 25)){
            return 13;
        } else if ((dom1 == 27 && dom2 == 28) || (dom1 == 28 && dom2 == 27)){
            return 14;
        } else if ((dom1 == 29 && dom2 == 30) || (dom1 == 30 && dom2 == 29)){
            return 15;
        } else if ((dom1 == 31 && dom2 == 32) || (dom1 == 32 && dom2 == 31)) {
            return 16;
        }
        return 0;
    }

    private void calculatePlayerHand () {
        //prva ruka
        if (isPair(player_domino_1, player_domino_2) > 0) {
            player_hand_1 = 12;
        } else {
            player_hand_1 = values[player_domino_1-1];
            player_hand_1 += values[player_domino_2-1];

            //wild domino gong joon check (3 ili 6) HAND 1
            if (player_domino_1 == 1 || player_domino_2 == 1) {
                if (lastDigit(player_hand_1) < lastDigit(player_hand_1+3)) {
                    player_hand_1 += 3;
                }
            }
            if (player_domino_1 == 2 || player_domino_2 == 2) {
                if (lastDigit(player_hand_1) < lastDigit(player_hand_1-3)) {
                    player_hand_1 -= 3;
                }
            }

            //zaokruzi
            player_hand_1 = lastDigit(player_hand_1);

            //gong check
            if ( (player_domino_1 == 3 || player_domino_1 == 4 || player_domino_1 == 5 || player_domino_1 == 6  ) && (player_domino_2 == 27 || player_domino_2 == 28) ) {
                player_hand_1 = 10;
            }
            if ( (player_domino_2 == 3 || player_domino_2 == 4 || player_domino_2 == 5 || player_domino_2 == 6  ) && (player_domino_1 == 27 || player_domino_1 == 28) ) {
                player_hand_1 = 10;
            }

            //wong check
            if ( (player_domino_1 == 3 || player_domino_1 == 4 || player_domino_1 == 5 || player_domino_1 == 6  ) && (player_domino_2 == 25 || player_domino_2 == 26) ) {
                player_hand_1 = 11;
            }
            if ( (player_domino_2 == 3 || player_domino_2 == 4 || player_domino_2 == 5 || player_domino_2 == 6  ) && (player_domino_1 == 25 || player_domino_1 == 26) ) {
                player_hand_1 = 11;
            }
        }

        //druga ruka
        if (isPair(player_domino_3, player_domino_4) > 0) {
            player_hand_2 = 12;
        } else {
            player_hand_2 = values[player_domino_3-1];
            player_hand_2 += values[player_domino_4-1];

            //wild domino gong joon check (3 ili 6) HAND 2
            if (player_domino_3 == 1 || player_domino_4 == 1) {
                if (lastDigit(player_hand_2) < lastDigit(player_hand_2+3)) {
                    player_hand_2 += 3;
                }
            }
            if (player_domino_3 == 2 || player_domino_4 == 2) {
                if (lastDigit(player_hand_2) < lastDigit(player_hand_2-3)) {
                    player_hand_2 -= 3;
                }
            }

            //zaokruzi
            player_hand_2 = lastDigit(player_hand_2);

            //gong check
            if ( (player_domino_3 == 3 || player_domino_3 == 4 || player_domino_3 == 5 || player_domino_3 == 6  ) && (player_domino_4 == 27 || player_domino_4 == 28) ) {
                player_hand_2 = 10;
            }
            if ( (player_domino_4 == 3 || player_domino_4 == 4 || player_domino_4 == 5 || player_domino_4 == 6  ) && (player_domino_3 == 27 || player_domino_3 == 28) ) {
                player_hand_2 = 10;
            }

            //wong check
            if ( (player_domino_3 == 3 || player_domino_3 == 4 || player_domino_3 == 5 || player_domino_3 == 6  ) && (player_domino_4 == 25 || player_domino_4 == 26) ) {
                player_hand_2 = 11;
            }
            if ( (player_domino_4 == 3 || player_domino_4 == 4 || player_domino_4 == 5 || player_domino_4 == 6  ) && (player_domino_3 == 25 || player_domino_3 == 26) ) {
                player_hand_2 = 11;
            }
        }
    }

    private void calculateDealerHand () {
        //prva ruka
        if (isPair(dealer_domino_1, dealer_domino_2) > 0) {
            dealer_hand_1 = 12;
        } else {
            dealer_hand_1 = values[dealer_domino_1-1];
            dealer_hand_1 += values[dealer_domino_2-1];

            //wild domino gong joon check (3 ili 6) HAND 1
            if (dealer_domino_1 == 1 || dealer_domino_2 == 1) {
                if (lastDigit(dealer_hand_1) < lastDigit(dealer_hand_1+3)) {
                    dealer_hand_1 += 3;
                }
            }
            if (dealer_domino_1 == 2 || dealer_domino_2 == 2) {
                if (lastDigit(dealer_hand_1) < lastDigit(dealer_hand_1-3)) {
                    dealer_hand_1 -= 3;
                }
            }

            //zaokruzi
            dealer_hand_1 = lastDigit(dealer_hand_1);

            //gong check
            if ( (dealer_domino_1 == 3 || dealer_domino_1 == 4 || dealer_domino_1 == 5 || dealer_domino_1 == 6  ) && (dealer_domino_2 == 27 || dealer_domino_2 == 28) ) {
                dealer_hand_1 = 10;
            }
            if ( (dealer_domino_2 == 3 || dealer_domino_2 == 4 || dealer_domino_2 == 5 || dealer_domino_2 == 6  ) && (dealer_domino_1 == 27 || dealer_domino_1 == 28) ) {
                dealer_hand_1 = 10;
            }

            //wong check
            if ( (dealer_domino_1 == 3 || dealer_domino_1 == 4 || dealer_domino_1 == 5 || dealer_domino_1 == 6  ) && (dealer_domino_2 == 25 || dealer_domino_2 == 26) ) {
                dealer_hand_1 = 11;
            }
            if ( (dealer_domino_2 == 3 || dealer_domino_2 == 4 || dealer_domino_2 == 5 || dealer_domino_2 == 6  ) && (dealer_domino_1 == 25 || dealer_domino_1 == 26) ) {
                dealer_hand_1 = 11;
            }
        }

        //druga ruka
        if (isPair(dealer_domino_3, dealer_domino_4) > 0) {
            dealer_hand_2 = 12;
        } else {
            dealer_hand_2 = values[dealer_domino_3-1];
            dealer_hand_2 += values[dealer_domino_4-1];

            //wild domino gong joon check (3 ili 6) HAND 2
            if (dealer_domino_3 == 1 || dealer_domino_4 == 1) {
                if (lastDigit(dealer_hand_2) < lastDigit(dealer_hand_2+3)) {
                    dealer_hand_2 += 3;
                }
            }
            if (dealer_domino_3 == 2 || dealer_domino_4 == 2) {
                if (lastDigit(dealer_hand_2) < lastDigit(dealer_hand_2-3)) {
                    dealer_hand_2 -= 3;
                }
            }

            //zaokruzi
            dealer_hand_2 = lastDigit(dealer_hand_2);

            //gong check
            if ( (dealer_domino_3 == 3 || dealer_domino_3 == 4 || dealer_domino_3 == 5 || dealer_domino_3 == 6  ) && (dealer_domino_4 == 27 || dealer_domino_4 == 28) ) {
                dealer_hand_2 = 10;
            }
            if ( (dealer_domino_4 == 3 || dealer_domino_4 == 4 || dealer_domino_4 == 5 || dealer_domino_4 == 6  ) && (dealer_domino_3 == 27 || dealer_domino_3 == 28) ) {
                dealer_hand_2 = 10;
            }

            //wong check
            if ( (dealer_domino_3 == 3 || dealer_domino_3 == 4 || dealer_domino_3 == 5 || dealer_domino_3 == 6  ) && (dealer_domino_4 == 25 || dealer_domino_4 == 26) ) {
                dealer_hand_2 = 11;
            }
            if ( (dealer_domino_4 == 3 || dealer_domino_4 == 4 || dealer_domino_4 == 5 || dealer_domino_4 == 6  ) && (dealer_domino_3 == 25 || dealer_domino_3 == 26) ) {
                dealer_hand_2 = 11;
            }
        }
    }


    private int lastDigit (int number) {
        return number % 10;
    }

    private int tieBreaker (int player_domino_1, int player_domino_2, int player_domino_3, int player_domino_4) {
        Calculate c = new Calculate();
        c.setPlayerHand(player_domino_1,player_domino_2,player_domino_3,player_domino_4);
        int[] hand = c.getWinner();
        if (hand[0] == hand[1] && hand[0] == 0) {
            System.out.println("Bankar je pobijednik");
            return 1;
        }
		/*if (rang[player_domino_1-1] == 1 || player_domino_1 == 2) {
			player_domino_1=33;
		}
		if (player_domino_2 == 1 || player_domino_2 == 2) {
			player_domino_2=33;
		}
		if (player_domino_3 == 1 || player_domino_3 == 2) {
			player_domino_3=33;
		}
		if (player_domino_4 == 1 || player_domino_4 == 2) {
			player_domino_4=33;
		}*/
        if ((rang[player_domino_1-1] < rang[player_domino_2-1])) {
            if ((rang[player_domino_3-1] < rang[player_domino_4-1])) {
                if ((rang[player_domino_1-1] < rang[player_domino_3-1])) {
                    System.out.println("Prvi par je pobjednik***)");
                    System.out.println(rang[player_domino_1-1] + "- " + rang[player_domino_3-1]);
                    return 0;
                } else if ((rang[player_domino_1-1] > rang[player_domino_3-1])) {
                    System.out.println("Drugi par je pobjednik :D");
                    System.out.println(player_domino_3 + "- " + player_domino_1);
                    return 1;
                } else {
                    System.out.println("Drugi par je pobjednik :D--bankar"); //dealer odn bankar pobjedjuje kad su tiles iste, "copy"
                    return 1;
                }
            } else {
                if ((rang[player_domino_1-1] < rang[player_domino_4-1])) {
                    System.out.println("Prvi par je pobjednik :)");
                    System.out.println(player_domino_1 + "- " + player_domino_4);
                    return 0;
                } else if ((rang[player_domino_1-1] > rang[player_domino_4-1])) {
                    System.out.println("Drugi par je pobjednik :D");
                    System.out.println(player_domino_4 + "- " + player_domino_1);
                    return 1;
                } else {
                    System.out.println("Drugi par je pobjednik :D--bankar"); //dealer odn bankar pobjedjuje kad su tiles iste, "copy"
                    return 1;
                }
            }

        } else {
            if ((rang[player_domino_3-1] < rang[player_domino_4-1])) {
                if ((rang[player_domino_2-1] < rang[player_domino_3-1])) {
                    System.out.println("Prvi par je pobjednik :)");
                    System.out.println(player_domino_2 + "- " + player_domino_3);
                    return 0;
                } else if ((rang[player_domino_2-1] > rang[player_domino_3-1])) {
                    System.out.println("Drugi par je pobjednik :D");
                    System.out.println(player_domino_3 + "- " + player_domino_2);
                    return 1;
                } else {
                    System.out.println("Drugi par je pobjednik :D--bankar"); //dealer odn bankar pobjedjuje kad su tiles iste, "copy"
                    return 1;
                }
            } else {
                if ((rang[player_domino_2-1] < rang[player_domino_4-1])) {
                    System.out.println("Prvi par je pobjednik :)");
                    System.out.println(player_domino_2 + "- " + player_domino_4);
                    return 0;
                } else if ((rang[player_domino_2-1] > rang[player_domino_4-1])) {
                    System.out.println("Drugi par je pobjednik :D");
                    System.out.println(player_domino_4 + "- " + player_domino_2);
                    return 1;
                } else {
                    System.out.println("Drugi par je pobjednik :D--bankar"); //dealer odn bankar pobjedjuje kad su tiles iste, "copy"
                    return 1;
                }
            }
        }
    }


}
