import java.util.concurrent.ThreadLocalRandom;
public class Jogo {
    private equipa equipa_casa;
    private equipa equipa_fora;
    private String estadio;
    private int golos_ecasa;
    private int golos_efora;
    private int resultado;
    private equipa equipa_vencedora;
    private equipa empate = new equipa("empate","emp","emp");




    public Jogo(equipa equipa_casa,equipa equipa_fora) {
        this.equipa_casa = equipa_casa;
        this.equipa_fora = equipa_fora;
        this.estadio = this.equipa_casa.getStadium();
    }

    public void gera_resultado() {
        this.golos_ecasa = ThreadLocalRandom.current().nextInt(0, 7);
        this.golos_efora = ThreadLocalRandom.current().nextInt(0, 7);

        equipa_casa.setGolos(this.golos_ecasa);
        equipa_fora.setGolos(this.golos_efora);


        if(this.golos_ecasa>this.golos_efora) {
            this.resultado = 0;
            this.equipa_vencedora=this.equipa_casa;
            this.equipa_casa.setPoints(this.equipa_casa.getPoints()+3);
        }else if(this.golos_ecasa<this.golos_efora)
        {
            this.resultado = 2;
            this.equipa_vencedora=this.equipa_fora;
            this.equipa_fora.setPoints(this.equipa_fora.getPoints()+3);
        }else {
            this.equipa_vencedora = null;
            this.resultado = 1;
            this.equipa_casa.setPoints(this.equipa_casa.getPoints()+1);
            this.equipa_fora.setPoints(this.equipa_fora.getPoints()+1);
        }

    }
    public void dados_jogo(){
        System.out.println(this.equipa_casa+" "+this.golos_ecasa+" - "+this.golos_efora+" "+this.equipa_fora);

    }
    public equipa getEquipa_vencedora()
    {
        if(this.equipa_vencedora == null)
            return this.empate;
        else
            return this.equipa_vencedora;
    }
    public equipa getEquipacasa()
    {
        return this.equipa_casa;
    }
    protected  void setEquipa_casa (equipa value){this.equipa_casa = value;}
    protected  void setEquipa_fora (equipa value){this.equipa_fora = value;}
    protected  void setEquipa_vencedora (equipa value){this.equipa_vencedora = value;}

    public equipa getEquipafora()
    {
        return this.equipa_fora;
    }
    public int getGoloscasa() {
        return this.golos_ecasa;
    }
    public int getGolosfora() {
        return this.golos_efora;
    }
    protected  void setGolos_ecasa(int value){this.golos_ecasa = value;}
    protected  void setGolos_efora(int value){this.golos_efora = value;}
    protected  void setResultado(int value){this.resultado = value;}
    public int resultado() {
        return this.resultado;
    }

}
