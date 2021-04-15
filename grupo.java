import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class grupo
{
    private char name;
    private List<equipa> teams = new ArrayList<equipa>();
    private List<Jogo> games = new ArrayList<Jogo>();

    public char getName()
    {
        return this.name;
    }
    public void setName(char value)
    {
        this.name=value;
    }

    public void addTeam(equipa team)
    {
        this.teams.add(team);
    }

    public equipa getTeam(int value) {
        return this.teams.get(value);
    }

    public List<equipa> getTeams()
    {
        return this.teams;
    }

    public void simula_jogos()
    {
        for(int i=0;i<teams.size();i++) {
            for(int j=0; j<teams.size();j++) {
                if(i!=j) {
                    Jogo jogo = new Jogo(teams.get(i), teams.get(j));
                    jogo.gera_resultado();
                    games.add(jogo);
                }
            }
        }
    }


    public void get_jogos()
    {
        games.forEach((n) -> System.out.println(n.getEquipacasa().getName()+"("+n.getGoloscasa()+") vs "+n.getEquipafora().getName()+"("+n.getGolosfora()+") : vencedor ->"+n.getEquipa_vencedora()));
        System.out.println();
    }

    public void classificacao()
    {
        equipa j1 = new equipa();
        equipa j2 = new equipa();
        for(int i = 0; i<4; i++)
        {
            for (int j =i+1; j<4;j++)
            {
                if(teams.get(i).getPoints() < teams.get(j).getPoints())
                    Collections.swap(teams,i,j);
                if(teams.get(i).getPoints() == teams.get(j).getPoints())
                {
                    if(teams.get(i).getgolos() < teams.get(j).getgolos())
                        Collections.swap(teams,i,j);
                }
            }
        }

        for(int k = 0; k<4;k++)
            System.out.println(teams.get(k).getName()+"("+teams.get(k).getgolos()+") - "+teams.get(k).getPoints()+" pontos");

    }




}
