import Lab.Cathedra;
import Lab.Faculty;
import Lab.Speciality;
import Lab.University;

public class Main {
    public static void main(String[] args){
        University university = new University("KMA");

        Faculty FI = university.addFaculty("FI");  //Faculty of informatics
        Faculty FES = university.addFaculty("FES"); //Faculty of Economic Studies
        Faculty FS = university.addFaculty("FS");  //Faculty of Humanities

        Cathedra FIMedia = FI.addCathedra("Media");
        Cathedra FINet = FI.addCathedra("Networking");
        Cathedra FIMath = FI.addCathedra("Math");

        FIMedia.addTeacher("Oksana Kirienko");
        FIMedia.addTeacher("Andrii Glibovets");
        Speciality CS = FIMedia.addSpec("Computer science");
        CS.addStudent("Mariya Kovalchuk", 1, 2);
        CS.addStudent("Maxim Kovalchuk", 1, 1);
        CS.addStudent("Stas Povalov", 2, 4);
        CS.addStudent("Kyril Mesnikov", 2, 3);
        CS.addStudent("Zhenya Amedro", 3, 1);

        FINet.addTeacher("Genadii Malashonok");
        FINet.addTeacher("Voznuk Yaroslav");
        Speciality NT = FINet.addSpec("Network technologies");
        NT.addStudent("Jaroslav Korchmar", 2, 2);
        NT.addStudent("Gorana Maksimenko", 4, 2);
        NT.addStudent("Kyrylo Bilous", 2, 4);
        NT.addStudent("Oleksiy Borysenko", 3, 2);
        NT.addStudent("Ludmila Kashchenko", 4, 4);
        Speciality SE = FINet.addSpec("Software engineering");
        SE.addStudent("Yaroslav Petryk", 1, 3);
        SE.addStudent("Yaroslav Kuchmirenko", 1, 2);
        SE.addStudent("Andrii Rozhko", 1, 3);
        SE.addStudent("Kyril Kudnik", 3, 1);
        SE.addStudent("Maxim Sebastyan", 2, 3);

        FIMath.addTeacher("Yulia Gorodnyk");
        Speciality AM = FINet.addSpec("Applied math");
        AM.addStudent("Zora Kryvoruchko", 1, 1);
        AM.addStudent("Marina Chorny", 4, 1);
        AM.addStudent("Maksym Ivashko", 2, 4);
        AM.addStudent("Kliment Klymenko", 2, 4);
        AM.addStudent("Jaromir Lutsenko", 3, 2);
        Speciality SA = FINet.addSpec("System analysis");
        SA.addStudent("Dmitry Barilko", 2, 3);
        SA.addStudent("Kliment Korchmar", 4, 1);
        SA.addStudent("Lubomir Chorny", 2, 4);
        SA.addStudent("Melania Malkovych", 2, 4);
        SA.addStudent("Nadia Hirnyk", 3, 2);

        Cathedra FESEco = FES.addCathedra("Economics");
        FESEco.addTeacher("Dragoslav Korchma");
        FESEco.addTeacher("Zinovy Moskalenko");
        Speciality Fin = FESEco.addSpec("Finance");
        Fin.addStudent("Oleksiy Levytsky", 2, 3);
        Fin.addStudent("Angelika Maidanik", 1, 1);
        Fin.addStudent("Nenad Kolachnyk", 2, 1);
        Fin.addStudent("Nina Lutsenko", 3, 2);
        Fin.addStudent("Branimir Klochko", 3, 4);
        Speciality Market = FESEco.addSpec("Marketing");
        Market.addStudent("Blagoje Achtymichuk", 2, 4);
        Market.addStudent("Radovan Chorny", 1, 2);
        Market.addStudent("Darko Kolashnyk", 1, 2);
        Market.addStudent("Inna Kravets", 4, 2);
        Market.addStudent("Radmila Kulesha", 4, 3);

        Cathedra FSPh = FS.addCathedra("Philosophy");
        FSPh.addTeacher("Zoran Ivanenko");
        Speciality PH = FSPh.addSpec("Philosophy");
        PH.addStudent("Ljuba Kalashnyk", 4, 2);
        PH.addStudent("Lubomir Korchmar", 4, 3);
        PH.addStudent("Milovan ButkoChalupa", 3, 2);
        PH.addStudent("Dmitry Kravets", 4, 2);
        PH.addStudent("Ruslan Nakonechny", 2, 2);

        Cathedra FSLang = FS.addCathedra("Philology");
        FSLang.addTeacher("Anatoly Berkovich");
        FSLang.addTeacher("Anastasia Maidanik");
        Speciality Slav = FSLang.addSpec("Slavic");
        Slav.addStudent("Dragoslav Fedorenko", 4, 2);
        Slav.addStudent("Lubomir Fedorenko", 4, 4);
        Slav.addStudent("Kliment Kostenko", 4, 3);
        Slav.addStudent("Vitomir Klymenko", 4, 4);
        Slav.addStudent("Draga Kolasnik", 1, 2);

        Speciality RG = FSLang.addSpec("Roman-German");
        RG.addStudent("Andrey Kostiuk", 3, 2);
        RG.addStudent("Zora Kolachnik", 4, 1);
        RG.addStudent("Hennadiy Kotlyarevsky", 2, 2);
        RG.addStudent("Borys Andreychuk", 3, 2);
        RG.addStudent("Draga Leshchenko", 1, 4);

        university.handleConsole();
    }
}
