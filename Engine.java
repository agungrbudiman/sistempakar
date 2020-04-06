import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Engine {

    private static List<String[]> rules;
    private static Scanner sc;

    public static void generateRules(String path) { //Baca file csv lalu simpan ke ArrayList
        rules = new ArrayList<String[]>();
        try {
            Scanner scanner = new Scanner(new File(path));
            scanner.useDelimiter("\n");
            while(scanner.hasNext()){
                String data = scanner.next();
                String[] parts = data.split(";");
                rules.add(parts);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void inferenceEngine() {
        System.out.println ("------------------------------\nSistem Pakar Rekomendasi Mobil\n------------------------------");
        sc = new Scanner(System.in);
        for (int i = 2; i < 9; i++) { //loop untuk setiap fakta
            checkRules(i);
            // if(rules.size() == 1) { //Jika rules tinggal satu maka sudah ketemu goalnya, tidak perlu cari fakta lain
            //     break;
            // }
        }
        System.out.println("\n-----------\nRekomendasi\n-----------");
        for (String[] r : rules) { //Output goal
            System.out.println(r[0]+" ("+r[1]+" jt)");
        }
        sc.close();
    }

    private static void filterRules(String fact, int i) {
        rules = rules.stream().filter(r -> r[i].equals(fact)).collect(Collectors.toList()); //filter lalu ambil rules yang sesuai fakta
        // for (String[] r : rules) {
        //     System.out.println(r[0]);
        // }
    }
    
    private static void checkRules(int i) {
        List<String> v = new ArrayList<>();
        for (String[] r : rules) {
            v.add(r[i]);
        }
        v = v.stream().distinct().sorted().collect(Collectors.toList()); //hapus duplikasi di array v lalu sort
        dialog(i, v);
        if (v.size() > 1) { //Perlu fakta baru jika minimal ada 2 pilihan value spesifikasi, jika tidak skip
            System.out.print("------------\nPilihan anda : ");
            filterRules(sc.nextLine(), i);
        }
        // System.out.print(v.toString());
    }

    private static void dialog(int i, List<String> v) {
        
        switch(i) {
            case 2 :
                System.out.println ("------\nBudget\n------");
                for (int j = 0; j < v.size(); j++) {
                    switch(Integer.parseInt(v.get(j))) {
                        case 1 : System.out.println("1.Budget <= 125jt");break;
                        case 2 : System.out.println("2.125jt < Budget <= 150jt");break;
                        case 3 : System.out.println("3.150jt < Budget <= 200jt");break;
                        case 4 : System.out.println("4.200jt < Budget <= 225jt");break;
                        case 5 : System.out.println("5.225jt < Budget <= 250jt");break;
                        case 6 : System.out.println("6.Budget > 250jt");break;
                    }
                }break;
            case 3 :
                System.out.println ("\n------------\nSegmen Mobil\n------------");
                for (int j = 0; j < v.size(); j++) {
                    switch(Integer.parseInt(v.get(j))) {
                        case 1 : System.out.println("1.Segmen LCGC 5 Seater");break;
                        case 2 : System.out.println("2.Segmen LCGC 7 Seater");break;
                        case 3 : System.out.println("3.Segmen LMPV 7 Seater");break;
                    }
                }break;
            case 4 :
                System.out.println ("\n-----------------\nPilihan Transmisi\n-----------------");
                for (int j = 0; j < v.size(); j++) {
                    switch(Integer.parseInt(v.get(j))) {
                        case 1 : System.out.println("1.Manual (M/T)");break;
                        case 2 : System.out.println("2.Otomatis (A/T,CVT,AMT)");break;
                    }
                }break;
            case 5 :
                System.out.println ("\n----------------\nKenyamanan [0~5]\n----------------");
                for (int j = 0; j < v.size(); j++) {
                    switch(Integer.parseInt(v.get(j))) {
                        case 0 : System.out.println("0.Sangat kurang, belum ada AC dan masih 3 silinder");break;
                        case 1 : System.out.println("1.Kurang, getaran kasar karena 3 silinder");break;
                        case 2 : System.out.println("2.Lumayan, mesin masih 3 silinder");break;
                        case 3 : System.out.println("3.Sedang, getaran halus karena sudah 4 silinder");break;
                        case 4 : System.out.println("4.Baik, getaran halus dan bantingan suspensi nyaman");break;
                        case 5 : System.out.println("5.Sangat baik, getaran halus dan kekedapan kabin baik");break;
                    }
                }break;
            case 6 :
                System.out.println ("\n-----------------------\nFitur Keselamatan [0~5]\n-----------------------");
                for (int j = 0; j < v.size(); j++) {
                    switch(Integer.parseInt(v.get(j))) {
                        case 0 : System.out.println("0.Sangat buruk, tanpa Airbag dan ABS");break;
                        case 1 : System.out.println("1.Buruk, tanpa Airbag dan ABS");break;
                        case 2 : System.out.println("2.Kurang, Single Airbag tanpa ABS");break;
                        case 3 : System.out.println("3.Cukup, Dual Airbag tanpa ABS");break;
                        case 4 : System.out.println("4.Baik, Dual Airbag dengan ABS");break;
                        case 5 : System.out.println("5.Sangat baik, Dual Airbag dengan ABS dan VSC");break;
                    }
                }break;
            case 7 :
                System.out.println ("\n----------------------\nFitur Multimedia [0~5]\n----------------------");
                for (int j = 0; j < v.size(); j++) {
                    switch(Integer.parseInt(v.get(j))) {
                        case 0 : System.out.println("0.Sangat buruk, tanpa multimedia");break;
                        case 1 : System.out.println("1.Buruk, hanya ada radio");break;
                        case 2 : System.out.println("2.Lumayan, bisa radio dan tersedia input aux");break;
                        case 3 : System.out.println("3.Sedang, bisa radio dan input aux serta usb");break;
                        case 4 : System.out.println("4.Baik, tersedia koneksi bluetooth");break;
                        case 5 : System.out.println("5.Sangat baik, sudah touchscreen");break;
                    }
                }break;
            case 8 :
                System.out.println ("\n----------------------------------\nAfter Sales dan Resale Value [0~5]\n----------------------------------");
                for (int j = 0; j < v.size(); j++) {
                    switch(Integer.parseInt(v.get(j))) {
                        case 0 : System.out.println("0.Sangat buruk");break;
                        case 1 : System.out.println("1.Buruk");break;
                        case 2 : System.out.println("2.Lumayan");break;
                        case 3 : System.out.println("3.Sedang");break;
                        case 4 : System.out.println("4.Baik");break;
                        case 5 : System.out.println("5.Sangat baik");break;
                    }
                }break;
        }
    }
}