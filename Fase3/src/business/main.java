import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class main {

    public static void main(String[] args){

        Piloto p = new Piloto("Manuel","portuguesa",60,50);

        Equipa e = new Equipa("JON",p);
        
        PC1 c = new PC1("Mercedes", "A", 200, 100,e,"Macio","Agressivo");
        PC1 c1 = new PC1("TOMA", "A", 200, 100,e,"Chuva","Agressivo");

        Piloto p2 = new Piloto("Tiago","frances",60,50);
        Equipa e1 = new Equipa("TEL",p2);
        PC2 c2 = new PC2("Ferrai","GT3", 200,100, e1, 80,"Chuva","Agressivo");
        
        HashMap<String,Long> temposMedios = new HashMap<>();
        temposMedios.put("PC1", (long) 100000);
        temposMedios.put("PC2", (long) 150000);
        temposMedios.put("PC1H", (long) 80000);
        temposMedios.put("PC2H", (long)  70000);
        temposMedios.put("SC", (long) 200000);
        temposMedios.put("GT", (long) 600);
        temposMedios.put("GTH", (long) 50000);
        Circuito circuito = new Circuito("pedra", 5, 5, temposMedios, 500, 500);
        System.out.println(c1.tempoProximaVolta(circuito, 0, 1));
        System.out.println(c2.tempoProximaVolta(circuito, 0, 1));



        // List<Carro> carros = new ArrayList<>();
        // carros.add(c1);
        // carros.add(c);
        // Set<Carro> classificacao = new HashSet<>();
        // classificacao.add(c1);
        // classificacao.add(c);
        // Corrida corrida = new Corrida(carros,circuito,classificacao,carros,1);
// 
        // corrida.simulaCorrida();
        // String result = corrida.printResultados();
        // 
        // 
        // 
        // 
        // 
        // System.out.println(result);


    }
    
}
