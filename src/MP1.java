
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
public class MP1 {
    public static String inputFile; 
    public static PrintWriter writer;
    public static ArrayList<Integer> urm = new ArrayList<>();
    public static ArrayList<String> instructions = new ArrayList();
    
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter file name:");
        inputFile = scan.nextLine();
        
        while (!new File(inputFile).exists()){
            System.out.println("Please enter file name: ");
            inputFile = scan.nextLine();
        }
        
        initialize();
        execute();
    }
    
    public static void initialize() throws FileNotFoundException, UnsupportedEncodingException{
        writer = new PrintWriter(inputFile+".out", "UTF-8");
        
        Scanner read = new Scanner(new File(inputFile));
        String urm_input = read.nextLine();
        inputToArrayList(urm_input);
        
        while(read.hasNext()){
            instructions.add(read.nextLine());
        }
        
        write();
    }
    
    public static void execute(){
        int index = 0;
        while(index != instructions.size()){
            String line = instructions.get(index);
            //System.out.println(line);
            if(line.contains("S")) {
                S(line);
                index++;
            }
            else if (line.contains("Z")){
                Z(line);
                index++;
            }
            else if (line.contains("C")){
                C(line);
                index++;
            }
            else if (line.contains("J")){
                int tmp = J(line);
                if(tmp!=-1)
                    index = J(line);
                else
                    index++;
            }
        }
        write();
        writer.close();
    }
    
    private static void inputToArrayList(String urm_input) {
        String[] tmp = urm_input.split(" ");
        for (String tmp1 : tmp) {
            urm.add(Integer.parseInt(tmp1));
        }
    }
    
    private static void write(){
        //System.out.println(urm);
        for(int i = 0; i < urm.size(); i++){
            String tmp = String.valueOf(urm.get(i)) + " ";
            writer.print(tmp);
        }
        writer.println();
    }
        
    private static void S(String instruction){
        String[] tmp = instruction.split(" ");
        int index = Integer.parseInt(tmp[1]);
        
        int val = urm.get(index);
        val++;
        
        urm.set(index, val);
        write();
    }
      
    private static void Z(String instruction){
        String[] tmp = instruction.split(" ");
        int index = Integer.parseInt(tmp[1]);
        
        urm.set(index, 0);
        write();
    }
    
    private static void C(String instruction){
        String[] tmp = instruction.split(" ");
        int y = Integer.parseInt(tmp[1]);
        int x = Integer.parseInt(tmp[2]);
        
        urm.set(x, urm.get(y));
        write();
    }
    
    private static int J(String instruction){
        String[] tmp = instruction.split(" ");
        int y = Integer.parseInt(tmp[1]);
        int x = Integer.parseInt(tmp[2]);
        int indx = Integer.parseInt(tmp[3]);
       
        if (urm.get(x).equals(urm.get(y))){
            return indx-1;
        }
        else {
            write(); //very vital, life-changing line!!!
            return -1;
        }
    }
}