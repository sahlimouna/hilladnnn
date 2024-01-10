package hilladn;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HillADN {

    private static final int[][] HILL_KEY = {
            {6, 24, 1},
            {13, 16, 10},
            {20, 17, 15}
    };

    private static final String[] BINARY_TO_ADN = {"A", "C", "G", "T"};
    private static void printBinaryText(String text) {
        System.out.print("Texte binaire : ");
        for (char c : text.toCharArray()) {
            String binaryChar = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
            System.out.print(binaryChar + " ");
        }
        System.out.println();
    }
    private static String textToBinary(String text) {
        StringBuilder binaryText = new StringBuilder();

        for (char c : text.toCharArray()) {
            String binaryChar = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
            binaryText.append(binaryChar);
        }

        return binaryText.toString();
    }
    
    private static final Map<String, String> CODON_TO_AMINO_ACID = new HashMap<>();

    static {

        // Define the mapping between DNA codons and amino acids
      CODON_TO_AMINO_ACID.put("GCU", "A");
        CODON_TO_AMINO_ACID.put("GCC", "A");
        CODON_TO_AMINO_ACID.put("GCA", "A");
        CODON_TO_AMINO_ACID.put("GCG", "A");  
        CODON_TO_AMINO_ACID.put("UUA", "L");
        CODON_TO_AMINO_ACID.put("UUG", "L");
        CODON_TO_AMINO_ACID.put("CUU", "L");
        CODON_TO_AMINO_ACID.put("CUC", "L");  
        CODON_TO_AMINO_ACID.put("CUA", "L");
        CODON_TO_AMINO_ACID.put("CUG", "L");
        CODON_TO_AMINO_ACID.put("CGU", "R");
        CODON_TO_AMINO_ACID.put("CGC", "R");  
        CODON_TO_AMINO_ACID.put("CGA", "R");
        CODON_TO_AMINO_ACID.put("CGG", "R");
        CODON_TO_AMINO_ACID.put("AGA", "R");
        CODON_TO_AMINO_ACID.put("AGG", "R");
        CODON_TO_AMINO_ACID.put("AAA", "K");
        CODON_TO_AMINO_ACID.put("AAG", "K");
        CODON_TO_AMINO_ACID.put("AUG", "M");
        CODON_TO_AMINO_ACID.put("UUU", "F");  
        CODON_TO_AMINO_ACID.put("UUC", "F");
        CODON_TO_AMINO_ACID.put("CCU", "P");
        CODON_TO_AMINO_ACID.put("CCG", "P");
        CODON_TO_AMINO_ACID.put("CCA", "P");  
        CODON_TO_AMINO_ACID.put("CCC", "P");
        CODON_TO_AMINO_ACID.put("GAC", "D");
        CODON_TO_AMINO_ACID.put("GAU", "D");
        CODON_TO_AMINO_ACID.put("UGU", "C");
        CODON_TO_AMINO_ACID.put("UGC", "C");
        CODON_TO_AMINO_ACID.put("CAA", "Q");
        CODON_TO_AMINO_ACID.put("CAG", "Q");
        CODON_TO_AMINO_ACID.put("GAA", "E");  
        CODON_TO_AMINO_ACID.put("GAU", "E");
        CODON_TO_AMINO_ACID.put("GGU", "G");
        CODON_TO_AMINO_ACID.put("GGC", "G");
        CODON_TO_AMINO_ACID.put("GGA", "G");
        CODON_TO_AMINO_ACID.put("GGG", "G");
        CODON_TO_AMINO_ACID.put("CAU", "H");
        CODON_TO_AMINO_ACID.put("CAC", "H");
        CODON_TO_AMINO_ACID.put("AUU", "I");  
        CODON_TO_AMINO_ACID.put("AUC", "I");
        CODON_TO_AMINO_ACID.put("AUA", "I");
        CODON_TO_AMINO_ACID.put("GUU", "V");
        CODON_TO_AMINO_ACID.put("GUC", "V");
        CODON_TO_AMINO_ACID.put("GU", "V");
        CODON_TO_AMINO_ACID.put("GUG", "V");
        CODON_TO_AMINO_ACID.put("UAU", "Y");
        CODON_TO_AMINO_ACID.put("UAC", "Y");
        CODON_TO_AMINO_ACID.put("ACU", "T");
        CODON_TO_AMINO_ACID.put("ACC", "T");
        CODON_TO_AMINO_ACID.put("ACA", "T");
        CODON_TO_AMINO_ACID.put("ACG", "T");  
        CODON_TO_AMINO_ACID.put("UCU", "S");
        CODON_TO_AMINO_ACID.put("UCC", "S");
        CODON_TO_AMINO_ACID.put("UCA", "S");
        CODON_TO_AMINO_ACID.put("UCG", "S");
        CODON_TO_AMINO_ACID.put("AGU", "S");
        CODON_TO_AMINO_ACID.put("AGC", "S");
        CODON_TO_AMINO_ACID.put("AUG", "START");
        CODON_TO_AMINO_ACID.put("UAA", "STOP");
        CODON_TO_AMINO_ACID.put("UGA", "STOP");
        CODON_TO_AMINO_ACID.put("UAG", "STOP");
        
    
        
    }  
        private static String adnToAminoAcid(String adnText) {


StringBuilder aminoAcidText = new StringBuilder();

            // Ensure the length of the DNA sequence is a multiple of 3
            while (adnText.length() % 3 != 0) {
                adnText += "A";  // Add 'A' to fill if necessary
            }

            // Convert each DNA codon to its corresponding amino acid
            for (int i = 0; i < adnText.length(); i += 3) {
                String codon = adnText.substring(i, i + 3);
                aminoAcidText.append(CODON_TO_AMINO_ACID.get(codon));
            }

            return aminoAcidText.toString();
        

    
    }
        

        private static String binaryToADN(String binaryText) {
            StringBuilder adnText = new StringBuilder();

            for (int i = 0; i < binaryText.length(); i += 2) {
                String nucleotide = binaryText.substring(i, i + 2);
                int index = Integer.parseInt(nucleotide, 2);
                adnText.append(BINARY_TO_ADN[index]);
            }

            return adnText.toString();
        }

        private static String encrypt(String plaintext) {
            StringBuilder encryptedText = new StringBuilder();

            // Assurez-vous que la longueur du texte est un multiple de 3 (taille de la matrice clé)
            while (plaintext.length() % 3 != 0) {
                plaintext += "A";  // Ajoutez des 'A' pour remplir si nécessaire
            }

            // Appliquer la matrice clé pour chaque bloc de 3 nucléotides
            for (int i = 0; i < plaintext.length(); i += 3) {
                String block = plaintext.substring(i, i + 3);
                int[] adnIndexes = new int[block.length()];

                // Convertir les nucléotides en indices correspondants
                for (int j = 0; j < block.length(); j++) {
                    char nucleotide = block.charAt(j);
                    adnIndexes[j] = indexOfADN(nucleotide);
                }

                // Appliquer la matrice clé
                for (int j = 0; j < HILL_KEY.length; j++) {
                    int sum = 0;
                    for (int k = 0; k < HILL_KEY[j].length; k++) {
                        sum += HILL_KEY[j][k] * adnIndexes[k];
                    }
                    encryptedText.append(BINARY_TO_ADN[sum % 4]);
                }
            }

            return encryptedText.toString();
        }

        private static String decrypt(String encryptedText) {
            StringBuilder decryptedText = new StringBuilder();


    // Appliquer l'inverse de la matrice clé pour chaque bloc de 3 nucléotides
            for (int i = 0; i < encryptedText.length(); i += 3) {
                String block = encryptedText.substring(i, i + 3);
                int[] adnIndexes = new int[block.length()];

                // Convertir les nucléotides en indices correspondants
                for (int j = 0; j < block.length(); j++) {
                    char nucleotide = block.charAt(j);
                    adnIndexes[j] = indexOfADN(nucleotide);
                }

                // Appliquer l'inverse de la matrice clé
                int[][] inverseMatrix = getInverseMatrix(HILL_KEY);
                for (int j = 0; j < inverseMatrix.length; j++) {
                    int sum = 0;
                    for (int k = 0; k < inverseMatrix[j].length; k++) {
                        sum += inverseMatrix[j][k] * adnIndexes[k];
                    }
                    decryptedText.append(BINARY_TO_ADN[Math.floorMod(sum, 4)]);
                }
            }

            return decryptedText.toString();
        }
    public static void main(String[] args) {
    	try (Scanner scanner = new Scanner(System.in)) {
      System.out.print("Entrez le texte clair : ");
      String plaintext = scanner.nextLine();
      plaintext.replaceAll("\\s+","").toUpperCase();
      System.out.print("le texte trimmed : " + plaintext);
      
      printBinaryText(plaintext);


String binaryText = textToBinary(plaintext);
      String adnText = binaryToADN(binaryText);
      System.out.println("Résultat intermédiaire (ADN) : " + adnText);
      
      
      String aminoAcidText = adnToAminoAcid(adnText);
          System.out.println("Résultat final (Acides aminés) : " + aminoAcidText);
      
      

      String encryptedText = encrypt(adnText);
      System.out.println("Texte chiffré : " + encryptedText);
            
      
          
      String decryptedText = decrypt(encryptedText);
      System.out.println("Texte déchiffré : " + decryptedText);
    }
    }

    

    // Fonction utilitaire pour obtenir l'inverse d'une matrice 3x3
    private static int[][] getInverseMatrix(int[][] matrix) {
        int det = (matrix[0][0] * matrix[1][1] * matrix[2][2])
                + (matrix[0][1] * matrix[1][2] * matrix[2][0])
                + (matrix[0][2] * matrix[1][0] * matrix[2][1])
                - (matrix[0][2] * matrix[1][1] * matrix[2][0])
                - (matrix[0][1] * matrix[1][0] * matrix[2][2])
                - (matrix[0][0] * matrix[1][2] * matrix[2][1]);

        int[][] inverseMatrix = new int[3][3];
        inverseMatrix[0][0] = (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1]);
        inverseMatrix[0][1] = (matrix[0][2] * matrix[2][1] - matrix[0][1] * matrix[2][2]);
        inverseMatrix[0][2] = (matrix[0][1] * matrix[1][2] - matrix[0][2] * matrix[1][1]);
        inverseMatrix[1][0] = (matrix[1][2] * matrix[2][0] - matrix[1][0] * matrix[2][2]);
        inverseMatrix[1][1] = (matrix[0][0] * matrix[2][2] - matrix[0][2] * matrix[2][0]);
        inverseMatrix[1][2] = (matrix[0][2] * matrix[1][0] - matrix[0][0] * matrix[1][2]);
        inverseMatrix[2][0] = (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]);
        inverseMatrix[2][1] = (matrix[0][1] * matrix[2][0] - matrix[0][0] * matrix[2][1]);
        inverseMatrix[2][2] = (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]);

        int detInverse = modInverse(det, 4);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                inverseMatrix[i][j] = (inverseMatrix[i][j] * detInverse) % 4;
            }
        }

        return inverseMatrix;
    }

    // Fonction utilitaire pour calculer l'inverse modulaire
    private static int modInverse(int a, int m) {
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return -1; // Inverse modulaire n'existe pas
    }

    // Fonction utilitaire pour obtenir l'indice correspondant d'un nucléotide ADN
    private static int indexOfADN(char nucleotide) {
        for (int i = 0; i < BINARY_TO_ADN.length; i++) {
            if (BINARY_TO_ADN[i].charAt(0) == nucleotide) {
                return i;
            }
        }
        return -1; // Nuléotide non trouvé
    
    }
    }



