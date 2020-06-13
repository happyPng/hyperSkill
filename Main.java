package readability;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String text;
        File f = new File(args[0]);
        Scanner in = new Scanner(f);
        int sentC=0;
        int wordC=0;
        int charC=0;
        int syllC=0;
        int pollyC=0;
        while(in.hasNextLine()){
            text=in.nextLine();
            if(text.charAt(text.length()-1)=='.'||text.charAt(text.length()-1)=='?'||text.charAt(text.length()-1)=='!'){
                charC++;
            }
            String[] textD=text.split("[\\.\\?\\!]");
            sentC+=textD.length;
            for(String i:textD) {
                String[] words=i.trim().split(" ");
                wordC+=words.length;
                for(String j:words){
                    if(syllableCounter(j)>2){
                        pollyC++;
                    }
                    syllC+=syllableCounter(j);
                    charC+=j.length();

                }
            }
        }
        charC+=sentC-1;
        double scoreARI=4.71*charC/wordC+0.5*wordC/sentC-21.43;
        String[] ariI ={"6","7","9","10","11","12","13","14","15","16","17","18","24","24+"};
        double scoreFK = 0.39*wordC/sentC+11.8*syllC/wordC-15.59;
        double scoreSMOG= (1.043 *Math.sqrt(pollyC*30/sentC)+3.1291);
        int L=100*charC/wordC;
        int S=100*sentC/wordC;
        double scoreCL=0.0588*L-0.296*S-15.8;

        System.out.println("Words: "+wordC+"\n"+ "Sentences: "+sentC+"\n"+"Characters: "+charC+"\n"+"Syllables: "+syllC+"\n"+"Polysyllables: "+pollyC);
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");

        in.close();
        in=new Scanner(System.in);
        String scoreT=in.nextLine();
        switch (scoreT){
            case "ARI":System.out.printf("Automated Readability Index: %.2f (about %s year olds).\n",scoreARI,ariI[((int)Math.round(scoreARI))-1]);
            case "FK":System.out.printf("Flesch–Kincaid readability tests: %.2f (about %s year olds).\n",scoreFK,ariI[((int)Math.round(scoreFK))-1]);
            case "SMOG":System.out.printf("Simple Measure of Gobbledygook: %.2f (about %s year olds).\n",scoreSMOG,ariI[((int)Math.round(scoreSMOG))-1]);
            case "CL":System.out.printf("Coleman–Liau index: %.2f (about %s year olds).\n",scoreCL,ariI[(int)Math.round(scoreCL)-1]);
            case "all":System.out.printf("Automated Readability Index: %.2f (about %s year olds).\n",scoreARI,ariI[((int)Math.round(scoreARI))-1]);
                System.out.printf("Flesch–Kincaid readability tests: %.2f (about %s year olds).\n",scoreFK,ariI[((int)Math.round(scoreFK))-1]);
                System.out.printf("Simple Measure of Gobbledygook: %.2f (about %s year olds).\n",scoreSMOG,ariI[((int)Math.round(scoreSMOG))-1]);
                System.out.printf("Coleman–Liau index: %.2f (about %s year olds).\n",scoreCL,ariI[(int)Math.round(scoreCL)-1]);
        }
        in.close();
    }
    public static int syllableCounter(String word){
        int count=0;
        word=word.toLowerCase();
        for(int i=0;i<word.length()-1;i++){
            if(word.charAt(i)=='a'||word.charAt(i)=='e'||word.charAt(i)=='i'||word.charAt(i)=='o'||word.charAt(i)=='u'||word.charAt(i)=='y'){
                if(word.charAt(i+1)=='a'||word.charAt(i+1)=='e'||word.charAt(i+1)=='i'||word.charAt(i+1)=='o'||word.charAt(i+1)=='u'||word.charAt(i+1)=='y'){
                    count--;
                }
                    count++;
            }
        }
        if(word.charAt(word.length()-1)=='a'||word.charAt(word.length()-1)=='i'||word.charAt(word.length()-1)=='o'||word.charAt(word.length()-1)=='u'||word.charAt(word.length()-1)=='y'){
            count++;
        }
        return (count==0)? 1 : count;
    }
}
