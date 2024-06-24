import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class test_project {

    public static String mathSymbols = "+-*/";

    public static void main (String[] args) {
       
        System.out.println("Введите выражение");
        Scanner keyboardScan = new Scanner (System.in);
        String string = keyboardScan.nextLine();
        
        int o = 0; 
      
        Boolean lat = false;
        Boolean rom = false;
        Boolean contune = true;
    
        String romString = "ivxzlcdm";
        
        String [] calcArray = new String [string.length()];

        Arrays.fill(calcArray, "");
 

    for (String s: string.split("")) {

        try {
            int i = Integer.parseInt (s);
            lat = true;
            calcArray [o] += s;
        }
        
        catch (NumberFormatException e) {
            
            if (romString.contains(s)){
                calcArray [o] += s;
                rom = true;     
                }

            else if (!mathSymbols.contains(s)){
                System.out.println("Недопустимый символ");
                break;
                }

            else{
                o++;
                calcArray [o] += s;
                o++;
                }
        }

        if (rom == lat) {
            System.out.println("Ошибка ввода данных");
            contune = false;
            break;
        }
    }

        if (contune == true & lat == true) {
            latCalc (calcArray, o, false);
        }
        else if (contune == true & rom == true) {
            romToLat (calcArray, o);
        }
    
    }

    public static void romToLat (String[] args, int arg) {

        int currentElement = 0;
        int beforeElement = 0;
        int calcElement = 0;
        String result = "";
        String [] calcArray = new String [arg+1];

        HashMap<String, Integer> latRomMap = new HashMap<>();

        latRomMap.put("i", 1);
        latRomMap.put("v", 5);
        latRomMap.put("x", 10);
        latRomMap.put("l", 50);
        latRomMap.put("c", 100);
        latRomMap.put("d", 500);
        latRomMap.put("m", 1000);

        for (int i = 0; i <= arg; i++) {
            for (String element: args[i].split("")){
                if (!mathSymbols.contains(element)) {
                    if (calcElement == 0) {
                        calcElement = currentElement = beforeElement = latRomMap.get(element);
                    }

                    else {

                        currentElement = latRomMap.get(element);
                        
                        if (beforeElement >= currentElement) {
                            calcElement += currentElement;
                            beforeElement = currentElement;
                        }

                        else {
                            calcElement += currentElement - (beforeElement*2);
                            beforeElement = currentElement;
                        }
                    }   
                }

                else {
                    currentElement=calcElement=beforeElement=0;
                    result = element;
                }
            }

            if (calcElement != 0) {result = String.valueOf(calcElement);}

            calcArray[i] = result;

        }
        latCalc (calcArray, arg, true);
    }


    public static void latToRom (int arg) {
        System.out.print("Результат (Перевод): " + arg);
    }


    public static void latCalc (String[] args, int arg, Boolean rom) {

        //ScriptEngine не используется специально

        int result=0;
        int n=0;
    
        for (int i = 0; i <= arg; i++) {

            try {
                if (i == 0 ) {
                    n = Integer.parseInt (args[i]);
                    result = n;
                }
                else if (i%2 == 0) {
                    n = Integer.parseInt (args[i]);
                }
                else {
                    n = Integer.parseInt (args[i+1]);
                    switch (args[i]) {
                        case "+": result = result + n; break;
                        case "-": result = result - n; break;
                        case "*": result = result * n; break; 
                        case "/": result = result / n; break; 
                        default: System.out.print ("Неверный оператор: " + args[i]);
                    }
                }
            }
            
            catch (NumberFormatException e) {
                System.out.print("Ошибка");
                break;
            }
        }

        if (rom) {
            latToRom(result);
        }

        else {
            System.out.print("Результат: " + result);
        }
    }

}