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
    public static void main(String[] args) throws FileNotFoundException 
    {
        AlgoTSPProject obj=new AlgoTSPProject();int data[][];int chromones[][];
        File file = new File("D:\\University\\Semester 5\\Projects\\Algo\\gr21.txt");
        data=obj.readdata(file,17);
        chromones=obj.makechromones(17);
    }
    
}
