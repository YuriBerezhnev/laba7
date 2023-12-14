import java.io.*;
import java.util.Scanner;
class Person implements Serializable {
    String lastName;
    String firstName;
    String fatherName;
    int age;
    double weight;
}
public class Project2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in, "cp1251");
// создается файл на диске
        File f = new File("C:\\Users\\User\\Desktop\\свфу Юра\\3 семестр\\АИП\\laba7\\My\\Project2.txt");
        f.createNewFile();

        System.out.println("Введите количество людей: ");
        int N = sc.nextInt();
        sc.nextLine(); // очистка буфера после ввода числа
// -------------ЗАПИСЬ ОБЪЕКТА В ФАЙЛ-------------
// Создается поток для записи объекта
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
// Вводится информация об объекте
        Person person = null;
        for (int i = 0; i <N; i++){
            person = new Person();
            System.out.println("Введите информацию о человеке: ");
            System.out.print("Фамилия => ");
            person.lastName = sc.nextLine();
            System.out.print("Имя => ");
            person.firstName = sc.nextLine();
            System.out.print("Отчество => ");
            person.fatherName = sc.nextLine();
            System.out.print("Возраст => ");
            person.age = sc.nextInt();
            System.out.print("Вес => ");
            person.weight = sc.nextDouble();
            sc.nextLine();
            oos.writeObject(person);
        }
// Объект записывается в файл

// Дописывается информация и закрывается файловый поток
        oos.flush();
        oos.close();
// -------------ЧТЕНИЕ ОБЪЕКТА ИЗ ФАЙЛА-------------
// Создается поток для чтения объекта из файла
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream oin = new ObjectInputStream(fis);
// Объект считывается, и на экран выводится требуемая информация
        for (int i = 0; i < N; i++){
            person = (Person) oin.readObject();
            if (person.age < 18){
                System.out.println("Фамилия "+ person.lastName);
                System.out.println("Имя "+ person.firstName);
                System.out.println("Отчество "+ person.fatherName);
                System.out.println("Возраст "+ person.age);
                System.out.println("Вес "+ person.weight);
            }
        }
        // Поток закрывается
        oos.close();
    }
}
