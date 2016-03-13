/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otimizafuncao;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Matheus
 */
public class OtimizaFuncao {

    public static float calculaFuncao(float[] x)
    {
        float retorno = 0;
        
        retorno = x[0]*x[0]+x[1]*x[1]+((3*x[0]+4*x[1]-26)*(3*x[0]+4*x[1]-26));
           
        return retorno;
    }
    
    public static float calculaDesvioPadrao(float[] v, int qtd)
    {
        float desvioPadrao = 0;
        
        float soma = 0;
        for(int i=0; i<qtd; i++)
        {
            soma = soma + v[i];
        }
        
        float media = soma/qtd;
        
        float somaAoQuadrado = 0;
        for(int i=0; i<qtd; i++)
        {
            somaAoQuadrado = somaAoQuadrado + (((v[i]-media)*(v[i]-media))/(qtd-1));
        }
        
        desvioPadrao = (float) Math.sqrt(somaAoQuadrado);
        
        return desvioPadrao;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        /* Recebe a quantidade de x que a funcao possui
            (a dimensão do domínio da função) */
        int qtd_x = 2;
        
        // Recebe o valor inicial de cada x
        float xIni[] = new float[qtd_x];
        xIni[0] = 0;
        xIni[1] = 0;
        
        // Recebe o valor final de cada x
        float xFin[] = new float[qtd_x];
        xFin[0] = 100;
        xFin[1] = 50;
        
        // Quantidade de divisões na primeira população
        float divisoes = 1000;
        
        // Quantidade de iterações
        int qtd_iteracoes = 18;
        
        // Recebe o valor inicial auxiliar de cada x
        float xIniAux[] = new float[qtd_x];
        xIniAux[0] = xIni[0];
        xIniAux[1] = xIni[1];
        
        ArrayList<Funcao> f = new ArrayList<Funcao>();
        
        // Povoando a população de Pais
        int tam = 0;
        while(xIniAux[0]<=xFin[0])
        {
            Funcao aux_f = new Funcao(qtd_x);
            
            float aux_x[] = new float[qtd_x];
            for(int i=0; i<qtd_x; i++)
            {
                aux_x[i] = xIniAux[i];
                xIniAux[i] = aux_x[i] + (xFin[i])/divisoes;
            }
            
            aux_f.setX(aux_x);
            aux_f.setY(calculaFuncao(aux_x));
            
            f.add(aux_f);
            tam++;
        }
        
        for(int iteracoes=0; iteracoes<qtd_iteracoes; iteracoes++)
        {
            System.out.println("--------- ITERAÇÃO "+(iteracoes+1)+" ---------");
            
            int tamPais = tam;
            
            // Gerando os Filhos
            if(qtd_x>1){
                
                int metade = (qtd_x/2);
                int p = 0;
                
                for(int j=0; (j+1)<tamPais; j++)
                {
                   Funcao aux_f1 = new Funcao(qtd_x);
                   float aux_x1[] = new float[qtd_x];
                   
                   Funcao aux_f2 = new Funcao(qtd_x);
                   float aux_x2[] = new float[qtd_x];
                    
                    int k = qtd_x-1;
                    for (int i = 0; i < metade; i++) {
                        aux_x1[i] = f.get(p).getX()[i];
                        aux_x1[k] = f.get(p+1).getX()[k];
                        k--; 
                    }

                    k = 0;
                    for (int i = metade; i < qtd_x; i++) {
                        aux_x2[i] = f.get(p).getX()[i];
                        aux_x2[k] = f.get(p+1).getX()[k];
                        k++; 
                    }
                    
                    aux_f1.setX(aux_x1);
                    aux_f1.setY(calculaFuncao(aux_x1));

                    f.add(aux_f1);
                    tam++;
                    
                    aux_f2.setX(aux_x2);
                    aux_f2.setY(calculaFuncao(aux_x2));

                    f.add(aux_f2);
                    tam++;

                    p++;
                }
                
                // Mutação dos Pais
                float[] desvioPadrao_x = new float[qtd_x];
                ArrayList<Object> todos_x = new ArrayList<Object>();
                
                for(int i=0; i<qtd_x; i++)
                {
                    float aux_x[] = new float[tamPais];
                    for(int j=0; j<tamPais; j++)
                    {
                        aux_x[j] = f.get(j).getX()[i];
                    }
                    
                    todos_x.add(aux_x);
                }
                
                for(int i=0; i<todos_x.size(); i++)
                {
                    desvioPadrao_x[i] = calculaDesvioPadrao((float[]) todos_x.get(i), todos_x.size());
                }
                
                for(int i=0; i<tamPais; i++)
                {
                    Funcao aux_f = new Funcao(qtd_x);

                    float aux_x[] = new float[qtd_x];
                    for(int j=0; j<qtd_x; j++)
                    {
                        float novoValor = f.get(i).getX()[j] + desvioPadrao_x[j];
                        
                        if((novoValor<=xFin[j])&&(novoValor>=xIni[j]))
                        {
                            aux_x[j] = novoValor;
                        }else
                        {
                            novoValor = f.get(i).getX()[j] - desvioPadrao_x[j];
                            
                            if((novoValor<=xFin[j])&&(novoValor>=xIni[j]))
                            {
                                aux_x[j] = novoValor;
                            }else
                            {
                                novoValor = f.get(i).getX()[j];
                                aux_x[j] = novoValor;
                            }
                        }
                    }

                    aux_f.setX(aux_x);
                    aux_f.setY(calculaFuncao(aux_x));

                    f.add(aux_f);
                    tam++;
                }
                
                // Selecionar os melhores para a próxima geração
                Collections.sort (f, new ComparadorDeY()); // Ordena a população j
                
                ArrayList<Funcao> f_novo = new ArrayList<Funcao>();
                int tamNovo = 0;
                
                int pos = 0;
                int pos_f = 0;
                
                
                while(pos<tamPais)
                {
                    if(pos_f==f.size())
                    {
                        break;
                    }
                    
                    Funcao aux_f = new Funcao(qtd_x);
                                       
                    float aux_x[] = new float[qtd_x];
                    
                    for(int i=0; i<qtd_x; i++)
                    {
                        aux_x[i] = f.get(pos_f).getX()[i];
                        
                    }
                    pos_f++;
                    
                    aux_f.setX(aux_x);
                    
                    int podeAdicionar = 0;
                    
                    for(int j=0; j<f_novo.size(); j++)
                    {
                        for(int i=0; i<qtd_x; i++)
                        {   
                            if(aux_x[i]==f_novo.get(j).getX()[i])
                            {
                                podeAdicionar++;
                                
                                int aux = i+1;
                                while(aux<qtd_x)
                                {
                                    if(aux_x[i]==f_novo.get(j).getX()[i])
                                    {
                                        podeAdicionar++;
                                        
                                        if(podeAdicionar==qtd_x)
                                        {
                                            break;
                                        }
                                    }
                                    
                                    aux++;
                                }
                            }
                        }
                    }
                    
                    if(podeAdicionar<qtd_x)
                    {
                        aux_f.setY(calculaFuncao(aux_x));

                        f_novo.add(aux_f);
                        tamNovo++;

                        pos++;
                    }
                } 
                
                tam = tamNovo;
                f = f_novo;
                
                // Impressão do melhor resultado
                System.out.println("\nMELHOR RESULTADO: ");
                System.out.println("y="+f.get(0).getY());
                for(int j=0; j<qtd_x; j++)
                {
                    System.out.println("x["+j+"]="+f.get(0).getX()[j]);
                }
                System.out.println();
                
                /*
                
                // Impressão do Array j
                for(int i=0; i<f.size(); i++)
                {
                    System.out.println("\n"+i+": \n");
                    System.out.println("y="+f.get(i).getY()+"\n");
                    for(int j=0; j<qtd_x; j++)
                    {
                        System.out.println("x["+j+"]="+f.get(i).getX()[j]+"\n");
                    }
                }
                
                */
                
            }
            
            
        }
        
        /*
        for(int i=0; i<qtd_x; i++)
        {
            f[i] = new ArrayList<Float>();
        }*/
        
        
        
        
        
    }
    
}
