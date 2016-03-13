/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otimizafuncao;

import java.util.Comparator;

/**
 *
 * @author Matheus
 */
class ComparadorDeY implements Comparator<Funcao> {
    public int compare(Funcao o1, Funcao o2) {
        if (o1.y < o2.y) return -1;
        else if (o1.y > o2.y) return +1;
        else return 0;
    }
}