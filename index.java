
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Comparator;

public class index {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        List<equipa> pote1 = new ArrayList<equipa>(); // array com as equipas do pote1
        List<equipa> pote2 = new ArrayList<equipa>();// array com as equipas do pote2
        List<equipa> pote3= new ArrayList<equipa>();// array com as equipas do pote3
        List<equipa> pote4 = new ArrayList<equipa>();// array com as equipas do pote4
        List<grupo> grupos = new ArrayList<grupo>(); // array dos grupos
        List<equipa> oitavos = new ArrayList<equipa>(); // array com as equipas dos oitavos de final
        List<eliminatoria> oitavos_eliminatorias = new ArrayList<eliminatoria>();// array com as eliminatórias dos oitavos de final
        List<equipa> quartos = new ArrayList<equipa>();// array com as equipas dos quartos de final
        List<eliminatoria> quartos_eliminatorias = new ArrayList<eliminatoria>();// array com as eliminatórias dos quartos de final
        List<equipa> meias = new ArrayList<equipa>();// array com as equipas das meias de final
        List<eliminatoria> meias_eliminatorias = new ArrayList<eliminatoria>();// array com as eliminatórias das meias de final
        final_match finalUCL; // final
        equipa e1,e2;
        boolean f;




        fill_pote("C:\\Users\\ASUS\\IdeaProjects\\Ucl\\src\\pote1.txt",pote1);
        fill_pote("C:\\Users\\ASUS\\IdeaProjects\\Ucl\\src\\pote2.txt",pote2);
        fill_pote("C:\\Users\\ASUS\\IdeaProjects\\Ucl\\src\\pote3.txt",pote3);
        fill_pote("C:\\Users\\ASUS\\IdeaProjects\\Ucl\\src\\pote4.txt",pote4);


        criar_grupos(grupos);
        fill_groups(grupos.get(0),grupos.get(1),grupos.get(2),grupos.get(3),grupos.get(4),grupos.get(5),grupos.get(6),grupos.get(7),pote1,pote2,pote3,pote4);

        System.out.println("\n------------------------|FASE DE GRUPOS|-----------------------------\n");
        simular_grupos(grupos);
        oitavos_sorteio(grupos,oitavos,oitavos_eliminatorias);
        System.out.println("\n------------------------|OITAVOS DE FINAL|---------------------------\n");
        System.out.println("\n--------------------|Equipas Qualificadas|----------------------------\n");

        for(eliminatoria e : oitavos_eliminatorias)
        {
            for(equipa x : e.getTeams())
                System.out.println(x.getName());
        }

        simular(oitavos_eliminatorias);

        System.out.println("\n------------------------|QUARTOS DE FINAL|---------------------------\n");
        System.out.println("\n--------------------|Equipas Qualificadas|----------------------------\n");

        sorteio_elimin(oitavos_eliminatorias,quartos,quartos_eliminatorias);
        for(eliminatoria e : quartos_eliminatorias)
        {
            for(equipa x : e.getTeams())
                System.out.println(x.getName());
        }
        simular(quartos_eliminatorias);

        System.out.println("\n------------------------|MEIAS FINAIS|--------------------------------\n");
        sorteio_elimin(quartos_eliminatorias,meias,meias_eliminatorias);
        System.out.println("\n--------------------|Equipas Qualificadas|----------------------------\n");
        for(eliminatoria e : meias_eliminatorias)
        {
            for(equipa x : e.getTeams())
                System.out.println(x.getName());
        }
        simular(meias_eliminatorias);

        System.out.println("\n------------------------|FINAL|--------------------------------\n");


        finalUCL = new final_match(meias_eliminatorias.get(0).getWinning_team(),meias_eliminatorias.get(1).getWinning_team());
        finalUCL.gera_resultado();
        finalUCL.dados_jogo();
        System.out.println("Vencedor:"+finalUCL.getEquipa_vencedora());
    }

    private static void criar_grupos(List<grupo> lista){
        int i;
        char nome = 'A';


        for(i=0;i<8;i++)
        {
            grupo grupo = new grupo();
            grupo.setName(nome);
            nome++;
            lista.add(grupo);
        }
    }

    private static void simular_grupos(List<grupo> lista) // simula grupos
    {

        System.out.println("-------- FASE DE GRUPOS --------");
        for(grupo group : lista)
        {
            System.out.println("Grupo"+group.getName());
            group.simula_jogos();
            //grupoA.get_jogos();
            group.get_jogos();
            group.classificacao();
            System.out.println("");
        }
    }

    private static void oitavos_sorteio(List<grupo> lista, List<equipa> oitavos_teams,List<eliminatoria> oitavos_matches){ // sorteio dos otiavos de final
        equipa e1,e2;
        boolean f;
        for(grupo group : lista){
            oitavos_teams.add(group.getTeam(0));
            oitavos_teams.add(group.getTeam(1));
        }
        for (int i = 0; i<8;i++)
        {
            eliminatoria group = new eliminatoria();
            e1 = givenList_shouldReturnARandomElement(oitavos_teams);
            group.addTeam(e1);
            oitavos_teams.remove(e1);
            do {
                e2=givenList_shouldReturnARandomElement(oitavos_teams);
                f = verify_team_el(e2,group);
            }while(f==false);
            oitavos_teams.remove(e2);
            e1.setPoints(0);
            e2.setPoints(0);
            oitavos_matches.add(group);
        }
    }



    private static void simular(List<eliminatoria> elimnatorias){ // simula uma eliminatoria
        for (eliminatoria group : elimnatorias)
        {
            System.out.println(group.getName());
            System.out.println("\n--------------------------|MATCH-UP|-----------------------------\n");
            for (equipa teams : group.getTeams())
            {
                System.out.println(teams.getName());
            }
            group.simula_jogos();
            System.out.println("");
            group.get_jogos();
            group.classificacao();
            System.out.println("A equipa vencedora da eliminatória é:"+group.getWinning_team());
            System.out.println("--------------------------------------------------------------------");
        }
    }

    private static void sorteio_elimin(List<eliminatoria> grup_el_ant, List<equipa> teams,List<eliminatoria> matches){ //simula o sorteio da eliminatoria
        for (eliminatoria x : grup_el_ant)
            teams.add(x.getWinning_team());

        for (int i = 0; i<grup_el_ant.size()/2;i++)
        {
            equipa e1,e2;
            boolean f;
            eliminatoria group = new eliminatoria();
            e1 = givenList_shouldReturnARandomElement(teams);
            group.addTeam(e1);
            teams.remove(e1);
            e2=givenList_shouldReturnARandomElement(teams);
            group.addTeam(e2);
            teams.remove(e2);
            e1.setPoints(0);
            e2.setPoints(0);
            matches.add(group);
        }
    }

    private static void fill_pote(String folder, List<equipa> pote) // preenche um array com os dados do ficheiro
    {
        int lineNumber = 1;
        try {
            File myObj = new File(folder);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] tmp = line.split(" ");
                if(tmp[0].contains("_"))
                    tmp[0] = tmp[0].replace("_", " ");
                if(tmp[2].contains("_"))
                    tmp[2] = tmp[2].replace("_", " ");
                equipa x = new equipa(tmp[0],tmp[1],tmp[2]);
                pote.add(x);
                lineNumber++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static equipa givenList_shouldReturnARandomElement(List<equipa> x) { // retorna um elemento random de uma lista
        Random rand = new Random();
        equipa randomElement = x.get(rand.nextInt(x.size()));


        return randomElement;
    }

    public static boolean verify_team(equipa team, grupo gr) { // verifica se duas equipas são do mesmo pais
        int aux=0;
        for(equipa x : gr.getTeams()) {
            if(team.getCountry().equals(x.getCountry()))
                aux++;
        }
        if(aux==0)
        {
            gr.addTeam(team);
            return true;
        }else {
            return false;
        }

    }

    public static boolean verify_team_el(equipa team, eliminatoria gr) {
        int aux=0;
        for(equipa x : gr.getTeams()) {
            if(team.getCountry().equals(x.getCountry()))
                aux++;
        }
        if(aux==0)
        {
            gr.addTeam(team);
            return true;
        }else {
            return false;
        }

    }

    // gera os grupos
    public static void fill_groups(grupo grupoA, grupo grupoB, grupo grupoC, grupo grupoD, grupo grupoE, grupo grupoF,grupo grupoG, grupo grupoH,List<equipa> pote1, List<equipa> pote2, List<equipa> pote3, List<equipa> pote4 ) {
        equipa x;
        boolean f;

        //GRUPO1
        x=givenList_shouldReturnARandomElement(pote1);
        grupoA.addTeam(x);
        pote1.remove(x);
        do {
            x=givenList_shouldReturnARandomElement(pote2);
            f = verify_team(x,grupoA);
        }while(f==false);
        pote2.remove(x);
        do {
            x=givenList_shouldReturnARandomElement(pote3);
            f = verify_team(x,grupoA);
        }while(f==false);
        pote3.remove(x);
        do {
            x=givenList_shouldReturnARandomElement(pote4);
            f = verify_team(x,grupoA);
        }while(f==false);
        pote4.remove(x);


        //GRUPO2
        x=givenList_shouldReturnARandomElement(pote1);
        grupoB.addTeam(x);
        pote1.remove(x);
        do {
            x=givenList_shouldReturnARandomElement(pote2);
            f = verify_team(x,grupoB);
        }while(f==false);
        pote2.remove(x);
        do {
            x=givenList_shouldReturnARandomElement(pote3);
            f = verify_team(x,grupoB);
        }while(f==false);
        pote3.remove(x);
        do {
            x=givenList_shouldReturnARandomElement(pote4);
            f = verify_team(x,grupoB);
        }while(f==false);
        pote4.remove(x);

        //GRUPO3
        x=givenList_shouldReturnARandomElement(pote1);
        grupoC.addTeam(x);
        pote1.remove(x);
        do {
            x=givenList_shouldReturnARandomElement(pote2);
            f = verify_team(x,grupoC);
        }while(f==false);
        pote2.remove(x);
        do {
            x=givenList_shouldReturnARandomElement(pote3);
            f = verify_team(x,grupoC);
        }while(f==false);
        pote3.remove(x);
        do {
            x=givenList_shouldReturnARandomElement(pote4);
            f = verify_team(x,grupoC);
        }while(f==false);
        pote4.remove(x);

        //GRUPO4
        x=givenList_shouldReturnARandomElement(pote1);
        grupoD.addTeam(x);
        pote1.remove(x);
        do {
            x=givenList_shouldReturnARandomElement(pote2);
            f = verify_team(x,grupoD);
        }while(f==false);
        pote2.remove(x);
        do {
            x=givenList_shouldReturnARandomElement(pote3);
            f = verify_team(x,grupoD);
        }while(f==false);
        pote3.remove(x);
        do {
            x=givenList_shouldReturnARandomElement(pote4);
            f = verify_team(x,grupoD);
        }while(f==false);
        pote4.remove(x);


        //GRUPO5
        x=givenList_shouldReturnARandomElement(pote1);
        grupoE.addTeam(x);
        pote1.remove(x);
        do {
            x=givenList_shouldReturnARandomElement(pote2);
            f = verify_team(x,grupoE);
        }while(f==false);
        pote2.remove(x);
        do {
            x=givenList_shouldReturnARandomElement(pote3);
            f = verify_team(x,grupoE);
        }while(f==false);
        pote3.remove(x);
        do {
            x=givenList_shouldReturnARandomElement(pote4);
            f = verify_team(x,grupoE);
        }while(f==false);
        pote4.remove(x);

        //GRUPO6
        x=givenList_shouldReturnARandomElement(pote1);
        grupoF.addTeam(x);
        pote1.remove(x);
        do {
            x=givenList_shouldReturnARandomElement(pote2);
            f = verify_team(x,grupoF);
        }while(f==false);
        pote2.remove(x);
        do {
            x=givenList_shouldReturnARandomElement(pote3);
            f = verify_team(x,grupoF);
        }while(f==false);
        pote3.remove(x);
        do {
            x=givenList_shouldReturnARandomElement(pote4);
            f = verify_team(x,grupoF);
        }while(f==false);
        pote4.remove(x);

        //GRUPO7
        x=givenList_shouldReturnARandomElement(pote1);
        grupoG.addTeam(x);
        pote1.remove(x);
        do {
            x=givenList_shouldReturnARandomElement(pote2);
            f = verify_team(x,grupoG);
        }while(f==false);
        pote2.remove(x);
        do {
            x=givenList_shouldReturnARandomElement(pote3);
            f = verify_team(x,grupoG);
        }while(f==false);
        pote3.remove(x);
        do {
            x=givenList_shouldReturnARandomElement(pote4);
            f = verify_team(x,grupoG);
        }while(f==false);
        pote4.remove(x);

        //GRUPO8
        x=givenList_shouldReturnARandomElement(pote1);
        grupoH.addTeam(x);
        pote1.remove(x);
        do {
            x=givenList_shouldReturnARandomElement(pote2);
            f = verify_team(x,grupoH);
        }while(f == false);
        pote2.remove(x);
        do {
            x=givenList_shouldReturnARandomElement(pote3);
            f = verify_team(x,grupoH);
        }while(f == false);
        pote3.remove(x);
        do {
            x=givenList_shouldReturnARandomElement(pote4);
            f = verify_team(x,grupoH);
        }while(f == false);
        pote4.remove(x);
    }
}
