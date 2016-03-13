/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otimizafuncao;

/**
 *
 * @author Matheus
 */
public class Funcao {
    
    float y;
    float x[];
    int qtd_x;

    public Funcao(int qtd_x) {
        this.qtd_x = qtd_x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float[] getX() {
        return x;
    }

    public void setX(float[] x) {
        this.x = new float[qtd_x];
        System.arraycopy(x, 0, this.x, 0, qtd_x);
    }

    public int getQtd_x() {
        return qtd_x;
    }

    public void setQtd_x(int qtd_x) {
        this.qtd_x = qtd_x;
    }
    
    
    
    
    
}
