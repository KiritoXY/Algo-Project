/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo.tsp.project;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Adil Ayub
 */
public class AlgoTSPProject {

    public int [][] readdata(File file_name,int city_size) throws FileNotFoundException
    {
        Scanner sc = new Scanner(file_name);int temp;
        int data[][]= new int [city_size][city_size];
        for(int i=0;i<city_size;i++)
        {
            for(int j=0;j<city_size;j++)
            {
                temp=sc.nextInt();
                if(temp == 0)
                    break;
                else
                    data[i][j]=temp;
            }
        }
        return data;
    }
    public int [][] shuffle(int data[][],int index)
    {
        Random seed = new Random();int temp;
        int range,range2;
        for(int i=0;i<data[0].length;i++)
        {
            data[index+1][i]=data[index][i];
        }
        for(int i=0;i<5;i++)
        {
            range=seed.nextInt((data[0].length)-2) + 1;
            range2=seed.nextInt((data[0].length)-2) + 1;
            temp=data[index+1][range];
            data[index+1][range]=data[index+1][range2];
            data[index+1][range2]=temp;
        }
        return data;
    }
    public int [][] makechromones(int size)
    {
        int data[][]=new int [20][size+1];
        for(int i=0;i<size;i++)
        {
            data[0][i]=i;
        }
        for(int i=0;i<19;i++)
        {
            data=shuffle(data, i);
        }
        return data;
    }
    public double [] fitness(int data[][],int chromones[][])
    {
        int city1,city2;double getdist,total=0;double fitness[]=new double [20];double temp;
        for(int i=0;i<chromones.length;i++)
        {
            for(int j=0;j<(chromones[0].length)-1;j++)
            {
                city1=chromones[i][j];city2=chromones[i][j+1];
                getdist=data[city2][city1];
                total=total+getdist;
            }
            temp=1/total;
            fitness[i]=temp;
        }
        return fitness;
    }
    public void debug(int newdata[][])
    {
        String temp;
        for(int i=0;i<newdata.length;i++)
        {
            for(int j=0;j<newdata[0].length;j++)
            {
                temp=Integer.toString(newdata[i][j]);
                System.out.print(temp+'\t');
            }
            System.out.printf("\n");
        }   
    }
    public int [][] Tournment_Selection(double fitness[],int city,int chromones[][])
    {
        Random seed = new Random();int selected[]=new int [city],num,selected_population[][]= new int [10][city+1],parents [][]=new int [2][city+1];
        double new_fitness[]= new double [10],best_fit_1=0,best_fit_2=0;int p1=0,p2=0;
        for(int i=0;i<10;i++)
        {
            num=seed.nextInt(city);
            if(selected[num] == 0)
            {
                selected[num]=1;
                for(int j=0;j<chromones[0].length;j++)
                {
                    System.out.println(num);
                    selected_population[i][j]=chromones[num][j];
                    new_fitness[i]=fitness[num];
                }
            }
            else
                i--;
        }
        for(int i=0;i<10;i++)
        {
            if(new_fitness[i] > best_fit_1)
            {
                best_fit_1=new_fitness[i];
                p1=i;
            }
        }
        for(int i=0;i<10;i++)
        {
            if((new_fitness[i] > best_fit_2) && i != p1)
            {
                best_fit_2=new_fitness[i];
                p2=i;
            }
        }
        for(int i=0;i<city+1;i++)
        {
            parents[0][i]=selected_population[p1][i];
            parents[1][i]=selected_population[p2][i];       
        }
        return parents;
    }
    public static void main(String[] args) throws FileNotFoundException 
    {
        AlgoTSPProject obj=new AlgoTSPProject();int data[][];int chromones[][];double fitness[];int parents[][];
        File file = new File("D:\\University\\Semester 5\\Projects\\Algo\\gr17.txt");
        data=obj.readdata(file,17);
        obj.debug(data);
        chromones=obj.makechromones(17);
        fitness=obj.fitness(data, chromones);
        parents=obj.Tournment_Selection(fitness,17,chromones);
    }  
}
