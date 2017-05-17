/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iasandbox.TicTacToePackage;

import ArvoreMinimax.Position;
import iasandbox.TicTacToe;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Gabriel
 */
public class TicTacToeRules implements ticTacToePlayer {

    private final int player;
    int[][] mapa;

    public TicTacToeRules(int player) {
        this.player = player;
        mapa = TicTacToe.getInstance().getMap();
    }

    private int[] setPosRandom() {
        Random rand = new Random();
        int randomPos;
        ArrayList<Position> zeros = new ArrayList<>();
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j] == 0) {
                    zeros.add(new Position(i, j));
                }
            }

        }
        randomPos = rand.nextInt(zeros.size());
        return new int[]{zeros.get(randomPos).getX(),zeros.get(randomPos).getY()};
    }

    public int[] verificaHorizontal() {
        int auxzero = -1;
        int cont = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (player == 1) {
                    if (mapa[i][j] == 2) {
                        cont++;
                    }
                    if (mapa[i][j] == 0) {
                        auxzero = j;
                    }
                } else {
                    if (mapa[i][j] == 1) {
                        cont++;
                    }
                    if (mapa[i][j] == 0) {
                        auxzero = j;
                    }
                }
                if (cont == 2 && auxzero != -1) {
                    return new int[]{i, auxzero};
                }
                // System.out.println("Auxzero" + auxzero + "\nCont:" + cont);
            }
            cont = 0;
            auxzero = -1;
        }
        return null;
    }

    public int[] verificaVertical() {
        int auxzero = -1;
        int cont = 0;
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (player == 1) {
                    if (mapa[i][j] == 2) {
                        cont++;
                    }
                    if (mapa[i][j] == 0) {
                        auxzero = i;
                    }
                } else {
                    if (mapa[i][j] == 1) {
                        cont++;
                    }
                    if (mapa[i][j] == 0) {
                        auxzero = i;
                    }
                }
                if (cont == 2 && auxzero != -1) {
                    return new int[]{auxzero, j};
                }
                //  System.out.println("Auxzero" + auxzero + "\nCont:" + cont);
            }
            cont = 0;
            auxzero = -1;
        }
        return null;
    }

    private int[] verificaDiagonalPrincipal() {
        int cont = 0;
        int auxzero = -1;
        for (int i = 0; i < 3; i++) {
            if (player == 1) {
                if (mapa[i][i] == 2) {
                    cont++;
                }
                if (mapa[i][i] == 0) {
                    auxzero = i;
                }
            } else {
                if (mapa[i][i] == 1) {
                    cont++;
                }
                if (mapa[i][i] == 0) {
                    auxzero = i;
                }
            }
            if (cont == 2 && auxzero != -1) {
                return new int[]{auxzero, auxzero};
            }

        }
        return null;
    }

    private int[] verificaDiagonalSecundaria() {
        int j = 3;
        int cont = 0;
        int[] auxzero = {-1, -1};
        for (int i = 0; i < 3; i++) {
            j--;
            if (player == 1) {
                if (mapa[i][j] == 2) {
                    cont++;
                }
                if (mapa[i][j] == 0) {
                    auxzero[0] = i;
                    auxzero[1] = j;
                }
            } else {
                if (mapa[i][j] == 1) {
                    cont++;
                }
                if (mapa[i][j] == 0) {
                    auxzero[0] = i;
                    auxzero[1] = j;
                }
            }
            if (cont == 2 && auxzero[0] != -1 && auxzero[1] != -1) {
                return auxzero;
            }
        }
        return null;
    }

    private int[] verificaVitVertical() {
        int cont = 0, auxzero = -1;
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (player == 1) {
                    if (mapa[i][j] == 1) {
                        cont++;
                    }
                    if (mapa[i][j] == 0) {
                        auxzero = i;
                    }
                } else {
                    if (player == 2) {
                        if (mapa[i][j] == 2) {
                            cont++;
                        }
                        if (mapa[i][j] == 0) {
                            auxzero = i;
                        }
                    }
                }
                if (cont == 2 && auxzero != -1) {
                    return new int[]{auxzero, j};
                }
            }
            cont = 0;
            auxzero = -1;
        }

        return null;
    }

    private int[] verificaVitHorizontal() {
        int cont = 0, auxzero = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (player == 1) {
                    if (mapa[i][j] == 1) {
                        cont++;
                    }
                    if (mapa[i][j] == 0) {
                        auxzero = j;
                    }
                } else {
                    if (player == 2) {
                        if (mapa[i][j] == 2) {
                            cont++;
                        }
                        if (mapa[i][j] == 0) {
                            auxzero = j;
                        }
                    }
                }
                if (cont == 2 && auxzero != -1) {
                    return new int[]{i, auxzero};
                }
            }
            cont = 0;
            auxzero = -1;
        }
        return null;
    }

    private int[] verificaVitDiagonalPrincipal() {
        int cont = 0;
        int auxzero = -1;
        for (int i = 0; i < 3; i++) {
            if (player == 1) {
                if (mapa[i][i] == 1) {
                    cont++;
                }
                if (mapa[i][i] == 0) {
                    auxzero = i;
                }
            } else {
                if (mapa[i][i] == 2) {
                    cont++;
                }
                if (mapa[i][i] == 0) {
                    auxzero = i;
                }
            }
            if (cont == 2 && auxzero != -1) {
                return new int[]{auxzero, auxzero};
            }

        }
        return null;
    }

    private int[] verificaVitDiagonalSecundaria() {
        int j = 3;
        int cont = 0;
        int[] auxzero = {-1, -1};
        for (int i = 0; i < 3; i++) {
            j--;
            if (player == 1) {
                if (mapa[i][j] == 1) {
                    cont++;
                }
                if (mapa[i][j] == 0) {
                    auxzero[0] = i;
                    auxzero[1] = j;
                }
            } else {
                if (mapa[i][j] == 2) {
                    cont++;
                }
                if (mapa[i][j] == 0) {
                    auxzero[0] = i;
                    auxzero[1] = j;
                }
            }
            if (cont == 2 && auxzero[0] != -1 && auxzero[1] != -1) {
                return auxzero;
            }
        }
        return null;
    }

    private int[] verificaPerigo() {
        if (verificaHorizontal() != null) {
            return verificaHorizontal();
        } else if (verificaVertical() != null) {
            return verificaVertical();
        } else if (verificaDiagonalPrincipal() != null) {
            return verificaDiagonalPrincipal();
        } else if (verificaDiagonalSecundaria() != null) {
            return verificaDiagonalSecundaria();
        }
        return null;
    }

    private int[] verificaVitoria() {
        if (verificaVitVertical() != null) {
            return verificaVitVertical();
        } else if (verificaVitHorizontal() != null) {
            return verificaVitHorizontal();
        } else if (verificaVitDiagonalPrincipal() != null) {
            return verificaVitDiagonalPrincipal();
        } else if (verificaVitDiagonalSecundaria() != null) {
            return verificaVitDiagonalSecundaria();
        }
        return null;
    }

    @Override
    public int[] logic() {

        if (verificaVitoria() != null) {
            return verificaVitoria();
        } else if (verificaPerigo() != null) {
            return verificaPerigo();
        }
        return setPosRandom();
    }

}
