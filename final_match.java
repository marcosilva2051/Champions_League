import java.util.concurrent.ThreadLocalRandom;

public class final_match extends Jogo {
    public final_match(equipa e1, equipa e2)
    {
        super(e1,e2);
    }

    @Override
    public void gera_resultado() {
        super.gera_resultado();
        this.setGolos_ecasa(ThreadLocalRandom.current().nextInt(0, 7));
        this.setGolos_efora(ThreadLocalRandom.current().nextInt(0, 7));

        if(this.getGoloscasa()>this.getGolosfora()) {
            this.setResultado(0);
            this.setEquipa_vencedora(getEquipacasa());
        }else if(this.getGoloscasa()<this.getGolosfora())
        {
            this.setResultado(2);
            this.setEquipa_vencedora(this.getEquipafora());
        }else {
            if(ThreadLocalRandom.current().nextInt(0, 1) == 0)
                this.setEquipa_vencedora(this.getEquipacasa());
            else
                this.setEquipa_vencedora(this.getEquipafora());
        }
    }
}
