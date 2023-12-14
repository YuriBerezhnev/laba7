import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Project1{
    public static void main(String[] args) {
        try{
            File f1 = new File("C:\\Users\\User\\Desktop\\свфу Юра\\3 семестр\\АИП\\laba7\\My\\Project1.txt");
            if (f1.exists()) f1.delete();
            f1.createNewFile();
            RandomAccessFile rf = new RandomAccessFile(f1, "rw");  // чтение и запись
            long fSize = rf.length(); // размер файла
            Scanner sc = new Scanner(System.in, "cp1251");
            System.out.println("Введите количество людей для записи в файл " + "=> ");
            int N = sc.nextInt();
            sc.nextLine(); // очистка буфера после ввода числа
            String lastName, firstName, fatherName;
            int age;
            double weight;
            //----ЗАПИСЬ ИНФОРМАЦИИ О ЛЮДЯХ В ФАЙЛ----
            for (int i = 0; i < N; i++){
                System.out.print("Введите фамилию " + (i + 1) + " человека => ");
                lastName = sc.nextLine();
                rf.seek(rf.length());
                rf.writeUTF(lastName); // запись фамилия
                for (int j = 0; j < 20 - lastName.length(); j++){
                    rf.writeByte(1);
                }

                System.out.print("Введите имя " + (i + 1) + " человека => ");
                firstName = sc.nextLine();
                rf.writeUTF(firstName); // запись имени
                for (int j = 0; j < 20 - firstName.length(); j++){
                    rf.writeByte(1);
                }

                System.out.print("Введите отчество " + (i + 1) + " человека => ");
                fatherName = sc.nextLine();
                rf.writeUTF(fatherName); // запись отчества
                for (int j = 0; j < 20 - fatherName.length(); j++){
                    rf.writeByte(1);
                }

                System.out.print("Введите возраст " + (i + 1) + " человека => ");
                age = sc.nextInt();
                sc.nextLine();
                rf.writeInt(age); // запись возраста

                System.out.print("Введите вес " + (i + 1) + " человека => ");
                weight = sc.nextDouble();
                sc.nextLine();
                rf.writeDouble(weight); // запись веса
            }
            rf.close();
            //----ЧТЕНИЕ ИНФОРМАЦИИ О ЛЮДЯХ ИЗ ФАЙЛА----
            rf = new RandomAccessFile(f1, "r");
            rf.seek(0); // перемещение в начало файла

            System.out.println("Информация о людях в возрасте до 18 лет");
            System.out.println("Фамилия \t\t Имя \t\t Отчество \t\t Возраст \t\t Вес");

            for (int i = 0; i < N; i++){
                lastName = rf.readUTF();
                for (int j = 0; j < 20 - lastName.length(); j++){
                    rf.readByte();
                }
                firstName = rf.readUTF();
                for (int j = 0; j < 20 - firstName.length(); j++){
                    rf.readByte();
                }
                fatherName = rf.readUTF();
                for (int j = 0; j < 20 - fatherName.length(); j++){
                    rf.readByte();
                }
                age = rf.readInt();
                weight = rf.readDouble();
                if (age < 18){
                    System.out.println(lastName + "\t\t" + firstName + "\t\t" + fatherName + "\t\t" + age + "\t\t" + weight);
                }
            }
            rf.close();
        }
        catch (IOException e){
            System.out.println("End of file " + e);
        }
    }
}