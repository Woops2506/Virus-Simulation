import java.util.Scanner;
import java.util.Random;

class Virus {
    
    public static void main (String[] args) {
        double rate = Double.parseDouble(args[0]);
        int w1 = Integer.parseInt(args[1]);
        int debug = Integer.parseInt(args[2]);
        String[][] world = new String [w1][w1];
        int[][] infTimer = new int [w1][w1];
        
        Random random = new Random();
        
        int w2 = w1-1;
        double w3 = w1*w1;
        
        int rng = random.nextInt(w1);
        int rng1 = random.nextInt(w1);             
        
        
        
        for (int i = 0; i < w1; i++) {
            
            
            for (int z = 0; z < w1; z++) {
                
                world[i][z] = "-";
                
          
            }
           
        }
        
        for (int i = 0; i < w1; i++) {
            
            for (int z = 0; z < w1; z++) {
                
                infTimer[i][z] = -1;
            }  
        }
        
        int timer = random.nextInt(7 + 1 - 3) + 3;
        int days = 1;
        world[rng][rng1] = "*";
        infTimer[rng][rng1] = timer;
        System.out.println ();
        System.out.println ("Day "+days);
        printMap(world);
        
        double mon = 1;
        double tue = 0;
        double wed = 0;
        double thu = 0;
        double fri = 0;
        double sat = 0;
        double sun = 0;
        double cases = 0;
        double incidence = 0;
        double incidenceP;
        double inci = 0;
        
        int counter = 1;
        int recovered = 0;
        
        
        double infect = 0;
        
       
        while (!infectCheck(world)) {
            
            if (counter == 7) {
                
                counter = 0;
                
            }
            
            counter++;
            
            for (int i = 0; i < w1; i++) {
                
                for (int z = 0; z < w1; z++) {
                    
                    infect = (Math.random()*100);
                    timer = random.nextInt(7 + 1 - 3) + 3;
                    
                    
                    if (world[i][z].equals("*")) {
                        
                        if (infect <= rate) {
                            
                            if (i > 0 && world[i-1][z].equals("-")) {
                            
                                world[i-1][z] = "*";
                                infTimer[i-1][z] = timer;
                                inci++;
                            
                            }
                            
                            if (i == 0 && world[i+w2][z].equals("-")) {
                                
                                world[i+w2][z] = "*";
                                infTimer[i+w2][z] = timer;
                                inci++;
                                
                            }
                        }
                        
                        infect = (Math.random()*100);
                        timer = random.nextInt(7 + 1 - 3) + 3;
                        
                        if (infect <= rate) {
                            
                            if (i < w2 && world[i+1][z].equals("-")) {
                            
                                world[i+1][z] = "*";
                                infTimer[i+1][z] = timer;
                                inci++;
                            
                            }
                            
                            if (i == w2 && world[i-w2][z].equals("-")) {
                                
                                world[i-w2][z] = "*";
                                infTimer[i-w2][z] = timer;
                                inci++;
                                
                            }
                        }
                        
                        infect = (Math.random()*100);
                        timer = random.nextInt(7 + 1 - 3) + 3;
                        
                        if (infect <= rate) {
                            
                            if (z > 0 && world[i][z-1].equals("-")) {
                            
                                world[i][z-1] = "*";
                                infTimer[i][z-1] = timer;
                                inci++;
                            
                            }
                            
                            if (z == 0 && world[i][z+w2].equals("-")) {
                                
                                world[i][z+w2] = "*";
                                infTimer[i][z+w2] = timer;
                                inci++;
                                
                            }
                        }
                        
                        infect = (Math.random()*100);
                        timer = random.nextInt(7 + 1 - 3) + 3;
                        
                        if (infect <= rate) {
                            
                            if (z < w2 && world[i][z+1].equals("-")) {
                            
                                world[i][z+1] = "*";
                                infTimer[i][z+1] = timer;
                                inci++;
                            
                            }
                            
                            if (z == w2 && world[i][z-w2].equals("-")) {
                                
                                world[i][z-w2] = "*";
                                infTimer[i][z-w2] = timer;
                                inci++;
                                
                            }
                        }
                        
                        
                      
                    }
                    
                    if  (infTimer[i][z] > 0) {
                    
                            infTimer[i][z]--;
                        }
                
                    if  (infTimer[i][z] == 0 && world[i][z] != "#") {
                    
                            world[i][z] = "#";
                            recovered++;
                    
                    }
                
                }
                
            }
            
            for (int i = 0; i < w1; i++) {
                
                for (int z = 0; z < w1; z++) {
                        
                    if (world[i][z].equals("*")) {
                            
                        cases++;
                        
                    }
                     
                }
               
            }
            
            switch(counter) {
                            
                case 1: mon = inci;
                        break;
                            
                case 2: tue = inci;
                        break;
                            
                case 3: wed = inci;
                        break;
                            
                case 4: thu = inci;
                        break;
                            
                case 5: fri = inci;
                        break;
                            
                case 6: sat = inci;
                        break;
                            
                case 7: sun = inci;
                        break;
                                
            }
            
            incidence = mon+tue+wed+thu+fri+sat+sun;
            incidenceP = incidence/w3*100000;
          
            days++;
            System.out.println ();
            System.out.println ("Day "+days);
            System.out.println ("Amount infected: "+String.format("%.0f",cases)+" / "+String.format("%.0f",w3)+" | Newly infected: "+String.format("%.0f",inci)+" | Total of people who recovered: "+recovered+" / "+String.format("%.0f",w3));
            System.out.println ("The seven day incidence per 100k is at: "+String.format("%.2f",incidenceP));
            System.out.println ();
            System.out.println ();
            printMap(world);
            System.out.println ();
            System.out.println ();
            
            if (debug == 1) {
                
                printInfMap(infTimer);
                
            }
            
            cases = 0;
            inci = 0;
            
            if (infectCheck(world)) {
                
                break;
                
            }
            
        }        
        
        
       
    }

    public static void printMap (String[][] pmap) {
        
         for (int i = 0; i < pmap.length; i++) {         
            
            for (int z = 0; z < pmap.length; z++) {
                
                System.out.print (" "+pmap[i][z]+" |");
            }
            
            System.out.println ();
        }
    }
    
    public static void printInfMap (int[][] infMap) {
        
        for (int i = 0; i < infMap.length; i++) {         
            
            for (int z = 0; z < infMap.length; z++) {
                
                if (infMap [i][z] == -1) {
                
                    System.out.print (infMap[i][z]+" |");
                    
                }
                
                else {
                    
                    System.out.print (" "+infMap[i][z]+" |");
                    
                }
            }
            
            System.out.println ();
        }
        
    }

    /**
      * Checkt ob infiziert werden kann.
      * 
      * @param infmap Um die Größe des Arrays zu kennen
      * @return false heißt es kann infiziert werden, else true
      */
    
    
    
    
    public static boolean infectCheck (String[][] infmap) {
        
        int w2 = infmap.length-1;
        
        for (int i = 0; i < infmap.length; i++) {         
            
            for (int z = 0; z < infmap.length; z++) {
                
                if (infmap[i][z].equals("*")) {
                    
                    if (i > 0 && infmap[i-1][z].equals("-") ||
                        i == 0 && infmap[i+w2][z].equals("-") ||
                        i < w2 && infmap[i+1][z].equals("-") ||
                        i == w2 && infmap[i-w2][z].equals("-") ||
                        z > 0 && infmap[i][z-1].equals("-") ||
                        z == 0 && infmap[i][z+w2].equals("-") ||
                        z < w2 && infmap[i][z+1].equals("-") ||
                        z == w2 && infmap[i][z-w2].equals("-")) {
                        
                        return false;
                        
                    }
                    
                }
                
            }
            
        }

        return true;    
        
    }
    
}    
    
    
    


    
    
    //vergleichsString.equals("suche")//
    //if(welt[i][z].equals("*"){}//
    // break / continue//
    // ARRAY GRENZEN!!!!!//
