import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.RandomAccess;
import java.util.Scanner;

public class example3{
    public static void main(String [] args) throws IOException{
        try{
            File folder = new File("C:\\Users\\kedua\\Учеба\\3 семестр\\Алгоритмика\\Лаба 7\\My");
            if (!folder.exists()){
                folder.mkdir();
            }

            File f1 = new File("C:\\Users\\kedua\\Учеба\\3 семестр\\Алгоритмика\\Лаба 7\\My\\rec_RAF.txt");
            if (!f1.exists()){
                f1.createNewFile();
            }

            RandomAccessFile rf = new RandomAccessFile(f1, "rw");
            long fSize = rf.length();

            Scanner sc = new Scanner(System.in, "cp1251");
            System.out.println("Введите количество сотрудников для записи в файл\n" + "=> ");
            int kol = sc.nextInt();
            sc.nextLine();

            // Запись инфы о сутрудниках
            String fam, doljnost;
            int oklad;
            for (int i = 0; i < kol; i++){
                System.out.print("Введите фамилию " + (i + 1) + " сотрудника => ");
                fam = sc.next();
                rf.seek(rf.length());
                rf.writeUTF(fam);
                for (int j = 0; j < 20 - fam.length(); j++){
                    rf.writeByte(1);
                }
                System.out.print("Введите его оклад => ");
                oklad = sc.nextInt();
                sc.nextLine();
                rf.writeInt(oklad);
            }
            rf.close();

            // Чтение инфы о сутрудниках
            rf = new RandomAccessFile(f1, "r");
            rf.seek(0);
            System.out.println("Информация о сотрудниках");
            System.out.println("Фамилия \t\t Должность \t\t Оклад");

            for (int i = 0; i < kol; i++){
                fam = rf.readUTF();
                for (int j = 0; j < 20 - fam.length(); j++){
                    rf.readByte();
                }
                doljnost = rf.readUTF();
                for (int j = 0; j < 20 - doljnost.length(); j++){
                    rf.readByte();
                }
                oklad = rf.readInt();
                System.out.println(fam + "\t\t\t" + doljnost + "\t\t\t" + oklad);
            }
            rf.close();
        }
        catch (IOException e){
            System.out.println("End of file " + e);
        }
    }
}
