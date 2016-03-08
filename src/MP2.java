import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author April Dae Bation
 */
public class MP2 {
    public static String inputFile; 
    public static PrintWriter writer;
    public static Scanner read;
    
    public static Queue<Character> moves;
    public static ArrayList<Character> LeftSide;
    public static ArrayList<Character> RightSide;
    public static Character man = 'M';
    
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter file name:");
        inputFile = scan.nextLine();
        
        writer = new PrintWriter(inputFile+".out", "UTF-8");
        read = new Scanner(new File(inputFile));
        
        while (!new File(inputFile).exists()){
            System.out.println("Please enter file name: ");
            inputFile = scan.nextLine();
        }
        
        execute();
    }
    
    public static void initialize() throws FileNotFoundException, UnsupportedEncodingException{
        LeftSide = new ArrayList<>(Arrays.asList('M','R','L','C'));
        RightSide = new ArrayList<>();
        moves = new LinkedList();
    }
    
    public static void execute() throws FileNotFoundException, UnsupportedEncodingException{
        int ctr = 0;
        while(read.hasNext()){
            boolean flag = false;
            initialize();
            String tmp = read.next();
            initializeMoves(tmp);
            while(!moves.isEmpty()){
                if (!CrossRiver(moves.remove())){
                    writer.write("NG\n");
                    flag = true;
                    break;
                }
            }
            if(flag == false){
                if (RightSide.containsAll(Arrays.asList(man,'C','L','R'))){
                    System.out.println(tmp);
                    writer.write("OK\n");
                }
                else
                    writer.write("NG\n");
            }
        }
        writer.close();
    }
    
    public static void initializeMoves(String tmp){
        char[] c = tmp.toCharArray();
        for (int i=0; i<c.length; i++){
            moves.add(c[i]);  
        }
    }

    private static boolean CrossRiver(Character move) {
        if(LeftSide.contains(man)){
            if(move == 'N'){
                LeftSide.remove(man);
                RightSide.add(man);
                return checkStatus();
            }
            else { 
                //checks if man and possession (Lion/Rabbit/Carrot) are both on the left side
                if (LeftSide.contains(move)) {
                    LeftSide.removeAll(Arrays.asList(man,move));
                    RightSide.addAll(Arrays.asList(man,move));
                    return checkStatus();
                }
                else {
                   return false;
                }
            }
        }
        else {
            if(move == 'N'){
                RightSide.remove(man);
                LeftSide.add(man);
                return checkStatus();
            }
            else { 
                if (RightSide.contains(move)){
                    RightSide.removeAll(Arrays.asList(man,move));
                    LeftSide.addAll(Arrays.asList(man,move));
                    return checkStatus();
                }
                else {
                    return false;
                }
            }
        }
    }

    private static boolean checkStatus() {
        if ((LeftSide.size() == 3 && LeftSide.containsAll(Arrays.asList('C','L','R'))) ||
                (RightSide.size() == 3 && RightSide.containsAll(Arrays.asList('C','L','R'))))
            return false;
        if ((LeftSide.size() == 2 && LeftSide.containsAll(Arrays.asList('C','R'))) ||
                (RightSide.size() == 2 && RightSide.containsAll(Arrays.asList('C','R'))))
            return false;
        if ((LeftSide.size() == 2 && LeftSide.containsAll(Arrays.asList('L','R'))) ||
                (RightSide.size() == 2 && RightSide.containsAll(Arrays.asList('L','R'))))
            return false;
        
        return true;
    }
}
