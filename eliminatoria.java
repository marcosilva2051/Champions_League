import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class eliminatoria {
    private char name;
    private List<equipa> teams = new ArrayList<equipa>();
    private List<Jogo> games = new ArrayList<Jogo>();
    private equipa winning_team;

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
    public equipa getWinning_team(){
        return this.winning_team;
    }

    public void get_jogos()
    {
        games.forEach((n) -> System.out.println("Jogo:"+n.getEquipacasa().getName()+"(H) vs "+n.getEquipafora().getName()+"(A)\nResultado :"+n.getGoloscasa()+"-"+n.getGolosfora()+"\nVencedor: "+n.getEquipa_vencedora()+"\n \n"));
    }

    public void classificacao()
    {
        int e1_golos=0;
        int e2_golos=0;

        if(teams.get(0).getPoints() == teams.get(1).getPoints()){
            for (Jogo game:games) {
                if(game.getEquipafora() == teams.get(0))
                    e1_golos = game.getGolosfora();
                else{
                    e2_golos = game.getGolosfora();
                }
            }
            if(e1_golos > e2_golos)
                this.winning_team = teams.get(0);
            if(e1_golos < e2_golos)
                this.winning_team = teams.get(1);
            if(e1_golos == e2_golos){
                Random r = new Random();
                int low = 0;
                int high = 1;
                int result = r.nextInt(high-low) + low;
                this.winning_team = teams.get(result);
            }


        }
        if(teams.get(0).getPoints() > teams.get(1).getPoints())
            this.winning_team = teams.get(0);
        if(teams.get(0).getPoints() < teams.get(1).getPoints())
            this.winning_team = teams.get(1);
    }


}
